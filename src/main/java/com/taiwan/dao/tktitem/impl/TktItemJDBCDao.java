package com.taiwan.dao.tktitem.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.TktItem;
import com.taiwan.dao.tktitem.TktItemDao;
import com.taiwan.utils.DbUtil;

public class TktItemJDBCDao implements TktItemDao {

	@Override
	public int insertTktItem(TktItem tktItem, Connection conn) {
		int count = 0;
		PreparedStatement prep = null;
		String sql = "insert into TKT_ITEM(tkt_id,tkt_order_id,amount) values(?,?,?)";
		
		try {
			prep = conn.prepareStatement(sql);
			
			prep.setInt(1, tktItem.getTktId());
			prep.setInt(2, tktItem.getTktOrderId());
			prep.setInt(3, tktItem.getAmount());
			
			count = prep.executeUpdate();
			System.out.println("success " + count);
		} catch (SQLException e) {
			if (conn != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Ord_food");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
		} finally {
			if (prep != null) {
				try {
					prep.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return count;
	}

	@Override
	public List<TktItem> queryTktItemByTktOrderId(Integer tktOrderId) {
		List<TktItem> ls=new ArrayList<TktItem>();
		String sql = "select tkt_id,tkt_order_id,amount,used,score,content from TKT_ITEM where tkt_order_id=?";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, tktOrderId);
			ResultSet rs=prep.executeQuery();
			while(rs.next()) {
				Integer tktId=rs.getInt("tkt_id");
				Integer querytktOrderId=rs.getInt("tkt_order_id");
				Integer amout=rs.getInt("amount");
				Integer used=rs.getInt("used");
				Integer score=rs.getInt("score");
				String content=rs.getString("content");
				TktItem tktItemVO=new TktItem(tktId, querytktOrderId, amout, used, score, content);
				ls.add(tktItemVO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public int updateTktItemUsedByTKTId(Integer tktOrderId, Integer tktId, Integer used) {
		int count=0;
		String sql = "update TKT_ITEM set used=? where tkt_order_id=? and tkt_id=?;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, used);
			prep.setInt(2,tktOrderId);
			prep.setInt(3,tktId);
			count=prep.executeUpdate();
			System.out.println("success "+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int updateTktItemScoreContentByTktId(Integer tktOrderId, Integer tktId, Integer score, String content) {
		int count=0;
		String sql = "update TKT_ITEM set score=?,content=? where tkt_order_id=? and tkt_id=?;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, score);
			prep.setString(2, content);
			prep.setInt(3, tktOrderId);
			prep.setInt(4, tktId);
			count=prep.executeUpdate();
			System.out.println("success"+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<TktItem> queryAllTktItemConTentByTktId(Integer tktId) {
		List<TktItem> ls =new ArrayList<TktItem>();
		TktItem tktItem = null;
		String sql = "select content from TKT_ITEM where tkt_id=?;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1,tktId);
			ResultSet rs=prep.executeQuery();
			while(rs.next()) {
				tktItem = new TktItem();
				tktItem.setContent(rs.getString("content"));
				ls.add(tktItem);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}


	@Override
	public List<TktItem> queryFiveTktItemContentByScore(Integer tktId) {
		List<TktItem> ls =new ArrayList<TktItem>();
		TktItem tktItem = null;
		String sql = "select content from TKT_ITEM where tkt_id=? order by score desc limit 5;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, tktId);
			ResultSet rs=prep.executeQuery();
			while(rs.next()) {
				tktItem = new TktItem();
				tktItem.setContent(rs.getString("content"));
				ls.add(tktItem);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public int queryTktItemTllScoreByTktId(Integer tktId) {
		int ttlScore=0;
		String sql = "select sum(score) as ttlScore from TKT_ITEM where tkt_id=?;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, tktId);
			ResultSet rs=prep.executeQuery();
			if(rs.next()) {
				ttlScore=rs.getInt("ttlScore");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ttlScore;
	}

	@Override
	public int queryTktItemTtlPeopleByTktId(Integer tktId) {
		int ttlPeople=0;
		String sql = "select count(*) as ttlPeople from TKT_ITEM where tkt_id=?;";
		Connection conn = null;
		PreparedStatement prep = null;
		try {
			conn=DbUtil.getConnection();
			prep  = conn.prepareStatement(sql);
			prep.setInt(1, tktId);
			ResultSet rs=prep.executeQuery();
			if(rs.next()) {
				ttlPeople=rs.getInt("ttlPeople");
			}
		}  catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (prep!= null) {
				try {
					prep.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return ttlPeople;
	}

	//test
	public static void main(String[] args) {
		TktItemJDBCDao dao = new TktItemJDBCDao();
		
//		Integer integer  = dao.queryTktItemTllScoreByTktId(4);
//		System.out.println(integer);
//		
//		Integer integer2  = dao.queryTktItemTtlPeopleByTktId(4);
//		System.out.println(integer2);

		List<TktItem> list = dao.queryTktItemByTktOrderId(5);
		for(TktItem t : list) {
			System.out.println(t);
		}
	}
}
