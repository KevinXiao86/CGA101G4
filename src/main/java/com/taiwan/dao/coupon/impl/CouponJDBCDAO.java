package com.taiwan.dao.coupon.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.coupon.CouponDAO_interface;
import java.sql.Timestamp;




public class CouponJDBCDAO implements CouponDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT = "INSERT INTO COUPON(cop_name, introduce, discount, startdate, enddate, img) VALUES (?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE COUPON SET cop_name=?, introduce=?, discount=?, startdate=?, enddate=?, img=? where cop_id=?";
	private static final String DELETE = "DELETE FROM COUPON WHERE cop_id=?";
	private static final String GET_BYCOPID = "SELECT cop_id, cop_name, introduce, discount, startdate, enddate, img, status FROM COUPON WHERE cop_id=?";
	private static final String GET_BYCOPNAME = "SELECT cop_id, cop_name, introduce, discount, startdate, enddate, img, status FROM COUPON WHERE cop_name like ?";
	private static final String GET_BYSTATUS = "SELECT cop_id, cop_name, introduce, discount, startdate, enddate, img FROM COUPON WHERE status=?";
	private static final String GET_ALL = "SELECT cop_id, cop_name, introduce, discount, startdate, enddate, img, status FROM COUPON";
	private static final String GET_Cust_ByCupId = "SELECT  CUST_ID, COP_ID, GETDATE,DISCOUNT, STATUS FROM CUST_COUPON where COP_ID = ? order by CUST_ID";

