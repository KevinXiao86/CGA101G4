package com.taiwan.dao.employeeFunction.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.taiwan.beans.EmployeeFunctionVO;
import com.taiwan.beans.EmployeeVO;
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
	private static final String DELETE = "DELETE FROM EMPLOYEE_FUNCTION WHERE FUNC_ID = ? ";
	private static final String GET_All_Emp_ByFuncID = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,FUNC_ID,EMP_STATUS,HIREDATE FROM EMPLOYEE where FUNC_ID=? order by EMP_ID";
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
			pstmt.setString(1, employeeFunctionVO.getFuncName());
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
			
			pstmt.setString(1, employeeFunctionVO.getFuncName());
			pstmt.setInt(2, employeeFunctionVO.getFuncId());
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
	public EmployeeFunctionVO findByPrimaryKey(Integer funcId) {
		// TODO Auto-generated method stub

		EmployeeFunctionVO employeeFunctionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, funcId);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				employeeFunctionVO = new EmployeeFunctionVO();
				employeeFunctionVO.setFuncId(rs.getInt(1));
				employeeFunctionVO.setFuncName(rs.getString(2));

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
				employeeFunctionVO.setFuncId(rs.getInt(1));
				employeeFunctionVO.setFuncName(rs.getString(2));
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

	@Override
	public void delete(Integer funcId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, funcId);
		

			count = pstmt.executeUpdate();
			System.out.println("delete sucess" + count);

			// Handle any driver errors
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
	public Set<EmployeeVO> getEmpsByfuncId(Integer funcId) {

		Set<EmployeeVO> set = new LinkedHashSet<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_All_Emp_ByFuncID);
			pstmt.setInt(1, funcId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmpId(rs.getInt(1));
				employeeVO.setEmpName(rs.getString(2));
				employeeVO.setEmpPassword(rs.getString(3));
				employeeVO.setFuncID(rs.getInt(4));
				employeeVO.setEmpStatus(rs.getString(5));
				employeeVO.setHiredate(rs.getDate(6));
				set.add(employeeVO); // Store the row in the vector
				System.out.println(set);
			}
		}

		// Handle any driver errors
		catch (ClassNotFoundException e) {
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
		return set;
	}

	public static void main(String[] args) {

		EmployeeFunctionJDBCDAO dao = new EmployeeFunctionJDBCDAO();
//
//		// 新增
//		EmployeeFunctionVO employeeFunctionVO1 = new EmployeeFunctionVO();
//		employeeFunctionVO1.setFuncName("會員管理員");
//		dao.insert(employeeFunctionVO1);
//
//		// 修改
//		EmployeeFunctionVO employeeFunctionVO2 = new EmployeeFunctionVO();
//		
//		employeeFunctionVO2.setFunc_id(2);
//		employeeFunctionVO2.setFunc_name("票券管理員");
//		dao.update(employeeFunctionVO2);
//
//		// 查詢
//		EmployeeFunctionVO employeeFunctionVO3 = dao.findByPrimaryKey(4);
//		System.out.print(employeeFunctionVO3.getFuncId() + ",");
//		System.out.println(employeeFunctionVO3.getFuncName() );
//		System.out.println("---------------------");
//
//		// 查詢
//		List<EmployeeFunctionVO> list = dao.getAll();
//		for (EmployeeFunctionVO aEmp : list) {
//			System.out.print(aEmp.getFuncId() + ",");
//			System.out.print(aEmp.getFuncName() + ",");
//			System.out.println();
//		}
		Set<EmployeeVO> set = dao.getEmpsByfuncId(1);
		for (EmployeeVO aEmp : set) {
			System.out.print(aEmp.getEmpName() + ",");
		}
	}
}
