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

		// 1. ??????????????????
		Map<String, String> errorMap = CustomerRejectService.check(request.getParameterMap());

		// 2. ?????? errorMap ?????????, ???????????? 0 ????????????????????????, ?????????????????????????????????????????????
		if (errorMap.size() > 0) {
			// ?????????????????????
			model.addAttribute("errorInfo", errorMap);
			// ??????????????????
			model.addAttribute("registCustomer", customer);
			// ??????????????????
			return "/front-end/rejest/custmomer_reject.jsp";
		}

		// 3. ???????????????????????????
		if (uploadFile.isEmpty()) {
			// ????????????, ?????????????????????????????????
			errorMap.put("uploadFile", "??????????????????");
			model.addAttribute("errorInfo", errorMap);
			// ??????????????????
//			model.addAttribute("registCustomer", customerVO);
			return "/front-end/rejest/custmomer_reject.jsp";
		}

		// ??????????????????????????????
		String savePath = CustomerRejectService.getPath(uploadFile, session, customer);
		// ????????????
		customer.setImg(savePath);

		// ?????? service ??????????????????
		Customer registCustomerVO = customerRejectService.regist(customer);

		if (registCustomerVO.isSuccessful()) {

			// ?????????????????????????????????????????????
			String realPath = session.getServletContext().getRealPath("/");
			uploadFile.transferTo(new File(realPath + savePath));

			

			// ???????????????
			String sendEmail = customer.getEmail();
			// ????????????
			String subject = "????????????????????????";
			// ????????????
			String messageText = "Hello ~!" + customer.getName() + "??????????????????????????????\n" + "???????????????, ??????????????????";
			// ??????????????????
			customerRejectService.sendEmail(email, subject, messageText);

			//
//			model.addAttribute("cmpName", registCompany.getCmpName());
		} else {
			// ????????????, ?????????????????????????????????
			System.out.println("??????????????????118");
			model.addAttribute("registCustomercustomerVO", registCustomerVO);
		}

		return registCustomerVO.getUrl();

		// ??????????????????
//		String savePath = CustomerRejectService.getPath(uploadFile, session, customer);
//		customer.setImg(savePath);

		// ?????? service ??????????????????
//		Customer registCustomerVO = customerRejectService.regist(customer);

//		if (registCustomerVO.isSuccessful()) {
//			// ?????????????????????????????????????????????
//			String realPath = session.getServletContext().getRealPath("/");
//			uploadFile.transferTo(new File(realPath + savePath));
//		} else {
		// ????????????, ?????????????????????????????????
//			System.out.println("??????????????????118");
//			model.addAttribute("registCustomercustomerVO", registCustomerVO);
//		}

//		// ???????????????
//		String sendEmail = customer.getEmail();
//		// ????????????
//		String subject = "????????????????????????";
//		// ????????????
//		String messageText = "Hello ~!" + customer.getName() + "??????????????????????????????\n" + "???????????????, ??????????????????";
//		// ??????????????????
//		customerRejectService.sendEmail(email, subject, messageText);

		// ??????????????????
//		return registCustomerVO.getUrl();
	}
}
