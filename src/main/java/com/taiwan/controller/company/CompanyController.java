package com.taiwan.controller.company;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.taiwan.beans.Company;
import com.taiwan.beans.Page;
import com.taiwan.beans.Roomtype;
import com.taiwan.service.company.CompanyService;
import com.taiwan.service.roomtype.RoomtypeService;
import com.taiwan.utils.CommonUtils;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private RoomtypeService roomtypeService;

	// 產生令牌
	@RequestMapping("/getToken")
//	@ResponseBody
	public void getToken(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		UUID uuid = UUID.randomUUID();
		String token = uuid.toString().replaceAll("-", "");
		System.out.println(token);
		// 訪問頁面時隨機生成一個 token 保存在服務端的 session 中
		request.getSession().setAttribute("token", token);

		Gson gson = new Gson();
		String jsonString = gson.toJson(token);
		response.getWriter().write(jsonString);
	}

	// 檢查表單是否重複提交
	private boolean checkRepeatSubmit(HttpServletRequest request) {
		// 這是獲取 session 中的 token
		Object sessionTokenObj = request.getSession().getAttribute("token");
		if (sessionTokenObj == null) {
			// 表單重複提交
			System.out.println("表單重複提交");
			return true;
		}

		// 獲取從頁面發送過來的 token
		String paramToken = request.getParameter("token");
		if (paramToken == null) {
			// 非法請求
			System.out.println("非法請求");
			return true;
		}

		if (!paramToken.equals(sessionTokenObj.toString())) {
			// 非法請求
			System.out.println("非法請求");
			return true;
		}

		return false;
	}

	
	@RequestMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) throws IOException {
		//銷毀session域中的用戶訊息
		request.getSession().invalidate();
		//重定向到首頁
		return "redirect:/";
	}
	
	
	// 登陸
	@RequestMapping("/login")
	@ResponseBody
	public Company login(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		// 1. 獲取請求參數
		String cmpAccount = request.getParameter("cmpAccount");
		String cmpPassword = request.getParameter("cmpPassword");

		// 2. 調用 login 業務方法
		Company company = companyService.login(cmpAccount, cmpPassword);

		// 3. 判斷 company
		if (company.isSuccessful() && "審核通過".equals(company.getAuditStatus())) {
			// 表示登入成功, 將廠商訊息保存在 session 當中
			request.getSession().setAttribute("loginCompany", company);
			
			// 1.接收請求參數 pageNo 和 pageSize, 並轉成int類型
			// 默認就是第一頁
			int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
			// 默認每頁顯示數量就是4
			int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
			// 獲取廠商編號
			int cmpId = company.getCmpId();

			// 2. 獲取page對象
			Page<Roomtype> page = roomtypeService.page(pageNo, pageSize, cmpId);

			// 3. 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
			// 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
			page.setUrl("company/getAllRoomtypesByPage?cmpId=" + cmpId + "&");

			// 4. 將獲取的page對象放入到request域中
			request.getSession().setAttribute("page", page);
		} else if (company.isSuccessful() && "審核未通過".equals(company.getAuditStatus())) {
			request.getSession().setAttribute("editCompany", company);
		}

		// 4. 回傳結果
		return company;
	}
	
	
	// 分頁房型列表
	@RequestMapping(value = "/getAllRoomtypesByPage", method = RequestMethod.GET)
	public String getAllRoomtypesByPage(HttpServletRequest request, Model model) {
		// 1.接收請求參數 pageNo 和 pageSize, 並轉成int類型
		// 默認就是第一頁
		int pageNo = CommonUtils.parseInt(request.getParameter("pageNo"), 1);
		// 默認每頁顯示數量就是4
		int pageSize = CommonUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
		// 獲取廠商編號
		int cmpId = CommonUtils.parseInt(request.getParameter("cmpId"), 0);

		// 2. 獲取page對象
		Page<Roomtype> page = roomtypeService.page(pageNo, pageSize, cmpId);

		// 3. 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
		// 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
		page.setUrl("company/getAllRoomtypesByPage?cmpId=" + cmpId + "&");

		// 4.
		request.getSession().setAttribute("page", page);

		// 4.請求轉發到列表頁面
		return "/front-end/company/index.jsp";
	}
	
	
	
	

	// 修改
	@RequestMapping("/editCompany")
	public String editCompany(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String cmpId = request.getParameter("cmpId");

		// 先找出之前的廠商資料
		Company company = companyService.getCompanyByCmpId(cmpId);

		if (checkRepeatSubmit(request)) {
			System.out.println("請不要重複提交表單!");
			company.setSuccessful(false);
			company.setMessage("請不要重複提交表單!");
			company.setUrl("/front-end/company/edit.jsp");
			request.getSession().setAttribute("editCompany", company);
			return company.getUrl();
		}

		// 在第一次處理表單之後需要清空 token, 這一步非常關鍵
		request.getSession().removeAttribute("token");

		// 獲取從頁面提交過來的參數
		String cmpName = request.getParameter("cmpName");
		String cmpTel = request.getParameter("cmpTel");
		String headBank = request.getParameter("headBank");
		String endBank = request.getParameter("endBank");
		String bankAccount = headBank + "-" + endBank;
		String cmpMail = request.getParameter("cmpMail");
		String cmper = request.getParameter("cmper");
		String cmperTel = request.getParameter("cmperTel");
		String cmpIntroduce = request.getParameter("cmpIntroduce");
		String checkinTime = request.getParameter("checkinTime");
		String checkoutTime = request.getParameter("checkoutTime");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String road = request.getParameter("road");
		String location = city + town + road;
		String notice = request.getParameter("notice");
		String canx = request.getParameter("canx");

		// 將獲取到的值設置進從數據庫中獲取到的 Company 物件
		company.setCmpName(cmpName);
		company.setCmpTel(cmpTel);
		company.setBankAccount(bankAccount);
		company.setCmpMail(cmpMail);
		company.setCmper(cmper);
		company.setCmperTel(cmperTel);
		if ("審核未通過".equals(company.getAuditStatus())) {
			company.setAuditStatus("待審核");
		} else {
			company.setAuditStatus("審核通過");
		}
		company.setCmpIntroduce(cmpIntroduce);
		company.setCheckinTime(checkinTime);
		company.setCheckoutTime(checkoutTime);
		company.setLocation(location);
		company.setNotice(notice);
		company.setCanx(canx);

		// 調用修改方法
		Company editCompany = companyService.updateRegistCompany(company);
		
		if (!editCompany.isSuccessful()) {
			model.addAttribute("cmpName", editCompany.getCmpName());
		}

		// 回傳結果
		request.getSession().setAttribute("editCompany", editCompany);
		return editCompany.getUrl();
	}

	// 獲取廠商訊息, 轉發到頁面
	@RequestMapping("/getCompany")
	public String getCompanyByCmpId(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cmpId = request.getParameter("cmpId");
		Company company = companyService.getCompanyByCmpId(cmpId);
		request.getSession().setAttribute("editCompany", company);
		return "/front-end/company/edit.jsp";
	}

	// 驗證廠商帳號是否可用
	@RequestMapping("/existsCmpAccount")
	@ResponseBody
	public boolean existsCmpAccount(HttpServletRequest request, HttpServletResponse response) {
		String cmpAccount = request.getParameter("cmpAccount");
		boolean result = companyService.existsCmpAccount(cmpAccount);
		return result;
	}

	@RequestMapping("/regist")
	public String regist(@RequestParam("serialNo") MultipartFile serialNo, HttpServletRequest request, Model model,
			HttpSession session) throws IllegalStateException, IOException {
		//	由於自動封裝功能不能跟文件一起使用, 所以這邊使用手動封裝為物件
		String cmpName = request.getParameter("cmpName");
		String cmpTel = request.getParameter("cmpTel");
		String headBank = request.getParameter("headBank");
		String endBank = request.getParameter("endBank");
		String bankAccount = headBank + "-" + endBank;
		String cmpMail = request.getParameter("cmpMail");
		String cmper = request.getParameter("cmper");
		String cmperTel = request.getParameter("cmperTel");
		String cmpAccount = request.getParameter("cmpAccount");
		String cmpPassword = request.getParameter("cmpPassword");
		String cmpIntroduce = request.getParameter("cmpIntroduce");
		String checkinTime = request.getParameter("checkinTime");
		String checkoutTime = request.getParameter("checkoutTime");
		String city = request.getParameter("city");
		String town = request.getParameter("town");
		String road = request.getParameter("road");
		String location = city + town + road;
		String notice = request.getParameter("notice");
		String canx = request.getParameter("canx");

		Company company = new Company(null, cmpName, cmpTel, cmpMail, cmper, cmperTel, null, null, null, cmpAccount,
				cmpPassword, cmpIntroduce, checkinTime, checkoutTime, location, notice, canx, bankAccount);
	
		
		// 1. 進行數據校驗
		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());

		
		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			// 回到註冊頁面
			return "/front-end/company/regist.jsp";
		}		
		

		//判斷文件是否為空
		if (serialNo.isEmpty()) {
			System.out.println("訪問成功");
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("serialNo", "未上傳旅館登記證");
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			return "/front-end/company/regist.jsp";
		}
		
		
		if (checkRepeatSubmit(request)) {
			System.out.println("請不要重複提交表單!");
			company.setMessage("請不要重複提交表單!");
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			// 回到註冊頁面
			return "/front-end/company/regist.jsp";
		}

		// 在第一次處理表單之後需要清空 token, 這一步非常關鍵
		request.getSession().removeAttribute("token");

		//根據業務方法獲取路徑
		String savePath = companyService.savePath(company, serialNo, session);
		//設置路徑
		company.setSerialNo(savePath);
		
		Company registCompany = companyService.regist(company);
		
		if (registCompany.isSuccessful()) {
			// 成功執行文件上傳至服務器的操作
			String realPath = session.getServletContext().getRealPath("/");
			System.out.println("註冊成功");
			serialNo.transferTo(new File(realPath + savePath));
			
			//註冊成功要發信通知
			// 設定收件人
			String email = company.getCmpMail();
			// 設定主旨
			String subject = "註冊成功通知信件";
			// 設定內容
			String messageText = "Hello ~!" + company.getCmpName() + "感謝您成為我們的廠商\n" + "我們將盡快完成審核, 請靜待審核結果";
			// 寄信通知廠商
			companyService.sendEmail(email, subject, messageText);
			
			//
			model.addAttribute("cmpName", registCompany.getCmpName());
		} else {
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("registCompany", registCompany);
			System.out.println("註冊失敗");
		}

		return company.getUrl();
	}

	// 註冊
