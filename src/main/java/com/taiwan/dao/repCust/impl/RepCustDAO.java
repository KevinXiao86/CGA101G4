package com.taiwan.dao.repCust.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.RepCustVO;
import com.taiwan.dao.repCust.RepCustDAO_interface;

import java.sql.Timestamp;



public class RepCustDAO implements RepCustDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String insert = "INSERT INTO Taiwan.REP_CUST (CUST_ID, CMP_ID, REP_CUST_REASON) VALUES (?, ?, ?) ";
	private static final String update = "UPDATE Taiwan.REP_CUST SET EMP_ID = ?, REP_CUST_STATUS = ?, REP_CUST_RESULT = ? WHERE REP_CUST_ID = ?";
	private static final String find = "SELECT * FROM Taiwan.REP_CUST ";
	private static final String findFrom = "SELECT * FROM Taiwan.REP_CUST where ";

	
	
	
	@Override
	public RepCustVO queryRepCustByRepCustId(Integer repCustId) {
		RepCustVO repCustVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "rep_cust_id = ? ");

			pstmt.setInt(1, repCustId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRepCustId(rs.getInt("REP_CUST_ID"));
				repCustVO.setCustId(rs.getInt("CUST_ID"));
				repCustVO.setCmpId(rs.getInt("CMP_ID"));
				repCustVO.setEmpId(rs.getInt("EMP_ID"));
				repCustVO.setRepCustReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRepCustDate(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setRepCustStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setRepCustResult(rs.getString("REP_CUST_RESULT"));
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
		return repCustVO;
	}

	@Override
	public List<RepCustVO> queryRepCustByCustId(Integer custId) {
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + " cust_id = ? ");
			pstmt.setInt(1, custId);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRepCustId(rs.getInt("REP_CUST_ID"));
				repCustVO.setCustId(rs.getInt("CUST_ID"));
				repCustVO.setCmpId(rs.getInt("CMP_ID"));
				repCustVO.setEmpId(rs.getInt("EMP_ID"));
				repCustVO.setRepCustReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRepCustDate(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setRepCustStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setRepCustResult(rs.getString("REP_CUST_RESULT"));

				list.add(repCustVO); // Store the row in the list
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
		return list;
	}


	@Override
	public List<RepCustVO> queryRepCustByEmpId(Integer empId) {
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "emp_id = ? ");
			pstmt.setInt(1, empId);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRepCustId(rs.getInt("REP_CUST_ID"));
				repCustVO.setCustId(rs.getInt("CUST_ID"));
				repCustVO.setCmpId(rs.getInt("CMP_ID"));
				repCustVO.setEmpId(rs.getInt("EMP_ID"));
				repCustVO.setRepCustReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRepCustDate(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setRepCustStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setRepCustResult(rs.getString("REP_CUST_RESULT"));

				list.add(repCustVO); // Store the row in the list
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
		return list;
	}

	@Override
	public List<RepCustVO> queryRepCustByRepCustDate(Timestamp startDate, Timestamp endDate) {
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "rep_cust_date between ? and ? ");
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);

			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRepCustId(rs.getInt("REP_CUST_ID"));
				repCustVO.setCustId(rs.getInt("CUST_ID"));
				repCustVO.setCmpId(rs.getInt("CMP_ID"));
				repCustVO.setEmpId(rs.getInt("EMP_ID"));
				repCustVO.setRepCustReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRepCustDate(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setRepCustStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setRepCustResult(rs.getString("REP_CUST_RESULT"));

				list.add(repCustVO); // Store the row in the list
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
		return list;
	}

	@Override
	public List<RepCustVO> queryRepCustByStatus(String status) {
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "REP_CUST_STATUS = ? ");
			pstmt.setString(1, status);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRepCustId(rs.getInt("REP_CUST_ID"));
				repCustVO.setCustId(rs.getInt("CUST_ID"));
				repCustVO.setCmpId(rs.getInt("CMP_ID"));
				repCustVO.setEmpId(rs.getInt("EMP_ID"));
				repCustVO.setRepCustReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRepCustDate(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setRepCustStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setRepCustResult(rs.getString("REP_CUST_RESULT"));

				list.add(repCustVO); // Store the row in the list
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
		return list;
	}
	
	@Override
	public List<RepCustVO> queryRepCustByCmpId(Integer cmpId) {
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "CMP_ID = ? ");
			pstmt.setInt(1, cmpId);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRepCustId(rs.getInt("REP_CUST_ID"));
				repCustVO.setCustId(rs.getInt("CUST_ID"));
				repCustVO.setCmpId(rs.getInt("CMP_ID"));
				repCustVO.setEmpId(rs.getInt("EMP_ID"));
				repCustVO.setRepCustReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRepCustDate(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setRepCustStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setRepCustResult(rs.getString("REP_CUST_RESULT"));

				list.add(repCustVO); // Store the row in the list
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
		return list;
	}

	@Override
	public List<RepCustVO> queryRepCustAll() {
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRepCustId(rs.getInt("REP_CUST_ID"));
				repCustVO.setCustId(rs.getInt("CUST_ID"));
				repCustVO.setCmpId(rs.getInt("CMP_ID"));
				repCustVO.setEmpId(rs.getInt("EMP_ID"));
				repCustVO.setRepCustReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRepCustDate(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setRepCustStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setRepCustResult(rs.getString("REP_CUST_RESULT"));

				list.add(repCustVO); // Store the row in the list
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
		return list;
	}

	@Override
	public Integer insertRepCust(RepCustVO repCustVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, repCustVO.getCustId());
			pstmt.setInt(2, repCustVO.getCmpId());
			pstmt.setString(3, repCustVO.getRepCustReason());

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
		return null;
	}

	@Override
	public RepCustVO updateRepCust(RepCustVO repCustVO) {
		// �B�z�|�����|
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);

			pstmt.setInt(1, repCustVO.getEmpId());
			pstmt.setString(2,repCustVO.getRepCustStatus());
			pstmt.setString(3, repCustVO.getRepCustResult());
			pstmt.setInt(4, repCustVO.getRepCustId());
		

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
		return repCustVO;
	}


	
	

}