//	private static final String UPDATE_BYENDDATE =
//			"UPDATE COUPON SET status='下架' WHERE DATE(enddate)+1 = current_date()";

	@Override
	public int insert(CouponVO couponVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);

			ps.setString(1, couponVO.getCopName());
			ps.setString(2, couponVO.getIntroduce());
			ps.setInt(3, couponVO.getDiscount());
			ps.setTimestamp(4, couponVO.getStartdate());
			ps.setTimestamp(5, couponVO.getEnddate());
			ps.setString(6, couponVO.getImg());

			count = ps.executeUpdate();
			System.out.println("success" + count);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
		return count;
	}

	@Override
	public int update(CouponVO couponVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);

			ps.setString(1, couponVO.getCopName());
			ps.setString(2, couponVO.getIntroduce());
			ps.setInt(3, couponVO.getDiscount());
			ps.setTimestamp(4, couponVO.getStartdate());
			ps.setTimestamp(5, couponVO.getEnddate());
			ps.setString(6, couponVO.getImg());
			ps.setInt(7, couponVO.getCopId());

			count = ps.executeUpdate();
			System.out.println("success" + count);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
		return count;
	}

	@Override
	public int delete(Integer copId) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE);

			ps.setInt(1, copId);

			count = ps.executeUpdate();
			System.out.println("success" + count);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
		return count;
	}

	@Override
	public CouponVO queryById(Integer copId) {

		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYCOPID);

			ps.setInt(1, copId);
			rs = ps.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCopId(rs.getInt(1));
				couponVO.setCopName(rs.getString(2));
				couponVO.setIntroduce(rs.getString(3));
				couponVO.setDiscount(rs.getInt(4));
				couponVO.setStartdate(rs.getTimestamp(5));
				couponVO.setEnddate(rs.getTimestamp(6));
				couponVO.setImg(rs.getString(7));
				couponVO.setStatus(rs.getString(8));
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
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
		return couponVO;
	}

	@Override
	public List<CouponVO> queryCouponByCopName(String copName) {

		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYCOPNAME);

			ps.setString(1, "%" + copName + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCopId(rs.getInt(1));
				couponVO.setCopName(rs.getString(2));
				couponVO.setIntroduce(rs.getString(3));
				couponVO.setDiscount(rs.getInt(4));
				couponVO.setStartdate(rs.getTimestamp(5));
				couponVO.setEnddate(rs.getTimestamp(6));
				couponVO.setImg(rs.getString(7));
				couponVO.setStatus(rs.getString(8));
				list.add(couponVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<CouponVO> queryCouponByStatus(String status) {

		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
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
				couponVO = new CouponVO();
				couponVO.setCopId(rs.getInt(1));
				couponVO.setCopName(rs.getString(2));
				couponVO.setIntroduce(rs.getString(3));
				couponVO.setDiscount(rs.getInt(4));
				couponVO.setStartdate(rs.getTimestamp(5));
				couponVO.setEnddate(rs.getTimestamp(6));
				couponVO.setImg(rs.getString(7));
				list.add(couponVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<CouponVO> queryAll() {

		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();

			while (rs.next()) {
				couponVO = new CouponVO();
				couponVO.setCopId(rs.getInt(1));
				couponVO.setCopName(rs.getString(2));
				couponVO.setIntroduce(rs.getString(3));
				couponVO.setDiscount(rs.getInt(4));
				couponVO.setStartdate(rs.getTimestamp(5));
				couponVO.setEnddate(rs.getTimestamp(6));
				couponVO.setImg(rs.getString(7));
				couponVO.setStatus(rs.getString(8));
				list.add(couponVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

//	@Override
//	public int updateByEnddate() {
//		return 0;
//	}

	@Override
	public Set<CustCoupon> getCustByCopId(Integer copId) {

		Set<CustCoupon> set = new LinkedHashSet<CustCoupon>();
		CustCoupon custCop = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_Cust_ByCupId);
			pstmt.setInt(1, copId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				custCop = new CustCoupon();
				custCop.setCustId(rs.getInt(1));
				custCop.setCopId(rs.getInt(2));
				custCop.setGetdate(rs.getTimestamp(3));
				custCop.setDiscount(rs.getInt(4));
				custCop.setStatus(rs.getString(5));
				
				set.add(custCop); // Store the row in the vector
//				System.out.println(rs.getTimestamp(4));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return set;
	}

	public static void main(String[] args) {

		CouponJDBCDAO dao = new CouponJDBCDAO();

//		// 新增
//		CouponVO couponVO1 = new CouponVO();
//		couponVO1.setCopName("折抵");
//		couponVO1.setIntroduce("結帳時輸入編號");
//		couponVO1.setDiscount(100);
//		couponVO1.setStartdate(java.sql.Timestamp.valueOf("2022-04-15 08:00:00"));
//		couponVO1.setEnddate(java.sql.Timestamp.valueOf("2022-05-30 12:00:00"));
//		couponVO1.setImg("C:\\file_imgs\\h4.jpg");
//		dao.insert(couponVO1);
//
//		// 修改
//		CouponVO couponVO2 = new CouponVO();
//		couponVO2.setCopId(1);
//		couponVO2.setCopName("折抵優惠");
//		couponVO2.setIntroduce("結帳時輸入編號即可!!!!!!!!!!");
//		couponVO2.setDiscount(120);
//		couponVO2.setStartdate(java.sql.Timestamp.valueOf("2022-04-13 08:00:00"));
//		couponVO2.setEnddate(java.sql.Timestamp.valueOf("2022-05-30 13:00:00"));
//		couponVO2.setImg("C:\\file_imgs\\h0.jpg");
//		dao.update(couponVO2);
//
//		// 刪除
//		dao.delete(1);
//
//		// 根據優惠券編號查詢
//		CouponVO couponVO3 = dao.queryById(1);
//		System.out.println(couponVO3.toString());
//
//		// 根據優惠券名稱查詢
//		List<CouponVO> list1 = dao.queryCouponByCopName("折抵");
//		for (CouponVO cop : list1) {
//			System.out.println(cop.toString());
//		}
//
//		// 根據狀態查詢
//		List<CouponVO> list2 = dao.queryCouponByStatus("下架");
//		for (CouponVO cop : list2) {
//			System.out.println(cop.toString());
//		}
//
//		// 查詢全部
//		List<CouponVO> list3 = dao.queryAll();
//		for (CouponVO cop : list3) {
//			System.out.println(cop.toString());
//		}
		//根據copId查custId
		Set<CustCoupon> set = dao.getCustByCopId(1);
		for (CustCoupon aCustCoupon : set) {
			System.out.print(aCustCoupon.getCustId() + ",");
//			System.out.print(aCustCoupon.getCopId() + ",");
			System.out.print(aCustCoupon.getGetdate() + ",");
//			System.out.print(aCustCoupon.getDiscount() + ",");
//			System.out.print(aCustCoupon.getStatus() + ",");
		
//			System.out.println(aCustCoupon.toString());
		}
		
	}

}
