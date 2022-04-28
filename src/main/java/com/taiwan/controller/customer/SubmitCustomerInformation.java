package com.taiwan.controller.customer;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;
import com.taiwan.beans.CustomerVO;
import com.taiwan.service.customer.impl.CustomerServiceImpl;
import com.taiwan.utils.UUIDFileName;

@WebServlet("/cust/SubmitCustomerInformation")
@MultipartConfig
public class SubmitCustomerInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SubmitCustomerInformation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		String name = request.getParameter("name").trim();
		if ("".equals(name)) {
			errorMsgs.put("name", "此為必填欄位");
		}
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel").trim();
		if (!tel.matches("^[\\d-()]+$")) {
			errorMsgs.put("tel", "電話號碼格式有誤");
		}
		String email = request.getParameter("email").trim();
		if (!email.matches("^.+@.+$")) {
			errorMsgs.put("email", "電子郵件格式有誤");
		}
		String address = request.getParameter("address").trim();
		String idCard = request.getParameter("idCard").trim().toUpperCase();
		if (!idCard.matches("^[A-Z]\\d{9}$")) {
			errorMsgs.put("idCard", "身份證字號格式有誤");
		}

		Date birth = null;
		String birthString = request.getParameter("birth").trim();
		if ("".equals(birthString)) {
			errorMsgs.put("birth", "此為必填欄位");
		} else {
			birth = Date.valueOf(birthString);
		}

		String account = request.getParameter("account").trim();
		if ("".equals(account)) {
			errorMsgs.put("account", "此為必填欄位");
		}
		String password = request.getParameter("password").trim();
		if ("".equals(password)) {
			errorMsgs.put("password", "此為必填欄位");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = request
					.getRequestDispatcher("/front-end/cust/UpdateCustomerInformation.jsp");
			failureView.forward(request, response);
			return;
		}
		// 取得上傳圖片
		Part part = request.getPart("imgUpdate");
		System.out.println("part=" + part);

		// 判斷有無選取新照片，用擷取content-disposition的值中的檔名，來看有無檔名做判斷
		String header = part.getHeader("content-disposition");
		System.out.println(header);
		String hasFileNameOrNot = header.substring(header.lastIndexOf("=") + 2, header.length() - 1);
		String img;
		if ("".equals(hasFileNameOrNot)) {
			System.out.println("沒修改");
			img = request.getParameter("imgOrigin");
		} else {
			System.out.println("有修改");
			// 幫圖片取新名字
			UUIDFileName uuidFileName = new UUIDFileName();
			String filename = uuidFileName.getUUIDFileName(part);
			// 創建存取路徑
			String saveDirectory = "/images/cust";
			String realPath = getServletContext().getRealPath(saveDirectory);
			File fsaveDirectory = new File(realPath);
			// 如果沒有這個路徑就創建一個
			if (!fsaveDirectory.exists()) {
				fsaveDirectory.mkdirs();
			}
			// 把圖片寫入路徑中
			part.write(realPath + "/" + filename);
			// 把路徑傳回db中
			String dbSaveDirectory = "images/cust";
			String dbPath = dbSaveDirectory + "/" + filename;
			img = dbPath;
		}

		String card = request.getParameter("card").trim();
		Integer custId = Integer.valueOf(request.getParameter("custId"));

		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		CustomerVO customerVO = customerServiceImpl.setUpdate(name, sex, tel, email, address, idCard, birth, account,
				password, img, card, custId);
		request.setAttribute("customer", customerVO);
		RequestDispatcher successView = request.getRequestDispatcher("/front-end/cust/CustomerInformation.jsp");
		successView.forward(request, response);
	}

}
