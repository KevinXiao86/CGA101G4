package com.taiwan.controller.company;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.taiwan.utils.ControllerUtil;

@Controller
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private RoomtypeService roomtypeService;
	
	// 登出
	@GetMapping("/logout")
	public String logout(HttpSession session) throws IOException {
		// 銷毀session域中的用戶訊息
		session.invalidate();

		// 重定向到首頁
		return "redirect:/";
	}

	// 登陸
//	@GetMapping("/login")
	@PostMapping("/login")
//	@ResponseBody
	public String login(HttpSession session, HttpServletResponse response, Model model, HttpServletRequest request,
			@RequestParam("cmpAccount") String cmpAccount, @RequestParam("cmpPassword") String cmpPassword,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			@RequestParam(value = "pageSize", required = false) String pageSizeStr) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		System.out.println("cmpAccount: " + cmpAccount);
		System.out.println("cmpPassword: " + cmpPassword);
		// 1. 進行數據校驗
		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());

		// 3. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			model.addAttribute("cmpAccount", cmpAccount);
			// 回到登陸頁面
			return "/front-end/company/login.jsp";
		}
		// 1. 獲取請求參數

		// 2. 調用 login 業務方法
		Company company = companyService.login(cmpAccount, cmpPassword);

		// 3. 判斷 company
		if (company.isSuccessful() && "審核通過".equals(company.getAuditStatus())) {
			// 表示登入成功, 將廠商訊息保存在 session 當中
			session.setAttribute("loginCompany", company);

			// 1.接收請求參數 pageNo 和 pageSize, 並轉成int類型
			// 默認就是第一頁
			int pageNo = CommonUtils.parseInt(pageNoStr, 1);
			// 默認每頁顯示數量就是4
			int pageSize = CommonUtils.parseInt(pageSizeStr, Page.PAGE_SIZE);
			// 獲取廠商編號
			int cmpId = company.getCmpId();

			// 2. 獲取page對象
			Page<Roomtype> page = roomtypeService.page(pageNo, pageSize, cmpId);

			// 3. 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
			// 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
			page.setUrl("company/getAllRoomtypesByPage?cmpId=" + cmpId + "&");

			// 4. 將獲取的page對象放入到request域中
			session.setAttribute("page", page);
		} else if (company.isSuccessful() && "審核未通過".equals(company.getAuditStatus())) {
			session.setAttribute("editCompany", company);
		} else {
			// 表示登入失敗
			// 將 company 存放到 request 域當中, 方便頁面做回顯訊息
			model.addAttribute("loginCompany", company);
		}

		// 4. 回傳結果
		return company.getUrl();
	}

	// 分頁房型列表
	@RequestMapping(value = "/getAllRoomtypesByPage", method = RequestMethod.GET)
	public String getAllRoomtypesByPage(Model model, HttpSession session,
			@RequestParam(value = "pageNo", required = false) String pageNoStr,
			@RequestParam(value = "pageSize", required = false) String pageSizeStr,
			@RequestParam("cmpId") String cmpIdStr) {
		// 1.接收請求參數 pageNo 和 pageSize, 並轉成int類型
		// 默認就是第一頁
		int pageNo = CommonUtils.parseInt(pageNoStr, 1);
		// 默認每頁顯示數量就是4
		int pageSize = CommonUtils.parseInt(pageSizeStr, Page.PAGE_SIZE);
		// 獲取廠商編號
		int cmpId = CommonUtils.parseInt(cmpIdStr, 0);

		// 2. 獲取page對象
		Page<Roomtype> page = roomtypeService.page(pageNo, pageSize, cmpId);

		// 3. 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
		// 設置分頁條的請求地址,注意最前面不要加上/,因為頁面都是從/開頭的
		page.setUrl("company/getAllRoomtypesByPage?cmpId=" + cmpId + "&");

		// 4.
		session.setAttribute("page", page);

		// 4.請求轉發到列表頁面
		return "/front-end/company/index.jsp";
	}

	// 修改
	@RequestMapping("/editCompany")
	public String editCompany(@RequestParam(value = "serialNo", required = false) MultipartFile serialNo,
			HttpServletRequest request, Model model, HttpSession session, @RequestParam("cmpName") String cmpName,
			@RequestParam("cmpTel") String cmpTel, @RequestParam("headBank") String headBank,
			@RequestParam("endBank") String endBank, @RequestParam("cmpMail") String cmpMail,
			@RequestParam("cmper") String cmper, @RequestParam("cmperTel") String cmperTel,
			@RequestParam("cmpIntroduce") String cmpIntroduce, @RequestParam("checkinTime") String checkinTime,
			@RequestParam("checkoutTime") String checkoutTime, @RequestParam("city") String city,
			@RequestParam("town") String town, @RequestParam("road") String road, @RequestParam("notice") String notice,
			@RequestParam("canx") String canx, @RequestParam("cmpId") String cmpId)
			throws IOException, ServletException {

		request.setCharacterEncoding("UTF-8");

		// 先找出之前的廠商資料
		Company company = companyService.getCompanyByCmpId(cmpId);
		System.out.println("未修改: " + company);
//		System.out.println("未修改前的圖片路徑: " + company.getSerialNo());

		// 判斷
		//獲取圖片要保存在服務器的路徑
		String savePath = "";
		if ("審核未通過".equals(company.getAuditStatus())) {
			// 判斷文件是否為空
			if (serialNo.isEmpty()) {
				System.out.println("訪問成功");
				// 註冊失敗, 回到註冊頁面並回顯訊息
				model.addAttribute("serialNo", "未上傳旅館登記證");
				// 用於回顯訊息
				session.setAttribute("editCompany", company);
				return "/front-end/company/edit.jsp";
			} else {
				// 根據業務方法獲取路徑
				savePath = companyService.savePath(company, serialNo, session);
				// 設置路徑
				company.setSerialNo(savePath);
//				System.out.println("savePath: " + savePath);
			}
		}

		// 1. 進行數據校驗
		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());

		// 2. 判斷 errorMap 的長度, 如果大於 0 就說明有錯誤訊息, 此時就須要回到頁面回顯錯誤訊息
		if (errorMap.size() > 0) {
			// 說明有錯誤訊息
			model.addAttribute("errorInfo", errorMap);
			// 用於回顯訊息
			session.setAttribute("editCompany", company);
			// 回到註冊頁面
			return "/front-end/company/edit.jsp";
		}

		// 獲取從頁面提交過來的參數
		company.setCmpName(cmpName);
		company.setCmpTel(cmpTel);
		String bankAccount = headBank + "-" + endBank;
		company.setBankAccount(bankAccount);
		company.setCmpMail(cmpMail);
		company.setCmper(cmper);
		company.setCmperTel(cmperTel);
		company.setCmpIntroduce(cmpIntroduce);
		company.setCheckinTime(checkinTime);
		company.setCheckoutTime(checkoutTime);
		String location = city + town + road;
		company.setLocation(location);
		company.setNotice(notice);
		company.setCanx(canx);
		if ("審核未通過".equals(company.getAuditStatus())) {
			company.setAuditStatus("待審核");
		} else {
			company.setAuditStatus("審核通過");
		}

		System.out.println("修改後: " + company);

		// 調用修改方法
		Company editCompany = companyService.updateRegistCompany(company);

		if (!editCompany.isSuccessful()) {
			model.addAttribute("cmpName", editCompany.getCmpName());
			// 成功執行文件上傳至服務器的操作
			String realPath = session.getServletContext().getRealPath("/");
			serialNo.transferTo(new File(realPath + savePath));
		}
		
		
		// 回傳結果
		session.setAttribute("editCompany", editCompany);
		return editCompany.getUrl();

