package com.taiwan.controller.news;

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

import com.taiwan.service.news.NewsService;
import com.taiwan.utils.ControllerUtil;
import com.taiwan.utils.UUIDFileName;

@WebServlet("/news/newsCreator")
@MultipartConfig
public class NewsCreator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	NewsService newsService = ControllerUtil.getBean(NewsService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		request.setAttribute("errorMsgs", errorMsgs);
		try {
			// 這邊確認輸入的最新消息名稱不是空值或是空字串
			String title = request.getParameter("title");
			if (title == null || title.trim().equals("")) {
				errorMsgs.put("title", "最新消息名稱請勿全部都輸入空白");
			}
			// 這邊確認最新消息內文是不是空值或是空字串
			String content = request.getParameter("content");
			if (content == null || content.trim().equals("")) {
				errorMsgs.put("content", "最新消息內文請勿不輸入，或是只有空白");
			}
			// 我最新消息要存在這個檔案目錄之下
			String saveDirectory = "/images/news";
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
				errorMsgs.put("updateFile", "沒有傳入最新消息的照片");
			}
			UUIDFileName uuidFileName = new UUIDFileName();
			String filename = uuidFileName.getUUIDFileName(part);
			part.write(realPath + "/" + filename);
			// 傳入db的路徑前面不能再有斜槓，不然伺服器找的時候會跑一次阿飄路徑
			String dbSaveDirectory = "images/news";
			// 要傳回數據庫的路徑
			String dbPath = dbSaveDirectory + "/" + filename;

			// 如果錯誤訊息的map不是空值的話，就請求轉發回/news/news_create.jsp
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_create.jsp");
				rd.forward(request, response);
				return;
			}

			// 開始新增資料
			newsService.addNews(title, content, dbPath);
			// 新增完成，請求轉發到theme首頁
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_index.jsp");
			rd.forward(request, response);
			// 其他錯誤處理
		} catch (Exception e) {
			errorMsgs.put("Exception", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("/back-end/news/news_create.jsp");
			rd.forward(request, response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
