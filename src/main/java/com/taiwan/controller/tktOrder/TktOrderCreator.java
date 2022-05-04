package com.taiwan.controller.tktOrder;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Iterator;
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

import com.taiwan.beans.CustomerVO;

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
			
			System.out.println("check in");
			
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*******************1.接收請求參數，輸入格式的錯誤處理*******************/
			String orderName = req.getParameter("orderName");
			String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (orderName == null || orderName.trim().length() == 0) {
				errorMsgs.put("orderName","請輸入訂購人姓名");
			} else if(!orderName.trim().matches(nameReg)) { 
				errorMsgs.put("orderName","訂購人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
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
			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cart/checkout.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/**************************2.開始成立訂單**************************/
			HttpSession session = req.getSession();
			//需要判斷是否登陸? 用filter檔掉
			CustomerVO customer = (CustomerVO) session.getAttribute("customer");
			//取得此時的會員id
			Integer custId = customer.getCustId();
			
			//取得購物車商品
			List<String> cart = (List<String>) session.getAttribute("cart");
			
			
			
			
			/*******************3.訂單成立，設定參數，送出成功頁面********************/ 
//			req.setAttribute("list", list);
//			RequestDispatcher success = req.getRequestDispatcher("");
//			success.forward(req, res);
			
			
			
			
		}
	}

}