//	@RequestMapping("/regist")
//	public String regist(@RequestParam("serialNo") MultipartFile serialNo, 
//			HttpServletRequest request, Model model, HttpSession session) throws IllegalStateException, IOException {
//		
////		由於自動封裝功能不能跟文件一起使用, 所以這邊使用手動封裝為物件
//		String cmpName = request.getParameter("cmpName");
//		String cmpTel = request.getParameter("cmpTel");
//		String headBank = request.getParameter("headBank");
//		String endBank = request.getParameter("endBank");
//		String bankAccount = headBank + "-" + endBank;
//		String cmpMail = request.getParameter("cmpMail");
//		String cmper = request.getParameter("cmper");
//		String cmperTel = request.getParameter("cmperTel");
//		String cmpAccount = request.getParameter("cmpAccount");
//		String cmpPassword = request.getParameter("cmpPassword");
//		String cmpIntroduce = request.getParameter("cmpIntroduce");
//		String checkinTime = request.getParameter("checkinTime");
//		String checkoutTime = request.getParameter("checkoutTime");
//		String city = request.getParameter("city");
//		String town = request.getParameter("town");
//		String road = request.getParameter("road");
//		String location = city + town + road;
//		String notice = request.getParameter("notice");
//		String canx = request.getParameter("canx");
//		
//		Company company = new Company(null, cmpName, cmpTel, cmpMail, cmper, cmperTel, null, null, null,
//				cmpAccount, cmpPassword, cmpIntroduce, checkinTime, checkoutTime, location, notice, canx, bankAccount);		
//		
//		// 1. 進行數據校驗
//		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());
//
//		
//		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
//		if (errorMap.size() > 0) {
//			// 說明有錯誤訊息
//			model.addAttribute("errorInfo", errorMap);
//			// 用於回顯訊息
//			model.addAttribute("registCompany", company);
//			// 回到註冊頁面
//			return "/front-end/company/regist.jsp";
//		}		
//		
//
//		//判斷文件是否為空
//		if (serialNo.isEmpty()) {
//			System.out.println("訪問成功");
//			// 註冊失敗, 回到註冊頁面並回顯訊息
//			model.addAttribute("serialNo", "未上傳旅館登記證");
//			// 用於回顯訊息
//			model.addAttribute("registCompany", company);
//			return "/front-end/company/regist.jsp";
//		}
//		
//		
//		//根據業務方法獲取路徑
//		String savePath = companyService.savePath(company, serialNo, session);
//		//設置路徑
//		company.setSerialNo(savePath);
//		
//		Company registCompany = companyService.regist(company);
//		
//		if (registCompany.isSuccessful()) {
//			// 成功執行文件上傳至服務器的操作
//			String realPath = session.getServletContext().getRealPath("/");
//			System.out.println("註冊成功");
//			serialNo.transferTo(new File(realPath + savePath));
//			
//			//註冊成功要發信通知
//			// 設定收件人
//			String email = company.getCmpMail();
//			// 設定主旨
//			String subject = "註冊成功通知信件";
//			// 設定內容
//			String messageText = "Hello ~!" + company.getCmpName() + "感謝您成為我們的廠商\n" + "我們將盡快完成審核, 請靜待審核結果";
//			// 寄信通知廠商
//			companyService.sendEmail(email, subject, messageText);
//			
//			//
//			model.addAttribute("cmpName", registCompany.getCmpName());
//		} else {
//			// 註冊失敗, 回到註冊頁面並回顯訊息
//			model.addAttribute("registCompany", registCompany);
//			System.out.println("註冊失敗");
//		}
//
//		return company.getUrl();
//	}	

