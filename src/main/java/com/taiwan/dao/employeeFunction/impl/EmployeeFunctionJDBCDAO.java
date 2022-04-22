package com.taiwan.dao.employeeFunction.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taiwan.beans.EmployeeFunctionVO;
import com.taiwan.dao.employeeFunction.EmployeeFunctionDAO_interface;

public class EmployeeFunctionJDBCDAO implements EmployeeFunctionDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE_FUNCTION (FUNC_NAME) VALUES ( ?)";
	private static final String GET_ALL_STMT = "SELECT FUNC_ID, FUNC_NAME FROM EMPLOYEE_FUNCTION order by FUNC_ID";
	private static final String GET_ONE_STMT = "SELECT FUNC_ID, FUNC_NAME FROM EMPLOYEE_FUNCTION where FUNC_ID = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE_FUNCTION set FUNC_NAME=? where FUNC_ID = ?";

	@Override
	public void insert(EmployeeFunctionVO employeeFunctionVO) {
		// TODO Auto-generated method stub
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, employeeFunctionVO.getFunc_name());
			count = pstmt.executeUpdate();
			System.out.println("success 新增" + count);
			// Handle any SQL errors
		} catch (

		ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public void update(EmployeeFunctionVO employeeFunctionVO) {
		// TODO Auto-generated method stub
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, employeeFunctionVO.getFunc_name());
			pstmt.setInt(2, employeeFunctionVO.getFunc_id());
			count = pstmt.executeUpdate();
			System.out.println("success 修改" + count);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, func_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				employeeFunctionVO = new EmployeeFunctionVO();
				employeeFunctionVO.setFunc_id(rs.getInt("func_id"));
				employeeFunctionVO.setFunc_name(rs.getString("func_name"));

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
		return employeeFunctionVO;
	}

	@Override
	public List<EmployeeFunctionVO> getAll() {
		List<EmployeeFunctionVO> list = new ArrayList<EmployeeFunctionVO>();
		EmployeeFunctionVO employeeFunctionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects

				employeeFunctionVO = new EmployeeFunctionVO();
				employeeFunctionVO.setFunc_id(rs.getInt("func_id"));
				employeeFunctionVO.setFunc_name(rs.getString("func_name"));
				list.add(employeeFunctionVO); // Store the row in the list
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

		EmployeeFunctionJDBCDAO dao = new EmployeeFunctionJDBCDAO();

		// 新增
		EmployeeFunctionVO employeeFunctionVO1 = new EmployeeFunctionVO();
		employeeFunctionVO1.setFunc_name("會員管理員");
		dao.insert(employeeFunctionVO1);

//		// 修改
//		EmployeeFunctionVO employeeFunctionVO2 = new EmployeeFunctionVO();
//		
//		employeeFunctionVO2.setFunc_id(2);
//		employeeFunctionVO2.setFunc_name("票券管理員");
//		dao.update(employeeFunctionVO2);

		// 查詢
		EmployeeFunctionVO employeeFunctionVO3 = dao.findByPrimaryKey(4);
		System.out.print(employeeFunctionVO3.getFunc_id() + ",");
		System.out.println(employeeFunctionVO3.getFunc_name() );
		System.out.println("---------------------");

		// 查詢
		List<EmployeeFunctionVO> list = dao.getAll();
		for (EmployeeFunctionVO aEmp : list) {
			System.out.print(aEmp.getFunc_id() + ",");
			System.out.print(aEmp.getFunc_name() + ",");
			System.out.println();
		}
	}
}
