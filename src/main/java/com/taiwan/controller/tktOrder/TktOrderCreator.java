package com.taiwan.controller.tktOrder;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapEntry;
import org.json.JSONException;
import org.json.JSONObject;

import com.taiwan.beans.CustCoupon;
import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TktItem;
import com.taiwan.beans.TktOrder;
import com.taiwan.dao.tktorder.TktOrderDao;
import com.taiwan.dao.tktorder.impl.TktOrderJDBCDao;
import com.taiwan.service.CartService;
import com.taiwan.service.TktOrderService;
import com.taiwan.service.coupon.CustCouponService2;
import com.taiwan.service.coupon.impl.CustCouponServiceImpl2;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.utils.MailQrCode11;
import com.taiwan.utils.Qrcode_11;

@WebServlet("/tktOrder/creator")
public class TktOrderCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("check");
		
		if("insert_order".equals(action)) {
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				
				/*******************1.接收請求參數，輸入格式的錯誤處理*******************/
				String orderName = req.getParameter("orderName");
				if (orderName == null || orderName.trim().length() == 0) {
					errorMsgs.put("orderName","請輸入訂購人姓名");
				} 
				
				String orderMobile = req.getParameter("orderMobile");
				String mobileReg = "^[0-9]{10}$";
				if(orderMobile == null || orderMobile.trim().length() == 0) {
					errorMsgs.put("orderMobile", "請輸入聯絡電話");
				}else if(!orderMobile.trim().matches(mobileReg)) {
					errorMsgs.put("orderMobile", "連絡電話必須為10位數字");
				}
				
				String orderEmail = req.getParameter("orderEmail");
				if(orderEmail == null || orderEmail.trim().length() == 0) {
					errorMsgs.put("orderEmail", "請輸入電子信箱");
				}
				
				String card = req.getParameter("card");
				if(card == null || card.trim().length() == 0) {
					errorMsgs.put("card", "請輸入信用卡號");
				}

				String expire = req.getParameter("expire");
				if(expire == null || expire.trim().length() == 0) {
					errorMsgs.put("expire", "請輸入信用卡到期日");
				}
				
				String cvv = req.getParameter("cvv");
				if(cvv == null || cvv.trim().length() == 0) {
					errorMsgs.put("cvv", "請輸入安全碼");
				}
				
				
				
				Integer copId = Integer.valueOf(req.getParameter("copId"));
				Integer discount = Integer.valueOf(req.getParameter("discount"));
				
				//遍歷map
	//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
	//				System.out.println(entry.getKey() + ":" + entry.getValue());
	//			}
				
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("name", orderName);
					req.setAttribute("email", orderEmail);
					req.setAttribute("tel", orderMobile);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cart/checkout.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/**************************2.開始成立訂單**************************/
				System.out.println("準備開始成立訂單");
				HttpSession session = req.getSession();
				//為了下方取優惠券的東西
				CustCouponService2 custCouponService = new CustCouponServiceImpl2();
				CustomerVO customer = (CustomerVO) session.getAttribute("customer");
				//取得此時的會員id
				Integer custId = customer.getCustId();
//				Integer custId = 10000;
				
				//取得購物車商品
				List<String> cartCheckout = (List<String>) session.getAttribute("list");
				List<TktItem> orders = new ArrayList<TktItem>();
				Integer tktId = 0;
				Integer amount = 0;
				Integer price = 0;
				Integer total = 0;
				
				for(int index = 0; index < cartCheckout.size(); index++) {
					JSONObject jsonProduct;
					try {
						jsonProduct = new JSONObject(cartCheckout.get(index));
						tktId = Integer.valueOf(jsonProduct.getString("tktId"));
						amount = Integer.valueOf(jsonProduct.getString("amount"));
	//					amountList.add(amount);
						price = Integer.valueOf(jsonProduct.getString("price"));
						total += (price * amount);
						
						TktItem tktItem = new TktItem();
						tktItem.setTktId(tktId);
						tktItem.setTktOrderId(tktId);
						tktItem.setAmount(amount);
						orders.add(tktItem);
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
				
				//變數price是票券一張的價格
				TktOrder tktOrder = new TktOrder();
				//有使用優惠券的
				if(copId != 0) {
					
					//取得從copId取得custCopId
					CustCoupon custCoupon = custCouponService.queryCustCouponByCustId(custId, copId);
					Integer custCopId = custCoupon.getCustCopId();
					
					tktOrder.setCustId(Integer.valueOf(custId));
					tktOrder.setOriginalPrice(total);
					tktOrder.setTtlPrice(total-discount);
					tktOrder.setCustCopId(custCopId);
					tktOrder.setQrcode(""); 
					tktOrder.setOrderName(orderName);
					tktOrder.setOrderEmail(orderEmail);
					tktOrder.setOrderMobile(orderMobile);
					tktOrder.setCustCopId(copId);
				}
				
				//沒有使用優惠券的
				else {
					tktOrder.setCustId(Integer.valueOf(custId));
					tktOrder.setOriginalPrice(total);
					tktOrder.setTtlPrice(total);
					tktOrder.setQrcode(""); 
					tktOrder.setOrderName(orderName);
					tktOrder.setOrderEmail(orderEmail);
					tktOrder.setOrderMobile(orderMobile);
				}
		
				TktOrderDao dao = new TktOrderJDBCDao();
				//此時新增的訂單編號
				String newOrderId = dao.insertTktOrderNoCoupon(tktOrder, orders);
				
				//更新已使用票券的資料
				if(copId != 0) {
					Long datetime = System.currentTimeMillis();
			        Timestamp usedate = new Timestamp(datetime);
					custCouponService.updateCustCouponStatusByTkt(custId, Integer.valueOf(newOrderId), "已使用", usedate);
				}
					
				/************************3.訂單成立後***********************/ 
				//移除session結帳列表
				session.removeAttribute("list");
				
				//移除Redis的商品
				List<String> cart = CartService.getCartList(custId.toString());
				CartService.deleteCartListbyTktId(custId.toString(), cart, tktId.toString());
										
				/*******************4.設定參數，送出成功頁面********************/ 	
				req.setAttribute("newOrderId", newOrderId);
				req.setAttribute("tktOrder", tktOrder);
				req.setAttribute("orders", orders);
				req.setAttribute("total", total);
				
				/*********************** 寄發mail ************************/ 
				// 設置QRCode的存放目錄、檔名與圖片格式
				String saveDirectory = "/images/qrcode/";
				// 找到阿飄路徑
				String realPath = getServletContext().getRealPath(saveDirectory);
				// 再如果阿飄路徑下沒有這個資料夾就創造，有就不用
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs();
				}
				String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new java.util.Date()) + ".jpg";
				
				Qrcode_11 quQrcode = new Qrcode_11();
				quQrcode.createQrcode(Integer.valueOf(newOrderId),realPath,fileName);
				
				MailQrCode11 mail = new MailQrCode11();
				String subject = "台玩 | 謝謝您，您所購買的票券訂單已成立拉！！！！！";
				String msgtxt = orderName + "您好: " + "\n" + "訂單編號" + newOrderId  + "已成立。請注意： 使用票券請出示Qr code方便店家進行掃描，台玩祝您旅途愉快~~";
				String qrcodePath = realPath + fileName;
				mail.SendMail(orderEmail, subject, msgtxt, qrcodePath);
				System.out.println("already send ticket order mail");
				
				RequestDispatcher success = req.getRequestDispatcher("/front-end/cart/confirmOrder.jsp");
				success.forward(req, res);
				
				/**********************其他可能的錯誤處理**********************/
			} catch (Exception e) {
			    e.printStackTrace();
			    System.out.println("新增訂單失敗");
			    RequestDispatcher view = req.getRequestDispatcher("/front-end/cart/checkout.jsp");
			    view.forward(req, res);
			}			
		}
	}

}