//	
//
//
//	// 這個方法會提前所有的目標方法先運行, 要特別小心
//	@ModelAttribute
//	public void getCompanyByCmpAccountAndCmpPassword(@RequestParam(value = "cmpAccount", required = false)String cmpAccount,
//			@RequestParam(value = "cmpPassword", required = false)String cmpPassword, HttpSession session) {
//		
//		if(cmpAccount != null && cmpPassword != null) {
//			//數據校驗
//			if ("".equals(cmpAccount)) {
//				return;
//			}
//			if ("".equals(cmpPassword)) {
//				return;
//			}
//			
//			//去數據庫查數據
//			Company company = companyService.getCompanyByCmpAccountAndCmpPassword(cmpAccount, cmpPassword);
//			//判斷
//			if (company != null) {
//				// 將這個廠商放入到隱含模型中
//				session.setAttribute("modelAttributeCompany", company);
//				System.out.println("modelAttributeCompany" + company);
//			}
//		}
//	}
//	
//	
//	//審核未通過, 需要補件的廠商
//	@RequestMapping("/editRegistCompany")
//	public String editRegistCompany(HttpServletRequest request, Model model, HttpSession session) {
//		//1. 數據校驗
//		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());
//		
//		Company company = (Company) session.getAttribute("modelAttributeCompany");
//		
//		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
//		if (errorMap.size() > 0) {
//			// 說明有錯誤訊息
//			model.addAttribute("errorInfo", errorMap);
//			// 用於回顯訊息
//			request.getSession().setAttribute("editCompany", company);
//			// 回到註冊頁面
//			return "/front-end/company/edit.jsp";
//		}
//		
//		String cmpName = request.getParameter("cmpName");
//		String cmpTel = request.getParameter("cmpTel");
//		String headBank = request.getParameter("headBank");
//		String endBank = request.getParameter("endBank");
//		String bankAccount = headBank + "-" + endBank;
//		String cmpMail = request.getParameter("cmpMail");
//		String cmper = request.getParameter("cmper");
//		String cmperTel = request.getParameter("cmperTel");
//		String cmpAccount = request.getParameter("cmpAccount");
//		String cmpIntroduce = request.getParameter("cmpIntroduce");
//		String checkinTime = request.getParameter("checkinTime");
//		String checkoutTime = request.getParameter("checkoutTime");
//		String city = request.getParameter("city");
//		String town = request.getParameter("town");
//		String road = request.getParameter("road");
//		String location = city + town + road;
//		String notice = request.getParameter("notice");
//		String canx = request.getParameter("canx");
//		
//		company.setCmpName(cmpName);
//		company.setCmpTel(cmpTel);
//		company.setBankAccount(bankAccount);
//		company.setCmpMail(cmpMail);
//		company.setCmper(cmper);
//		company.setCmperTel(cmperTel);
//		if("審核未通過".equals(company.getAuditStatus())) {			
//			company.setAuditStatus("待審核");
//		}else {
//			company.setAuditStatus("審核通過");
//		}
////		company.setCmpAccount(cmpAccount);
//		company.setCmpIntroduce(cmpIntroduce);
//		company.setCheckinTime(checkinTime);
//		company.setCheckoutTime(checkoutTime);
//		company.setLocation(location);
//		company.setNotice(notice);
//		company.setCanx(canx);
//		
//		System.out.println("editRegistCompany " + company);
//		
//		//調用業務方法
//		Company editCompany = companyService.updateRegistCompany(company);
//		request.getSession().setAttribute("editCompany", editCompany);
//		
//		return editCompany.getUrl();
//	}
//
//
//	@RequestMapping("/getCompany")
//	public String getCompany(HttpServletRequest request) {
//		String cmpId = request.getParameter("cmpId");
//		
//		Company company = companyService.getCompanyByCmpId(cmpId);
//		request.getSession().setAttribute("editCompany", company);
//		return "/front-end/company/edit.jsp";
//	}

