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

import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.TicketVO;
import com.taiwan.service.CartService;
import com.taiwan.service.TicketService;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/cart/do")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		HttpSession session = req.getSession();
//		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
//		CustomerVO customerVO = new CustomerVO();
//		String custId = customerVO.getCustId().toString();
		String custId = "10000";

		//先取看看
		List<String> cartlist = CartService.getCartList(custId);
		String action = req.getParameter("action");

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
				if (cartlist == null || cartlist.size()==0) {
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
						match = CartService.updateCartList(custId, cartlist, tktId, amount, match);
						System.out.println("update add amount");
					} catch (JSONException e) {
						e.printStackTrace();
					}
					// 如果購物車裡沒有
					if (!match) {
						try {
							CartService.addCartList(custId, addtkts);
							System.out.println("add the second kind of tkt");
						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}
			}

			List<String> list = CartService.getCartList(custId);
			List<TicketVO> tktlist = new ArrayList<>();
			Integer tktId = 0;
			Integer amount = 0;
			if (list != null && list.size() != 0) {

				for (int index = 0; index < list.size(); index++) {
					JSONObject jsonProduct;
					
					try {
						jsonProduct = new JSONObject(list.get(index));
						tktId = Integer.valueOf(jsonProduct.getString("tktId"));
						amount = Integer.valueOf(jsonProduct.getString("amount"));

					} catch (JSONException e) {
						e.printStackTrace();
					}

					TicketVO ticketVO = ticketService.findById(tktId);
					tktlist.add(ticketVO);
				}
			}
			
			//錯誤作法 -> 每當新增不同票種，大家的數量就會一起變動，改成直接從Redis取出最後的值
//			req.setAttribute("amount", amount);  
			
			session.setAttribute("tktlist", tktlist);
			session.setAttribute("tktId", tktId);
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/cart/cartList.jsp");
			// 不能直接做轉發，務必加上return
			try {
				rd.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 結帳、計算購物車總價
		else if (("checkout").equals(action)) {
			System.out.println("enter checkout");

			Integer total = 0;
			Integer amount = 0;
			for (int i = 0; i < cartlist.size(); i++) {
				JSONObject tkt;
				try {
					tkt = new JSONObject(cartlist.get(i));
					amount = Integer.valueOf(tkt.getString("amount"));
					Integer price = Integer.valueOf(tkt.getString("price"));
					total += (price * amount);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			// 結帳頁面 自動導入資料
			Integer custid = Integer.valueOf(custId);
			CustomerService customerService = new CustomerServiceImpl();
			CustomerVO customerVO = customerService.getAll(custid);

			req.setAttribute("total", String.valueOf(total));
			req.setAttribute("amount", String.valueOf(amount)); 
			req.setAttribute("name", customerVO.getName());
			req.setAttribute("email", customerVO.getEmail());
			req.setAttribute("tel", customerVO.getTel());
			RequestDispatcher success = req.getRequestDispatcher("/front-end/cart/checkout.jsp");
			success.forward(req, res);
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
