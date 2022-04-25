package com.taiwan.service.company;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;

import com.taiwan.beans.Company;

public interface CompanyService {

	//登入
	public Company login(String account, String password);
	
	//註冊
	public Company regist(Company company);

	//判斷廠商帳號是否重複
	public boolean existsCmpAccount(String cmpAccount);
	
	//更新廠商營業登記證路徑
	public boolean updateSerialNoPath(String cmpAccount, String serialNo); 
	
	//根據廠商編號查詢廠商(管理員, 用戶)
	public Company getCompanyByCmpId(Integer cmpId);
	
	//根據廠商編號更新廠商資料
	public Company updateCompanyById(Company company);
	
	//獲取所有廠商(管理員)
	public List<Company> getAllCompany();
	
	//根據廠商編號修改審核狀態(管理員)
	public boolean updateAuditStatusByCmpId(Integer cmpId, String auditStatus);
	
	//根據廠商編號修改廠商狀態
	public boolean updateStatusByCmpId(Integer cmpId, String status);
}
