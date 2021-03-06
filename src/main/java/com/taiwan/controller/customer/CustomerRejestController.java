package com.taiwan.controller.customer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.Company;
import com.taiwan.beans.Customer;
import com.taiwan.beans.EmployeeVO;
import com.taiwan.service.company.CompanyService;
import com.taiwan.service.customer.CustomerRejectService;
import com.taiwan.service.customer.CustomerService;
import com.taiwan.service.employee.EmployeeService;

@Controller
@RequestMapping("/cust")
public class CustomerRejestController {

	@Autowired
	private CustomerRejectService customerRejectService;

	@RequestMapping("/regist")
	public String regist(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request, Model model,
			HttpSession session) throws IllegalStateException, IOException, ParseException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String road = request.getParameter("road");

		String address = city + town + road;
//		String address = request.getParameter("address");
		String idCard = request.getParameter("idCard");
		String birth = request.getParameter("birth");
		String card = request.getParameter("card");
		System.out.println(card);

		System.out.println(sex);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		format.setTimeZone(TimeZone.getTimeZone("Asia/Taipei"));
		Date date = format.parse(birth);

		Customer customer = new Customer();
		customer.setAccount(account);
		customer.setPassword(password);
		customer.setName(name);
		customer.setSex(sex);
		customer.setTel(tel);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setIdCard(idCard);
		customer.setBirth(date);
		customer.setCard(card);

		// 1. 進行數據校驗
		Map<String, String> errorMap = CustomerRejectService.check(request.getParameterMap());

		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			model.addAttribute("registCustomer", customer);
			// 回到註冊頁面
			return "/front-end/rejest/custmomer_reject.jsp";
		}

		// 3. 判斷有沒有上傳圖檔
		if (uploadFile.isEmpty()) {
			// 註冊失敗, 回到註冊頁面並回顯訊息
			errorMap.put("uploadFile", "未上傳大頭貼");
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
//			model.addAttribute("registCustomer", customerVO);
			return "/front-end/rejest/custmomer_reject.jsp";
		}

		// 根據業務方法獲取路徑
		String savePath = CustomerRejectService.getPath(uploadFile, session, customer);
		// 設置路徑
		customer.setImg(savePath);

		// 調用 service 層的業務方法
		Customer registCustomerVO = customerRejectService.regist(customer);

		if (registCustomerVO.isSuccessful()) {

			// 成功執行文件上傳至服務器的操作
			String realPath = session.getServletContext().getRealPath("/");
			uploadFile.transferTo(new File(realPath + savePath));

			

			// 設定收件人
			String sendEmail = customer.getEmail();
			// 設定主旨
			String subject = "註冊成功通知信件";
			// 設定內容
			String messageText = "Hello ~!" + customer.getName() + "感謝您成為我們的會員\n" + "請點選網址, 開通會員資格";
			// 寄信通知廠商
			customerRejectService.sendEmail(email, subject, messageText);

			//
//			model.addAttribute("cmpName", registCompany.getCmpName());
		} else {
			// 註冊失敗, 回到註冊頁面並回顯訊息
			System.out.println("這裡是控制曾118");
			model.addAttribute("registCustomercustomerVO", registCustomerVO);
		}

		return registCustomerVO.getUrl();

		// 獲取圖片路徑
//		String savePath = CustomerRejectService.getPath(uploadFile, session, customer);
//		customer.setImg(savePath);

		// 調用 service 層的業務方法
//		Customer registCustomerVO = customerRejectService.regist(customer);

//		if (registCustomerVO.isSuccessful()) {
//			// 成功執行文件上傳至服務器的操作
//			String realPath = session.getServletContext().getRealPath("/");
//			uploadFile.transferTo(new File(realPath + savePath));
//		} else {
		// 註冊失敗, 回到註冊頁面並回顯訊息
//			System.out.println("這裡是控制曾118");
//			model.addAttribute("registCustomercustomerVO", registCustomerVO);
//		}

//		// 設定收件人
//		String sendEmail = customer.getEmail();
//		// 設定主旨
//		String subject = "註冊成功通知信件";
//		// 設定內容
//		String messageText = "Hello ~!" + customer.getName() + "感謝您成為我們的會員\n" + "請點選網址, 開通會員資格";
//		// 寄信通知廠商
//		customerRejectService.sendEmail(email, subject, messageText);

		// 進行頁面跳轉
//		return registCustomerVO.getUrl();
	}
}
