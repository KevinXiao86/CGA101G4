package com.taiwan.controller.cart;

import java.io.IOException;
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

import org.json.JSONException;
import org.json.JSONObject;

import com.taiwan.beans.CouponVO;
import com.taiwan.beans.CustCoupon;
import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TicketVO;
import com.taiwan.beans.TktImgVO;
import com.taiwan.service.CartService;
import com.taiwan.service.TicketService;
import com.taiwan.service.coupon.CouponService;
import com.taiwan.service.customer.CustCouponService;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/cart/do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);
	CouponService couponService = ControllerUtil.getBean(CouponService.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
		String custId = customerVO.getCustId().toString();
		
		// 先取看看購物車
		List<String> cartlist = CartService.getCartList(custId);
		String action = req.getParameter("action");
		// 先取看看票券圖片
		List<String> tktImgList = (List<String>) session.getAttribute("tktImgList");

		
		if (!("checkout").equals(action)) {

			// 刪除商品
			if (("delete").equals(action)) {
				String tktId = (String) req.getParameter("del");
				try {
					CartService.deleteCartListbyTktId(custId, cartlist, tktId);
					System.out.println("delete ok");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				String delImg = req.getParameter("delImg");
				System.out.println("del image = " + delImg);
				tktImgList.remove(Integer.parseInt(delImg));
			}

			// 新增商品至購物車中
			else if (("add").equals(action)) {
				boolean match = false;

				// 取得新增商品
				JSONObject addtkts = null;
				try {
					addtkts = getTicket(req);
				} catch (JSONException e) {
					e.printStackTrace();
				}

				// 新增第一項商品到購物車時
				if (cartlist == null || cartlist.size() == 0) {
					try {
						CartService.addCartList(custId, addtkts);
						System.out.println("add the first tkt");
					} catch (JSONException e) {
						e.printStackTrace();
					}
				} else {
					// 如果購物車裡已經有了
					String amount = req.getParameter("amount");
					String tktId = req.getParameter("tktId");
					System.out.println("amount:" + amount + ", tktId: " + tktId);
					try {
						// 更新成功就變成true
						match = CartService.updateCartList(custId, cartlist, tktId, amount, match);
						System.out.println("update add amount");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					// 購物車裡不同票券種類就add
					if (!match) {
						try {
							CartService.addCartList(custId, addtkts);
							System.out.println("add the second kind of tkt");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
				/******************************* 票券圖片 ********************************/
//				List<String> tktImgList = (List<String>) session.getAttribute("tktImgList");
				String tktImg = "";
				if(tktImgList == null || tktImgList.size() == 0) {
					tktImgList = new ArrayList<String>();
					tktImg = req.getParameter("tktImg");
					tktImgList.add(tktImg);
				}else {
					tktImg = req.getParameter("tktImg");
//					for(int index = 0; index < tktImgList.size(); index++) {   //第三筆開始會重複取
//						System.out.println("125 = "+tktImgList.get(index));      
						if(tktImgList.indexOf(tktImg) == -1)  
							tktImgList.add(tktImg);
//					}
				}
				
				System.out.println(tktImgList);
			}
			
			/******************************* 優惠券相關 ********************************/
			// 根據會員id取得所擁有的會員優惠券物件
			CustCouponService custCouponService = new CustCouponService();
			List<CustCoupon> custCouponList = custCouponService.queryCustCouponById(Integer.valueOf(custId));

			// 取得所擁有的優惠券編號
			List<Integer> copIds = new ArrayList<Integer>();
			for (CustCoupon custCoupon : custCouponList) {
				String status = "未使用";
				if (custCoupon.getStatus().equals(status)) {
					copIds.add(custCoupon.getCopId());
				}
			}
//			System.out.println("copIds = " +copIds);

			// 根據優惠券編號取得優惠券名稱
			List<CouponVO> couponList = new ArrayList<CouponVO>();
			for (Integer copId : copIds) {
				CouponVO couponVO = couponService.findById(copId);
				couponList.add(couponVO);
			}
//			System.out.println("couponList = " + couponList);

			session.setAttribute("couponList", couponList);
			
			/***************************** 取出購物車清單 ********************************/
			// 取出購物車清單
			List<String> list = CartService.getCartList(custId);
			// 目的為取得票券資料
			List<TicketVO> tktlist = new ArrayList<>();
			// 目的為存放各票券的購買數量
			List<Integer> amountList = new ArrayList<>();

			Integer tktId = 0;
			Integer amount = 0;
			Integer total = 0;
			if (list != null && list.size() != 0) {
				for (int index = 0; index < list.size(); index++) {
					JSONObject jsonProduct;
					try {
						jsonProduct = new JSONObject(list.get(index));
						tktId = Integer.valueOf(jsonProduct.getString("tktId"));
						amount = Integer.valueOf(jsonProduct.getString("amount"));
						amountList.add(amount);
						Integer price = Integer.valueOf(jsonProduct.getString("price"));
						total += (price * amount);
					} catch (JSONException e) {
						e.printStackTrace();
					}

					TicketVO ticketVO = ticketService.findById(tktId);
					tktlist.add(ticketVO);
				}
			}

			// 錯誤作法 -> 每當新增不同票種，大家的數量就會一起變動，所以改成將值存入集合中，一一讀取
//			req.setAttribute("amount", amount);  

			session.setAttribute("tktImgList", tktImgList);
			session.setAttribute("amountList", amountList);
			session.setAttribute("list", list);
			session.setAttribute("tktlist", tktlist);
			session.setAttribute("total", total);
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/cart/cartList.jsp");
			// 不能直接做轉發，務必加上return
			try {
				rd.forward(req, res);
				return;
//				res.sendRedirect("/CGA101G4/front-end/cart/cartList.jsp");
//				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 結帳、計算購物車總價
		else if (("checkout").equals(action)) {
			System.out.println("enter checkout");

			//計算總金額
			Integer total = 0;
			Integer amount = 0;
			for (int i = 0; i < cartlist.size(); i++) {
				JSONObject jsonProduct;
				try {
					jsonProduct = new JSONObject(cartlist.get(i));
					amount = Integer.valueOf(jsonProduct.getString("amount"));
					Integer price = Integer.valueOf(jsonProduct.getString("price"));
					total += (price * amount);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			// 折扣金額及優惠券id
			String discountString = req.getParameter("num");
			String copIdString = req.getParameter("numId");
			System.out.println("copId = " + copIdString);
			
			Integer discount = 0;
			Integer copId = 0;
			if (discountString == null || discountString.trim().equals("")) {
				discount = 0;
			} else {
				discount = Integer.valueOf(discountString);
				copId = Integer.valueOf(copIdString);
			}

			session.setAttribute("copId", copId);
			session.setAttribute("discount", discount);

//			double ttl = cartList.stream()
//								 .mapToDouble(book->book.getPrice()*book.getQuantity())
//								 .sum();

			// 結帳頁面checkout.jsp 自動導入資料
			Integer custid = Integer.valueOf(custId);
			CustomerService customerService = new CustomerServiceImpl();
			customerVO = customerService.getAll(custid);

			session.setAttribute("name", customerVO.getName());
			session.setAttribute("email", customerVO.getEmail());
			session.setAttribute("tel", customerVO.getTel());
			session.setAttribute("total", String.valueOf(total));
			session.setAttribute("amount", String.valueOf(amount));
			RequestDispatcher success = req.getRequestDispatcher("/front-end/cart/checkout.jsp");
			try {
				success.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private JSONObject getTicket(HttpServletRequest req) {

		String tktId = req.getParameter("tktId");
		String tktName = req.getParameter("tktName");
		String price = req.getParameter("price");
		String amount = req.getParameter("amount");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("tktId", tktId);
		jsonObject.put("tktName", tktName);
		jsonObject.put("price", price);
		jsonObject.put("amount", amount);

		return jsonObject;
	}

}
