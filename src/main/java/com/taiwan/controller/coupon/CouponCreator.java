package com.taiwan.controller.coupon;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.catalina.tribes.tipis.AbstractReplicatedMap.MapEntry;

import com.taiwan.service.coupon.CouponService;
import com.taiwan.utils.ControllerUtil;
import com.taiwan.utils.UUIDFileName;


@WebServlet("/coupon/couponCreator")
@MultipartConfig
public class CouponCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CouponService couponService = ControllerUtil.getBean(CouponService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);

		try {
			// 這邊確認輸入的優惠券名稱不是空值或是空字串
			String copName = request.getParameter("copName");
			if (copName == null || copName.trim().equals("")) {
				errorMsgs.put("copName", "優惠券名稱請勿全部都輸入空白");
			}
			// 這邊確認輸入的優惠券介紹不是空值或是空字串
			String introduce = request.getParameter("introduce");
			if (introduce == null || introduce.trim().equals("")) {
				errorMsgs.put("introduce", "優惠券介紹請勿不輸入，或是只有空白鍵");
			}
			// 以字串形式獲取開始日期跟結束日期
			String startString = request.getParameter("startdate");
			String endString = request.getParameter("enddate");
//			System.out.println(startString);
			// 對獲取來的日期格式做轉換，換成timestamp格式
			startString = startString.replace("T", " ") + ":00";
//			System.out.println(startString);
			endString = endString.replace("T", " ") + ":00";
			// 轉成timestamp類
			Timestamp startdate = Timestamp.valueOf(startString);
			Timestamp enddate = Timestamp.valueOf(endString);
			// 如果enddate比startdate還早那就有問題
			if (enddate.before(startdate)) {
				errorMsgs.put("timeError", "結束日期比開始日期還早");
			}
			// 判斷discount裡面有沒有值
			String discount = request.getParameter("discount");
			if (discount == null || discount.trim().equals("")) {
				errorMsgs.put("discount", "折扣不可為空");
			}
			// 判斷discount是不是負值
			Integer discount01 = Integer.valueOf(discount);
			if (discount01 <= 0) {
				errorMsgs.put("discount01", "折扣不可能為0");
			}
			// 我優惠券要存在這個檔案目錄之下
			String saveDirectory = "/images/coupon";
			// 找到阿飄路徑
			String realPath = getServletContext().getRealPath(saveDirectory);
//			System.out.println(realPath);
			// 再如果阿飄路徑下沒有這個資料夾就創造，有就不用
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists()) {
				fsaveDirectory.mkdirs();
			}
			// 取得上傳的檔案
			Part part = request.getPart("uploadFile");
			if (part.getHeader("content-disposition").contains("filename=\"\"")) {
				errorMsgs.put("uploadFile", "沒有傳入熱門活動的照片");
			}
			UUIDFileName uuidFileName = new UUIDFileName();
			String filename = uuidFileName.getUUIDFileName(part);
			part.write(realPath + "/" + filename);
			//傳入db的路徑前面不能再有斜槓，不然伺服器找的時候會跑一次阿飄路徑
			String dbSaveDirectory="images/coupon";
			// 要傳回數據庫的路徑
			String dbPath = dbSaveDirectory + "/" + filename;
//			System.out.println(dbPath);
			//遍歷一下MAP裡面的值
//			for (Map.Entry<String, String> entry : errorMsgs.entrySet()) {
//				System.out.println(entry.getKey() + ":" + entry.getValue());
//			}
			// 如果錯誤訊息的map不是空值的話，就請求轉發回/coupon/cop_create.jsp
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_create.jsp");
				rd.forward(request, response);
				return;
			}

			// 開始新增資料
			couponService.addCoupon(copName, introduce, discount01, startdate, enddate, dbPath);
			// 新增完成，請求轉發到coupon首頁
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_index.jsp");
			rd.forward(request, response);
			// 其他錯誤處理
		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/coupon/cop_create.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
