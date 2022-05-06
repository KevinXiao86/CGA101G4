package com.taiwan.dao.ticket.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.taiwan.beans.TicketVO;
import com.taiwan.dao.employee.EmployeeDAO_interface;
import com.taiwan.dao.ticket.TicketSearch_interface;
import com.taiwan.utils.jdbcUtil_CompositeQuery_Ticket;

public class TicketSearchJDBC  implements TicketSearch_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	private static final String GET_ALL = 
			"SELECT tkt_id, tkt_name, original_amount, price"
			+ ", startdate, enddate, ttl_score, "
			+ "ttl_people, location, instruction, "
			+ "address, notice, howuse, canxpolicy, status, sold_amount, kind FROM TICKET order by tkt_id ";
	private static final String GET_ALL_STATUS = 
			"SELECT tkt_id, tkt_name, original_amount, price, startdate, enddate, ttl_score, "
			+ "ttl_people, location, instruction, "
			+ "address, notice, howuse, canxpolicy, status, sold_amount, kind FROM TICKET order by status= '上架' ";
	
	
	@Override
	public List<TicketVO> getAll() {
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
				ticketVO.setHowuse(rs.getString(13));
				ticketVO.setCanxpolicy(rs.getString(14));
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

	@Override
	public List<TicketVO> getAllbyStatus() {
		List<TicketVO> list = new ArrayList<TicketVO>();
		TicketVO ticketVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL_STATUS);
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
				ticketVO.setHowuse(rs.getString(13));
				ticketVO.setCanxpolicy(rs.getString(14));
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

	@Override
	public List<TicketVO> getAll(Map<String, String[]> map) {
		
			List<TicketVO> list = new ArrayList<TicketVO>();
			TicketVO ticketVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				
				
				
				String finalSQL = "select * from TICKET "
			          + jdbcUtil_CompositeQuery_Ticket.get_WhereCondition(map)
			          + " group by  TKT_ID having status='上架'";
				pstmt = con.prepareStatement(finalSQL);
				System.out.println("●●finalSQL(by DAO) = "+finalSQL);
				rs = pstmt.executeQuery();
		
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
					ticketVO.setHowuse(rs.getString(13));
					ticketVO.setCanxpolicy(rs.getString(14));
					ticketVO.setStatus(rs.getString(15));
					ticketVO.setSoldAmount(rs.getInt(16));
					ticketVO.setKind(rs.getString(17));
					
					list.add(ticketVO); // Store the row in the List
				}
			
				// Handle any SQL errors
			} catch (SQLException | ClassNotFoundException se) {
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
				if (pstmt != null) {
					try {
						pstmt.close();
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
			System.out.println(list+"這裡是JDBC");
			return list ;
		}

}
