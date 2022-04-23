package com.taiwan.controller.company;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taiwan.beans.Company;
import com.taiwan.service.company.CompanyService;

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
	
	
	//修改廠商的審核狀態
	@RequestMapping("/audit")
	public String auditCompany(@RequestParam("cmpId")Integer cmpId) {
		companyService.updateAuditStatusByCmpId(cmpId);
		
		//重定向回 cmp_manager.jsp 
		return "redirect:/manager/company/companies";
	}
	
	
	//查看廠商詳情
	@RequestMapping("/companyDetail")
	public String companyDetail(@RequestParam("cmpId")Integer cmpId, Model model) {
		Company company = companyService.getCompanyByCmpId(cmpId);
		model.addAttribute("company", company);
		return "/back-end/company/cmp_detail.jsp";
	}
	
	
	//修改廠商狀態
	@RequestMapping("/updateStatus")
	public String updateStatus(@RequestParam("cmpId")Integer cmpId, @RequestParam("status")String status) {
		companyService.updateStatusByCmpId(cmpId, status);
		return "redirect:/manager/company/companies";
	}
}
