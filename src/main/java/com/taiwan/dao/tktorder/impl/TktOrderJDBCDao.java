package com.taiwan.dao.tktorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.TktItem;
import com.taiwan.beans.TktOrder;
import com.taiwan.dao.tktitem.impl.TktItemJDBCDao;
import com.taiwan.dao.tktorder.TktOrderDao;
import com.taiwan.utils.DbUtil;


public class TktOrderJDBCDao implements TktOrderDao {

	@Override
	public String insertTktOrderWithCoupon(TktOrder tktOrder, List<TktItem> tktItem_list) {
		Connection conn = null;
		PreparedStatement prep = null;
		String sql = "insert into TKT_ORDER(cust_id,original_price,ttl_price,cust_cop_id,qrcode,order_name,order_email,order_mobile)"
				+ " values(?,?,?,?,?,?,?,?);";
		try {
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);
			//新增訂單主檔
			String cols[] = {"tkt_order_id"};
			prep = conn.prepareStatement(sql,cols);
			
			prep.setInt(1, tktOrder.getCustId());
			prep.setInt(2, tktOrder.getOriginalPrice());
			prep.setInt(3, tktOrder.getTtlPrice());
			prep.setInt(4, tktOrder.getCustCopId());
			prep.setString(5, tktOrder.getQrcode());
			prep.setString(6, tktOrder.getOrderName());
			prep.setString(7, tktOrder.getOrderEmail());
			prep.setString(8, tktOrder.getOrderMobile());
			
			prep.executeUpdate();
			
			//取得對應的自增主鍵值(AI)
			String next_tktOrderId = null;
			ResultSet rs = prep.getGeneratedKeys();
			if(rs.next()) {
				next_tktOrderId = rs.getString(1);  //關鍵，取得PK
				tktOrder.setTktOrderId(new Integer(next_tktOrderId));
				System.out.println("自增主鍵值= " + next_tktOrderId + "剛新增成功的訂單編號");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			//同時新增訂單明細
			TktItemJDBCDao tktItemDAO = new TktItemJDBCDao();
			System.out.println("tktItem_list.size()= " + tktItem_list.size());
			for (TktItem tktItem : tktItem_list) {
				tktItem.setTktOrderId(new Integer(next_tktOrderId));  //關鍵
				tktItemDAO.insertTktItem(tktItem, conn);
			}
			// 2.設定於 pstmt.executeUpdate()之後
			conn.commit();
			conn.setAutoCommit(true); 
			System.out.println("新增訂單編號" + next_tktOrderId + "時,共有" + tktItem_list.size()
					+ "筆訂單明細同時被新增");		
			return next_tktOrderId;
				
		} catch (SQLException se) {
			if (conn != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (prep != null) {
				try {
					prep.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (conn != null ) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public String insertTktOrderNoCoupon(TktOrder tktOrder, List<TktItem> tktItem_list) {
		Connection conn =null;
		PreparedStatement prep = null;
		String sql = "insert into TKT_ORDER(cust_id,original_price,ttl_price,qrcode,order_name,order_email,order_mobile)" + " values(?,?,?,?,?,?,?);";
		try {
			conn = DbUtil.getConnection();
			conn.setAutoCommit(false);
			//新增訂單主檔
			String cols[] = {"tkt_order_id"};
			prep = conn.prepareStatement(sql,cols);
			
			prep.setInt(1, tktOrder.getCustId());
			prep.setInt(2, tktOrder.getOriginalPrice());
			prep.setInt(3, tktOrder.getTtlPrice());
			prep.setString(4, tktOrder.getQrcode());
			prep.setString(5, tktOrder.getOrderName());
			prep.setString(6, tktOrder.getOrderEmail());
			prep.setString(7, tktOrder.getOrderMobile());
			
			prep.executeUpdate();
			
			//取得對應的自增主鍵值(AI)
			String next_tktOrderId = null;
			ResultSet rs = prep.getGeneratedKeys();
			if(rs.next()) {
				next_tktOrderId = rs.getString(1);
				tktOrder.setTktOrderId(new Integer(next_tktOrderId));
				System.out.println("自增主鍵值= " + next_tktOrderId + "剛新增成功的訂單編號");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			
			//同時新增訂單明細
			TktItemJDBCDao tktItemDAO = new TktItemJDBCDao();
			System.out.println("tktItem_list.size()= " + tktItem_list.size());
			for (TktItem tktItem : tktItem_list) {
				tktItem.setTktOrderId(new Integer(next_tktOrderId));
				tktItemDAO.insertTktItem(tktItem, conn);
			}
			// 2.設定於 pstmt.executeUpdate()之後
			conn.commit();
			conn.setAutoCommit(true);
			System.out.println("新增訂單編號" + next_tktOrderId + "時,共有" + tktItem_list.size()
					+ "筆訂單明細同時被新增");				
				
			return next_tktOrderId;
			
		} catch (SQLException se) {
			if (conn != null) {
				try {
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (prep != null) {
				try {
					prep.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
			if (conn != null ) {
				try {
					conn.close();
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<TktOrder> queryAllTktOrder() {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode,order_name,order_email,order_mobile from TKT_ORDER;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer custId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				String orderName = rs.getString("order_name");
				String orderEmail = rs.getString("order_email");
				String orderMobile = rs.getString("order_mobile");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, custId, originalPrice, orderdate, ttlPrice, custCopId,
						qrcode, orderName, orderEmail, orderMobile);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public List<TktOrder> queryTktOrderByCustId(Integer custId) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode,order_name,order_email,order_mobile from TKT_ORDER where cust_id=? order by orderdate desc;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, custId);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				String orderName = rs.getString("order_name");
				String orderEmail = rs.getString("order_email");
				String orderMobile = rs.getString("order_mobile");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, querycustId, originalPrice, orderdate, ttlPrice,
						custCopId, qrcode, orderName, orderEmail, orderMobile);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public List<TktOrder> queryTktOrderByOrderDate(Timestamp orderDate) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String date = "";
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode,order_name,order_email,order_mobile from TKT_ORDER where orderdate like=?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setString(1, "%" + date + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				String orderName = rs.getString("order_name");
				String orderEmail = rs.getString("order_email");
				String orderMobile = rs.getString("order_mobile");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, querycustId, originalPrice, orderdate, ttlPrice,
						custCopId, qrcode,orderName,orderEmail,orderMobile);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public List<TktOrder> queryTktOrderByDateToDate(Timestamp startdate, Timestamp enddate) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode,order_name,order_email,order_mobile from TKT_ORDER where orderdate between ? and ?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setObject(1, startdate);
			prep.setObject(2, enddate);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				String orderName = rs.getString("order_name");
				String orderEmail = rs.getString("order_email");
				String orderMobile = rs.getString("order_mobile");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, querycustId, originalPrice, orderdate, ttlPrice,
						custCopId, qrcode,orderName,orderEmail,orderMobile);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ls;
	}

	@Override
	public List<TktOrder> queryTktOrderByDateCustID(Timestamp startdate, Timestamp enddate, Integer custId) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode,order_name,order_email,order_mobile from TKT_ORDER where cust_id = ? and orderdate between ? and ? order by orderdate desc;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setObject(1, custId);
			prep.setObject(2, startdate);
			prep.setObject(3, enddate);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				String orderName = rs.getString("order_name");
				String orderEmail = rs.getString("order_email");
				String orderMobile = rs.getString("order_mobile");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, querycustId, originalPrice, orderdate, ttlPrice,
						custCopId, qrcode,orderName,orderEmail,orderMobile);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ls;
	}
	
	@Override
	public int queryTktOrderTtlPrice() {
		int totalPrice = 0;
		String sql = "select sum(ttl_price) as ttlprice from TKT_ORDER;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				totalPrice = rs.getInt("ttlprice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return totalPrice;
	}

	@Override
	public int quertTktOrderTtlPriceById(Integer custId) {
		int ttlPrice = 0;
		String sql = "select sum(ttl_price) as ttlprice from TKT_ORDER where cust_id=?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, custId);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				ttlPrice = rs.getInt("ttlprice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ttlPrice;
	}

	@Override
	public int queryTktOrderTtlPriceByDateToDate(Timestamp startdate, Timestamp enddate) {
		int ttlPrice = 0;
		String sql = "select sum(ttl_price) as ttlprice from TKT_ORDER where order_date between ? and ?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setObject(1, startdate);
			prep.setObject(2, enddate);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				ttlPrice = rs.getInt("ttlprice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ttlPrice;
	}

	@Override
	public TktOrder queryTktOrderByTktOrderId(Integer tktOrderId) {
		TktOrder tktOrder = null;
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode,order_name,order_email,order_mobile from TKT_ORDER where tkt_order_id=?;";
		try (Connection conn = DbUtil.getConnection();
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, tktOrderId);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer querytktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				String orderName = rs.getString("order_name");
				String orderEmail = rs.getString("order_email");
				String orderMobile = rs.getString("order_mobile");
				tktOrder = new TktOrder(querytktOrderId, querycustId, originalPrice, orderdate, ttlPrice, custCopId,
						qrcode,orderName,orderEmail,orderMobile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return tktOrder;
	}
	
	@Override
	public int updateQrcode(String qrcode, Integer tktOrderId) {
		int count=0;
		String sql = "UPDATE TKT_ORDER set qrcode = ? WHERE TKT_ORDER_ID = ?;";
		try (Connection conn = DbUtil.getConnection(); 
				PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setString(1, qrcode);
			prep.setInt(2,tktOrderId);
			count=prep.executeUpdate();
			System.out.println("success "+count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
//	//未完成
//	@Override
//	public List<TktOrder> queryXX(Integer tktOrderId, Integer custId, Timestamp orderDate) {
//		List<TktOrder> ls = new ArrayList<TktOrder>();
//		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
//				+ "ttl_price,cust_cop_id,qrcode from TKT_ORDER where tkt_order_id=?";
//		String sql1 = ",cust_id=?";
//		String sql2 = ",orderdate=?";
//		if(custId!=null) {
//			sql=sql+sql1;
//		}
//		try (PreparedStatement prep=conn.prepareStatement(sql)){
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		return ls;
//	}
	
	//test
	public static void main(String[] args) {
		
		TktOrderJDBCDao dao = new TktOrderJDBCDao();
		
//		TktOrder tktOrder = new TktOrder();
//		tktOrder.setCustId(10027);
//		tktOrder.setOriginalPrice(1598);
//		tktOrder.setTtlPrice(1498);
//		tktOrder.setCustCopId(4);
//		tktOrder.setQrcode("wrewerewrewr");
//		tktOrder.setOrderName("Alex");
//		tktOrder.setOrderEmail("wre826@gmail.com");
//		tktOrder.setOrderMobile("0928945311");
//		
//		List<TktItem> list = new ArrayList<TktItem>();
//		TktItem tktItem1 = new TktItem();
//		tktItem1.setTktId(11);
//		tktItem1.setAmount(1);
//		
//		TktItem tktItem2 = new TktItem();
//		tktItem2.setTktId(12);
//		tktItem2.setAmount(1);
//		
//		list.add(tktItem1);
//		list.add(tktItem2);
//		
//		dao.insertTktOrderWithCoupon(tktOrder, list);
		
//		TktOrder tktOrder = new TktOrder();
//		dao.updateQrcode("12345", 15);
	}


	
}
