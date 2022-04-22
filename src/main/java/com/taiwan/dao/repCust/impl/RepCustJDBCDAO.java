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



public class RepCustJDBCDAO implements RepCustDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String insert = "INSERT INTO Taiwan.REP_CUST (CUST_ID, CMP_ID, REP_CUST_REASON) VALUES (?, ?, ?) ";
	private static final String update = "UPDATE Taiwan.REP_CUST SET EMP_ID = ?, REP_CUST_STATUS = ?, REP_CUST_REP_CUST_RESULT = ? WHERE REP_CUST_ID = ?";
	private static final String find = "SELECT * FROM Taiwan.REP_CUST ";
	private static final String findFrom = "SELECT * FROM Taiwan.REP_CUST where ";

	
	
	
	@Override
	public RepCustVO queryRepCustByRep_cust_id(Integer rep_cust_id) {
		// �ھڬy�����d�����|
		RepCustVO repCustVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "rep_cust_id = ? ");

			pstmt.setInt(1, rep_cust_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCustVO = new RepCustVO();
				repCustVO.setRep_cust_id(rs.getInt("REP_CUST_ID"));
				repCustVO.setCust_id(rs.getInt("CUST_ID"));
				repCustVO.setCmp_id(rs.getInt("CMP_ID"));
				repCustVO.setEmp_id(rs.getInt("EMP_ID"));
				repCustVO.setReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRep_cust_date(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setResult(rs.getString("REP_CUST_RESULT"));
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
	public List<RepCustVO> queryRepCustByCust_id(Integer cust_id) {
		// �ھڷ|��ID�d�����|
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "cust_id = ? ");
			pstmt.setInt(1, cust_id);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repCustVO �]�٬� Domain objects
				repCustVO = new RepCustVO();
				repCustVO.setRep_cust_id(rs.getInt("REP_CUST_ID"));
				repCustVO.setCust_id(rs.getInt("CUST_ID"));
				repCustVO.setCmp_id(rs.getInt("CMP_ID"));
				repCustVO.setEmp_id(rs.getInt("EMP_ID"));
				repCustVO.setReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRep_cust_date(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setResult(rs.getString("REP_CUST_RESULT"));

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
	public List<RepCustVO> queryRepCustByEmp_id(Integer emp_id) {
		// �ھں޲z��ID�d�����|
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "emp_id = ? ");
			pstmt.setInt(1, emp_id);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repCustVO �]�٬� Domain objects
				repCustVO = new RepCustVO();
				repCustVO.setRep_cust_id(rs.getInt("REP_CUST_ID"));
				repCustVO.setCust_id(rs.getInt("CUST_ID"));
				repCustVO.setCmp_id(rs.getInt("CMP_ID"));
				repCustVO.setEmp_id(rs.getInt("EMP_ID"));
				repCustVO.setReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRep_cust_date(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setResult(rs.getString("REP_CUST_RESULT"));

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
	public List<RepCustVO> queryRepCustByRep_cust_date(Timestamp startDate, Timestamp endDate) {
		// �ھڤ���d�����|
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
				// repCustVO �]�٬� Domain objects
				repCustVO = new RepCustVO();
				repCustVO.setRep_cust_id(rs.getInt("REP_CUST_ID"));
				repCustVO.setCust_id(rs.getInt("CUST_ID"));
				repCustVO.setCmp_id(rs.getInt("CMP_ID"));
				repCustVO.setEmp_id(rs.getInt("EMP_ID"));
				repCustVO.setReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRep_cust_date(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setResult(rs.getString("REP_CUST_RESULT"));

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
		// �ھڪ��A�d�����|
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
				// repCustVO �]�٬� Domain objects
				repCustVO = new RepCustVO();
				repCustVO.setRep_cust_id(rs.getInt("REP_CUST_ID"));
				repCustVO.setCust_id(rs.getInt("CUST_ID"));
				repCustVO.setCmp_id(rs.getInt("CMP_ID"));
				repCustVO.setEmp_id(rs.getInt("EMP_ID"));
				repCustVO.setReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRep_cust_date(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setResult(rs.getString("REP_CUST_RESULT"));

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
	public List<RepCustVO> queryRepCustByCmp_id(Integer cmp_id) {
		// �ھڼt��ID�d�����|
		List<RepCustVO> list = new ArrayList<RepCustVO>();
		RepCustVO repCustVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "REP_CUST_STATUS = ? ");
			pstmt.setInt(1, cmp_id);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repCustVO �]�٬� Domain objects
				repCustVO = new RepCustVO();
				repCustVO.setRep_cust_id(rs.getInt("REP_CUST_ID"));
				repCustVO.setCust_id(rs.getInt("CUST_ID"));
				repCustVO.setCmp_id(rs.getInt("CMP_ID"));
				repCustVO.setEmp_id(rs.getInt("EMP_ID"));
				repCustVO.setReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRep_cust_date(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setResult(rs.getString("REP_CUST_RESULT"));

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
		// �d�ߥ������|
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
				// repCustVO �]�٬� Domain objects
				repCustVO = new RepCustVO();
				repCustVO.setRep_cust_id(rs.getInt("REP_CUST_ID"));
				repCustVO.setCust_id(rs.getInt("CUST_ID"));
				repCustVO.setCmp_id(rs.getInt("CMP_ID"));
				repCustVO.setEmp_id(rs.getInt("EMP_ID"));
				repCustVO.setReason(rs.getString("REP_CUST_REASON"));
				repCustVO.setRep_cust_date(rs.getTimestamp("REP_CUST_DATE"));
				repCustVO.setStatus(rs.getString("REP_CUST_STATUS"));
				repCustVO.setResult(rs.getString("REP_CUST_RESULT"));

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
	public Integer insertRepCust(Integer cust_id, Integer cmp_id, String reason) {
		// �s�W�|�����|
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, cust_id);
			pstmt.setInt(2, cmp_id);
			pstmt.setString(3, reason);

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
	public Integer updateRepCust(Integer rep_cust_id,Integer emp_id ,String status ,String result) {
		// �B�z�|�����|
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);

			pstmt.setInt(1, emp_id);
			pstmt.setString(2,status);
			pstmt.setString(3, result);
			pstmt.setInt(4, rep_cust_id);
		

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


	
	

}
