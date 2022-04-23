package com.taiwan.service.company.impl;



import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.Company;
import com.taiwan.service.company.CompanyService;

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
			company = new Company();
			//1). 表示登入失敗, 設置回顯的錯誤訊息
			company.setMessage("帳號或密碼錯誤");
			company.setCmpAccount(account);
			company.setSuccessful(false);
			//2). 設置要跳轉的地址
			company.setUrl("/front-end/company/cmp_login.jsp");
			//3). 回傳結果
			return company;
		}else {
			// 進入到這, 表示有對應的廠商紀錄
			if ("停權".equals(company.getCmpStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("你已被停權!!");
				company.setSuccessful(false);
				
				//設置要跳轉的地址
				company.setUrl("/front-end/company/cmp_login.jsp");
				return company;
			}else if ("審核未通過".equals(company.getAuditStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("審核未通過, 請補件!");
				company.setSuccessful(false);
				//設置要跳轉的地址
				company.setUrl("/front-end/company/cmp_regist.jsp");
				
				return company;
			}else if ("待審核".equals(company.getAuditStatus())) {
				//設置回顯的錯誤訊息
				company.setMessage("審核中, 請靜待審核結果!");
				company.setSuccessful(false);
				//設置要跳轉的地址
				company.setUrl("/front-end/company/cmp_login.jsp");
				
				return company;
			}else {
				//表示成功登入
				company.setSuccessful(true);
				company.setUrl("/front-end/company/cmp_index.jsp");
				
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
			company.setUrl("/front-end/company/cmp_regist.jsp");
			return company;	
		}
		
		//數據校驗完成之後, 就可以調用 dao 層跟數據庫做互動
		int result = companyMapper.insertCompany(company);
		
		if (result == 0) {
			company.setMessage("註冊失敗");
			company.setSuccessful(false);
			company.setUrl("/front-end/company/cmp_regist.jsp");
			return company;			
		}else {
			company.setMessage("註冊成功");
			company.setSuccessful(true);
			company.setUrl("/front-end/company/cmp_regist_success.jsp");
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

	
	//更新廠商營業登記證路徑(因為註冊的寫法改了, 這個暫時用不到)
	@Override
	@Transactional
	public boolean updateSerialNoPath(String cmpAccount, String serialNo) {
		int result = companyMapper.updateSerialNoByCmpAccount(cmpAccount, serialNo);
		return result != 0;
	}

	
	//根據廠商編號查詢廠商(用戶)
	@Override
	@Transactional(readOnly = true)
	public Company getCompanyByCmpId(Integer cmpId) {
		Company company = companyMapper.queryCompanyByCmpId(cmpId);
		
		//判斷是否有數據
		if (company == null) {
			//說明沒有此廠商編號
			company = new Company();
			company.setSuccessful(false);
			company.setMessage("查無此廠商資訊");
			company.setUrl("/front-end/company/cmp_index.jsp");
			return company;
		}else {
			//說明有找到廠商
			company.setSuccessful(true);
			company.setUrl("/front-end/company/cmp_edit.jsp");
			return company;
		}
	}

	
	//根據廠商編號更新廠商
	@Override
	@Transactional
	public Company updateCompanyById(Company company) {
		int result = companyMapper.updateCompanyByCmpId(company);
		
		
		if (result == 0) {
			Company cmp = new Company();
			//說明更新失敗
			cmp.setSuccessful(false);
			cmp.setMessage("修改資料失敗");
			cmp.setUrl("/front-end/company/cmp_edit.jsp");
			return cmp;
		}else {
			//說明更新成功
			company.setSuccessful(true);
			company.setMessage("修改資料成功");
			//重定向回修改頁面, 看看是否有修改成功
			company.setUrl("redirect:/company/getCompany?cmpId=" + company.getCmpId());
			return company;
		}
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


	//數據校驗
	public static Map<String, String> check(Map map){
		//用來存儲錯誤訊息
		Map<String, String> errorMap = new HashMap<String, String>();
		
		//獲取 key 的迭代器
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
        	//獲取到 key 值
            String key = (String)iterator.next();
            
            //判斷遍歷到的 key 是不是 cmpName
            if ("cmpName".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);            	
            	//進行 cmpAccount 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
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
//            		!values[i].matches("^[0-9]*$")
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入手機號碼或電話號碼");
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
//            		!values[i].matches("^[0-9]*$")
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
            
            //判斷遍歷到的 key 是不是 location
            if ("location".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);  
            	//進行 location 值的判斷
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


	//獲取圖片路徑
	public static String getPath(MultipartFile uploadFile, HttpSession session, Company company) {
		//此時這裡的數據都是過濾好的
		// 用於獲取需要保存的圖片路徑
		String savePath = null;
		// 圖片在服務器中的真實路徑
		String finalPath = null;

		// 獲取圖片路徑
		// 獲取上傳的文件的文件名
		String fileName = uploadFile.getOriginalFilename();
		System.out.println(fileName);
		// 處理文件重名問題, 並拿到文件後綴名
		String hzName = fileName.substring(fileName.lastIndexOf("."));
		System.out.println(hzName);
		// 將 UUID 和文件後綴名結合
		fileName = UUID.randomUUID().toString() + hzName;
		System.out.println(fileName);

		
		// 獲取服務器中 images 目錄的路徑;
		ServletContext servletContext = session.getServletContext();
		System.out.println(session.getServletContext().getRealPath("/"));
		String imagePath = servletContext.getRealPath("/images");
		System.out.println(imagePath);
		File file = new File(imagePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		
		// 廠商目錄: /images/Company
		String companyPath = imagePath + File.separator + "Company";
		System.out.println(companyPath);
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

		
		// 圖片在服務器中的真實路徑
		finalPath = cmpAccountPath + File.separator + fileName;
		System.out.println(finalPath);
		
		
		// 這個才是我們需要存的路徑: images/Company/廠商帳號/營業登記證.jpg
		savePath = "images/Company/" + company.getCmpAccount() + "/" + fileName;
		System.out.println(savePath);
		
		return savePath;
	}


	//將字符串轉成int類型
	public static int parseInt(String id, int defaultValue) {
		try {
			return Integer.parseInt(id);
		} catch (Exception e) {
			
		}
		//轉換失敗返回默認值
		return defaultValue;
	}
}




