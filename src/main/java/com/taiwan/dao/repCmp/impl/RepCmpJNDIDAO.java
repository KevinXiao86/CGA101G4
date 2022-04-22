package com.taiwan.dao.repCmp.impl;

import java.sql.Connection;
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
			+ "REP_CMP_DATE ,STATUS,RESULT " + "FROM REP_CMP WHERE CUST_ID =?;";
	private static final String SET_REP_CMP = "INSERT INTO REP_CMP (CUST_ID,ROOM_ID,REASON) " + "VALUES(?,?,?);";
	private static final String DELETE_REP_CMP = "DELETE FROM REP_CMP WHERE REP_CMP_ID=?;";
	private static final String SET_REP_CMP_RESULT = "UPDATE REP_CMP SET EMP_ID=?,STATUS=?,RESULT=? WHERE REP_CMP_ID=?;";

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

			rs = ps.executeQuery();
			while (rs.next()) {
				RepCmpVO repCmpVO = new RepCmpVO();
				repCmpVO.setRepCmpId(rs.getInt("REP_CMP_ID"));
				repCmpVO.setCustId(rs.getInt("CUST_ID"));
				repCmpVO.setRoomId(rs.getInt("ROOM_ID"));
				repCmpVO.setEmpId(rs.getInt("EMP_ID"));
				repCmpVO.setReason(rs.getString("REASON"));
				repCmpVO.setStatus(rs.getString("STATUS"));
				repCmpVO.setResult(rs.getString("RESULT"));
				list.add(repCmpVO);
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
	public RepCmpVO queryRepCmpByRep_cmp_id(Integer rep_cmp_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepCmpVO> queryRepCmpByRoom_id(Integer room_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepCmpVO> queryRepCmpByEmp_id(Integer emp_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepCmpVO> queryRepCmpByRep_cmp_date(Timestamp startDate, Timestamp endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepCmpVO> queryRepCmpByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RepCmpVO> queryRepCmpAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateRepCmp(Integer rep_cmp_id, Integer emp_id, String status, String result) {
		// TODO Auto-generated method stub
		return null;
	}
}
