package com.taiwan.dao.roomOrder.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.RoomItemVO;
import com.taiwan.beans.RoomOrderVO;
import com.taiwan.dao.custcoupon12.impl.CustcouponDao12;
import com.taiwan.dao.roomItem.impl.RoomItemDAO;
import com.taiwan.dao.roomOrder.RoomOrderDAO_interface;

public class RoomOrderDAO implements RoomOrderDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";
	
	private static final String insert =
			"INSERT INTO Taiwan.ROOM_ORDER ( cust_id, room_order_price, checkin_date, checkout_date,total_price,cmp_id) VALUES ( ?, ?, ?, ?, ?,?)";
	private static final String insert2 =
			"INSERT INTO Taiwan.ROOM_ORDER ( cust_id, room_order_price, checkin_date, checkout_date,total_price,cmp_id,cust_cop_id) VALUES ( ?, ?, ?, ?, ?,?,?)";
	
	private static final String findDate= 
	"SELECT * FROM Taiwan.ROOM_ORDER where room_order_date between ? and ?";
	private static final String find= 
			"SELECT * FROM Taiwan.ROOM_ORDER where ";
	private static final String delete= 
			"delete FROM Taiwan.ROOM_ORDER where room_order_id = ?";
	private static final String cancel= 
			"update FROM Taiwan.ROOM_ORDER set room_order_status='已取消' , cancel= ?  where room_order_id = ?";
	

	@Override
	public RoomOrderVO queryRoomOrderByRoomOrderId(Integer roomOrderId) {
		RoomOrderVO roomOrderVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find + "room_order_id = ? ");

			pstmt.setInt(1, roomOrderId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomOrderVO.setCustId(rs.getInt("cust_id"));
				roomOrderVO.setRoomOrderDate(rs.getTimestamp("room_Order_date"));
				roomOrderVO.setRoomOrderPrice(rs.getInt("room_order_price"));
				roomOrderVO.setRoomOrderCheckinDate(rs.getTimestamp("checkin_date"));
				roomOrderVO.setRoomOrderCheckoutDate(rs.getTimestamp("checkout_date"));
				roomOrderVO.setRoomOrderStatus(rs.getString("room_order_status"));
				roomOrderVO.setRoomOrderCancel(rs.getString("cancel"));
				roomOrderVO.setRoomOrderTotalPrice(rs.getInt("total_price"));
				roomOrderVO.setCustCopId(rs.getInt("cust_cop_id"));
				roomOrderVO.setCmpId(rs.getInt("cmp_id"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return roomOrderVO;
	}

	@Override
	public List<RoomOrderVO> queryRoomOrderByRoomOrderCheckinDate(Timestamp CheckinDate) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find +  "checkin_date = ?");
			pstmt.setTimestamp(1, CheckinDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomOrderVO.setCustId(rs.getInt("cust_id"));
				roomOrderVO.setRoomOrderDate(rs.getTimestamp("room_Order_date"));
				roomOrderVO.setRoomOrderPrice(rs.getInt("room_order_price"));
				roomOrderVO.setRoomOrderCheckinDate(rs.getTimestamp("checkin_date"));
				roomOrderVO.setRoomOrderCheckoutDate(rs.getTimestamp("checkout_date"));
				roomOrderVO.setRoomOrderStatus(rs.getString("room_order_status"));
				roomOrderVO.setRoomOrderCancel(rs.getString("cancel"));
				roomOrderVO.setRoomOrderTotalPrice(rs.getInt("total_price"));
				roomOrderVO.setCustCopId(rs.getInt("cust_cop_id"));
				roomOrderVO.setCmpId(rs.getInt("cmp_id"));


				list.add(roomOrderVO); // Store the row in the list
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
	public List<RoomOrderVO> queryRoomOrderByRoomOrderCheckoutDate(Timestamp CheckoutDate) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find+ "checkout_date = ?");
			pstmt.setTimestamp(1, CheckoutDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomOrderVO.setCustId(rs.getInt("cust_id"));
				roomOrderVO.setRoomOrderDate(rs.getTimestamp("room_Order_date"));
				roomOrderVO.setRoomOrderPrice(rs.getInt("room_order_price"));
				roomOrderVO.setRoomOrderCheckinDate(rs.getTimestamp("checkin_date"));
				roomOrderVO.setRoomOrderCheckoutDate(rs.getTimestamp("checkout_date"));
				roomOrderVO.setRoomOrderStatus(rs.getString("room_order_status"));
				roomOrderVO.setRoomOrderCancel(rs.getString("cancel"));
				roomOrderVO.setRoomOrderTotalPrice(rs.getInt("total_price"));
				roomOrderVO.setCustCopId(rs.getInt("cust_cop_id"));
				roomOrderVO.setCmpId(rs.getInt("cmp_id"));


				list.add(roomOrderVO); // Store the row in the list
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
	public List<RoomOrderVO> queryRoomOrderByRoomOrderStatus(String roomOrderStatus) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find + "room_order_status = ?");
			pstmt.setString(1, roomOrderStatus);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomOrderVO.setCustId(rs.getInt("cust_id"));
				roomOrderVO.setRoomOrderDate(rs.getTimestamp("room_Order_date"));
				roomOrderVO.setRoomOrderPrice(rs.getInt("room_order_price"));
				roomOrderVO.setRoomOrderCheckinDate(rs.getTimestamp("checkin_date"));
				roomOrderVO.setRoomOrderCheckoutDate(rs.getTimestamp("checkout_date"));
				roomOrderVO.setRoomOrderStatus(rs.getString("room_order_status"));
				roomOrderVO.setRoomOrderCancel(rs.getString("cancel"));
				roomOrderVO.setRoomOrderTotalPrice(rs.getInt("total_price"));
				roomOrderVO.setCustCopId(rs.getInt("cust_cop_id"));
				roomOrderVO.setCmpId(rs.getInt("cmp_id"));


				list.add(roomOrderVO); // Store the row in the list
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
	public List<RoomOrderVO> queryRoomOrderByCustId(Integer custId) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find + "cust_id = ?");
			pstmt.setInt(1, custId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomOrderVO.setCustId(rs.getInt("cust_id"));
				roomOrderVO.setRoomOrderDate(rs.getTimestamp("room_Order_date"));
				roomOrderVO.setRoomOrderPrice(rs.getInt("room_order_price"));
				roomOrderVO.setRoomOrderCheckinDate(rs.getTimestamp("checkin_date"));
				roomOrderVO.setRoomOrderCheckoutDate(rs.getTimestamp("checkout_date"));
				roomOrderVO.setRoomOrderStatus(rs.getString("room_order_status"));
				roomOrderVO.setRoomOrderCancel(rs.getString("cancel"));
				roomOrderVO.setRoomOrderTotalPrice(rs.getInt("total_price"));
				roomOrderVO.setCustCopId(rs.getInt("cust_cop_id"));
				roomOrderVO.setCmpId(rs.getInt("cmp_id"));


				list.add(roomOrderVO); // Store the row in the list
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
	public Integer insert(RoomOrderVO roomOrderVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer pk=null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
		
			pstmt = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, roomOrderVO.getCustId());
			pstmt.setInt(2, roomOrderVO.getRoomOrderPrice());
			pstmt.setTimestamp(3, roomOrderVO.getRoomOrderCheckinDate());
			pstmt.setTimestamp(4, roomOrderVO.getRoomOrderCheckoutDate());
			pstmt.setInt(5, roomOrderVO.getRoomOrderTotalPrice());
			pstmt.setInt(6,roomOrderVO.getCmpId());


			pstmt.executeUpdate();
			//抓自動生成的PK
			ResultSet getPK =pstmt.getGeneratedKeys();
			getPK.next();
			pk=getPK.getInt(1);
			System.out.println("取得自增鍵"+pk);
			getPK.close();
		
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			
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
		return pk;
	}
	@Override
	public Integer insert(RoomOrderVO roomOrderVO,RoomItemVO roomItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer pk=null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, roomOrderVO.getCustId());
			pstmt.setInt(2, roomOrderVO.getRoomOrderPrice());
			pstmt.setTimestamp(3, roomOrderVO.getRoomOrderCheckinDate());
			pstmt.setTimestamp(4, roomOrderVO.getRoomOrderCheckoutDate());
			pstmt.setInt(5, roomOrderVO.getRoomOrderTotalPrice());
			pstmt.setInt(6,roomOrderVO.getCmpId());


			pstmt.executeUpdate();
			//抓自動生成的PK
			ResultSet getPK =pstmt.getGeneratedKeys();
			getPK.next();
			pk=getPK.getInt(1);
			System.out.println("取得自增鍵"+pk);
			getPK.close();
			RoomItemDAO roomItemDao = new RoomItemDAO();
			roomItemVO.setRoomOrderId(pk);
			roomItemDao.insertRoomItem(roomItemVO, con);
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-roomOrder");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			
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
		return pk;
	}
	@Override
	public Integer insert2(RoomOrderVO roomOrderVO,RoomItemVO roomItemVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		Integer pk=null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			// 1●設定於 pstm.executeUpdate()之前
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(insert2,Statement.RETURN_GENERATED_KEYS);

			pstmt.setInt(1, roomOrderVO.getCustId());
			pstmt.setInt(2, roomOrderVO.getRoomOrderPrice());
			pstmt.setTimestamp(3, roomOrderVO.getRoomOrderCheckinDate());
			pstmt.setTimestamp(4, roomOrderVO.getRoomOrderCheckoutDate());
			pstmt.setInt(5, roomOrderVO.getRoomOrderTotalPrice());
			pstmt.setInt(6,roomOrderVO.getCmpId());
			pstmt.setInt(7,roomOrderVO.getCustCopId());



			pstmt.executeUpdate();
			//抓自動生成的PK
			ResultSet getPK =pstmt.getGeneratedKeys();
			getPK.next();
			pk=getPK.getInt(1);
			System.out.println("取得自增鍵"+pk);
			getPK.close();
			RoomItemDAO roomItemDao = new RoomItemDAO();
			roomItemVO.setRoomOrderId(pk);
			roomItemDao.insertRoomItem(roomItemVO, con);
			
				CustcouponDao12 custcopDao =new CustcouponDao12();
				custcopDao.updateCustCouponStatusByRoomOrderId(roomOrderVO.getCustId(), pk,roomOrderVO.getCustCopId(), con);
				
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-roomOrder");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			
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
		return pk;
	}

	@Override
	public List<RoomOrderVO> queryRoomOrderByRoomOrderDate(Timestamp startDate,Timestamp endDate) {
		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findDate);
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setRoomOrderId(rs.getInt("room_order_id"));
				roomOrderVO.setCustId(rs.getInt("cust_id"));
				roomOrderVO.setRoomOrderDate(rs.getTimestamp("room_Order_date"));
				roomOrderVO.setRoomOrderPrice(rs.getInt("room_order_price"));
				roomOrderVO.setRoomOrderCheckinDate(rs.getTimestamp("checkin_date"));
				roomOrderVO.setRoomOrderCheckoutDate(rs.getTimestamp("checkout_date"));
				roomOrderVO.setRoomOrderStatus(rs.getString("room_order_status"));
				roomOrderVO.setRoomOrderCancel(rs.getString("cancel"));
				roomOrderVO.setRoomOrderTotalPrice(rs.getInt("total_price"));
				roomOrderVO.setCustCopId(rs.getInt("cust_cop_id"));
				roomOrderVO.setCmpId(rs.getInt("cmp_id"));


				list.add(roomOrderVO); // Store the row in the list
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
	public void delete(Integer roomOrderId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(delete);

			pstmt.setInt(1, roomOrderId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

	@Override
	public void cancel(RoomOrderVO roomOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(cancel);

			pstmt.setString(1,roomOrderVO.getRoomOrderCancel());
			pstmt.setInt(2, roomOrderVO.getRoomOrderId());
		

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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

	}

}
