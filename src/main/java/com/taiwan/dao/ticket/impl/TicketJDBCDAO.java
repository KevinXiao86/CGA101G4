package com.taiwan.dao.ticket.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.TicketVO;
import com.taiwan.dao.ticket.TicketDAO_interface;
public class TicketJDBCDAO implements TicketDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT = 
			"INSERT INTO TICKET(tkt_name, original_amount, price, startdate, enddate, location, instruction, address, notice, howuse, canxpolicy, kind) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"UPDATE TICKET SET tkt_name=?, original_amount=?, price=?, startdate=?, enddate=?, location=?, instruction=?, address=?, notice=?, howuse=?, canxpolicy=?, kind=? where tkt_id=?";
	private static final String DELETE =
			"DELETE FROM TICKET WHERE tkt_id=?";
	private static final String GET_BYTKTID = 
			"SELECT tkt_id, tkt_name, original_amount, price, startdate, enddate, ttl_score, ttl_people, location, instruction, address, notice, howuse, canxpolicy, status, sold_amount, kind FROM TICKET WHERE tkt_id=?";
	private static final String GET_BYSCORE = 
			"SELECT tkt_name, location, instruction, kind FROM TICKET ORDER BY ttl_score DESC LIMIT 3";
	private static final String GET_BYPEOPLE =    //?要的欄位
			"SELECT tkt_name, location, instruction, kind FROM TICKET ORDER BY ttl_people DESC LIMIT 3";
	private static final String GET_BYTKTNAME = 
			"SELECT tkt_id, tkt_name, original_amount, price, startdate, enddate, ttl_score, ttl_people, location, instruction, status, sold_amount, kind FROM TICKET WHERE tkt_name LIKE ?";
	private static final String GET_BYKIND =
			"SELECT tkt_id, tkt_name, original_amount, price, startdate, enddate, ttl_score, ttl_people, location, instruction, status, sold_amount FROM TICKET WHERE kind=?";
	private static final String GET_BYLOCATION = 
			"SELECT tkt_id, tkt_name, original_amount, price, startdate, enddate, ttl_score, ttl_people, instruction, status, sold_amount, kind FROM TICKET WHERE location=?";
	private static final String GET_BYSTATUS =
			"SELECT tkt_id, tkt_name FROM TICKET WHERE status=?";
	private static final String GET_ALL = 
			"SELECT tkt_id, tkt_name, original_amount, price, startdate, enddate, ttl_score, ttl_people, location, instruction, address, notice, howuse, canxpolicy, status, sold_amount, kind FROM TICKET ";
	
	@Override
	public int insert(TicketVO ticketVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, ticketVO.getTktName());
			ps.setInt(2, ticketVO.getOriginalAmount());
			ps.setInt(3, ticketVO.getPrice());
			ps.setTimestamp(4, ticketVO.getStartdate());
			ps.setTimestamp(5, ticketVO.getEnddate());
			ps.setString(6, ticketVO.getLocation());
			ps.setString(7, ticketVO.getInstruction());
			ps.setString(8, ticketVO.getAddress());
			ps.setString(9, ticketVO.getNotice());
			ps.setString(10, ticketVO.getHowUse());
			ps.setString(11, ticketVO.getCanxPolicy());
			ps.setString(12, ticketVO.getKind());
			
			count = ps.executeUpdate();
			System.out.println("success" + count);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return count;
		
	}
	
	@Override
	public int update(TicketVO ticketVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, ticketVO.getTktName());
			ps.setInt(2, ticketVO.getOriginalAmount());
			ps.setInt(3, ticketVO.getPrice());
			ps.setTimestamp(4, ticketVO.getStartdate());
			ps.setTimestamp(5, ticketVO.getEnddate());
			ps.setString(6, ticketVO.getLocation());
			ps.setString(7, ticketVO.getInstruction());
			ps.setString(8, ticketVO.getAddress());
			ps.setString(9, ticketVO.getNotice());
			ps.setString(10, ticketVO.getHowUse());
			ps.setString(11, ticketVO.getCanxPolicy());
			ps.setString(12, ticketVO.getKind());
			ps.setInt(13, ticketVO.getTktId());
			
			count = ps.executeUpdate();
			System.out.println("success" + count);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return count;
	}

	@Override
	public int delete(Integer tktId) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, tktId);
			
			count = ps.executeUpdate();
			System.out.println("success" + count);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return count;
	}
	
	@Override
	public TicketVO queryById(Integer tktId) {
		
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYTKTID);
			
			ps.setInt(1, tktId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktId(rs.getInt(1));
				ticketVO.setTktName(rs.getString(2));
				ticketVO.setOriginalAmount(rs.getInt(3));
				ticketVO.setPrice(rs.getInt(4));
				ticketVO.setStartdate(rs.getTimestamp(5));
				ticketVO.setEnddate(rs.getTimestamp(6));
				ticketVO.setTtlScore(rs.getInt(7));
				ticketVO.setTtlPeople(rs.getInt(8));
				ticketVO.setLocation(rs.getString(9));
				ticketVO.setInstruction(rs.getString(10));
				ticketVO.setAddress(rs.getString(11));
				ticketVO.setNotice(rs.getString(12));
				ticketVO.setHowUse(rs.getString(13));
				ticketVO.setCanxPolicy(rs.getString(14));
				ticketVO.setStatus(rs.getString(15));
				ticketVO.setSoldAmount(rs.getInt(16));
				ticketVO.setKind(rs.getString(17));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return ticketVO;
	}
	
	@Override
	public List<TicketVO> getTicketByScore() {
		
		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYSCORE);
			rs = ps.executeQuery();

			while(rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktName(rs.getString(1));
				ticketVO.setLocation(rs.getString(2));
				ticketVO.setInstruction(rs.getString(3));
				ticketVO.setKind(rs.getString(4));
				list.add(ticketVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<TicketVO> getTicketByPeople() {
		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYPEOPLE);
			rs = ps.executeQuery();

			while(rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktName(rs.getString(1));
				ticketVO.setLocation(rs.getString(2));
				ticketVO.setInstruction(rs.getString(3));
				ticketVO.setKind(rs.getString(4));
				list.add(ticketVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<TicketVO> queryTicketByTktName(String tktName) {
		
		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYTKTNAME);
			
			ps.setString(1, "%" + tktName + "%");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktId(rs.getInt(1));
				ticketVO.setTktName(rs.getString(2));
				ticketVO.setOriginalAmount(rs.getInt(3));
				ticketVO.setPrice(rs.getInt(4));
				ticketVO.setStartdate(rs.getTimestamp(5));
				ticketVO.setEnddate(rs.getTimestamp(6));
				ticketVO.setTtlScore(rs.getInt(7));
				ticketVO.setTtlPeople(rs.getInt(8));
				ticketVO.setLocation(rs.getString(9));
				ticketVO.setInstruction(rs.getString(10));
				ticketVO.setStatus(rs.getString(11));
				ticketVO.setSoldAmount(rs.getInt(12));
				ticketVO.setKind(rs.getString(13));
				list.add(ticketVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;

	}
	
	@Override
	public List<TicketVO> queryTicketByKind(String kind) {
		
		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYKIND);
			
			ps.setString(1, kind);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktId(rs.getInt(1));
				ticketVO.setTktName(rs.getString(2));
				ticketVO.setOriginalAmount(rs.getInt(3));
				ticketVO.setPrice(rs.getInt(4));
				ticketVO.setStartdate(rs.getTimestamp(5));
				ticketVO.setEnddate(rs.getTimestamp(6));
				ticketVO.setTtlScore(rs.getInt(7));
				ticketVO.setTtlPeople(rs.getInt(8));
				ticketVO.setLocation(rs.getString(9));
				ticketVO.setInstruction(rs.getString(10));
				ticketVO.setStatus(rs.getString(11));
				ticketVO.setSoldAmount(rs.getInt(12));
				list.add(ticketVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

	@Override
	public List<TicketVO> queryTicketByLocation(String location) {
		
		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYLOCATION);
			
			ps.setString(1, location);
			rs = ps.executeQuery();

			while (rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktId(rs.getInt(1));
				ticketVO.setTktName(rs.getString(2));
				ticketVO.setOriginalAmount(rs.getInt(3));
				ticketVO.setPrice(rs.getInt(4));
				ticketVO.setStartdate(rs.getTimestamp(5));
				ticketVO.setEnddate(rs.getTimestamp(6));
				ticketVO.setTtlScore(rs.getInt(7));
				ticketVO.setTtlPeople(rs.getInt(8));
				ticketVO.setInstruction(rs.getString(9));
				ticketVO.setStatus(rs.getString(10));
				ticketVO.setSoldAmount(rs.getInt(11));
				ticketVO.setKind(rs.getString(12));
				list.add(ticketVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<TicketVO> queryTicketByStatus(String status) {

		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYSTATUS);
			
			ps.setString(1, status);
			rs = ps.executeQuery();

			while (rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktId(rs.getInt(1));
				ticketVO.setTktName(rs.getString(2));
				list.add(ticketVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<TicketVO> queryAll() {

		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				ticketVO = new TicketVO();
				ticketVO.setTktId(rs.getInt(1));
				ticketVO.setTktName(rs.getString(2));
				ticketVO.setOriginalAmount(rs.getInt(3));
				ticketVO.setPrice(rs.getInt(4));
				ticketVO.setStartdate(rs.getTimestamp(5));
				ticketVO.setEnddate(rs.getTimestamp(6));
				ticketVO.setTtlScore(rs.getInt(7));
				ticketVO.setTtlPeople(rs.getInt(8));
				ticketVO.setLocation(rs.getString(9));
				ticketVO.setInstruction(rs.getString(10));
				ticketVO.setAddress(rs.getString(11));
				ticketVO.setNotice(rs.getString(12));
				ticketVO.setHowUse(rs.getString(13));
				ticketVO.setCanxPolicy(rs.getString(14));
				ticketVO.setStatus(rs.getString(15));
				ticketVO.setSoldAmount(rs.getInt(16));
				ticketVO.setKind(rs.getString(17));
				list.add(ticketVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		TicketJDBCDAO dao = new TicketJDBCDAO();
		
		//新增
//		TicketVO ticketVO1 = new TicketVO();
//		ticketVO1.setTktName("飛牛牧場");
//		ticketVO1.setOriginalAmount(500);
//		ticketVO1.setPrice(250);
//		ticketVO1.setStartdate(java.sql.Timestamp.valueOf("2022-04-15 08:00:00"));
//		ticketVO1.setEnddate(java.sql.Timestamp.valueOf("2022-08-01 08:00:00"));
//		ticketVO1.setLocation("苗栗");
//		ticketVO1.setInstruction("一起來看牛牛");
//		ticketVO1.setAddress("苗栗縣通霄鎮166號");
//		ticketVO1.setNotice("禁止打牛");
//		ticketVO1.setHowUse("使用QR Code");
//		ticketVO1.setCanxPolicy("無法取消");
//		ticketVO1.setKind("景點門票");
//		dao.insert(ticketVO1);
//
//		//根據id修改
//		TicketVO ticketVO2 = new TicketVO();		
//		ticketVO2.setTktId(1);
//		ticketVO2.setTktName("飛牛牧場");
//		ticketVO2.setOriginalAmount(500);
//		ticketVO2.setPrice(250);
//		ticketVO2.setStartdate(java.sql.Timestamp.valueOf("2022-04-15 08:00:00"));
//		ticketVO2.setEnddate(java.sql.Timestamp.valueOf("2022-08-01 08:00:00"));
//		ticketVO2.setLocation("苗栗");
//		ticketVO2.setInstruction("一起來看牛牛");
//		ticketVO2.setAddress("苗栗縣通霄鎮166號");
//		ticketVO2.setNotice("禁止打牛");
//		ticketVO2.setHowUse("使用QR Code");
//		ticketVO2.setCanxPolicy("無法取消");
//		ticketVO2.setKind("景點門票");
//		dao.insert(ticketVO2);
//		
//		//刪除
//		dao.delete(2);
		
		//根據票券編號查詢
		TicketVO ticketVO3 = dao.queryById(3);
		System.out.println(ticketVO3.toString());
		
		//查詢總評分最高前三
		List<TicketVO> list1 = dao.getTicketByScore();
		for(TicketVO tkt : list1) {
			System.out.println(tkt.toString());
		}
		
		//查詢評分總人數最高前三
		List<TicketVO> list2 = dao.getTicketByPeople();
		for(TicketVO tkt : list2) {
			System.out.println(tkt.toString());
		}
		
		//根據票券名稱查詢
		List<TicketVO> list3 = dao.queryTicketByTktName("牧場");
		for(TicketVO tkt : list3) {
			System.out.println(tkt.toString());
		}
		
		//根據票券種類查詢
		List<TicketVO> list4 = dao.queryTicketByKind("景點門票");
		for(TicketVO tkt : list4) {
			System.out.println(tkt.toString());
		}
		
		//根據地點查詢
		List<TicketVO> list5 = dao.queryTicketByLocation("苗栗");
		for(TicketVO tkt : list5) {
			System.out.println(tkt.toString());
		}
		
		//根據狀態查詢
		List<TicketVO> list6 = dao.queryTicketByStatus("下架");
		for(TicketVO tkt : list6) {
			System.out.println(tkt.toString());
		}
		
		//查詢全部
		List<TicketVO> list7 = dao.queryAll();
		for(TicketVO tkt : list7) {
			System.out.println(tkt.toString());
		}
		
	}
	
	
}
