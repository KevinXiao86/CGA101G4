package com.taiwan.dao.employee.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.EmployeeVO;
import com.taiwan.dao.employee.EmployeeDAO_interface;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE(EMP_NAME,EMP_PASSWORD)VALUES ( ?,?)";
	private static final String GET_ALL_STMT = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,EMP_STATUS,HIREDATE FROM EMPLOYEE order by EMP_ID";
	private static final String GET_ALL_DATE = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,EMP_STATUS,HIREDATE FROM EMPLOYEE order by EMP_ID";
	private static final String GET_ONE_STMT = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,EMP_STATUS,HIREDATE FROM EMPLOYEE where EMP_ID = ?";
	private static final String GET_ONE_NAME = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,EMP_STATUS,HIREDATE FROM EMPLOYEE where EMP_ID = ?";
	private static final String UPDATE_All = "UPDATE EMPLOYEE set EMP_NAME=? ,EMP_PASSWORD=?,EMP_STATUS=? where EMP_ID = ?";

	@Override
	public void insert(EmployeeVO employeeVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, employeeVO.getEmpName());
			pstmt.setString(2, employeeVO.getEmpPassword());
//			pstmt.setDate(3, employeeVO.getHiredate());　自動填寫

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver" + se.getMessage());
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
	public void update(EmployeeVO employeeVO) {
		// TODO Auto-generated method stub
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_All);

			pstmt.setString(1, employeeVO.getEmpName());
			pstmt.setString(2, employeeVO.getEmpPassword());
			pstmt.setString(3, employeeVO.getEmpStatus());
			pstmt.setInt(4, employeeVO.getEmpId());
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
	public EmployeeVO findByPrimaryKey(Integer empId) {
		// TODO Auto-generated method stub
		EmployeeVO employeeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				employeeVO = new EmployeeVO();
				employeeVO.setEmpId(rs.getInt(1));
				employeeVO.setEmpName(rs.getString(2));
				employeeVO.setEmpPassword(rs.getString(3));
				employeeVO.setEmpStatus(rs.getString(4));
				employeeVO.setHiredate(rs.getDate(5));
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
		return employeeVO;
	}

	@Override
//	public EmployeeVO findByName(String emp_name) {
//		EmployeeVO employeeVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_STMT);
//
//			pstmt.setString(1, "%" + emp_name+ "%");
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//
//				employeeVO = new EmployeeVO();
//				employeeVO.setEmp_id(rs.getInt("emp_id"));
//				employeeVO.setEmp_name(rs.getString("emp_name"));
//				employeeVO.setEmp_password(rs.getString("emp_password"));
//				employeeVO.setEmp_status(rs.getString("emp_status"));
//				employeeVO.setHiredate(rs.getDate("hiredate"));
//			}
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return employeeVO;
//	}

//	@Override
//	public EmployeeVO findByTimeName(Date HIREDATE) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
	public List<EmployeeVO> getAll() {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		EmployeeVO employeeVO = null;

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

				employeeVO = new EmployeeVO();
				employeeVO.setEmpId(rs.getInt(1));
				employeeVO.setEmpName(rs.getString(2));
				employeeVO.setEmpPassword(rs.getString(3));
				employeeVO.setEmpStatus(rs.getString(4));
				employeeVO.setHiredate(rs.getDate(5));
				list.add(employeeVO);
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
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

////		 新增
//		EmployeeVO employee01 = new EmployeeVO();
//		employee01.setEmp_name("汪達");
//		employee01.setEmp_password("12233322");
//		System.out.println("新建成功");
//		dao.insert(employee01);

//		// 修改
//		EmployeeVO employee02 = new EmployeeVO();
//		
//		employee02.setEmp_id(30000);
//		employee02.setEmp_name("謝美麗");
//		employee02.setEmp_password("122333");
//		employee02.setEmp_status("啟動");
//		dao.update(employee02);

//		// 查詢
//		EmployeeVO employee03 = dao.findByPrimaryKey(30000);
//		System.out.println(employee03.getEmp_id());
//		System.out.println(employee03.getEmp_name() );
//		System.out.println(employee03.getEmp_password());
//		System.out.println(employee03.getEmp_status());
//		System.out.println(employee03.getdate() );
//		System.out.println(employee03.toString() );
//		System.out.println("---------------------");

		// 查詢
		List<EmployeeVO> list = dao.getAll();
		for (EmployeeVO aEmp : list) {
			System.out.println(aEmp.getEmpId() );
			System.out.println(aEmp.getEmpName());
			System.out.println(aEmp.getEmpPassword());
			System.out.println(aEmp.getEmpStatus());
			System.out.println(aEmp.getHiredate());
			System.out.println("---------------------");
		}

	}

}