//	//修改
//	@RequestMapping("/editCompany")
//	public String editCompany(HttpServletRequest request) throws IOException {
//		System.out.println("訪問成功");
//		String cmpId = request.getParameter("cmpId");
//		String token = request.getParameter("token");
//		System.out.println("cmpId: " + cmpId);
//		System.out.println("token: " + token);
//		
//		// 先找出之前的廠商資料
//		Company company = companyService.getCompanyByCmpId(cmpId);
//		System.out.println("設置前: " + company);
//
////		// 檢查是否為非法請求
////		if (checkRepeatSubmit(request)) {
////			company.setSuccessful(false);
////			company.setMessage("請不要重複提交表單!");
////			company.setUrl("/front-end/company/edit.jsp");
////			request.getSession().setAttribute("editCompany", company);
////			return company.getUrl();
////		}
////
////		// 在第一次處理表單之後需要清空 token, 這一步驟非常的關鍵
////		request.getSession().removeAttribute("token");
//		
//		
//		// 獲取從頁面提交過來的參數
//		String cmpName = request.getParameter("cmpName");
//		String cmpTel = request.getParameter("cmpTel");
//		String headBank = request.getParameter("headBank");
//		String endBank = request.getParameter("endBank");
//		String bankAccount = headBank + "-" + endBank;
//		String cmpMail = request.getParameter("cmpMail");
//		String cmper = request.getParameter("cmper");
//		String cmperTel = request.getParameter("cmperTel");
//		String cmpIntroduce = request.getParameter("cmpIntroduce");
//		String checkinTime = request.getParameter("checkinTime");
//		String checkoutTime = request.getParameter("checkoutTime");
//		String city = request.getParameter("city");
//		String town = request.getParameter("town");
//		String road = request.getParameter("road");
//		String location = city + town + road;
//		String notice = request.getParameter("notice");
//		String canx = request.getParameter("canx");
//
//		// 將獲取到的值設置進從數據庫中獲取到的 Company 物件
//		company.setCmpName(cmpName);
//		company.setCmpTel(cmpTel);
//		company.setBankAccount(bankAccount);
//		company.setCmpMail(cmpMail);
//		company.setCmper(cmper);
//		company.setCmperTel(cmperTel);
//		if ("審核未通過".equals(company.getAuditStatus())) {
//			company.setAuditStatus("待審核");
//		} else {
//			company.setAuditStatus("審核通過");
//		}
//		company.setCmpIntroduce(cmpIntroduce);
//		company.setCheckinTime(checkinTime);
//		company.setCheckoutTime(checkoutTime);
//		company.setLocation(location);
//		company.setNotice(notice);
//		company.setCanx(canx);
//
//		System.out.println("設置完成之後:" + company);
//
//		// 調用修改方法
//		Company editCompany = companyService.updateRegistCompany(company);
//
//		System.out.println(editCompany.getUrl());
//		
//		// 回傳結果
//		request.getSession().setAttribute("editCompany", company);
//		System.out.println(editCompany.getMessage());
//		return editCompany.getUrl();
//	}

}
