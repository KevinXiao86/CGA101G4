package com.taiwan.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.Company;

public class CompanyUtils {

	//登入時的數據校驗
	public static Map<String, String> checkLogin(Map map){
		//用來存儲錯誤訊息
		Map<String, String> errorMap = new HashMap<String, String>();
		
		//獲取 key 的迭代器
        Iterator iterator = map.keySet().iterator();
        while(iterator.hasNext()){
        	//獲取到 key 值
            String key = (String)iterator.next();
            
            //判斷遍歷到的 key 是不是 cmpAccount
            if ("cmpAccount".equals(key)) {
            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
            	String[] values = (String[]) map.get(key);            	
            	//進行 cmpAccount 值的判斷
            	for(int i = 0; i < values.length; i++) {
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入帳號");
					}
            		if (!values[i].matches("^\\w+$")) {
            			errorMap.put(key, "只能輸入由數字、26個英文字母或者下劃線組成的字符串");	
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
            			errorMap.put(key, "請輸入密碼");
            		}
            	}
			}
        }
		return errorMap;
	}

	
	//註冊和修改時的數據校驗
	public static Map<String, String> checkRegistAndEdit(Map map){
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
            		if (!values[i].matches("^[a-zA-Z]\\w{3,17}$")) {
            			//正確格式：以字母開頭, 長度在 3 ~ 18 之間, 只能包含字符, 數字和下滑線
						errorMap.put(key, "必須由字母,數字下滑線組成,並且長度為3到18位");
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
            			//正確格式：以字母開頭, 長度在 6 ~ 18 之間, 只能包含字符, 數字和下滑線
						errorMap.put(key, "必須由字母,數字下滑線組成,並且長度為3到18位");
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

	
	//將字符串轉成int類型
	public static int parseInt(String id, int defaultValue) {
		try {
			return Integer.parseInt(id);
		} catch (Exception e) {
			
		}
		//轉換失敗返回默認值
		return defaultValue;
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
		// 處理文件重名問題, 並拿到文件後綴名
		String hzName = fileName.substring(fileName.lastIndexOf("."));
		// 將 UUID 和文件後綴名結合
		fileName = UUID.randomUUID().toString() + hzName;

		// 獲取服務器中 images 目錄的路徑;
		ServletContext servletContext = session.getServletContext();
		String imagePath = servletContext.getRealPath("/images");
		File file = new File(imagePath);
		if (!file.exists()) {
			file.mkdirs();
		}

		// 廠商目錄: /images/廠商帳號
		String cmpAccountPath = imagePath + File.separator + company.getCmpAccount();
		File file2 = new File(cmpAccountPath);
		if (!file2.exists()) {
			file2.mkdir();
		}

		// 圖片在服務器中的真實路徑
		finalPath = cmpAccountPath + File.separator + fileName;
		System.out.println(finalPath);

		System.out.println("文件上傳成功");
		// 這個才是我們需要存的路徑
		savePath = "images/" + company.getCmpAccount() + "/" + fileName;
		
		return savePath;
	}
}
