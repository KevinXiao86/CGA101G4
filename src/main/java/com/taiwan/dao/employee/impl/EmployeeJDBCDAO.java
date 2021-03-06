package com.taiwan.dao.employee.impl;

import java.sql.Connection;
import java.sql.Date;
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

import com.taiwan.beans.EmployeeVO;
import com.taiwan.dao.employee.EmployeeDAO_interface;

public class EmployeeJDBCDAO implements EmployeeDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE(EMP_NAME,EMP_PASSWORD,FUNC_ID)VALUES ( ?,?,?)";
	private static final String GET_ALL_STMT = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,FUNC_ID,EMP_STATUS,HIREDATE FROM EMPLOYEE order by EMP_ID";
	private static final String GET_ALL_DATE = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,FUNC_ID,EMP_STATUS,HIREDATE FROM EMPLOYEE order by EMP_ID";
	private static final String GET_ONE_STMT = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,FUNC_ID,EMP_STATUS,HIREDATE FROM EMPLOYEE where EMP_ID = ?";
	private static final String GET_ONE_NAME = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,FUNC_ID,EMP_STATUS,HIREDATE FROM EMPLOYEE where EMP_ID = ?";
	private static final String UPDATE_All = "UPDATE EMPLOYEE set EMP_NAME=? ,EMP_PASSWORD=?,FUNC_ID=?,EMP_STATUS=?,HIREDATE=?  where EMP_ID = ?" ;
	private static final String DELETE = "DELETE FROM EMPLOYEE WHERE EMP_ID = ? ";
	private static final String login = "SELECT EMP_ID,EMP_NAME,EMP_PASSWORD,FUNC_ID,EMP_STATUS,HIREDATE from EMPLOYEE where EMP_ID = ? and EMP_PASSWORD = ? ";
	private static final String GET_Emps_ByAuthority = "SELECT EMP_ID,EMP_NAME FROM EMPLOYEE where EMP_ID = ? order by EMP_ID";

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
//			pstmt.setDate(3, employeeVO.getHiredate());???????????????
			pstmt.setInt(3, employeeVO.getFuncID());
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
			pstmt.setInt(3, employeeVO.getFuncID());
			pstmt.setString(4, employeeVO.getEmpStatus());			
			pstmt.setDate(5, employeeVO.getHiredate());
			pstmt.setInt(6, employeeVO.getEmpId());
			
			count = pstmt.executeUpdate();
			System.out.println("success ??????" + count);
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
				employeeVO.setFuncID(rs.getInt(4));
				employeeVO.setEmpStatus(rs.getString(5));
				employeeVO.setHiredate(rs.getDate(6));
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
				// empVO ????????? Domain objects

				employeeVO = new EmployeeVO();
				employeeVO.setEmpId(rs.getInt(1));
				employeeVO.setEmpName(rs.getString(2));
				employeeVO.setEmpPassword(rs.getString(3));
				employeeVO.setFuncID(rs.getInt(4));
				employeeVO.setEmpStatus(rs.getString(5));
				employeeVO.setHiredate(rs.getDate(6));
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

	@Override

	public void delete(Integer empId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, empId);

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
	public EmployeeVO login(Integer empId, String empPassword) {
		Connection con = null;
		PreparedStatement pstmt = null;
		EmployeeVO employeeVO = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(login);

			pstmt.setInt(1, empId);
			pstmt.setString(2, empPassword);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				employeeVO = new EmployeeVO();
				employeeVO.setEmpId(rs.getInt(1));
				employeeVO.setEmpName(rs.getString(2));
				employeeVO.setEmpPassword(rs.getString(3));
				employeeVO.setFuncID(rs.getInt(4));
				employeeVO.setEmpStatus(rs.getString(5));
				employeeVO.setHiredate(rs.getDate(6));
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
	public Set<EmployeeVO> getEmployeeNameByAuthority(Integer empId) {

			Set<EmployeeVO> set = new LinkedHashSet<EmployeeVO>();
			EmployeeVO employeeVO = null;
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
		
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_Emps_ByAuthority);
				pstmt.setInt(1, empId);
				rs = pstmt.executeQuery();
		
				while (rs.next()) {
					employeeVO = new EmployeeVO();
					employeeVO.setEmpId(rs.getInt("empId"));
					employeeVO.setEmpName(rs.getString("empName"));
					set.add(employeeVO); // Store the row in the vector
				}
		
				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
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
		EmployeeJDBCDAO dao = new EmployeeJDBCDAO();

////		 ??????
//		EmployeeVO employee01 = new EmployeeVO();
//		employee01.setEmpName("??????");
//		employee01.setEmpPassword("12233322");
//		System.out.println("????????????");
//		dao.insert(employee01);
//			??????
//		dao.delete(30007);

		// ??????
//		EmployeeVO employee02 = new EmployeeVO();

//		employee02.setEmpId(30000);
//		employee02.setEmpName("??????");
//		employee02.setEmpPassword("132333");
//		employee02.setstatus("??????");
//		employee02.setHiredate(java.sql.Date.valueOf("2022-04-08"));
//		dao.update(employee02);

//		// ??????
//		EmployeeVO employee03 = dao.findByPrimaryKey(30000);
//		System.out.println(employee03.getEmp_id());
//		System.out.println(employee03.getEmp_name() );
//		System.out.println(employee03.getEmp_password());
//		System.out.println(employee03.getEmp_status());
//		System.out.println(employee03.getdate() );
//		System.out.println(employee03.toString() );
//		System.out.println("---------------------");

		// ??????
//		List<EmployeeVO> list = dao.getAll();
//		for (EmployeeVO aEmp : list) {
//			System.out.println(aEmp.getEmpId() );
//			System.out.println(aEmp.getEmpName());
//			System.out.println(aEmp.getEmpPassword());
//			System.out.println(aEmp.getEmpStatus());
//			System.out.println(aEmp.getHiredate());
//			System.out.println("---------------------");
//		}
//
		
		
	}



}
