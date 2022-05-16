package com.taiwan.service.company;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.Company;
import com.taiwan.beans.Page;
import com.taiwan.utils.CommonUtils;

import mybatis.mapper.CompanyMapper;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyMapper companyMapper;

	
	//獲取所有廠商
	@Transactional
	public List<Company> getAllCompanies(){
		List<Company> companies = companyMapper.getAllCompanies();
		return companies;
	}
	
	
	//根據地點獲取廠商
	public List<Company> getAllCompaniesByLocation(String location){
		List<Company> companies = companyMapper.getAllCompaniesByLocation(location);
		return companies;
	}
	
	
	
	//登入
	@Transactional(readOnly = true)
	public Company login(String account, String password) {
		
		//數據校驗
		if("".equals(account) || "".equals(password)) {
			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
			Company company = new Company();
			//1). 表示登入失敗, 設置回顯的錯誤訊息
			company.setMessage("帳號或密碼錯誤");
			company.setCmpAccount(account);
			company.setSuccessful(false);
			//2). 回傳結果
			return company;
		}
		if (!account.matches("^[a-zA-Z]\\w{3,17}$")) {
			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
			Company company = new Company();
			//1). 表示登入失敗, 設置回顯的錯誤訊息
			company.setMessage("帳號必須由字母,數字下滑線組成,並且長度為4到18位");
			company.setCmpAccount(account);
			company.setSuccessful(false);
			//2). 回傳結果
			return company;
		}
		if (!password.matches("^[a-zA-Z]\\w{3,17}$")) {
			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
			Company company = new Company();
			//1). 表示登入失敗, 設置回顯的錯誤訊息
			company.setMessage("密碼必須由字母,數字下滑線組成,並且長度為4到18位");
			company.setCmpAccount(account);
			company.setSuccessful(false);
			//2). 回傳結果
			return company;
		}		
		
		//1. 去數據庫查看有沒有這條數據
		Company company = companyMapper.queryCompanyByCmpAccountAndCmpPassword(account, password);
		
		//2. 判斷 company 是否為 null
		if (company == null) {
			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
			company = new Company();
			//1). 表示登入失敗, 設置回顯的錯誤訊息
			company.setMessage("帳號或密碼錯誤");
			company.setCmpAccount(account);
			company.setSuccessful(false);
			//2). 設置要跳轉的地址
			company.setUrl("/front-end/company/login.jsp");
			//3). 回傳結果
			return company;
		}else {
			// 進入到這, 表示有對應的廠商紀錄
			if ("停權".equals(company.getCmpStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("你已被停權!!");
				company.setSuccessful(false);
				//設置要跳轉的地址
				company.setUrl("/front-end/company/login.jsp");
				return company;
			}else if ("審核未通過".equals(company.getAuditStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("審核未通過, 請補件!");
				company.setSuccessful(true);
				//設置要跳轉的地址
				company.setUrl("/front-end/company/edit.jsp");
				
				return company;
			}else if ("待審核".equals(company.getAuditStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("審核中, 請靜待審核結果!");
				company.setSuccessful(false);
				//設置要跳轉的地址
				company.setUrl("/front-end/company/login.jsp");
				
				return company;
			}else {
				//表示成功登入
				company.setSuccessful(true);
				company.setUrl("/front-end/company/index.jsp");
				return company;
			}
		}
	}

	
	
	//登入
//	@Transactional(readOnly = true)
//	public Company login(String account, String password) {
//		//數據校驗
//		if("".equals(account) || "".equals(password)) {
//			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
//			Company company = new Company();
//			//1). 表示登入失敗, 設置回顯的錯誤訊息
//			company.setMessage("帳號或密碼錯誤");
//			company.setCmpAccount(account);
//			company.setSuccessful(false);
//			//2). 回傳結果
//			return company;
//		}
//		if (!account.matches("^[a-zA-Z]\\w{3,17}$")) {
//			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
//			Company company = new Company();
//			//1). 表示登入失敗, 設置回顯的錯誤訊息
//			company.setMessage("帳號必須由字母,數字下滑線組成,並且長度為4到18位");
//			company.setCmpAccount(account);
//			company.setSuccessful(false);
//			//2). 回傳結果
//			return company;
//		}
//		if (!password.matches("^[a-zA-Z]\\w{3,17}$")) {
//			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
//			Company company = new Company();
//			//1). 表示登入失敗, 設置回顯的錯誤訊息
//			company.setMessage("密碼必須由字母,數字下滑線組成,並且長度為4到18位");
//			company.setCmpAccount(account);
//			company.setSuccessful(false);
//			//2). 回傳結果
//			return company;
//		}
//		
//		
//		//1. 去數據庫查看有沒有這條數據
//		Company company = companyMapper.queryCompanyByCmpAccountAndCmpPassword(account, password);
//		
//		//2. 判斷 company 是否為 null
//		if (company == null) {
//			// 等於 null, 就創建一個新的 Company 物件, 方便設置回顯訊息
//			company = new Company();
//			//1). 表示登入失敗, 設置回顯的錯誤訊息
//			company.setMessage("帳號或密碼錯誤");
//			company.setCmpAccount(account);
//			company.setSuccessful(false);
//			//2). 回傳結果
//			return company;
//		}else {
//			// 進入到這, 表示有對應的廠商紀錄
//			if ("停權".equals(company.getCmpStatus())) {
//				//1). 設置回顯的錯誤訊息
//				company.setMessage("你已被停權!!");
//				company.setSuccessful(false);
//				//2). 回傳結果
//				return company;
//			}else if ("審核未通過".equals(company.getAuditStatus())) {
//				//1). 設置回顯的錯誤訊息
//				company.setMessage("審核未通過, 請補件!");
//				company.setSuccessful(true);//設置true, 只是說明他已經註冊過了
//				//2). 設置地址值
//				company.setUrl("front-end/company/edit.jsp");
//				//3). 回傳結果
//				return company;
//			}else if ("待審核".equals(company.getAuditStatus())) {
//				//1). 設置回顯的錯誤訊息
//				company.setMessage("審核中, 請靜待審核結果!");
//				company.setSuccessful(false);
//				//2). 回傳結果
//				return company;
//			}else {
//				//1). 表示成功登入
//				company.setSuccessful(true);
//				company.setUrl("front-end/company/index.jsp");
//				//2). 回傳結果
//				return company;
//			}
//		}
//	}

	//判斷廠商帳號是否重複, 返回 true 說明已存在, 返回 false 說明不存在
//	@Transactional(readOnly = true)
//	public boolean existsCmpAccount(String cmpAccount) {
//		Company company = companyMapper.queryCompanyByCmpAccount(cmpAccount);
//		
//		if (company == null) {
//			//說明不存在
//			return false;
//		}
//		//說明已存在
//		return true;
//	}
	
	
	//註冊
	@Transactional
	public Company regist(Company company) {
		//首先判斷廠商帳號是否有重複
		if (existsCmpAccount(company.getCmpAccount())) {
			company.setMessage("廠商帳號已存在");
			company.setSuccessful(false);
			company.setUrl("/front-end/company/regist.jsp");
			return company;	
		}
		
		//數據校驗完成之後, 就可以調用 dao 層跟數據庫做互動
		int result = companyMapper.insertCompany(company);
		
		if (result == 0) {
			company.setMessage("註冊失敗");
			company.setSuccessful(false);
			company.setUrl("/front-end/company/regist.jsp");
			return company;			
		}else {
			company.setMessage("註冊成功");
			company.setSuccessful(true);
			company.setUrl("/front-end/company/regist_success.jsp");
			return company;	
		}
	}
	//查詢廠商帳號是否存在: 返回 true 說明已存在, 返回 false 說明不存在
	//因為註冊實的帳號必須是唯一性
	@Transactional(readOnly = true)
	public boolean existsCmpAccount(String cmpAccount) {
		Company company = companyMapper.queryCompanyByCmpAccount(cmpAccount);
		return company != null;
	}
	
	
	//註冊
//	@Transactional
//	public Company regist(Company company) {
//		//數據校驗完成之後, 就可以調用 dao 層跟數據庫做互動
//		int result = companyMapper.insertCompany(company);
//		
//		if (result == 0) {
//			company.setMessage("註冊失敗");
//			company.setSuccessful(false);
//			company.setUrl("/front-end/company/regist.jsp");
//			return company;			
//		}else {
//			company.setMessage("註冊成功");
//			company.setSuccessful(true);
//			company.setUrl("/front-end/company/regist_success.jsp");			
//			return company;	
//		}
//	}

	
	//根據廠商帳號和廠商密碼來查詢廠商(配合 @ModelAttribute 使用的)
//	public Company getCompanyByCmpAccountAndCmpPassword(String cmpAccount, String cmpPassword) {
//		Company company = companyMapper.queryCompanyByCmpAccountAndCmpPassword(cmpAccount, cmpPassword);
//		return company;
//	}

	
	//根據廠商編號查詢廠商
	@Transactional(readOnly = true)
	public Company getCompanyByCmpId(String cmpId) {
		Integer id = CommonUtils.parseInt(cmpId, 0);
		if (id == 0) {
			return null;
		}
		Company company = companyMapper.queryCompanyByCmpId(id);
		return company;
	}
	//審核未通過的廠商修改廠商訊息
	//修改廠商訊息
	@Transactional
	public Company updateRegistCompany(Company company) {
		//調用業務方法
		int result = companyMapper.updateCompanyByCmpId(company);
		
		Company editCompany = companyMapper.queryCompanyByCmpId(company.getCmpId());
		
		if("待審核".equals(company.getAuditStatus())){
			//說明是審核未通過的, 所以當提交完之後就要回到註冊成功頁面
			editCompany.setUrl("/front-end/company/regist_success.jsp");
			editCompany.setSuccessful(false);
		}else if (result != 0) {			
			// 說明修改成功
			editCompany.setMessage("修改資料成功");
			editCompany.setSuccessful(true);
			editCompany.setUrl("/front-end/company/edit.jsp");
		}else {			
			// 說明修改失敗
			editCompany.setMessage("修改資料失敗");
			editCompany.setSuccessful(true);
			editCompany.setUrl("/front-end/company/edit.jsp");
		}
		
		return editCompany;
	}
	
	//分頁
	public Page<Company> page(int pageNo, int pageSize) {
		//pageNo, pageSize都是從頁面傳遞過來的,所以我們這邊只需要獲取,pageTotal, pageTotalCount, items
		
		// 1.先創建page對象
		Page<Company> page = new Page<Company>();
		
		// 3.設置每頁顯示數量
		page.setPageSize(pageSize);
		
		// 4.設置總紀錄數
		Integer pageTotalCount = companyMapper.queryForPageTotalCount();
		page.setPageTotalCount(pageTotalCount);
		
		// 5.設置總頁碼 [總頁碼 = 總紀錄數 / 每頁顯示數量 , 如果有餘數則總頁碼 + 1]
		Integer pageTotal = pageTotalCount / pageSize ;	
		
		if (pageTotalCount % pageSize > 0) {
			pageTotal += 1;
		}
		
		page.setPageTotal(pageTotal);
		
		
		// 2.設置當前頁碼
		// 注意!!必須在這裡設置當前頁碼,因為這樣在setPageNo()方法裡面的pageTotal才會有值
		// 這樣才不會報空指針異常
		page.setPageNo(pageNo);	
		

		// 6.設置當前頁數據
		int begin = (page.getPageNo() - 1) * pageSize;
		List<Company> companies = companyMapper.queryForPageItems(begin, pageSize);
		page.setItems(companies);
		
		return page;
	}

	
	//根據審核狀態進行分頁
	public Page<Company> pageByAuditStatus(String auditStatus, int pageNo, int pageSize) {
		//pageNo, pageSize都是從頁面傳遞過來的,所以我們這邊只需要獲取,pageTotal, pageTotalCount, items
		System.out.println("CompanyService 1 pageNo: " + pageNo);
		
		// 1.先創建page對象
		Page<Company> page = new Page<Company>();
		
		// 3.設置每頁顯示數量
		page.setPageSize(pageSize);
		
		// 4.設置總紀錄數
		Integer pageTotalCount = companyMapper.queryForPageTotalCountByAuditStatus(auditStatus);		
		page.setPageTotalCount(pageTotalCount);
		
		// 5.設置總頁碼 [總頁碼 = 總紀錄數 / 每頁顯示數量 , 如果有餘數則總頁碼 + 1]
		Integer pageTotal = pageTotalCount / pageSize ;	
		
		if (pageTotalCount % pageSize > 0) {
			pageTotal += 1;
		}
		
		page.setPageTotal(pageTotal);
		
		
		// 2.設置當前頁碼
		// 注意!!必須在這裡設置當前頁碼,因為這樣在setPageNo()方法裡面的pageTotal才會有值
		// 這樣才不會報空指針異常
		System.out.println("CompanyService 2 pageNo: " + pageNo);
		
		page.setPageNo(pageNo);
		

		// 6.設置當前頁數據
		int begin = (page.getPageNo() - 1) * pageSize;
		System.out.println("CompanyService 3 begin:" + begin);
		List<Company> companies = companyMapper.queryForPageItemsByAuditStatus(auditStatus, begin, pageSize);
		page.setItems(companies);
		
		return page;
	}
	
	
	
	//根據廠商編號和審核狀態查詢廠商
	@Transactional(readOnly = true)
	public List<Company> getCompaniesByAuditStatus(String auditStatus){
		List<Company> companies = companyMapper.queryCompanyByAuditStatus(auditStatus);
		return companies;
	}
	
	
	//查詢所有廠商(管理員)
	@Transactional(readOnly = true)
	public List<Company> getAllCompany() {
		return companyMapper.queryAllCompany();
	}

	
	//根據廠商編號修改廠商狀態
	@Transactional
	public boolean updateStatusByCmpId(Integer cmpId, String status) {
		// 1. 先查看 id 是否正確
		if (cmpId == 0 && cmpId < 20000) {
			//說明不正確
			return false;
		}
		// 2. 先看狀態是否是以下兩個值
		if (status.equals("正常") || status.equals("停權")) {
			int result = companyMapper.updateStatusByCmpId(cmpId, status);
			
			return result != 0;
		}
		return false;
	}
	
	
	//根據廠商編號修改審核狀態(管理員)
	@Transactional
	public boolean updateAuditStatusByCmpId(Integer cmpId, String auditStatus) {
		//1. 數據校驗
		if (cmpId == 0 && cmpId < 20000) {
			return false;
		}
		
		int result = 0;
		
		Company company = companyMapper.queryCompanyByCmpId(cmpId);
		
		if("審核通過".equals(auditStatus) || "審核未通過".equals(auditStatus)) {
			String email = null;
			String subject = null;
			String messageText = null;
			if ("審核通過".equals(auditStatus)) {
				System.out.println("審核通過");
				// 設定收件人
				email = company.getCmpMail();
				// 設定主旨
				subject = "審核通過通知信件";
				// 設定內容
				messageText = "Hello ~!" + company.getCmpName() + ", 感謝您成為我們的廠商\n" + " 您的帳號已開通, 可以開始使用了";
			}else if ("審核未通過".equals(auditStatus)) {
				System.out.println("審核未通過");
				// 設定收件人
				email = company.getCmpMail();
				// 設定主旨
				subject = "審核未通過通知信件";
				// 設定內容
				messageText = "Hello ~!" + company.getCmpName() + ", 感謝您成為我們的廠商\n" + "由於您的審核未通過, 請盡速補件";
				
			}
			System.out.println(email);
			// 寄信通知廠商
			sendEmail(email, subject, messageText);
			//是這兩個值我們才做修改操作
			result = companyMapper.updateAuditStatusByCmpId(cmpId, auditStatus);
		}
		return result != 0;
	}
	
	
	
	//寄信
	public void sendEmail(String cmpMail, String subject, String messageText) {
		try {
			// 放置 SMTP/SMTPS 相關參數到 java.util.Properties 物件
			// G-mail SMTPS Server
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			
			// 設定帳號密碼
			final String gmailAccount = "cga101g1@gmail.com";
			final String gmailPassword = "Tibamecga101g1";
			// 將帳號密碼放入Session中
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(gmailAccount, gmailPassword);
				}
			});
			
			// 建立javax.mail.Message 物件
			// javax.mail.Message 為抽象類別;
			// 使用 javax.mail.internet.MimeMessage 實作類別建立 Message 物件。
			// 電子郵件地址使用 javax.mail.internet.InternetAddress 物件表示。
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(gmailAccount));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(cmpMail));

			// 設定主旨
			message.setSubject(subject);
			// 設定內容
			message.setText(messageText);
			
			// 送出郵件
			Transport.send(message);
			System.out.println("成功");
		} catch (MessagingException e) {
			System.out.println("失敗");
			e.printStackTrace();
		}
	}
	

	//註冊時的數據校驗
	public Map<String, String> registCheck(Map map){
		//用來存儲錯誤訊息
		Map<String, String> errorMap = new HashMap<String, String>();
		
		//獲取 key 的迭代器
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
        	//獲取到 key 值
            String key = (String)iterator.next();
            System.out.println("key: " + key);
            
            //判斷遍歷到的 key 是不是 cmpName
            if ("cmpName".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);            	
            	//進行 cmpAccount 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
            			System.out.println("數據有誤");
            			errorMap.put(key, "廠商名稱不能為空!");	
					}
            	}
            }
            
            
            //判斷遍歷到的 key 是不是 cmpTel
            if ("cmpTel".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 cmpTel 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (!values[i].matches("^[0-9]*$")) {
						errorMap.put(key, "請輸入手機號碼或電話號碼");
					}
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入手機號碼或電話號碼");
					}
            	}
			}
            
            
            //判斷遍歷到的 key 是不是 headBank
            if ("headBank".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 headBank 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (!values[i].matches("^[0-9]*$")) {
						errorMap.put(key, "請輸入數字");
					}
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入銀行帳號");
					}
            	}
			}
            
            //判斷遍歷到的 key 是不是 endBank
            if ("endBank".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 endBank 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (!values[i].matches("^[0-9]*$")) {
            			errorMap.put(key, "請輸入數字");
            		}
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "請輸入銀行帳號");
            		}
            	}
            }
            
            
            //判斷遍歷到的 key 是不是 cmpMail
            if ("cmpMail".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 cmpMail 值的判斷
            	for(int i = 0; i < values.length; i++) {            		
            		if (!values[i].matches("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$")) {
            			errorMap.put(key, "信箱格式錯誤!");
            		}
            	}
			}
            
            
            //判斷遍歷到的 key 是不是 cmper
            if("cmper".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 cmpMail 值的判斷
            	for(int i = 0; i < values.length; i++) {            		
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "廠商負責人必須填寫");
            		}
            	}
            }
            
            
            
            //判斷遍歷到的 key 是不是 cmperTel
            if ("cmperTel".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 cmperTel 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (!values[i].matches("^[0-9]*$")) {
            			errorMap.put(key, "請輸入數字");
            		}
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入手機號碼或電話號碼");
					}
            	}
			}
            
            
            //判斷遍歷到的 key 是不是 cmpAccount
            if ("cmpAccount".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 cmpAccount 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入帳號");
					}
            		if (!values[i].matches("^[a-zA-Z]\\w{3,17}$")) {
            			//正確格式：以字母開頭, 長度在 4 ~ 18 之間, 只能包含字符, 數字和下滑線
						errorMap.put(key, "必須由字母,數字下滑線組成,並且長度為4到18位");
					}
            	}
			}
            
            
            //判斷遍歷到的 key 是不是 cmpPassword
            if ("cmpPassword".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 cmpPassword 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入帳號");
					}
            		if (!values[i].matches("^[a-zA-Z]\\w{3,17}$")) {
            			//正確格式：以字母開頭, 長度在 4 ~ 18 之間, 只能包含字符, 數字和下滑線
						errorMap.put(key, "必須由字母,數字下滑線組成,並且長度為4到18位");
					}
            	}
			}
            
            
            //判斷遍歷到的 key 是不是 cmpIntroduce
            if ("cmpIntroduce".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 cmpIntroduce 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入飯店介紹");
					}
            	}
			}
            
            
            //判斷遍歷到的 key 是不是 checkinTime
            if ("checkinTime".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 checkinTime 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入入住時間");
					}
            	}
			}
            
            //判斷遍歷到的 key 是不是 checkoutTime
            if ("checkoutTime".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 checkoutTime 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入退房時間");
					}
            	}
			}
            
            
            //判斷遍歷到的 key 是不是 city
            if ("city".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 city 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "請輸入地址");
            		}
            	}
            }
            
            //判斷遍歷到的 key 是不是 town
            if ("town".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 town 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "請輸入地址");
            		}
            	}
            }
            
            //判斷遍歷到的 key 是不是 road
            if ("road".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 road 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "請輸入地址");
            		}
            	}
            }
            
            //判斷遍歷到的 key 是不是 notice
            if ("notice".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 notice 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "請輸入注意事項");
            		}
            	}
            }
            
            //判斷遍歷到的 key 是不是 canx
            if ("canx".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 canx 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
            			errorMap.put(key, "請輸入取消政策");
            		}
            	}
            }
        }
        
        
        return errorMap;
	}
	
	
	//獲取旅館登記證的保存路徑
	public String savePath(Company company, MultipartFile serialNo, HttpSession session) {
		//此時這裡的數據都是過濾好的
		// 用於獲取需要保存的圖片路徑
		String savePath = null;
		// 圖片在服務器中的真實路徑
		String finalPath = null;

		// 獲取圖片路徑
		// 獲取上傳的文件的文件名
		String fileName = serialNo.getOriginalFilename();
//		System.out.println(fileName);
		// 處理文件重名問題, 並拿到文件後綴名
		String hzName = fileName.substring(fileName.lastIndexOf("."));
//		System.out.println(hzName);
		// 將 UUID 和文件後綴名結合
		fileName = UUID.randomUUID().toString() + hzName;
//		System.out.println(fileName);

		
		// 獲取服務器中 images 目錄的路徑;
		ServletContext servletContext = session.getServletContext();
//		System.out.println(session.getServletContext().getRealPath(File.separator));
		String imagePath = servletContext.getRealPath(File.separator + "images");
//		System.out.println(imagePath);
		File file = new File(imagePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		
		// 廠商目錄: /images/Company
		String companyPath = imagePath + File.separator + "Company";
//		System.out.println(companyPath);
		File file2 = new File(companyPath);
		if (!file2.exists()) {
			file2.mkdir();
		}
		
		
		// 廠商目錄: /images/Company/廠商帳號
		String cmpAccountPath = companyPath + File.separator + company.getCmpAccount();
		System.out.println(cmpAccountPath);
		File file3 = new File(cmpAccountPath);
		if (!file3.exists()) {
			file3.mkdir();
		}

		
		// 圖片在服務器中的真實路徑: /images/Company/廠商帳號/xxxxx.jpg
		finalPath = cmpAccountPath + File.separator + fileName;
		System.out.println(finalPath);
		
		
		// 這個才是我們需要存的路徑: images/Company/廠商帳號/營業登記證.jpg
		savePath = "images/Company/" + company.getCmpAccount() + "/" + fileName;
		System.out.println(savePath);
		
		return savePath;
	}
	
}
