package com.taiwan.dao.repCmp.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taiwan.beans.RepCmpVO;
import com.taiwan.dao.repCmp.RepCmpDao_interface;

public class RepCmpJNDIDAO implements RepCmpDao_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}
	private static final String GET_PEP_CMP_BY_CUST_ID = "SELECT REP_CMP_ID ,CUST_ID ,ROOM_ID ,EMP_ID ,REASON,"
			+ "REP_CMP_DATE ,STATUS,RESULT " + "FROM REP_CMP WHERE CUST_ID =? ORDER BY REP_CMP_DATE DESC;";
	private static final String SET_REP_CMP = "INSERT INTO REP_CMP (CUST_ID,ROOM_ID,REASON) " + "VALUES(?,?,?);";
	private static final String DELETE_REP_CMP = "DELETE FROM REP_CMP WHERE REP_CMP_ID=?;";
	private static final String SET_REP_CMP_RESULT = "UPDATE REP_CMP SET EMP_ID=?,STATUS=?,RESULT=? WHERE REP_CMP_ID=?;";

	private static final String insert = "INSERT INTO Taiwan.REP_CMP (CUST_ID, ROOM_ID, REASON) VALUES (?, ?, ?) ";
	private static final String update = "UPDATE Taiwan.REP_CMP SET EMP_ID = ?, STATUS = ?, RESULT = ? WHERE REP_CMP_ID = ?";
	private static final String find = "SELECT * FROM Taiwan.REP_CMP order by DESC REP_CMP_DATE ";
	private static final String findFrom = "SELECT * FROM Taiwan.REP_CMP where ";

	@Override
	public List<RepCmpVO> getRepCmpByCustId(Integer custId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_PEP_CMP_BY_CUST_ID);
			ps.setInt(1, custId);
			System.out.println("我進來JNDI囉!只是還沒到while裡面");
			rs = ps.executeQuery();
			while (rs.next()) {
				RepCmpVO repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("REP_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setRoomId(rs.getInt("ROOM_ID"));
				repCmpVO.setEmpId(rs.getInt("EMP_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setRepCmpDate(rs.getTimestamp("REP_CMP_DATE"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));
				list.add(repCmpVO);
				System.out.println("我在JNDI層while迴圈裡:" + repCmpVO);
			}
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}

		}
		return list;
	}

	@Override
	public void setRepCmp(RepCmpVO repCmp) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SET_REP_CMP);
			ps.setInt(1, repCmp.getCustId());
			ps.setInt(2, repCmp.getRoomId());
			ps.setString(3, repCmp.getReason());

			int count = ps.executeUpdate();
			System.out.println(count + "success");
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void deleteRepCmp(Integer repCmpId) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(DELETE_REP_CMP);
			ps.setInt(1, repCmpId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void setRepCmpResult(Integer repCmpId, Integer empId, String status, String result) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SET_REP_CMP_RESULT);
			ps.setInt(1, empId);
			ps.setString(2, status);
			ps.setString(3, result);
			ps.setInt(4, repCmpId);

			int count = ps.executeUpdate();
			System.out.println(count + "success");
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
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	@Override
	public RepCmpVO queryRepCmpByRepCmpId(Integer repCmpId) {
		RepCmpVO repCmpVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(findFrom + "rep_cmp_id = ? ");

			pstmt.setInt(1, repCmpId);

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
		return repCmpVO;

	}

	@Override
	public List<RepCmpVO> queryRepCmpByRoomId(Integer roomId) {
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(findFrom + "room_id = ? ");
			pstmt.setInt(1, roomId);

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

				list.add(repCmpVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<RepCmpVO> queryRepCmpByEmpId(Integer empId) {
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(findFrom + "emp_id = ? ");
			pstmt.setInt(1, empId);

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

				list.add(repCmpVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<RepCmpVO> queryRepCmpByRepCmpDate(Timestamp startDate, Timestamp endDate) {
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(findFrom + "rep_cmp_date between ? and ? ");
			pstmt.setTimestamp(1, startDate);
			pstmt.setTimestamp(2, endDate);

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

				list.add(repCmpVO); // Store the row in the list
			}

			// Handle any driver errors
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
	public List<RepCmpVO> queryRepCmpByStatus(String status) {
		List<RepCmpVO> list = new ArrayList<RepCmpVO>();
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
	public List<RepCmpVO> queryRepCmpAll() {
		RepCmpVO repCmpVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(find);

			rs = pstmt.executeQuery();
			List<RepCmpVO> list = new ArrayList<RepCmpVO>();
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

				list.add(repCmpVO); // Store the row in the list
			}

			return list;
			// Handle any driver errors
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
	}

	@Override
	public RepCmpVO updateRepCmp(RepCmpVO repCmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(update);

			pstmt.setInt(1, repCmpVO.getEmpId());
			pstmt.setString(2, repCmpVO.getStatus());
			pstmt.setString(3, repCmpVO.getResult());
			pstmt.setInt(4, repCmpVO.getRepCmpId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return repCmpVO;
	}

	@Override
	public RepCmpVO insertRepCmp(RepCmpVO repCmpVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(insert);

			pstmt.setInt(1, repCmpVO.getCustId());
			pstmt.setInt(2, repCmpVO.getRoomId());
			pstmt.setString(3, repCmpVO.getReason());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return repCmpVO;
	}
}
