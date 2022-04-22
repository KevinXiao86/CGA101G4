package com.taiwan.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.Company;
import com.taiwan.service.CompanyService;
import com.taiwan.utils.CompanyUtils;


@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;

	//登入
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		//1. 獲取請求參數 cmp_account(帳號), cmp_password(密碼)
		String cmpAccount = request.getParameter("cmpAccount");
		String cmpPassword = request.getParameter("cmpPassword");
		
		//2. 進行數據校驗
		Map<String, String> errorMap = CompanyUtils.checkLogin(request.getParameterMap());
		
		//3. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			//說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			//回到登陸頁面
			return "/company/cmp_login.jsp";
		}
		
		//4. 調用 CompanyService 的 login(String username, String password): Company
		Company company = companyService.login(cmpAccount, cmpPassword);
		
		//5. 判斷 company
		if (company.isSuccessful()) {
			//表示登入成功, 將廠商訊息保存在 session 當中
			request.getSession().setAttribute("loginCompany", company);
			//跳轉到場商首頁
			return company.getUrl();
		}else {
			//將 company 存放到 request 域當中, 方便頁面做回顯訊息
			request.setAttribute("loginCompany", company);
			//跳轉回登陸頁面
			return company.getUrl();
		}
	}


	//登出
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		//銷毀session域中的用戶訊息
		request.getSession().invalidate();
		
		//重定向到首頁
		return "redirect:/company/cmp_login.jsp";
	}
	
	
	//註冊
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request, Model model) {
		//2. 進行數據校驗
		Map<String, String> errorMap = CompanyUtils.checkRegist(request.getParameterMap());
		
		//3. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			//說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			//回到登陸頁面
			return "/company/cmp_regist.jsp";
		}
		return "";
	}


	//獲取廠商訊息
	@RequestMapping("/getCompany")
	public String getCompany(@RequestParam("cmpId")Integer cmpId, Model model) {
		Company company = companyService.getCompanyByCmpId(cmpId);
		model.addAttribute("company", company);
		//請求轉發到修改頁面
		return "/company/cmp_edit.jsp";
	}
	//這個方法會提前所有的目標方法先運行, 要特別小心
	//@RequestParam(value = "id", required = false): 並不是每個請求都需要先查詢廠商, 所以我這邊設置為 false
	@ModelAttribute
	public void getCompanyByModelAttribute(@RequestParam(value = "cmpId", required = false)Integer cmpId, Model model) {
		//先判斷 id 是不是空值, 因為並不是所有的請求都會有 id, 以防空指針異常
		if (cmpId != null) {			
			//先透過廠商編號查詢廠商
			Company company = companyService.getCompanyByCmpId(cmpId);
			//將這個廠商
			model.addAttribute("company", company);
		}
	}
	//廠商資料修改操作
	@RequestMapping("/editCompany")
	public String editCompany(@RequestParam(value = "cmpId")Integer cmpId,@ModelAttribute("company")Company company,
			Model model, HttpServletRequest request) {
		//更新廠商訊息
		companyService.updateCompanyById(company);
		//將廠商編號轉成 String 
		String id = cmpId.toString();
		//重定向回 cmp_edit.jsp
		return "redirect:/company/getCompany?cmpId=" + id;		
	}
}
