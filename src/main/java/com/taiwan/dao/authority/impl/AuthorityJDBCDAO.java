package com.taiwan.dao.authority.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.AuthorityVO;
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
			pstmt.setInt(1, authorityVO.getEmp_id());
			pstmt.setInt(2, authorityVO.getFunc_id());
			count = pstmt.executeUpdate();
			System.out.println("success �s�W" + count);
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
	public void update(AuthorityVO authorityVO, Integer newfunc_id) {
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, newfunc_id);
			pstmt.setInt(2, authorityVO.getFunc_id());
			pstmt.setInt(3, authorityVO.getEmp_id());

			count = pstmt.executeUpdate();
			System.out.println("success �ק�" + count);
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
	public void delete(Integer func_id, Integer emp_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, func_id);
			pstmt.setInt(2, emp_id);

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
	public List<AuthorityVO> findByFunction(Integer func_id) {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_FUNC);
			pstmt.setInt(1, func_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setFunc_id(rs.getInt("func_id"));
				authorityVO.setEmp_id(rs.getInt("emp_id"));
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
	public List<AuthorityVO> findById(Integer emp_id) {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ID);
			pstmt.setInt(1, emp_id);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				authorityVO = new AuthorityVO();
				authorityVO.setFunc_id(rs.getInt("func_id"));
				authorityVO.setEmp_id(rs.getInt("emp_id"));
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
				authorityVO.setEmp_id(rs.getInt("emp_id"));
				authorityVO.setFunc_id(rs.getInt("func_id"));
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
				authorityVO.setEmp_id(rs.getInt("emp_id"));
				authorityVO.setFunc_id(rs.getInt("func_id"));
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

	public static void main(String[] args) {
		AuthorityJDBCDAO dao = new AuthorityJDBCDAO();
		// �s�W
//				AuthorityVO authority1 = new AuthorityVO();
//				authority1.setEmp_id(30000);
//				authority1.setFunc_id(4);
//				System.out.println("�s�ئ��\");
//				dao.insert(authority1);
		// �R��

		dao.delete(5,30000);
		List<AuthorityVO> list1 = dao.findById(30000);
		for (AuthorityVO aEmp : list1) {
			System.out.println(aEmp.getEmp_id());
			System.out.println(aEmp.getFunc_id());
			System.out.println(aEmp.toString());
			System.out.println("---------------------");
		}

		List<AuthorityVO> list2 = dao.AllFunctuon();
		for (AuthorityVO aEmp : list2) {
			System.out.print(aEmp.getEmp_id() + ",");
			System.out.println(aEmp.getFunc_id());
			System.out.println("---------------------");
	}

		List<AuthorityVO> list3 = dao.AllID();
		for (AuthorityVO aEmp : list3) {
			System.out.print(aEmp.getEmp_id() + ",");
			System.out.println(aEmp.getFunc_id());
			System.out.println("---------------------");
		}
	}

}
