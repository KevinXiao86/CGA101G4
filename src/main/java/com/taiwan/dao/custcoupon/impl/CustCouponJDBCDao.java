package com.taiwan.dao.custcoupon.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.taiwan.beans.CustCoupon;
import com.taiwan.dao.custcoupon.CustCoupon_interface;
import com.taiwan.utils.DbUtil;


public class CustCouponJDBCDao implements CustCoupon_interface{


	@Override
	public List<CustCoupon> queryCustCouponUsedById(Integer customerId, String status) {
		List<CustCoupon> ls=new ArrayList<CustCoupon>();
		String sql="select cust_cop_id,cust_id,cop_id,getdate,usedate,room_order_id,tkt_order_id,discount,status "
				+ "from CUST_COUPON where cust_id=? and status=?";
		try (Connection conn=DbUtil.getConnection();
				PreparedStatement prep=conn.prepareStatement(sql);){
			prep.setInt(1, customerId);
			prep.setString(2, status);
			ResultSet rs=prep.executeQuery();
			while(rs.next()) {
				Integer custCopId=rs.getInt("cust_cop_id");
				Integer custId=rs.getInt("cust_id");
				Integer copId=rs.getInt("cop_id");
				Timestamp getDate=rs.getObject("getdate",Timestamp.class);
				Timestamp useDate=rs.getObject("usedate",Timestamp.class);
				Integer roomOrderId=rs.getInt("room_order_id");
				Integer tktOrderId=rs.getInt("tkt_order_id");
				Integer discount=rs.getInt("discount");
				String querystatus=rs.getString("status");
				CustCoupon couponVO=new CustCoupon(custCopId, custId, copId, getDate, useDate, roomOrderId, tktOrderId, discount, querystatus);
				ls.add(couponVO);					
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}

	@Override
	public int updateCustCouponStatusByTktOrderId(Integer custId,Integer tktOrderId, String status) {
		int count=0;
		String sql="update CUST_COUPON set tkt_order_id=?,status=? where cust_id=?;";
		try (Connection conn=DbUtil.getConnection();
				PreparedStatement prep=conn.prepareStatement(sql);){
			prep.setInt(1, tktOrderId);
			prep.setString(2, status);
			prep.setInt(3, custId);
			count=prep.executeUpdate();
			System.out.println("success "+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	

	@Override
	public int updateCustCouponStatusByRoomOrderId(Integer custId,Integer roomOrderId, String status) {
		int count=0;
		String sql="update CUST_COUPON set room_order_id=?,status=? where cust_id=?;";
		try (Connection conn=DbUtil.getConnection();
				PreparedStatement prep=conn.prepareStatement(sql);){
			prep.setInt(1, roomOrderId);
			prep.setString(2, status);
			prep.setInt(3, custId);
			count=prep.executeUpdate();
			System.out.println("success "+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
    //未完成join
	@Override
	public int updateCustCouponStatusByEnddate(Integer custId,Timestamp enddate, String status) {
		int count=0;
		String sql="update CUST_COUPON set status=? where cust_id=? and ;";
		try (Connection conn=DbUtil.getConnection();
				PreparedStatement prep=conn.prepareStatement(sql);){
			prep.setString(1, status);
			prep.setInt(2, custId);
			count=prep.executeUpdate();
			System.out.println("success "+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}


	@Override
	public List<CustCoupon> queryCustCouponById(Integer id) {
		List<CustCoupon> ls=new ArrayList<CustCoupon>();
		String sql="select cust_cop_id,cust_id,cop_id,getdate,usedate,room_order_id,tkt_order_id,discount,status from CUST_COUPON where cust_id=?;";
		try (Connection conn=DbUtil.getConnection();
				PreparedStatement prep=conn.prepareStatement(sql);){
			prep.setInt(1, id);
			ResultSet rs=prep.executeQuery();
			while(rs.next()) {
				Integer custCopId=rs.getInt("cust_cop_id");
				Integer custId=rs.getInt("cust_id");
				Integer copId=rs.getInt("cop_id");
				Timestamp getDate=rs.getObject("getdate",Timestamp.class);
				Timestamp useDate=rs.getObject("usedate",Timestamp.class);
				Integer roomOrderId=rs.getInt("room_order_id");
				Integer tktOrderId=rs.getInt("tkt_order_id");
				Integer discount=rs.getInt("discount");
				String status=rs.getString("status");
				CustCoupon couponVO=new CustCoupon(custCopId, custId, copId, getDate, useDate, roomOrderId, tktOrderId, discount, status);
				ls.add(couponVO);					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}



	@Override
	public int insertCustCoupon(CustCoupon obj) {
		int count=0;
		String sql="insert into CUST_COUPON(cust_id,cop_id,discount) "
				+ "values(?,?,?);";
		try (Connection conn=DbUtil.getConnection();
				PreparedStatement prep=conn.prepareStatement(sql);){
			prep.setInt(1,obj.getCustId());
			prep.setInt(2,obj.getCopId());
			prep.setInt(3,obj.getDiscount());
			count=prep.executeUpdate();
			System.out.println("success "+count);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public List<CustCoupon> getAll() {
		List<CustCoupon> ls=new ArrayList<CustCoupon>();
		String sql="select cust_cop_id,cust_id,cop_id,getdate,usedate,room_order_id,tkt_order_id,discount,status from CUST_COUPON order by cust_id;";
		try (Connection conn=DbUtil.getConnection();
				PreparedStatement prep=conn.prepareStatement(sql);){
			
			ResultSet rs=prep.executeQuery();
			while(rs.next()) {
				Integer custCopId=rs.getInt("cust_cop_id");
				Integer custId=rs.getInt("cust_id");
				Integer copId=rs.getInt("cop_id");
				Timestamp getDate=rs.getObject("getdate",Timestamp.class);
				Timestamp useDate=rs.getObject("usedate",Timestamp.class);
				Integer roomOrderId=rs.getInt("room_order_id");
				Integer tktOrderId=rs.getInt("tkt_order_id");
				Integer discount=rs.getInt("discount");
				String status=rs.getString("status");
				CustCoupon couponVO=new CustCoupon(custCopId, custId, copId, getDate, useDate, roomOrderId, tktOrderId, discount, status);
				ls.add(couponVO);					
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ls;
	}
}
