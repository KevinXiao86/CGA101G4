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
import com.taiwan.utils.ControllerUtil;

@WebServlet("/cart/show")
public class CartShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService = ControllerUtil.getBean(TicketService.class);
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
//		CustomerVO customerVO = (CustomerVO) session.getAttribute("customer");
//		String custId = customerVO.getCustId().toString();
		String custId = "10015";
		
		List<String> cartlist = CartService.getCartList(custId);
//		Map<String, List<TicketVO>> map = new LinkedHashMap<>();
//	    Map<Integer, Integer> qtyMap = new LinkedHashMap<>();
		String action = req.getParameter("action");
		
		if (("showcart").equals(action)) {
			
			if (cartlist != null && cartlist.size() != 0) {
				 for (int index = 0; index < cartlist.size(); index++){
		         	JSONObject jsonProduct;
		         	Integer tktId = 0;
		         	Integer amount = 0;
					try {
						jsonProduct = new JSONObject(cartlist.get(index));
						tktId = Integer.valueOf(jsonProduct.getString("tktId"));
						amount = Integer.valueOf(jsonProduct.getString("amount"));
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
		         			         			
		         	TicketVO ticketVO = ticketService.findById(tktId); 
//		         	qtyMap.put(tktId, amount);
//					qtyMap.put(tktId, amount);
//
					List<TicketVO> list = new ArrayList<>();
					list.add(ticketVO);
//					map.put(busidString, list);
					session.setAttribute("list", list);
				
				 }
			}
			
//			List<String> savelist = CartService.getCartList(custId);
//			session.setAttribute("list", list);
//			session.setAttribute("qtymap", qtyMap);
//			session.setAttribute("map", map);
			RequestDispatcher rd = req.getRequestDispatcher("/front-end/cart/cartList.jsp");
			rd.forward(req, res);
			
		}
	}

}
