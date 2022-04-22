package com.taiwan.dao.repCmp.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.RepCmpVO;
import com.taiwan.dao.repCmp.RepCmpDao_interface;

public class RepCmpJDBCDAO implements RepCmpDao_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String GET_PEP_CMP_BY_CUST_ID = "SELECT REP_CMP_ID ,CUST_ID ,ROOM_ID ,EMP_ID ,REASON,"
			+ "REP_CMP_DATE ,STATUS,RESULT " + "FROM REP_CMP WHERE CUST_ID =?;";
	private static final String SET_REP_CMP = "INSERT INTO REP_CMP (CUST_ID,ROOM_ID,REASON) " + "VALUES(?,?,?);";
	private static final String DELETE_REP_CMP = "DELETE FROM REP_CMP WHERE REP_CMP_ID=?;";
	private static final String SET_REP_CMP_RESULT = "UPDATE REP_CMP SET EMP_ID=?,STATUS=?,RESULT=? WHERE REP_CMP_ID=?;";
	private static final String insert = "INSERT INTO Taiwan.REP_CMP (CUST_ID, ROOM_ID, REASON) VALUES (?, ?, ?) ";
	private static final String update = "UPDATE Taiwan.REP_CMP SET EMP_ID = ?, STATUS = ?, RESULT = ? WHERE REP_CMP_ID = ?";
	private static final String find = "SELECT * FROM Taiwan.REP_CMP order by DESC REP_CMP_DATE ";
	private static final String findFrom = "SELECT * FROM Taiwan.REP_CMP where ";

	@Override
	public List<RepCmpVO> getRepCmpByCustId(Integer custId) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_PEP_CMP_BY_CUST_ID);
			ps.setInt(1, custId);
			rs = ps.executeQuery();

			while (rs.next()) {
				RepCmpVO repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("Rep_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setRoomId(rs.getInt("ROOM_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setEmpId(rs.getInt("EMP_Id"));
				repCmpVO.setRepCmpDate(rs.getTimestamp("REP_CMP_DATE"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));
				list.add(repCmpVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public void setRepCmp(RepCmpVO repCmp) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(SET_REP_CMP);
			ps.setInt(1, repCmp.getCustId());
			ps.setInt(2, repCmp.getRoomId());
			ps.setString(3, repCmp.getReason());

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
	}

	@Override
	public void deleteRepCmp(Integer repCmpId) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE_REP_CMP);
			ps.setInt(1, repCmpId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}

	}

	@Override
	public void setRepCmpResult(Integer repCmpId, Integer empId, String status, String result) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(SET_REP_CMP_RESULT);
			ps.setInt(1, empId);
			ps.setString(2, status);
			ps.setString(3, result);
			ps.setInt(4, repCmpId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}

	}

	@Override
	public RepCmpVO queryRepCmpByRep_cmp_id(Integer rep_cmp_id) {
		RepCmpVO repCmpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "rep_cmp_id = ? ");

			pstmt.setInt(1, rep_cmp_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("REP_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setEmpId(rs.getInt("ROOM_ID"));
				repCmpVO.setEmpId(rs.getInt("EMP_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setRepCmpDate(rs.getTimestamp("REP_CMP_DATE"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));
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
		return repCmpVO;
	
	}

	@Override
	public List<RepCmpVO> queryRepCmpByRoom_id(Integer room_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepCmpVO> queryRepCmpByEmp_id(Integer emp_id) {
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "room_id = ? ");
			pstmt.setInt(1, emp_id);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repCmpVO �]�٬� Domain objects
				repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("REP_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setEmpId(rs.getInt("ROOM_ID"));
				repCmpVO.setEmpId(rs.getInt("EMP_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setRepCmpDate(rs.getTimestamp("REP_CMP_DATE"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));

				list.add(repCmpVO); // Store the row in the list
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
	public List<RepCmpVO> queryRepCmpByRep_cmp_date(Timestamp startDate, Timestamp endDate) {
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "rep_cmp_date between ? and ? ");
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);

			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repCmpVO �]�٬� Domain objects
				repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("REP_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setEmpId(rs.getInt("ROOM_ID"));
				repCmpVO.setEmpId(rs.getInt("EMP_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setRepCmpDate(rs.getTimestamp("REP_CMP_DATE"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));

				list.add(repCmpVO); // Store the row in the list
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
	public List<RepCmpVO> queryRepCmpByStatus(String status) {
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(findFrom + "status = ? ");
			pstmt.setString(1, status);
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// repCmpVO �]�٬� Domain objects
				repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("REP_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setEmpId(rs.getInt("ROOM_ID"));
				repCmpVO.setEmpId(rs.getInt("EMP_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setRepCmpDate(rs.getTimestamp("REP_CMP_DATE"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));

				list.add(repCmpVO); // Store the row in the list
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
	public List<RepCmpVO> queryRepCmpAll() {
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(find);
			

			rs = pstmt.executeQuery();
			List<RepCmpVO> list = new ArrayList<RepCmpVO>();
			while (rs.next()) {
				// repCmpVO �]�٬� Domain objects
				repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("REP_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setEmpId(rs.getInt("ROOM_ID"));
				repCmpVO.setEmpId(rs.getInt("EMP_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setRepCmpDate(rs.getTimestamp("REP_CMP_DATE"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));

				list.add(repCmpVO); // Store the row in the list
			}
			
			return list;
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
	}

	@Override
	public Integer updateRepCmp(Integer rep_cmp_id, Integer emp_id, String status, String result) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);

			pstmt.setInt(1, emp_id);
			pstmt.setString(2,status);
			pstmt.setString(3, result);
			pstmt.setInt(4, rep_cmp_id);
		

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