//		// 先找出之前的廠商資料
//		Company company = companyService.getCompanyByCmpId(cmpId);
//
//		// 獲取從頁面提交過來的參數
//		company.setCmpName(newCompany.getCmpName());
//		company.setCmpTel(newCompany.getCmpTel());
//		String bankAccount = headBank + "-" + endBank;
//		company.setBankAccount(bankAccount);
//		company.setCmpMail(newCompany.getCmpMail());
//		company.setCmper(newCompany.getCmper());
//		company.setCmperTel(newCompany.getCmperTel());
//		company.setCmpIntroduce(newCompany.getCmpIntroduce());
//		company.setCheckinTime(newCompany.getCheckinTime());
//		company.setCheckoutTime(newCompany.getCheckoutTime());
//		String location = city + town + road;
//		company.setLocation(location);
//		company.setNotice(newCompany.getNotice());
//		company.setCanx(newCompany.getCanx());
//		if ("審核未通過".equals(company.getAuditStatus())) {
//			company.setAuditStatus("待審核");
//		} else {
//			company.setAuditStatus("審核通過");
//		}
//		
//		
//		// 1. 進行數據校驗
//		Map<String, String> errorMap = companyService.registCheck(request.getParameterMap());
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
//		// 調用修改方法
//		Company editCompany = companyService.updateRegistCompany(company);
//		
//		if (!editCompany.isSuccessful()) {
//			model.addAttribute("cmpName", editCompany.getCmpName());
//		}
//
//		// 回傳結果
//		session.setAttribute("editCompany", editCompany);
//		return editCompany.getUrl();

