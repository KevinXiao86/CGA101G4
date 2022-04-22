package com.taiwan.dao.tktorder.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.TktOrder;
import com.taiwan.dao.tktorder.TktOrderDao;
import com.taiwan.utils.DbUtil;

public class TktOrderJDBCDao implements TktOrderDao {
	private static Connection conn = DbUtil.getConnection();

	@Override
	public int insertTktOrderWithCoupon(TktOrder obj) {
		int count = 0;
		String sql = "insert into TKT_ORDER(cust_id,original_price,ttl_price,cust_cop_id,qrcode)"
				+ " values(?,?,?,?,?);";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, obj.getCustId());
			prep.setInt(2, obj.getOriginalPrice());
			prep.setInt(3, obj.getTtlPrice());
			// 包裝類沒有值為0的存在
			prep.setInt(4, obj.getCustCopId());
			prep.setString(5, obj.getQrcode());
			count = prep.executeUpdate();
			System.out.println("success " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}

	@Override
	public int insertTktOrderNoCoupon(TktOrder obj) {
		int count = 0;
		String sql = "insert into TKT_ORDER(cust_id,original_price,ttl_price,qrcode)" + " values(?,?,?,?);";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, obj.getCustId());
			prep.setInt(2, obj.getOriginalPrice());
			prep.setInt(3, obj.getTtlPrice());
			// 包裝類沒有值為0的存在
			prep.setString(4, obj.getQrcode());
			count = prep.executeUpdate();
			System.out.println("success " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return count;
	}

	@Override
	public List<TktOrder> queryAllTktOrder() {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode from TKT_ORDER;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer custId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, custId, originalPrice, orderdate, ttlPrice, custCopId,
						qrcode);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}

	@Override
	public List<TktOrder> queryTktOrderByCustId(Integer custId) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode from TKT_ORDER where cust_id=?;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, custId);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, querycustId, originalPrice, orderdate, ttlPrice,
						custCopId, qrcode);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}

	@Override
	public List<TktOrder> queryTktOrderByOrderDate(Timestamp orderDate) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String date = "";
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode from TKT_ORDER where orderdate like=?;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setString(1, "%" + date + "%");
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, querycustId, originalPrice, orderdate, ttlPrice,
						custCopId, qrcode);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}

	@Override
	public List<TktOrder> queryTktOrderByDateToDate(Timestamp startdate, Timestamp enddate) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode from TKT_ORDER where orderdate between ? and ?;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setObject(1, startdate);
			prep.setObject(2, enddate);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer tktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				TktOrder tktOrderVO = new TktOrder(tktOrderId, querycustId, originalPrice, orderdate, ttlPrice,
						custCopId, qrcode);
				ls.add(tktOrderVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ls;
	}

	@Override
	public int queryTktOrderTtlPrice() {
		int totalPrice = 0;
		String sql = "select sum(ttl_price) as ttlprice from TKT_ORDER;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				totalPrice = rs.getInt("ttlprice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return totalPrice;
	}

	@Override
	public int quertTktOrderTtlPriceById(Integer custId) {
		int ttlPrice = 0;
		String sql = "select sum(ttl_price) as ttlprice from TKT_ORDER where cust_id=?;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, custId);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				ttlPrice = rs.getInt("ttlprice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ttlPrice;
	}

	@Override
	public int queryTktOrderTtlPriceByDateToDate(Timestamp startdate, Timestamp enddate) {
		int ttlPrice = 0;
		String sql = "select sum(ttl_price) as ttlprice from TKT_ORDER where order_date between ? and ?;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setObject(1, startdate);
			prep.setObject(2, enddate);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				ttlPrice = rs.getInt("ttlprice");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ttlPrice;
	}

	@Override
	public TktOrder queryTktOrderByTktOrderId(Integer tktOrderId) {
		TktOrder tktOrder = new TktOrder();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode from TKT_ORDER where tkt_order_id=?;";
		try (PreparedStatement prep = conn.prepareStatement(sql)) {
			prep.setInt(1, tktOrderId);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				Integer querytktOrderId = rs.getInt("tkt_order_id");
				Integer querycustId = rs.getInt("cust_id");
				Integer originalPrice = rs.getInt("original_price");
				Timestamp orderdate = rs.getObject("orderdate", Timestamp.class);
				Integer ttlPrice = rs.getInt("ttl_price");
				Integer custCopId = rs.getInt("cust_cop_id");
				String qrcode = rs.getString("qrcode");
				tktOrder = new TktOrder(querytktOrderId, querycustId, originalPrice, orderdate, ttlPrice, custCopId,
						qrcode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return tktOrder;
	}
	//未完成
	@Override
	public List<TktOrder> queryXX(Integer tktOrderId, Integer custId, Timestamp orderDate) {
		List<TktOrder> ls = new ArrayList<TktOrder>();
		String sql = "select tkt_order_id,cust_id,original_price,orderdate,"
				+ "ttl_price,cust_cop_id,qrcode from TKT_ORDER where tkt_order_id=?";
		String sql1 = ",cust_id=?";
		String sql2 = ",orderdate=?";
		if(custId!=null) {
			sql=sql+sql1;
		}
		try (PreparedStatement prep=conn.prepareStatement(sql)){
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ls;
	}
}
