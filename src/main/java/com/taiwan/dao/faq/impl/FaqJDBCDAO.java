package com.taiwan.dao.faq.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.FaqVO;
import com.taiwan.dao.faq.FaqDAO_interface;

public class FaqJDBCDAO implements FaqDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT = 
			"INSERT INTO FAQ(title, content) VALUES (?, ?)";
	private static final String DELETE =
			"DELETE FROM FAQ WHERE faq_id=?";
	private static final String UPDATE = 
			"UPDATE FAQ SET title=?, content=? where faq_id=?";
	private static final String GET_BYFAQID = 
			"SELECT faq_id, title, content, create_date FROM FAQ WHERE faq_id=?";
	private static final String GET_BYTITLE = 
			"SELECT faq_id, title, content, create_date FROM FAQ WHERE title like ?";
	private static final String GET_ALL = 
			"SELECT faq_id, title, content, create_date FROM FAQ ";

	@Override
	public int insert(FaqVO faqVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, faqVO.getTitle());
			ps.setString(2, faqVO.getContent());
			
			count = ps.executeUpdate();
			System.out.println("success" + count);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
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
		return count;
	}

	@Override
	public int delete(Integer faqId) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, faqId);
			
			count = ps.executeUpdate();
			System.out.println("success" + count);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
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
		return count;
	}

	@Override
	public int update(FaqVO faqVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, faqVO.getTitle());
			ps.setString(2, faqVO.getContent());
			ps.setInt(3, faqVO.getFaqId());
			
			count = ps.executeUpdate();
			System.out.println("success" + count);
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
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
		return count;

	}

	@Override
	public FaqVO queryById(Integer faqId) {
		
		FaqVO faqVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYFAQID);
			
			ps.setInt(1, faqId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaqId(rs.getInt("faq_id"));
				faqVO.setTitle(rs.getString("title"));
				faqVO.setContent(rs.getString("content"));
				faqVO.setCreateDate(rs.getTimestamp("create_date"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
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
		return faqVO;
	}

	@Override
	public List<FaqVO> queryFaqByTitle(String title) {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYTITLE);
			
			ps.setString(1, "%" + title + "%");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaqId(rs.getInt("faq_id"));
				faqVO.setTitle(rs.getString("title"));
				faqVO.setContent(rs.getString("content"));
				faqVO.setCreateDate(rs.getTimestamp("create_date"));
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
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
	public List<FaqVO> queryAll() {
		
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				faqVO = new FaqVO();
				faqVO.setFaqId(rs.getInt("faq_id"));
				faqVO.setTitle(rs.getString("title"));
				faqVO.setContent(rs.getString("content"));
				faqVO.setCreateDate(rs.getTimestamp("create_date"));
				list.add(faqVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		}finally {
			if (ps != null) {
				try {
					ps.close();
				}catch (SQLException se){
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

		FaqJDBCDAO dao = new FaqJDBCDAO();

		// 新增
		FaqVO faqVO1 = new FaqVO();
		faqVO1.setTitle("票券使用");
		faqVO1.setContent("查看");
		dao.insert(faqVO1);

		// 刪除
		dao.delete(1);

		// 修改
		FaqVO faqVO2 = new FaqVO();
		faqVO2.setFaqId(1);
		faqVO2.setTitle("忘記帳號");
		faqVO2.setContent("根據以下步驟進行驗證");
		dao.update(faqVO2);

		// 根據流水號查詢
		FaqVO faqVO3 = dao.queryById(1);
		System.out.println(faqVO3.toString());

		// 根據標題查詢
		List<FaqVO> list1 = dao.queryFaqByTitle("帳號");
		for (FaqVO faq : list1) {
			System.out.println(faq.toString());
		}

		// 查詢全部
		List<FaqVO> list2 = dao.queryAll();
		for (FaqVO faq : list2) {
			System.out.println(faq.toString());
		}

	}
}
