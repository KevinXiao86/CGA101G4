package com.taiwan.controller.roomOrder;

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

import com.taiwan.beans.Company;
import com.taiwan.beans.CustomerVO;
import com.taiwan.beans.RoomOrder;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.service.roomOrder.RoomOrderMyService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/roomOrder/cmpFindOrder")
public class CmpFindOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RoomOrderMyService roomOrderMyService = ControllerUtil.getBean(RoomOrderMyService.class);
	CustomerService customerService=new CustomerServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			//這邊要獲取廠商的Id
			Company company=(Company)session.getAttribute("loginCompany");
			// 把廠商的Id值從company拿出
			Integer cmpId=company.getCmpId();
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
				rd.forward(request, response);
				return;
			}
			
			// 開始查詢
			List<RoomOrder> roomOrders = roomOrderMyService.findByCmpId(cmpId);
			if (roomOrders.isEmpty() || roomOrders.size() == 0) {
				errorMsgs.put("roomOrder", "查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
				rd.forward(request, response);
				return;
			}
			List<CustomerVO> customerVOs=new ArrayList<CustomerVO>();
			//這邊想要顯示會員的名字而不是編號
			for(RoomOrder roomOrder:roomOrders) {
				Integer custId=roomOrder.getCustId();
				CustomerVO customerVO=customerService.getAll(custId);
				customerVOs.add(customerVO);
			}
			System.out.println(customerVOs);
			// 把查到的roomOrders放到request域中
			request.setAttribute("roomOrders", roomOrders);
			request.setAttribute("customerVOs", customerVOs);
			// 準備請求轉向跳轉頁面
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/cmpRoomOrder/cmp_findOrder.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			errorMsgs.put("其他錯誤發生", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/front-end/company/cmp_index.jsp");
			rd.forward(request, response);
		}

	}

	

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
