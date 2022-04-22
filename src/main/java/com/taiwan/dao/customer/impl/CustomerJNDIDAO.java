package com.taiwan.dao.customer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.taiwan.beans.CustomerVO;
import com.taiwan.dao.customer.CustomerDAO_interface;

import ch.qos.logback.core.Context;


public class CustomerJNDIDAO implements CustomerDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = (Context) new InitialContext();
			ds = (DataSource) ((InitialContext) ctx).lookup("java:comp/env/jdbc/TestDB2");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private static final String SET_ALL = "INSERT INTO CUSTOMER(NAME,SEX,TEL,EMAIL,ADDRESS,ID_CARD,BIRTH,ACCOUNT,PASSWORD,CUST_USE,CUST_RIGHT) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SET_UPDATE = "UPDATE CUSTOMER "
			+ "SET NAME=?,SEX=?,TEL=?,EMAIL=?,ADDRESS=?,ID_CARD=?,BIRTH=?,ACCOUNT=?,PASSWORD=?,IMG=?,CUST_USE=?,CARD=?,CUST_RIGHT=? "
			+ "WHERE CUST_ID=?;";
	private static final String GET_ALL = "select cust_Id,name,sex,tel,email,address,id_Card ,birth,account,password,img,cust_Use ,card,cust_Right "
			+ "from CUSTOMER WHERE CUST_ID= ? ;";
	private static final String GET_PASSWORD = "SELECT PASSWORD FROM CUSTOMER WHERE ACCOUNT=?;";
	private static final String GET_EMAIL = "SELECT EMAIL FROM CUSTOMER WHERE ACCOUNT=?;";
	private static final String SET_CUST_RIGHT = "UPDATE CUSTOMER SET CUST_RIGHT =? WHERE CUST_ID =?;";
	private static final String GET_LOGIN = "SELECT CUST_ID FROM CUSTOMER WHERE ACCOUNT=? AND PASSWORD=?;";

	@Override
	public void setAll(CustomerVO customer) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SET_ALL);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getSex());
			ps.setString(3, customer.getTel());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getIdCard());
			ps.setDate(7, customer.getBirth());
			ps.setString(8, customer.getAccount());
			ps.setString(9, customer.getPassword());
			ps.setString(10, customer.getCustUse());
			ps.setString(11, customer.getCustRight());
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
	public void setUpdate(CustomerVO customer) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SET_UPDATE);
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getSex());
			ps.setString(3, customer.getTel());
			ps.setString(4, customer.getEmail());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getIdCard());
			ps.setDate(7, customer.getBirth());
			ps.setString(8, customer.getAccount());
			ps.setString(9, customer.getPassword());
			ps.setString(10, customer.getImg());
			ps.setString(11, customer.getCustUse());
			ps.setString(12, customer.getCard());
			ps.setString(13, customer.getCustRight());
			ps.setInt(14, customer.getCustId());
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
	public CustomerVO getAll(Integer custId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CustomerVO customerVO = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_ALL);
			ps.setInt(1, custId);
			rs = ps.executeQuery();

			if (rs.next()) {
				customerVO = new CustomerVO();
				customerVO.setCustId(rs.getInt("cust_Id"));
				customerVO.setName(rs.getString("name"));
				customerVO.setSex(rs.getString("sex"));
				customerVO.setTel(rs.getString("tel"));
				customerVO.setEmail(rs.getString("email"));
				customerVO.setAddress(rs.getString("address"));
				customerVO.setIdCard(rs.getString("id_Card"));
				customerVO.setBirth(rs.getDate("birth"));
				customerVO.setAccount(rs.getString("account"));
				customerVO.setPassword(rs.getString("password"));
				customerVO.setImg(rs.getString("img"));
				customerVO.setCustUse(rs.getString("cust_Use"));
				customerVO.setCard(rs.getString("card"));
				customerVO.setCustRight(rs.getString("cust_Right"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return customerVO;
	}

	@Override
	public String getPassword(String account) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String password = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_PASSWORD);
			ps.setString(1, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				password = rs.getString(1);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return password;
	}

	@Override
	public String getEmail(String account) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String email = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_EMAIL);
			ps.setString(1, account);
			rs = ps.executeQuery();
			if (rs.next()) {
				email = rs.getString(1);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return email;
	}

	@Override
	public void setCustRight(Integer custId, String custRight) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(SET_CUST_RIGHT);
			ps.setString(1, custRight);
			ps.setInt(2, custId);
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
	public Integer getLogin(String account, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Integer custId = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_LOGIN);
			ps.setString(1, account);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				custId = rs.getInt(1);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
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
		return custId;
	}

}
