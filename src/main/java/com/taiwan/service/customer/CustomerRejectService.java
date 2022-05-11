package com.taiwan.service.customer;

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

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.Company;
import com.taiwan.beans.Customer;
import mybatis.mapper.CustomerMapper;

@Service
public class CustomerRejectService {

	@Autowired
	private CustomerMapper customerMapper;

	
	// 註冊
	@Transactional
	public Customer regist(Customer customer) {
		System.out.println(customer.getCard());
//		// 首先判斷顧客帳號是否有重複
//		if (existsCustAccount(customer.getAccount())) {
//			customer.setMessage("用戶名已存在");
//			customer.setSuccessful(false);
//			customer.setUrl("/front-end/company/cmp_regist.jsp");
//			return customer;
//		}

		// 數據校驗完成之後, 就可以調用 dao 層跟數據庫做互動
		int result = customerMapper.insertCustomer(customer);

		if (result == 0) {
			customer.setMessage("註冊失敗");
			customer.setSuccessful(false);
			customer.setUrl("/front-end/rejest/custmomer_reject.jsp");
			return customer;
		} else {
			customer.setMessage("註冊成功");
			customer.setSuccessful(true);
			customer.setUrl("/front-end/rejest/cust_regist_success.jsp");
			return customer;
		}
	}

	// 判斷廠商帳號是否重複, 返回 true 說明已存在, 返回 false 說明不存在
//	@Transactional(readOnly = true)
//	public boolean existsCustAccount(String Account) {
//		Customer customer = customerMapper.queryCustomerByCustAccount(Account);
//
//		if (customer == null) {
//			// 說明不存在
//			return false;
//		}
//		// 說明已存在
//		return true;
//	}

	// 寄信
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

	// 數據校驗
	public static Map<String, String> check(Map map) {
		// 用來存儲錯誤訊息
		Map<String, String> errorMap = new HashMap<String, String>();

		// 獲取 key 的迭代器
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			// 獲取到 key 值
			String key = (String) iterator.next();

			// 判斷遍歷到的 key 是不是 Name
			if ("name".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 cmpAccount 值的判斷
				for (int i = 0; i < values.length; i++) {
					if (values[i].trim().equals("")) {
						errorMap.put(key, "姓名不能為空!");
					}
				}
			}
			// 判斷遍歷到的 key 是不是 sex
			if ("sex".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 cmpTel 值的判斷
				for (int i = 0; i < values.length; i++) {
//	            		!values[i].matches("^[0-9]*$")
					if (values[i].trim().equals("")) {
						errorMap.put(key, "請選擇性別");
					}
				}
			}
			// 判斷遍歷到的 key 是不是 Tel
			if ("tel".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 cmpTel 值的判斷
				for(int i = 0; i < values.length; i++) {
//            		if (!values[i].matches("^[0-9]{2}\\D{8}*$")) {
//						errorMap.put(key, "請輸入手機號碼或電話號碼");
//					}
            		if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入手機號碼");
					}
            	}
			}
			// 判斷遍歷到的 key 是不是 email
			if ("email".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 cmpMail 值的判斷
				for (int i = 0; i < values.length; i++) {
					if (!values[i].matches("^([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})$")) {
						errorMap.put(key, "信箱格式錯誤!");
					}
				}
			}

//	            //判斷遍歷到的 key 是不是 address
	            if ("address".equals(key)) {
	            	//獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
	            	String[] values = (String[]) map.get(key);  
	            	//進行 location 值的判斷
	            	for(int i = 0; i < values.length; i++) {
	            		if (values[i].trim().equals("")) {
	            			errorMap.put(key, "請輸入地址");
	            		}
	            	}
	            }

			// 判斷遍歷到的 key 是不是 idCard
			if ("idCard".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 idCard 值的判斷
				for (int i = 0; i < values.length; i++) {
					if (!values[i].matches("^[A-Z]\\d{9}$")) {
						errorMap.put(key, "身分證格式錯誤!");
					}
				}
			}
			// 判斷遍歷到的 key 是不是 birth
			if ("birth".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 checkinTime 值的判斷
				for (int i = 0; i < values.length; i++) {
					if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入生日");
					}
				}
			}
			// 判斷遍歷到的 key 是不是 account

			if ("account".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 cmpAccount 值的判斷
				for (int i = 0; i < values.length; i++) {
					if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入帳號");
					}
					if (!values[i].matches("^[a-zA-Z]\\w{3,17}$")) {
						// 正確格式：以字母開頭, 長度在 4 ~ 18 之間, 只能包含字符, 數字和下滑線
						errorMap.put(key, "必須由字母,數字下滑線組成,並且長度為4到18位");
					}
				}
			}

			// 判斷遍歷到的 key 是不是 password
			if ("password".equals(key)) {
				// 獲取到 key 對應的 value; 注意:key是String型，value是String型數組, 所以這邊需要做強制轉型
				String[] values = (String[]) map.get(key);
				// 進行 cmpPassword 值的判斷
				for (int i = 0; i < values.length; i++) {
					if (values[i].trim().equals("")) {
						errorMap.put(key, "請輸入帳號");
					}
					if (!values[i].matches("^[a-zA-Z]\\w{3,17}$")) {
						// 正確格式：以字母開頭, 長度在 4 ~ 18 之間, 只能包含字符, 數字和下滑線
						errorMap.put(key, "必須由字母,數字下滑線組成,並且長度為4到18位");
					}
				}
			}

			
		}
		return errorMap;
	}

	// 獲取圖片路徑
	public static String getPath(MultipartFile uploadFile, HttpSession session, Customer customer) {
		// 此時這裡的數據都是過濾好的
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

		// 顧客目錄: /images/Customer
		String customerPath = imagePath + File.separator + "customer";
		System.out.println(customerPath);
		File file2 = new File(customerPath);
		if (!file2.exists()) {
			file2.mkdir();
		}

		// 顧客目錄: /images/Customer/顧客帳號
		String accountPath = customerPath + File.separator + customer.getAccount();
		System.out.println(accountPath);
		File file3 = new File(accountPath);
		if (!file3.exists()) {
			file3.mkdir();
		}

		// 圖片在服務器中的真實路徑
		finalPath = accountPath + File.separator + fileName;
		System.out.println(finalPath);

		// 這個才是我們需要存的路徑: images/Company/廠商帳號/營業登記證.jpg
		savePath = "images/Customer/" + customer.getAccount() + "/" + fileName;
		System.out.println(savePath);

		return savePath;
	}
}
