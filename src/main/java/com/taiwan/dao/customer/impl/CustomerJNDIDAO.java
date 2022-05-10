package com.taiwan.dao.customer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.taiwan.beans.CustomerVO;
import com.taiwan.dao.customer.CustomerDAO_interface;

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
			+ "SET NAME=?,SEX=?,TEL=?,EMAIL=?,ADDRESS=?,ID_CARD=?,BIRTH=?,ACCOUNT=?,PASSWORD=?,IMG=?,CARD=? "
			+ "WHERE CUST_ID=?;";
	private static final String GET_ALL = "select cust_Id,name,sex,tel,email,address,id_Card ,birth,account,password,img,cust_Use ,card,cust_Right "
			+ "from CUSTOMER WHERE CUST_ID= ? ;";
	private static final String GET_PASSWORD = "SELECT PASSWORD FROM CUSTOMER WHERE ACCOUNT=?;";
	private static final String GET_EMAIL = "SELECT EMAIL FROM CUSTOMER WHERE ACCOUNT=?;";
	private static final String SET_CUST_RIGHT = "UPDATE CUSTOMER SET CUST_RIGHT =? WHERE CUST_ID =?;";
	private static final String GET_LOGIN = "SELECT * FROM CUSTOMER WHERE ACCOUNT=? AND PASSWORD=?;";
	private static final String GET_ALL_NOMATTERWHO = "select cust_Id,name,sex,tel,email,address,id_Card ,birth,account,password,img,cust_Use ,card,cust_Right "
			+ "from CUSTOMER;";

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
//			ps.setDate(7, customer.getBirth());
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
//			ps.setDate(7, customer.getBirth());
			ps.setString(8, customer.getAccount());
			ps.setString(9, customer.getPassword());
			ps.setString(10, customer.getImg());
			ps.setString(11, customer.getCard());
			ps.setInt(12, customer.getCustId());
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
	public CustomerVO getLogin(String account, String password) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CustomerVO customerVO = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_LOGIN);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if (rs.next()) {
				customerVO = new CustomerVO();
				customerVO.setAccount(rs.getString("ACCOUNT"));
				customerVO.setAddress(rs.getString("ADDRESS"));
				customerVO.setBirth(rs.getDate("BIRTH"));
				customerVO.setCard(rs.getString("CARD"));
				customerVO.setCustId(rs.getInt("CUST_ID"));
				customerVO.setCustRight(rs.getString("cust_right"));
				customerVO.setCustUse(rs.getString("cust_use"));
				customerVO.setEmail(rs.getString("EMAIL"));
				customerVO.setIdCard(rs.getString("ID_CARD"));
				customerVO.setImg(rs.getString("IMG"));
				customerVO.setName(rs.getString("NAME"));
				customerVO.setPassword(rs.getString("PASSWORD"));
				customerVO.setSex(rs.getString("SEX"));
				customerVO.setTel(rs.getString("TEL"));
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

	public List<CustomerVO> getAllNoMatterWho() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<CustomerVO> list = new ArrayList<CustomerVO>();

		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(GET_ALL_NOMATTERWHO);
			rs = ps.executeQuery();
			CustomerVO customerVO = null;
			while (rs.next()) {
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
				list.add(customerVO);
			}
			System.out.println(list);
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
		return list;
	}

	@Override
	public int regist(CustomerVO customer) {
		// TODO Auto-generated method stub
		return 0;
	}
}
