package com.taiwan.dao.roomSelect.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.taiwan.beans.Roomtype;
import com.taiwan.beans.Company;
import com.taiwan.beans.EmployeeVO;
import com.taiwan.beans.Reservation;
import com.taiwan.beans.RoomSelectVO;
import com.taiwan.beans.TicketVO;
import com.taiwan.dao.roomSelect.RoomSelect_interface;
import com.taiwan.dao.ticket.impl.TicketJDBCDAO;
import com.taiwan.utils.jdbcUtil_CompositeQuery_Ticket;

public class RoomSelectJDBCDAO implements RoomSelect_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

//	private static final String find_location = "SELECT cmp_id,cmp_name,cmp_tel,cmp_mail,cmp_status,audit_status,location from COMPANY "
//			+ "where cmp_status = '正常' and audit_status='審核通過' and location like %?%";
	
	private static final String find_people_amount = "SELECT roomtype_id,cmp_id,roomtype_name"
			+ ",roomtype_amount,roomtype_people,total_score,total_people,roomtype_price,roomtype_status"
			+ ",roomtype_introduce FROM ROOMTYPE where roomtype_people =? and cmp_id=? ";

	private static final String find_time = "SELECT reservation_id, roomtype_id, reserve_date, roomtype_amount, reserve_amount "
			+ "from RESERVATION where roomtype_id =? and reserve_date between ? and ? "
			+ "and roomtype_amount > reserve_amount ";
	private static final String GET_all_Table ="SELECT t.roomtype_id,t.cmp_id,t.roomtype_name"
			+ ",t.roomtype_amount,t.roomtype_people,t.total_score"
			+ ",t.total_people,t.roomtype_price,t.roomtype_status"
			+ ",t.roomtype_introduce"
			+ ",c.cmp_name,c.cmp_tel,c.location"
			+ ",r.reserve_date,r.roomtype_amount,r.reserve_amount"
			+ " FROM ROOMTYPE t join COMPANY c on t.cmp_id=c. cmp_id "
			+ " join RESERVATION r on t.roomtype_id=r.roomtype_id"
			+ " where "
			+ " c.location like '%?%' "
			+ " and roomtype_people =? "
			+ " and r.reserve_date between '?' and'?' "
			+ " and t.roomtype_status='上架'"
			+ " and r.roomtype_amount > r.reserve_amount";

	@Override
	public List<Company> fineByLocation(String location) {

		List<Company> list = new ArrayList<Company>();
		Company company = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String str = "select * from COMPANY  where cmp_status = '正常' and audit_status='審核通過' and location like ? ";
			pstmt = con.prepareStatement(str);
			pstmt.setString(1, "%" + location + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				company = new Company();
				company.setCmpId(rs.getInt("cmp_id"));
				company.setCmpName(rs.getString("cmp_name"));
				company.setCmpTel(rs.getString("cmp_tel"));
				company.setCmpMail(rs.getString("cmp_mail"));
				company.setLocation(rs.getString("location"));
				company.setCmpStatus(rs.getString("cmp_status"));
				company.setAuditStatus(rs.getString("audit_status"));
				list.add(company);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}

	@Override
	public List<Roomtype> fineByPeople(Integer roomtype_people, Integer cmp_id) {
		List<Roomtype> list = new ArrayList<Roomtype>();
		Roomtype roomtype = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find_people_amount);

			pstmt.setInt(1, roomtype_people);
			pstmt.setInt(2, cmp_id);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomtype = new Roomtype();
				roomtype.setRoomtypeId(rs.getInt("roomtype_id"));
				roomtype.setRoomtypeName(rs.getString("roomtype_name"));
				roomtype.setCmpId(rs.getInt("cmp_id"));
				roomtype.setRoomtypeAmount(rs.getInt("roomtype_amount"));
				roomtype.setRoomtypePeople(rs.getInt("roomtype_people"));
				roomtype.setTotalScore(rs.getInt("total_score"));
				roomtype.setTotalPeople(rs.getInt("total_people"));
				roomtype.setRoomtypePrice(rs.getInt("roomtype_price"));
				roomtype.setRoomtypeStatus(rs.getString("roomtype_status"));
				roomtype.setRoomtypeIntroduce(rs.getString("roomtype_introduce"));
				list.add(roomtype);
				
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}

	@Override
	public List<Reservation> fineByTime(Integer roomtype_id, Timestamp reserve_date, Timestamp reserve_date2) {
		List<Reservation> list = new ArrayList<Reservation>();
		Reservation reservation = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find_time);

			pstmt.setInt(1, roomtype_id);
			pstmt.setTimestamp(2, reserve_date);
			pstmt.setTimestamp(3, reserve_date2);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				reservation=new Reservation();
				reservation.setReservationId(rs.getInt("reservation_id"));
				reservation.setRoomtypeId(rs.getInt("roomtype_id"));
				reservation.setReserveDate(rs.getTimestamp("reserve_date"));
				reservation.setRoomtypeAmount(rs.getInt("roomtype_amount"));
				reservation.setRoomtypeAmount(rs.getInt("reserve_amount"));
				list.add(reservation);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}

	
	@Override
	public List<RoomSelectVO> getAllTable(String location,Integer roomtype_people,Date reserve_date,Date reserve_date2) {
		List<RoomSelectVO> list = new ArrayList<RoomSelectVO>();
		RoomSelectVO roomSelectVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String str = "SELECT t.roomtype_id,t.cmp_id,t.roomtype_name"
								+ ",t.roomtype_amount,t.roomtype_people,t.total_score"
								+ ",t.total_people,t.roomtype_price,t.roomtype_status"
							    + ",t.roomtype_introduce"
								+ ",c.cmp_name,c.cmp_tel,c.location"
								+ ",r.reserve_date,r.reserve_amount"
								+ ",m.room_img"
								+ " FROM ROOMTYPE t join COMPANY c on t.cmp_id=c. cmp_id "
								+ " join RESERVATION r on t.roomtype_id=r.roomtype_id"
								+ " join ROOM_IMG m on t.roomtype_id=m.roomtype_id"
								+ " where "
								+ " c.location like ?" 
								+ " and roomtype_people =?" 
								+ " and r.reserve_date between ? and ?" 
								+ " and t.roomtype_status='下架'"
								+ " and r.roomtype_amount > r.reserve_amount"; 
			pstmt = con.prepareStatement(str);
			pstmt.setString(1, "%" + location + "%");
			pstmt.setInt(2, roomtype_people);
			pstmt.setDate(3, (java.sql.Date) reserve_date);
			pstmt.setDate(4, (java.sql.Date) reserve_date2);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomSelectVO = new RoomSelectVO();
				roomSelectVO.setRoomtypeId(rs.getInt("roomtype_id"));
				roomSelectVO.setRoomtypeName(rs.getString("roomtype_name"));
				roomSelectVO.setCmpId(rs.getInt("cmp_id"));
				roomSelectVO.setRoomtypeAmount(rs.getInt("roomtype_amount"));
				roomSelectVO.setRoomtypePeople(rs.getInt("roomtype_people"));
				roomSelectVO.setTotalScore(rs.getInt("total_score"));
				roomSelectVO.setTotalPeople(rs.getInt("total_people"));
				roomSelectVO.setRoomtypePrice(rs.getInt("roomtype_price"));
				roomSelectVO.setRoomtypeStatus(rs.getString("roomtype_status"));
				roomSelectVO.setRoomtypeIntroduce(rs.getString("roomtype_introduce"));
				roomSelectVO.setCmpName(rs.getString("cmp_name"));
				roomSelectVO.setCmpTel(rs.getString("cmp_tel"));
				roomSelectVO.setLocation(rs.getString("location"));
				roomSelectVO.setReserveDate(rs.getDate("reserve_date"));
				roomSelectVO.setRoomtypeAmount(rs.getInt("roomtype_amount"));
				roomSelectVO.setReserveAmount(rs.getInt("reserve_amount"));
				roomSelectVO.setRoomImg(rs.getString("room_img"));
				list.add(roomSelectVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return list;
	}

	
	
	public static void main(String[] args) {

		RoomSelectJDBCDAO dao = new RoomSelectJDBCDAO();

//		List<Company> list2 = dao.fineByLocation("台北");
//		System.out.println(list2.get(1));
//		for (int i = 0; i < list2.size(); i++  ) {
////			System.out.println(list2.get(i));
//			for(int j=0;j<list2.size(z);j++ ) {
//				System.out.println(list2.get(j));
//			}
//			}
		
		
		List<RoomSelectVO> list3 = dao.getAllTable("台北",2,java.sql.Date.valueOf("2022-05-25"),java.sql.Date.valueOf("2022-05-26"));
		for (int i = 0; i < list3.size(); i++  ) {
		System.out.println(list3.get(i));
		}
		for(RoomSelectVO room : list3) {
			System.out.println(room.toString());
		}
		
		
	
		
//	List<Roomtype> list3 = dao.fineByPeople(4,20058);
//	for(Roomtype room : list3) {
//		System.out.println(room.toString());
//	}
//	List<Reservation> list4 = dao.fineByTime(11,java.sql.Timestamp.valueOf("2022-05-25 08:00:00"),java.sql.Timestamp.valueOf("2022-05-26 08:00:00"));
//	for(Reservation reser : list4) {
//		System.out.println(reser.toString());
//	}
	
	
	

	}



}
