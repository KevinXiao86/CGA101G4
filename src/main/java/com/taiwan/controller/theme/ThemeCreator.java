package com.taiwan.controller.theme;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.taiwan.service.theme.ThemeService;
import com.taiwan.utils.ControllerUtil;
import com.taiwan.utils.UUIDFileName;

@WebServlet("/theme/themeCreator")
@MultipartConfig
public class ThemeCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ThemeService themeService = ControllerUtil.getBean(ThemeService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 這邊確認輸入的熱門活動名稱不是空值或是空字串
			String title = request.getParameter("title");
			if (title == null || title.trim().equals("")) {
				errorMsgs.put("title", "熱門活動名稱請勿全部都輸入空白");
			}
			// 這邊確認熱門活動內文是不是空值或是空字串
			String content = request.getParameter("content");
			if (content == null || content.trim().equals("")) {
				errorMsgs.put("content", "熱門活動內文請勿不輸入，或是只有空白");
			}
			// 我熱門活動要存在這個檔案目錄之下
			String saveDirectory = "/images/theme";
			// 找到阿飄路徑
			String realPath = getServletContext().getRealPath(saveDirectory);
//			System.out.println(realPath);
			// 再如果阿飄路徑下沒有這個資料夾就創造，有就不用
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists()) {
				fsaveDirectory.mkdirs();
			}
			// 取得上傳的檔案
			Part part = request.getPart("updateFile");
			if (part.getHeader("content-disposition").contains("filename=\"\"")) {
				errorMsgs.put("updateFile", "沒有傳入熱門活動的照片");
			}
			UUIDFileName uuidFileName = new UUIDFileName();
			String filename = uuidFileName.getUUIDFileName(part);
			part.write(realPath + "/" + filename);
			// 傳入db的路徑前面不能再有斜槓，不然伺服器找的時候會跑一次阿飄路徑
			String dbSaveDirectory = "images/theme";
			// 要傳回數據庫的路徑
			String dbPath = dbSaveDirectory + "/" + filename;

			// 如果錯誤訊息的map不是空值的話，就請求轉發回/theme/theme_create.jsp
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_create.jsp");
				rd.forward(request, response);
				return;
			}

			// 開始新增資料
			themeService.addtheme(title, content, dbPath);
			// 新增完成，請求轉發到theme首頁
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_index.jsp");
			rd.forward(request, response);
			// 其他錯誤處理
		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/theme/theme_create.jsp");
			rd.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
