package com.taiwan.dao.employeeFunction.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taiwan.beans.EmployeeFunctionVO;
import com.taiwan.dao.employeeFunction.EmployeeFunctionDAO_interface;

public class EmployeeFunctionDAO implements EmployeeFunctionDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE(EMP_NAME,EMP_PASSWORD,)VALUES ( ?,?)";
	private static final String GET_ALL_STMT = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,EMP_STATUS,HIREDATE FROM EMPLOYEE order by EMP_ID";
	private static final String GET_ONE_STMT = "SELECT FUNC_ID, FUNC_NAME FROM EMPLOYEE_FUNCTION where FUNC_ID = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE_FUNCTION set FUNC_NAME=? where FUNC_ID = ?";

	@Override
	public void insert(EmployeeFunctionVO employeeFunctionVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei";
			String userid = "root";
			String passwd = "123456";

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, employeeFunctionVO.getFunc_name());

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
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
	public void update(EmployeeFunctionVO employeeFunctionVO) {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, employeeFunctionVO.getFunc_name());

			pstmt.executeUpdate();
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

	}

	@Override
	public EmployeeFunctionVO findByPrimaryKey(Integer func_id) {
		// TODO Auto-generated method stub

		EmployeeFunctionVO employeeFunctionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, func_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 銋迂� Domain objects
				employeeFunctionVO = new EmployeeFunctionVO();
				employeeFunctionVO.setFunc_id(rs.getInt("func_id"));
				employeeFunctionVO.setFunc_name(rs.getString("func_name"));

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
		return employeeFunctionVO;

	}

	@Override
	public List<EmployeeFunctionVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
