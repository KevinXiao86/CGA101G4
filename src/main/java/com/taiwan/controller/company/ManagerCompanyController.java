package com.taiwan.controller.company;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.taiwan.beans.Company;
import com.taiwan.beans.Page;
import com.taiwan.service.company.CompanyService;
import com.taiwan.utils.CommonUtils;

@Controller
@RequestMapping("/manager/company")
public class ManagerCompanyController {

	@Autowired
	private CompanyService companyService;

	@RequestMapping("/getCompaniesByPage")
	public String getCompaniesByPage(HttpServletRequest request, Model model) {
		// 1.接收請求參數 pageNo 和 pageSize, 並轉成int類型
		// 默認就是第一頁
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		// 默認每頁顯示數量就是4
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);

		// 2. 獲取page對象
		Page<Company> page = companyService.page(pageNo, pageSize);
		// 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
		page.setUrl("manager/company/getCompaniesByPage?");

		// 3.將獲取的page對象放入到request域中
		model.addAttribute("page", page);

		// 4.請求轉發到列表頁面
		return "/back-end/company/cmp_manager.jsp";
	}

	@RequestMapping("/getCompaniesByAuditStatus")
	public String getCompaniesByAuditStatus(HttpServletRequest request, Model model)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");

		// 1.接收請求參數 pageNo 和 pageSize, 並轉成int類型
		// 默認就是第一頁
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		// 默認每頁顯示數量就是4
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
		// 獲取審核狀態
		String auditStatus = request.getParameter("auditStatus");
		System.out.println(auditStatus);

		// 2. 獲取page對象
		Page<Company> page = companyService.pageByAuditStatus(auditStatus, pageNo, pageSize);

		// 3. 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
		StringBuilder stringBuilder = new StringBuilder("manager/company/getCompaniesByAuditStatus");
		if (request.getParameter("auditStatus") != null) {
			stringBuilder.append("?auditStatus=").append(request.getParameter("auditStatus") + "&");
		}

		// 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
		page.setUrl(stringBuilder.toString());

		// 4. 將獲取的page對象放入到request域中
		model.addAttribute("page", page);

		// 4.請求轉發到列表頁面
		return "/back-end/company/cmp_manager.jsp";
	}

	// 修改廠商狀態
	@RequestMapping("/updateStatus")
	public String updateStatus(@RequestParam("cmpId") String cmpId, @RequestParam("status") String status) {
		// 1. 數據校驗
		int id = CommonUtils.parseInt(cmpId, 0);
		// 2. 調用業務方法
		companyService.updateStatusByCmpId(id, status);
		// 3. 重定向回列表頁面
		return "redirect:/manager/company/getCompaniesByPage";
	}

	// 查看廠商詳情
	@RequestMapping("/companyDetail")
	public String companyDetail(@RequestParam("cmpId") String cmpId, Model model) {
		// 1. 獲取廠商
		Company company = companyService.getCompanyByCmpId(cmpId);
		// 2. 將廠商資料保存起來
		model.addAttribute("detailCompany", company);
		// 3. 進行頁面挑轉
		return "/back-end/company/cmp_detail.jsp";
	}

	// 告知廠商補件
	@RequestMapping("/sendEmail")
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String cmpId = request.getParameter("cmpId");
		// 1. 獲取廠商訊息
		Company company = companyService.getCompanyByCmpId(cmpId);

		// 設定收件人
		String email = company.getCmpMail();
		// 設定主旨
		String subject = "審核未通過通知信件";
		// 設定內容
		String messageText = "Hello ~!" + company.getCmpName() + ", 感謝您成為我們的廠商\n" + "由於您的審核未通過, 請盡速補件";
		// 寄信通知廠商
		companyService.sendEmail(email, subject, messageText);

		Gson gson = new Gson();
		String jsonString = gson.toJson("已告知廠商補件");
		response.getWriter().write(jsonString);
	}

	// 修改廠商的審核狀態
	@RequestMapping("/audit")
	public String auditCompany(@RequestParam("cmpId") String cmpId, @RequestParam("auditStatus") String auditStatus,
			Model model, HttpServletRequest request) {
		// 1.數據校驗
		int id = CommonUtils.parseInt(cmpId, 0);

		// 2. 調用業務方法
		boolean result = companyService.updateAuditStatusByCmpId(id, auditStatus);

		// 3. 判斷
		if (result) {
			// 3. 重定向回列表頁面
			return "redirect:/manager/company/companyDetail?cmpId=" + cmpId;
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}

//	// 告知廠商補件
//	@RequestMapping("sendEmail")
//	public String sendEmail(@RequestParam("cmpId") String cmpId, Model model, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
//		// 1. 獲取廠商訊息
//		Company company = companyService.getCompanyByCmpId(cmpId);
//		System.out.println(company);
//
//		// 設定收件人
//		String email = company.getCmpMail();
//		// 設定主旨
//		String subject = "審核未通過通知信件";
//		// 設定內容
//		String messageText = "Hello ~!" + company.getCmpName() + ", 感謝您成為我們的廠商\n" + "由於您的審核未通過, 請盡速補件";
//		// 寄信通知廠商
//		companyService.sendEmail(email, subject, messageText);
//
//		// 3. 重定向回列表頁面
//		return "redirect:/manager/company/getCompaniesByPage";
//	}

//	@RequestMapping("/getCompaniesByAuditStatus")
//	public String getCompaniesByAuditStatus(HttpServletRequest request, Model model){
//		String auditStatus = request.getParameter("auditStatus");
//		System.out.println(auditStatus);
//		// 查詢出所有廠商, 帶到頁面顯示
//		List<Company> companies = companyService.getCompaniesByAuditStatus(auditStatus);
//		model.addAttribute("companies", companies);
//		return "/back-end/company/cmp_manager.jsp";
//	}

//	// 查詢所有廠商
//	@RequestMapping("/companies")
//	public String getAllCompany(Model model) {
//		// 查詢出所有廠商, 帶到頁面顯示
//		List<Company> companies = companyService.getAllCompany();
//		model.addAttribute("companies", companies);
//		return "/back-end/company/cmp_manager.jsp";
//	}
}
