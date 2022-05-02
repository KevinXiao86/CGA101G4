package com.taiwan.controller.ticket;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.TktImgVO;
import com.taiwan.service.TicketService;
import com.taiwan.utils.ControllerUtil;

@WebServlet("/ticket/ticketCreator")
public class TicketCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	TicketService ticketService =ControllerUtil.getBean(TicketService.class);
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorsMsgs", errorMsgs);
		try {
			// 收到票券名稱的請求參數
			String tktName = request.getParameter("tktName");
			// 如果是空值 或是 去掉空白等於空字串
			if (tktName == null || tktName.trim().equals("")) {
				errorMsgs.put("tktName", "票券名稱不能為空");
			}
			// 票券數量
			Integer originalAmount = null;
			try {
				// String 轉型轉成 Integer
				originalAmount = Integer.valueOf(request.getParameter("originalAmount"));
				// 如果輸入數量小於等於0
				if (originalAmount <= 0) {
					errorMsgs.put("originalPrice", "票券起始販賣數量請輸入大於0的數字");
				}
			} catch (Exception e) {
				errorMsgs.put("originalAmount", "票券可販賣數量請填數字");
			}
			// 票券價格
			Integer price = null;
			try {
				price = Integer.valueOf(request.getParameter("price"));
				if (price <= 0) {
					errorMsgs.put("price", "票券價格請輸入大於0的數字");
				}

			} catch (Exception e) {
				errorMsgs.put("price", "票券價格請輸入數字");
			}
			// 以字串形式獲取開始日期跟結束日期
			String startString = request.getParameter("startdate");
			String endString = request.getParameter("enddate");
			// 把不要的符號去掉
			startString = startString.replace("T", " ") + ":00";
			endString = endString.replace("T", " ") + ":00";
			// 轉成timestamp類
			Timestamp startdate = Timestamp.valueOf(startString);
			Timestamp enddate = Timestamp.valueOf(endString);
			if (enddate.before(startdate)) {
				errorMsgs.put("timeError", "結束日期比開始日期還早");
			}
			// 獲取票券種類
			String kind = request.getParameter("kind");
//			System.out.println(kind);
			// 取得縣市名稱
			String city = request.getParameter("city");
//			System.out.println(city);
			// 取得鄉鎮市區名
			String town = request.getParameter("town");
//			System.out.println(town);
			String address = request.getParameter("address");
			// 把地址串接起來
			String allAddress = new StringBuffer(city).append(town).append(address).toString();
			if (address == null || address.trim().equals("")) {
				errorMsgs.put("address", "地址欄位不得為空");
			}
			String instruction = request.getParameter("instruction");
			if (instruction == null || instruction.trim().equals("")) {
				errorMsgs.put("instruction", "請輸入票券介紹");
			}
			String notice = request.getParameter("notice");
			if (notice == null || notice.trim().equals("")) {
				errorMsgs.put("notice", "請輸入票券注意事項");
			}
			String howuse = request.getParameter("howuse");
			if (howuse == null || howuse.trim().equals("")) {
				errorMsgs.put("howuse", "請輸入如何使用");
			}
			String canxpolicy = request.getParameter("canxpolicy");
			if (canxpolicy == null || canxpolicy.trim().equals("")) {
				errorMsgs.put("canxpolicy", "請輸入取消政策");
			}
			// 遍歷一下MAP裡面的值
//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
//				System.out.println(entry.getKey() + ":" + entry.getValue());
//			}
			// 如果錯誤訊息的map不是空值的話，就請求轉發回/ticket/ticket_create.jsp
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_create.jsp");
				rd.forward(request, response);
				return;
			}
			// 開始新增資料
			ticketService.addTicket(tktName, originalAmount, price, startdate, enddate, city, instruction, allAddress,
					notice, howuse, canxpolicy, kind);
			// 新增完成，請求轉發到ticket首頁
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_index.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			errorMsgs.put("anotherError", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/ticket/ticket_create.jsp");
			rd.forward(request, response);
		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doGet(request, response);
	}



}


//Collection<Part> parts=request.getParts();
//UUIDFileName uuidFileName = new UUIDFileName();
//for(Part part:parts) {		
//	String filename = uuidFileName.getFileNameFromPart(part);
//	if(filename!= null && part.getContentType()!=null) {
//		part.write(realPath + "/" + filename);
//		//傳入db的路徑前面不能再有斜槓，不然伺服器找的時候會跑一次阿飄路徑
//		String dbSaveDirectory="images/ticket";
//		// 要傳回數據庫的路徑
//		String dbPath = dbSaveDirectory + "/" + filename;
//		TktImgVO tktImgVO=new TktImgVO();
//		tktImgVO.setImg(dbPath);
//		tktImgVOs.add(tktImgVO);
//	}
//}
// 創建一個集合來保存圖片
//List<TktImgVO> tktImgVOs = new ArrayList<TktImgVO>();
//
//// 我票券要存在這個檔案目錄之下
//String saveDirectory = "/images/ticket";
//// 找到阿飄路徑
//String realPath = getServletContext().getRealPath(saveDirectory);
//// 再如果阿飄路徑下沒有這個資料夾就創造，有就不用
//File fsaveDirectory = new File(realPath);
//if (!fsaveDirectory.exists()) {
//	fsaveDirectory.mkdirs();
//}
//for(MultipartFile multipartFile: files) {
//	if(!multipartFile.isEmpty()) {
//		TktImgVO tktImgVO=new TktImgVO();
//		String filename=multipartFile.getOriginalFilename();
//		System.out.println(filename);
//		multipartFile.transferTo(new File(realPath + "/" + filename));
//	}
//		
//}
