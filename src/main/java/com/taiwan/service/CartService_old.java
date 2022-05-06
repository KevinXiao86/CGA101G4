package com.taiwan.service;

import java.util.Map;

import com.taiwan.utils.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class CartService_old {
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	//取得購物車內容
	public static Map<String, String> getCartList(String custId) {
		String key = new StringBuilder("customer").append(":").append(custId).toString();  //key-> customer:10001
		Jedis jedis = pool.getResource();
		
		Map<String, String> savelist = jedis.hgetAll(key);
		jedis.close();
		
		return savelist;
	}
	
//	//新增一筆到購物車
//	public static Long addCartList(String custId, String tktId, int amount){  //原本用String amount直接存入
//		String key = new StringBuilder("customer").append(":").append(custId).toString();
//		Jedis jedis = pool.getResource();
//		
//		//先取看看
//		String product = jedis.hget(key, tktId);
//        if (product!=null || !"".equals(product)){
//            return jedis.hincrBy(key, tktId, amount); //原本的數量再疊加上去
//		}else {
//			//如果沒有此商品，則新增到購物車
//			return jedis.hset(key, tktId, String.valueOf(amount));			
//		}		
//	}
	
	//新增一筆到購物車
	public static Long addCartList(String custId, String tktId, int amount){  //原本用String amount直接存入
		String key = new StringBuilder("customer").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();
		
		Long addList = jedis.hset(key, tktId, String.valueOf(amount));	
		jedis.close();
		
		return addList;
	}
	
	//
	public static Long updateCartList(String custId, String tktId, int amount){  //原本用String amount直接存入
		String key = new StringBuilder("customer").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();
		
		Long updateList = jedis.hincrBy(key, tktId, amount);
		jedis.close();
		
		return updateList;
	}
	
	//刪除一筆
	public static void deleteCartListbyTktId (String custId, String tktId){
		String key = new StringBuilder("customer").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();

		jedis.del(key,tktId);
		jedis.close();
	}
	
	//清空購物車
	public static void deleteCartbycustId (String custId){
		String key = new StringBuilder("customer").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();

		
		
		jedis.close();
	}
	
	//取得此會員的購物車總數量?
	public static long hlen(String custId) {
		String key = new StringBuilder("customer").append(":").append(custId).toString();
		Jedis jedis = pool.getResource();
		return jedis.hlen(key);
	}

	
	
	//===================================================================================================

	
//	// 添加商品到购物车中
//	public ShopCart addShopCart(ShopCart shopCart) throws IllegalAccessException {
//	    
//	    // 定义每个用户的购物车key
//	    String shopCartKey = SHOP_CART_KEY + shopCart.getUserId();
//	    // 获取一个操作hash的数据对象
//	    HashOperations hashOperations = redisTemplate.opsForHash();
//	    // 判断一个商品是否已经添加到购物车中 HEXISTS shopCart:user:1
//	    Boolean hasKey = hashOperations.getOperations().hasKey(shopCart.getProductId().toString());
//	    if (hasKey) {
//	    
//	        // 然后把shopCart商品添加到redisHash中
//	        shopCart.setProductNum(shopCart.getProductNum() + 1);
//	        Map<String, Object> map = ObjectUtils.objectToMap(shopCart);
//	        // 做功能增强：如果是相同的商品的把数量进行递增+1
//	        // 如果不存在，就直接覆盖最新的数据进去&设置过期时间30天
//	        hashOperations.put(shopCartKey, shopCart.getProductId().toString(), map);
//	        // 设置购物车的过期时间
//	        this.redisTemplate.expire(shopCartKey, 30, TimeUnit.DAYS);
//	    } else {
//	    
//	        // 参数1： shopCartKey 这个是redis操作的key，其实就购物车
//	        // 参数2： shopCart.getProductId().toString() 这个是hash数据结构map的key,也就是商品的id
//	        // 参数3： map 对应的是具体的商品
//	        // 一句话：就把指定的shopCart.getProductId()添加到指定的用户的shopCartKey购物车中，具体的详细内容是map
//	        // 然后把shopCart商品添加到redisHash中
//	        Map<String, Object> map = ObjectUtils.objectToMap(shopCart);
//	        hashOperations.put(shopCartKey, shopCart.getProductId().toString(), map);
//	    }
//
//	    // 添加成功返回即可
//	    return shopCart;
//	}
//
//	// 修改购物车的数量
//	public ShopCart updateShopCartNum(ShopCart shopCart) throws IllegalAccessException {
//	    
//	    // 存储购物车的缓存key
//	    String shopCartKey = SHOP_CART_KEY + shopCart.getUserId();
//	    // 修改购物车的数量
//	    Map<String, Object> map = ObjectUtils.objectToMap(shopCart);
//	    this.redisTemplate.opsForHash().put(shopCartKey, shopCart.getProductId().toString(), map);
//	    return shopCart;
//	}
//
//	// 删除购物车的某个商品
//	public String delShopCart(Long userId, Long productId) {
//	    
//	    // 存储购物车的缓存key
//	    String shopCartKey = SHOP_CART_KEY + userId;
//	    // 修改购物车的数量
//	    this.redisTemplate.opsForHash().delete(shopCartKey, productId.toString());
//	    return "success";
//	}
//
//	// 查询购物车中所有的数据信息
//	public Map<String,Object> selectShopCart(Long userId) {
//	    
//	    // 购物车的缓存key
//	    String shopCartKey = SHOP_CART_KEY + userId;
//	    // 从redis中获取购物车中的数量  hlen shopCart:user:1
//	    Long size = this.redisTemplate.opsForHash().size(shopCartKey);
//	    // 从redis中获取购物车中所有的商品 获取到hgetall shopCartKey  2 map 1 map 3 map
//	    Map<String, Map<String, Object>> entries = this.redisTemplate.opsForHash().entries(shopCartKey);
//	    // 计算总金额
//	    double totalmoney = 0d;
//	    List<Map<String,Object>> resultList = new ArrayList<>();
//	    // 把hash数据结构中的商品全部循环迭代出来
//	    for (Map.Entry<String, Map<String, Object>> entry : entries.entrySet()) {
//	    
//	        resultList.add(entry.getValue());
//	        totalmoney += Integer.parseInt(String.valueOf(entry.getValue().get("productNum"))) *
//	                Double.parseDouble(String.valueOf(entry.getValue().get("productPrice")));
//	    }
//
//	    // 返回购物车的数据信息
//	    Map<String,Object> map = new HashMap<>();
//	    // 总计
//	    map.put("totalPrice",totalmoney);
//	    // 这个商品的个数
//	    map.put("totalCount",size);
//	    // 具体商品的数据
//	    map.put("resultList",resultList);
//	    return map;
//	}
	
}



//Map   {"1","{'pudId':1,'name':'card','amount':10}"}


/*
	key field value
	會員id 商品id 數量
	
	1. 新增購物車商品
	hset cart:1001 5 1
	
	2. 全選，展示用
	hgetall cart:1001
	
	3. 商品數量：購物車的圖示數量
	hlen cart:1001 → 顯示的筆數
	
	4. 刪除商品
	hdel cart:1001 5
	
	5. 購物車已有商品存在時，用此方式增加商品+1，用hget key field取得數量為0時，hdel key field
	hincrby cart:1001 5 2
	
	6. 是否存在指定的商品
	hexists cart:1001 5
	
	7. 清空購物車
	刪除key
	
	8. 獲取商品數量，數量及金額統計
	hvals key
	
	9. 取出所有的key
	hkeys key
*/
