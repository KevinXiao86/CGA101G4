package com.taiwan.controller.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taiwan.beans.Company;
import com.taiwan.service.company.CompanyService;
import com.taiwan.service.company.impl.CompanyServiceImpl;

@Controller
@RequestMapping("/manager/company")
public class ManagerCompanyController {

	@Autowired
	private CompanyService companyService;
	
	//查詢所有廠商
	@RequestMapping("/companies")
	public String getAllCompany(Model model) {
		//查詢出所有廠商, 帶到頁面顯示
		List<Company> companies = companyService.getAllCompany();
		model.addAttribute("companies", companies);
		return "/back-end/company/cmp_manager.jsp";
	}
	

	//修改廠商狀態
	@RequestMapping("/updateStatus")
	public String updateStatus(@RequestParam("cmpId")String cmpId, @RequestParam("status")String status) {
		//1. 數據校驗
		int id = CompanyServiceImpl.parseInt(cmpId, 0);
		//2. 調用業務方法
		companyService.updateStatusByCmpId(id, status);
		//3. 進行頁面跳轉
		return "redirect:/manager/company/companies";
	}

	
	//查看廠商詳情
	@RequestMapping("/companyDetail")
	public String companyDetail(@RequestParam("cmpId")String cmpId, Model model) {
		//1. 數據校驗
		int id = CompanyServiceImpl.parseInt(cmpId, 0);
		//2. 調用業務方法
		Company company = companyService.getCompanyByCmpId(id);
		//3. 判斷 company
		if (company == null) {
			company = new Company();
			//跳轉到錯誤頁面
			company.setUrl("/error/error404.jsp");
		}else {
			//說明有廠商訊息
			company.setUrl("/back-end/company/cmp_detail.jsp");
		}
		//4. 進行頁面挑轉
		model.addAttribute("detailCompany", company);
		return company.getUrl();
	}
	
	
	//修改廠商的審核狀態
	@RequestMapping("/audit")
	public String auditCompany(@RequestParam("cmpId")String cmpId, @RequestParam("auditStatus") String auditStatus, Model model) {
		//1.數據校驗
		int id = CompanyServiceImpl.parseInt(cmpId, 0);
		//2. 調用業務方法
		boolean result = companyService.updateAuditStatusByCmpId(id, auditStatus);
		//3. 判斷
		if (result) {			
			//重定向回 cmp_manager.jsp 
			return "redirect:/manager/company/companies";
		}else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}

}
