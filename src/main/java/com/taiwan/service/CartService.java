package com.taiwan.service;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.taiwan.utils.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CartService {
	
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static void addCartList(String custId, JSONObject product) throws JSONException {
		String key = new StringBuilder("custId").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();
		
		jedis.rpush(key, product.toString());
		
		jedis.close();		
	}
	
	public static boolean updateCartList(String custId, List<String> cartlist, String tktId, String amount, boolean match) throws JSONException {
		String key = new StringBuilder("custId").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();
		
		for (int i = 0; i < cartlist.size(); i++) {
			JSONObject product = new JSONObject(cartlist.get(i));
			if (product.getString("tktId").equals(tktId)) {
				Integer oldAmount = Integer.valueOf(product.getString("amount"));
				Integer newAmount = oldAmount + Integer.valueOf(amount);
				product.put("amount", newAmount.toString());
				jedis.lset(key, i, product.toString());   //針對指定的索引值更改value
				match = true;
			}	
		}
		jedis.close();
		return match;
	}
	
	public static List<String> getCartList(String custId){
		String key = new StringBuilder("custId").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();
		
		List<String> allList = jedis.lrange(key, 0, -1);  //取全部
		jedis.close();
		
		return allList;
	}
	
	public static void deleteCartListbyTktId (String custId, List<String> cartlist, String tktId) throws JSONException {
		String key = new StringBuilder("custId").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();

		for (int i = 0; i < cartlist.size(); i++) {
			JSONObject product = new JSONObject(cartlist.get(i));
			if (product.getString("tktId").equals(tktId)) {
				jedis.lrem(key, 0, product.toString());  //count=0時，會刪除所有值為value的元素
			}
		}
		jedis.close();
	}
	
}
