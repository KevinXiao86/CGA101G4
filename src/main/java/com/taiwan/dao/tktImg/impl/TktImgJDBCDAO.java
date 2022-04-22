package com.taiwan.dao.tktImg.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.TktImgVO;
import com.taiwan.dao.tktImg.TktImgDAO_interface;

public class TktImgJDBCDAO implements TktImgDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://104.199.153.224:3306/Taiwan?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "rootitri";

	private static final String INSERT =
			"INSERT INTO TKT_IMG(img, tkt_id) VALUES (?,?)";
	private static final String UPDATE =
			"UPDATE TKT_IMG SET img=?, tkt_id=? where tkt_img_id=?";
	private static final String DELETE =
			"DELETE FROM TKT_IMG WHERE tkt_img_id=?";
	private static final String GET_BYTKTIMGID =
			"SELECT img, tkt_id FROM TKT_IMG  WHERE tkt_img_id=?";
	private static final String GET_ALL =
			"SELECT tkt_img_id, img, tkt_id FROM TKT_IMG";
	private static final String GET_BYTKTID =
			"SELECT tkt_img_id, img FROM TKT_IMG  WHERE tkt_id=?";
	
	@Override
	public int insert(TktImgVO tktImgVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(INSERT);
			
			ps.setString(1, tktImgVO.getImg());
			ps.setInt(2, tktImgVO.getTktId());
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
	public int update(TktImgVO tktImgVO) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(UPDATE);
			
			ps.setString(1, tktImgVO.getImg());
			ps.setInt(2, tktImgVO.getTktId());
			ps.setInt(3, tktImgVO.getTktImgId());
			
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
	public int delete(Integer tktImgId) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(DELETE);
			
			ps.setInt(1, tktImgId);
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
	public TktImgVO queryById(Integer tktId) {
		
		TktImgVO tktImgVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYTKTIMGID);
			
			ps.setInt(1, tktId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				tktImgVO = new TktImgVO();
				tktImgVO.setImg(rs.getString(1));
				tktImgVO.setTktId(rs.getInt(2));
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
		return tktImgVO;
	}

	@Override
	public List<TktImgVO> queryAll() {

		List<TktImgVO> list = new ArrayList<TktImgVO>();
		TktImgVO tktImgVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_ALL);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				tktImgVO = new TktImgVO();
				tktImgVO.setTktImgId(rs.getInt(1));
				tktImgVO.setImg(rs.getString(2));
				tktImgVO.setTktId(rs.getInt(3));
				list.add(tktImgVO);
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
	public List<TktImgVO> queryByTktId(Integer tktId) {
		
		List<TktImgVO> list = new ArrayList<TktImgVO>();
		TktImgVO tktImgVO = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			ps = con.prepareStatement(GET_BYTKTID);
			
			ps.setInt(1, tktId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				tktImgVO = new TktImgVO();
				tktImgVO.setTktImgId(rs.getInt(1));
				tktImgVO.setImg(rs.getString(2));
				list.add(tktImgVO);
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
		
		TktImgJDBCDAO dao = new TktImgJDBCDAO();
		
		//新增
		TktImgVO tktImgVO1 = new TktImgVO();
		tktImgVO1.setImg("C:\\file_imgs\\h0.jpg");
		tktImgVO1.setTktId(2);
		dao.insert(tktImgVO1);
		
		//修改
		TktImgVO tktImgVO2 = new TktImgVO();
		tktImgVO2.setTktImgId(1);
		tktImgVO2.setImg("C:\\file_imgs\\h4.jpg");
		tktImgVO2.setTktId(3);
		dao.update(tktImgVO2);
		
		//刪除
		dao.delete(1);
		
		//根據id查詢
		TktImgVO tktImgVO3 = dao.queryById(1);
		System.out.println(tktImgVO3.toString());
		
		//搜尋全部
		List<TktImgVO> list = dao.queryAll();
		for(TktImgVO tkt : list) {
			System.out.println(tkt.toString());
		}
		
		//根據票券編號查詢
		List<TktImgVO> list2 = dao.queryByTktId(3);
		for(TktImgVO tkt : list2) {
			System.out.println(tkt.toString());
		}
		
	}

	

	
}
