package com.taiwan.controller.company;

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
import com.taiwan.service.company.CompanyService;
import com.taiwan.service.company.impl.CompanyServiceImpl;
import com.taiwan.utils.CompanyUtils;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	// 登入
	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model) {
		// 1. 獲取請求參數 cmp_account(帳號), cmp_password(密碼)
		String cmpAccount = request.getParameter("cmpAccount");
		String cmpPassword = request.getParameter("cmpPassword");

		// 2. 進行數據校驗
		Map<String, String> errorMap = CompanyServiceImpl.check(request.getParameterMap());

		// 3. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			model.addAttribute("cmpAccount", cmpAccount);
			// 回到登陸頁面
			return "/front-end/company/cmp_login.jsp";
		}

		// 4. 調用 CompanyService 的 login(String username, String password): Company
		Company company = companyService.login(cmpAccount, cmpPassword);

		// 5. 判斷 company
		if (company.isSuccessful()) {
			// 表示登入成功, 將廠商訊息保存在 session 當中
			request.getSession().setAttribute("loginCompany", company);
		} else {
			// 表示登入失敗
			// 將 company 存放到 request 域當中, 方便頁面做回顯訊息
			request.setAttribute("loginCompany", company);
		}

		// 進行跳轉
		return company.getUrl();
	}

	// 登出
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		// 銷毀session域中的用戶訊息
		request.getSession().invalidate();

		// 重定向到首頁
		return "redirect:/front-end/company/cmp_login.jsp";
	}

	// 註冊
	@RequestMapping("/regist")
	public String regist(Company company, @RequestParam("uploadFile") MultipartFile uploadFile,
			HttpServletRequest request, Model model, HttpSession session) throws IllegalStateException, IOException {
		// 1. 進行數據校驗
		Map<String, String> errorMap = CompanyServiceImpl.check(request.getParameterMap());

		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			// 回到註冊頁面
			return "/front-end/company/cmp_regist.jsp";
		}

		// 3. 判斷文件不為空
		if (uploadFile.isEmpty()) {
			// 註冊失敗, 回到註冊頁面並回顯訊息
			errorMap.put("uploadFile", "未上傳旅館登記證");
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			return "/front-end/company/cmp_regist.jsp";
		}

		// 獲取圖片路徑
		String savePath = CompanyServiceImpl.getPath(uploadFile, session, company);
		company.setSerialNo(savePath);

		// 調用 service 層的業務方法
		Company registCompany = companyService.regist(company);

		if (registCompany.isSuccessful()) {
			// 成功執行文件上傳至服務器的操作
			String realPath = session.getServletContext().getRealPath("/");
			uploadFile.transferTo(new File(realPath + savePath));
		} else {
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("registCompany", registCompany);
		}

		// 進行頁面跳轉
		return registCompany.getUrl();
	}

	// 獲取廠商訊息
	@RequestMapping("/getCompany")
	public String getCompany(@RequestParam(value = "cmpId") String cmpId, Model model) {
		// 1. 數據校驗
		int reslut = CompanyServiceImpl.parseInt(cmpId, 0);
		if (reslut == 0) {
			model.addAttribute("errorInfo", "編號必須是數字");
			return "/front-end/company/cmp_index.jsp";
		}

		// 2. 調用業務方法
		Company company = companyService.getCompanyByCmpId(reslut);
		model.addAttribute("editCompany", company);

		// 3. 請求轉發到修改頁面
		// service 層已經決定要取哪個頁面了
		return company.getUrl();
	}

	// 這個方法會提前所有的目標方法先運行, 要特別小心
	// @RequestParam(value = "id", required = false): 並不是每個請求都需要先查詢廠商, 所以我這邊設置為 false
	@ModelAttribute
	public void getCompanyByModelAttribute(@RequestParam(value = "cmpId", required = false) String cmpId, Model model) {
		int result = 0;
		// 做這個判斷是為了防止空指針異常
		if (cmpId != null) {
			// 1. 數據校驗
			result = CompanyServiceImpl.parseInt(cmpId, 0);
//			System.out.println("@ModelAttribute 進行數據校驗");
		}

		// 2. 數據是正確的情況下我才去查廠商資料
		if (result != 0 && result >= 20000) {
//			System.out.println("@ModelAttribute 進行廠商數據查找");
			// 先透過廠商編號查詢廠商
			Company company = companyService.getCompanyByCmpId(result);
			// 將這個廠商放入到隱含模型中
			model.addAttribute("modelAttributeCompany", company);
		}
	}

	// 廠商資料修改操作
	@RequestMapping("/editCompany")
	public String editCompany(@RequestParam(value = "cmpId") String cmpId, @ModelAttribute("modelAttributeCompany") Company company,
			Model model, HttpServletRequest request) {
		// 1. 進行數據校驗
		Map<String, String> errorMap = CompanyServiceImpl.check(request.getParameterMap());

		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			model.addAttribute("editCompany", company);
			// 回到修改頁面
			return "/front-end/company/cmp_edit.jsp";
		}
		
		System.out.println(cmpId);
		System.out.println(company.getCmpId());
		
		//3. 調用業務方法, 更新廠商訊息
		Company editCompany = companyService.updateCompanyById(company);
		if (editCompany.isSuccessful()) {
			//修改成功
		}
		return"";

//		// 將廠商編號轉成 String
//		String id = cmpId.toString();
//		// 重定向回 cmp_edit.jsp
//		return "redirect:/company/getCompany?cmpId=" + id;
	}
}
