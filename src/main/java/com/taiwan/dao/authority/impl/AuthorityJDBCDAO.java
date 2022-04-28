package com.taiwan.dao.authority.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.taiwan.beans.AuthorityVO;
import com.taiwan.beans.EmployeeVO;
import com.taiwan.dao.authority.AuthorityDAO_interface;

public class AuthorityJDBCDAO implements AuthorityDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT_STMT = "INSERT INTO AUTHORITY (EMP_ID, FUNC_ID) VALUES ( ?,?)";
	private static final String GET_ALL_FUNC = "SELECT FUNC_ID,EMP_ID FROM AUTHORITY order by FUNC_ID";
	private static final String GET_ALL_ID = "SELECT FUNC_ID,EMP_ID FROM AUTHORITY order by EMP_ID";
	private static final String GET_ONE_FUNC = "SELECT FUNC_ID, EMP_ID FROM AUTHORITY where FUNC_ID = ?";
	private static final String GET_ONE_ID = "SELECT FUNC_ID, EMP_ID FROM AUTHORITY where EMP_ID = ?";
	private static final String UPDATE = "UPDATE AUTHORITY set FUNC_ID=? where FUNC_ID = ? and EMP_ID = ?";
	private static final String DELETE = "DELETE FROM AUTHORITY WHERE FUNC_ID = ? and EMP_ID = ?";
	private static final String GET_All_Emp_ByAuthority = "SELECT empId,empName FROM employee  order by empId";

	@Override
	public void insert(AuthorityVO authorityVO) {
		// TODO Auto-generated method stub
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, authorityVO.getEmpId());
			pstmt.setInt(2, authorityVO.getFuncId());
			count = pstmt.executeUpdate();
			System.out.println("success 幾" + count + "筆");
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
	public void update(AuthorityVO authorityVO, Integer newfuncId) {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, newfuncId);
			pstmt.setInt(2, authorityVO.getFuncId());
			pstmt.setInt(3, authorityVO.getEmpId());

			count = pstmt.executeUpdate();
			System.out.println("success 幾" + count + "筆");
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
	public void delete(Integer funcId, Integer empId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, funcId);
			pstmt.setInt(2, empId);

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
	public List<AuthorityVO> findByFunction(Integer funcId) {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FUNC);
			pstmt.setInt(1, funcId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setFuncId(rs.getInt("funcId"));
				authorityVO.setEmpId(rs.getInt("empId"));
				list.add(authorityVO);
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
	public List<AuthorityVO> findById(Integer empId) {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ID);
			pstmt.setInt(1, empId);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setFuncId(rs.getInt("funcId"));
				authorityVO.setEmpId(rs.getInt("empId"));
				list.add(authorityVO);
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
	public List<AuthorityVO> AllFunctuon() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_FUNC);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setEmpId(rs.getInt(1));
				authorityVO.setFuncId(rs.getInt(2));
				list.add(authorityVO);
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
	public List<AuthorityVO> AllID() {

		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ID);

			rs = pstmt.executeQuery();
			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setEmpId(rs.getInt(1));
				authorityVO.setFuncId(rs.getInt(2));
				list.add(authorityVO);
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
	public Set<EmployeeVO> getEmployeeByAuthority(Integer empId) {

		Set<EmployeeVO> set = new LinkedHashSet<EmployeeVO>();
		EmployeeVO employeeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_All_Emp_ByAuthority);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				employeeVO = new EmployeeVO();
				employeeVO.setEmpId(rs.getInt("empId"));
				employeeVO.setEmpName(rs.getString("empName"));
				set.add(employeeVO); // Store the row in the vector
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

	@Override
	public Set<EmployeeVO> getFunctionByAuthority(Integer funcId) {
		// TODO Auto-generated method stub
		return null;
	}

//	public static void main(String[] args) {
//		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
//		// 新增
//				AuthorityVO authority1 = new AuthorityVO();
//				authority1.setEmpId(30000);
//				authority1.setFuncId(3);
//				System.out.println("");
//				dao.insert(authority1);
////		 刪除
//
//		dao.delete(5, 30000);
//		List<AuthorityVO> list1 = dao.findById(30000);
//		for (AuthorityVO aEmp : list1) {
//			System.out.println(aEmp.getEmpId());
//			System.out.println(aEmp.getFuncId());
//			System.out.println(aEmp.toString());
//			System.out.println("---------------------");
//		}
//
//		List<AuthorityVO> list2 = dao.AllFunctuon();
//		for (AuthorityVO aEmp : list2) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.println(aEmp.getFuncId());
//			System.out.println("---------------------");
//		}
//
//		List<AuthorityVO> list3 = dao.AllID();
//		for (AuthorityVO aEmp : list3) {
//			System.out.print(aEmp.getEmpId() + ",");
//			System.out.println(aEmp.getFuncId());
//			System.out.println("---------------------");
//		}

}
