package com.taiwan.controller.company;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.parsing.GenericTokenParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.taiwan.beans.Company;
import com.taiwan.service.company.CompanyService;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	//登陸
	@RequestMapping("/login")
	@ResponseBody
	public Company login(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		//1. 獲取請求參數
		String cmpAccount = request.getParameter("cmpAccount");
		String cmpPassword = request.getParameter("cmpPassword");
		
		//4. 調用 login 業務方法 
		Company company = companyService.login(cmpAccount, cmpPassword);
		
		// 5. 判斷 company
		if (company.isSuccessful() && "審核通過".equals(company.getAuditStatus())) {
			// 表示登入成功, 將廠商訊息保存在 session 當中
			request.getSession().setAttribute("loginCompany", company);
		}else if(company.isSuccessful() && "審核未通過".equals(company.getAuditStatus())){
			request.getSession().setAttribute("editCompany", company);
			System.out.println("審核未通過");
		}
		
		//6. 回傳結果
		return company;
	}
	
	
	//驗證廠商帳號是否可用
	@RequestMapping("/existsCmpAccount")
	@ResponseBody
	public boolean existsCmpAccount(HttpServletRequest request, HttpServletResponse response) {
		String cmpAccount = request.getParameter("cmpAccount");
		boolean result = companyService.existsCmpAccount(cmpAccount);
		return result;
	}
	
	
	//註冊
	@RequestMapping("/regist")
	public String regist(@RequestParam("serialNo") MultipartFile serialNo, 
			HttpServletRequest request, Model model, HttpSession session) throws IllegalStateException, IOException {
		
//		由於自動封裝功能不能跟文件一起使用, 所以這邊使用手動封裝為物件
		String cmpName = request.getParameter("cmpName");
		String cmpTel = request.getParameter("cmpTel");
		String headBank = request.getParameter("headBank");
		String endBank = request.getParameter("endBank");
		String bankAccount = headBank + "-" + endBank;
		String cmpMail = request.getParameter("cmpMail");
		String cmper = request.getParameter("cmper");
		String cmperTel = request.getParameter("cmperTel");
		String cmpAccount = request.getParameter("cmpAccount");
		String cmpPassword = request.getParameter("cmpPassword");
		String cmpIntroduce = request.getParameter("cmpIntroduce");
		String checkinTime = request.getParameter("checkinTime");
		String checkoutTime = request.getParameter("checkoutTime");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String road = request.getParameter("road");
		String location = city + town + road;
		String notice = request.getParameter("notice");
		String canx = request.getParameter("canx");
		
		Company company = new Company(null, cmpName, cmpTel, cmpMail, cmper, cmperTel, null, null, null,
				cmpAccount, cmpPassword, cmpIntroduce, checkinTime, checkoutTime, location, notice, canx, bankAccount);		
		
		// 1. 進行數據校驗
		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());

		
		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			// 回到註冊頁面
			return "/front-end/company/regist.jsp";
		}		
		

		//判斷文件是否為空
		if (serialNo.isEmpty()) {
			System.out.println("訪問成功");
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("serialNo", "未上傳旅館登記證");
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			return "/front-end/company/regist.jsp";
		}
		
		
		//根據業務方法獲取路徑
		String savePath = companyService.savePath(company, serialNo, session);
		//設置路徑
		company.setSerialNo(savePath);
		
		Company registCompany = companyService.regist(company);
		
		if (registCompany.isSuccessful()) {
			// 成功執行文件上傳至服務器的操作
			String realPath = session.getServletContext().getRealPath("/");
			System.out.println("註冊成功");
			serialNo.transferTo(new File(realPath + savePath));
			
			//註冊成功要發信通知
			// 設定收件人
			String email = company.getCmpMail();
			// 設定主旨
			String subject = "註冊成功通知信件";
			// 設定內容
			String messageText = "Hello ~!" + company.getCmpName() + "感謝您成為我們的廠商\n" + "我們將盡快完成審核, 請靜待審核結果";
			// 寄信通知廠商
			companyService.sendEmail(email, subject, messageText);
			
			//
			model.addAttribute("cmpName", registCompany.getCmpName());
		} else {
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("registCompany", registCompany);
			System.out.println("註冊失敗");
		}

		return company.getUrl();
	}


	// 這個方法會提前所有的目標方法先運行, 要特別小心
	@ModelAttribute
	public void getCompanyByCmpAccountAndCmpPassword(@RequestParam(value = "cmpAccount", required = false)String cmpAccount,
			@RequestParam(value = "cmpPassword", required = false)String cmpPassword, HttpSession session) {
		
		if(cmpAccount != null && cmpPassword != null) {
			//數據校驗
			if ("".equals(cmpAccount)) {
				return;
			}
			if ("".equals(cmpPassword)) {
				return;
			}
			
			//去數據庫查數據
			Company company = companyService.getCompanyByCmpAccountAndCmpPassword(cmpAccount, cmpPassword);
			//判斷
			if (company != null) {
				// 將這個廠商放入到隱含模型中
				session.setAttribute("modelAttributeCompany", company);
				System.out.println("modelAttributeCompany" + company);
			}
		}
	}
	
	
	//審核未通過, 需要補件的廠商
	@RequestMapping("/editRegistCompany")
	public String editRegistCompany(HttpServletRequest request, Model model, HttpSession session) {
		//1. 數據校驗
		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());
		
		Company company = (Company) session.getAttribute("modelAttributeCompany");
		
		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			request.getSession().setAttribute("editCompany", company);
			// 回到註冊頁面
			return "/front-end/company/edit.jsp";
		}
		
		String cmpName = request.getParameter("cmpName");
		String cmpTel = request.getParameter("cmpTel");
		String headBank = request.getParameter("headBank");
		String endBank = request.getParameter("endBank");
		String bankAccount = headBank + "-" + endBank;
		String cmpMail = request.getParameter("cmpMail");
		String cmper = request.getParameter("cmper");
		String cmperTel = request.getParameter("cmperTel");
//		String cmpAccount = request.getParameter("cmpAccount");
		String cmpIntroduce = request.getParameter("cmpIntroduce");
		String checkinTime = request.getParameter("checkinTime");
		String checkoutTime = request.getParameter("checkoutTime");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String road = request.getParameter("road");
		String location = city + town + road;
		String notice = request.getParameter("notice");
		String canx = request.getParameter("canx");
		
		company.setCmpName(cmpName);
		company.setCmpTel(cmpTel);
		company.setBankAccount(bankAccount);
		company.setCmpMail(cmpMail);
		company.setCmper(cmper);
		company.setCmperTel(cmperTel);
		if("審核未通過".equals(company.getAuditStatus())) {			
			company.setAuditStatus("待審核");
		}else {
			company.setAuditStatus("審核通過");
		}
//		company.setCmpAccount(cmpAccount);
		company.setCmpIntroduce(cmpIntroduce);
		company.setCheckinTime(checkinTime);
		company.setCheckoutTime(checkoutTime);
		company.setLocation(location);
		company.setNotice(notice);
		company.setCanx(canx);
		
		System.out.println("editRegistCompany " + company);
		
		//調用業務方法
		Company editCompany = companyService.updateRegistCompany(company);
		request.getSession().setAttribute("editCompany", editCompany);
		
		return editCompany.getUrl();
	}


	@RequestMapping("/getCompany")
	public String getCompany(HttpServletRequest request) {
		String cmpId = request.getParameter("cmpId");
		
		Company company = companyService.getCompanyByCmpId(cmpId);
		request.getSession().setAttribute("editCompany", company);
		return "/front-end/company/edit.jsp";
	}
	
}