//		return "";
	}

	// 獲取廠商訊息, 轉發到頁面
	@GetMapping("/getCompany")
	public String getCompanyByCmpId(HttpSession session, @RequestParam("cmpId") String cmpId) throws IOException {
		Company company = companyService.getCompanyByCmpId(cmpId);
		session.setAttribute("editCompany", company);
		return "/front-end/company/edit.jsp";
	}

	// 驗證廠商帳號是否可用
	@GetMapping("/existsCmpAccount")
	@ResponseBody
	public boolean existsCmpAccount(HttpServletResponse response, @RequestParam("cmpAccount") String cmpAccount) {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		boolean result = companyService.existsCmpAccount(cmpAccount);
		return result;
	}

	// 註冊
	@PostMapping("/regist")
	public String regist(@RequestParam("serialNo") MultipartFile serialNo, HttpServletRequest request, Model model,
			HttpSession session, @RequestParam("cmpName") String cmpName, @RequestParam("cmpTel") String cmpTel,
			@RequestParam("headBank") String headBank, @RequestParam("endBank") String endBank,
			@RequestParam("cmpMail") String cmpMail, @RequestParam("cmper") String cmper,
			@RequestParam("cmperTel") String cmperTel, @RequestParam("cmpAccount") String cmpAccount,
			@RequestParam("cmpPassword") String cmpPassword, @RequestParam("cmpIntroduce") String cmpIntroduce,
			@RequestParam("checkinTime") String checkinTime, @RequestParam("checkoutTime") String checkoutTime,
			@RequestParam("city") String city, @RequestParam("town") String town, @RequestParam("road") String road,
			@RequestParam("notice") String notice, @RequestParam("canx") String canx)
			throws IllegalStateException, IOException {
		String bankAccount = headBank + "-" + endBank;
		String location = city + town + road;

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

		// 判斷文件是否為空
		if (serialNo.isEmpty()) {
			System.out.println("訪問成功");
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("serialNo", "未上傳旅館登記證");
			// 用於回顯訊息
			model.addAttribute("registCompany", company);
			return "/front-end/company/regist.jsp";
		}

		// 根據業務方法獲取路徑
		String savePath = companyService.savePath(company, serialNo, session);
		// 設置路徑
		company.setSerialNo(savePath);

		Company registCompany = companyService.regist(company);

		if (registCompany.isSuccessful()) {
			// 成功執行文件上傳至服務器的操作
			String realPath = session.getServletContext().getRealPath("/");
			System.out.println("註冊成功");
			serialNo.transferTo(new File(realPath + savePath));

			// 註冊成功要發信通知
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
}
