package com.taiwan.service.impl;



import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.Company;
import com.taiwan.service.CompanyService;

import mybatis.mapper.CompanyMapper;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyMapper companyMapper;
	

	//登入
	@Override
	@Transactional(readOnly = true)
	public Company login(String account, String password) {
		//1. 去數據庫查看有沒有這條數據
		Company company = companyMapper.queryCompanyByAccountAndPassword(account, password);
		
		//2. 判斷 company 是否為 null
		if (company == null) {
			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
			Company cmp = new Company();
			//1). 表示登入失敗, 設置回顯的錯誤訊息
			cmp.setMessage("帳號或密碼錯誤");
			cmp.setCmpAccount(account);
			cmp.setSuccessful(false);
			//2). 設置要跳轉的地址
			cmp.setUrl("/company/cmp_login.jsp");
			//3). 回傳結果
			return cmp;
		}else {
			// 進入到這, 表示有對應的廠商紀錄
			if ("停權".equals(company.getCmpStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("你已被停權!!");
				company.setSuccessful(false);
				
				//設置要跳轉的地址
				company.setUrl("/company/cmp_login.jsp");
				return company;
			}else if ("審核未通過".equals(company.getAuditStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("審核未通過, 請補件!");
				company.setSuccessful(false);
				//設置要跳轉的地址
				company.setUrl("/company/cmp_regist.jsp");
				
				return company;
			}else if ("待審核".equals(company.getAuditStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("審核中, 請靜待審核結果!");
				company.setSuccessful(false);
				//設置要跳轉的地址
				company.setUrl("/company/cmp_login.jsp");
				
				return company;
			}else {
				//表示成功登入
				company.setSuccessful(true);
				company.setUrl("/company/cmp_index.jsp");
				
				return company;
			}
		}
	}


	//註冊
	@Override
	@Transactional
	public Company regist(Company company) {
		//首先判斷廠商帳號是否有重複
		if (existsCmpAccount(company.getCmpAccount())) {
			company.setMessage("用戶名已存在");
			company.setSuccessful(false);
			company.setUrl("/company/cmp_regist.jsp");
			return company;	
		}
		
		//數據校驗完成之後, 就可以調用 dao 層跟數據庫做互動
		int result = companyMapper.insertCompany(company);
		
		if (result == 0) {
			company.setMessage("註冊失敗");
			company.setSuccessful(false);
			company.setUrl("/company/cmp_regist.jsp");
			return company;			
		}else {
			company.setMessage("註冊成功");
			company.setSuccessful(true);
			company.setUrl("/company/cmp_regist_success.jsp");
			return company;	
		}
	}

	
	//判斷廠商帳號是否重複, 返回 true 說明已存在, 返回 false 說明不存在
	@Override
	@Transactional(readOnly = true)
	public boolean existsCmpAccount(String cmpAccount) {
		Company company = companyMapper.queryCompanyByCmpAccount(cmpAccount);
		
		if (company == null) {
			//說明不存在
			return false;
		}
		//說明已存在
		return true;
	}

	
	//更新廠商營業登記證路徑
	@Override
	@Transactional
	public boolean updateSerialNoPath(String cmpAccount, String serialNo) {
		int result = companyMapper.updateSerialNoByCmpAccount(cmpAccount, serialNo);
		return result != 0;
	}

	
	//根據廠商編號查詢廠商(管理員, 用戶)
	@Override
	@Transactional(readOnly = true)
	public Company getCompanyByCmpId(Integer cmpId) {

		Company company = companyMapper.queryCompanyByCmpId(cmpId);

		return company;
	}

	
	//根據廠商編號更新廠商
	@Override
	@Transactional
	public boolean updateCompanyById(Company company) {
		int result = companyMapper.updateCompanyByCmpId(company);
		
		return result != 0;
	}

	//查詢所有廠商(管理員)
	@Override
	@Transactional(readOnly = true)
	public List<Company> getAllCompany() {
		return companyMapper.queryAllCompany();
	}

	
	//根據廠商編號將審核狀態改為審核通過(管理員)
	@Override
	@Transactional
	public boolean updateAuditStatusByCmpId(Integer cmpId) {
		int result = companyMapper.updateAuditStatusByCmpId(cmpId, "審核通過");
		
		return result != 0;
	}

	
	//根據廠商編號修改廠商狀態
	@Override
	@Transactional
	public boolean updateStatusByCmpId(Integer cmpId, String status) {
		// 1. 數據校驗
		if (status.equals("正常") || status.equals("停權")) {
			int result = companyMapper.updateStatusByCmpId(cmpId, status);
			
			return result != 0;
		}
		return false;
	}
}




