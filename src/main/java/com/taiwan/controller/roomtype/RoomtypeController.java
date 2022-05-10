package com.taiwan.controller.roomtype;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.taiwan.beans.Company;
import com.taiwan.beans.Page;
import com.taiwan.beans.RoomImg;
import com.taiwan.beans.Roomtype;
import com.taiwan.service.roomtype.RoomtypeService;
import com.taiwan.utils.CommonUtils;

@Controller
@RequestMapping("/roomtype")
public class RoomtypeController {

	@Autowired
	private RoomtypeService roomtypeService;

	// 產生令牌
	@RequestMapping("/getToken")
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
		page.setUrl("roomtype/getAllRoomtypesByPage?cmpId=" + cmpId + "&");

		// 4. 將獲取的page對象放入到request域中
		model.addAttribute("page", page);

		// 4.請求轉發到列表頁面
		return "/front-end/roomtype/room_list.jsp";
	}

	// 修改房型價錢
	@RequestMapping("/updateRoomtypePrice")
	public Roomtype updateRoomtypePrice(HttpServletRequest request, HttpServletResponse response, Model model)
			throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		String price = request.getParameter("price");
		Roomtype roomtype = roomtypeService.updatePriceByCmpIdAndRoomtypeId(cmpId, roomtypeId, price);
		if (roomtype == null) {
			roomtype = new Roomtype();
			roomtype.setSuccessful(false);
			roomtype.setMessage("修改價錢失敗");
		}

		Gson gson = new Gson();
		String jsonString = gson.toJson(roomtype);
		response.getWriter().write(jsonString);
		return roomtype;
	}

	// 修改房型的狀態
	@RequestMapping("/updateRoomtypeStatus")
	public String updateRoomtypeStatus(HttpServletRequest request, Model model) {
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		String status = request.getParameter("status");

		boolean result = roomtypeService.updateStatusByCmpIdAndRoomtypeId(cmpId, roomtypeId, status);

		if (result) {
			return "redirect:/roomtype/getAllRoomtypesByPage?cmpId=" + cmpId;
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}

//	// 修改房型價錢
//	@RequestMapping("/updateRoomtypePrice")
//	public String updateRoomtypePrice(HttpServletRequest request, Model model) {
//		String cmpId = request.getParameter("cmpId");
//		String roomtypeId = request.getParameter("roomtypeId");
//		String price = request.getParameter("price");
//		boolean result = roomtypeService.updatePriceByCmpIdAndRoomtypeId(cmpId, roomtypeId, price);
//		if (result) {
//			return "redirect:/roomtype/getAllRoomtypes";
//		} else {
//			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
//			return "/error/error.jsp";
//		}
//	}
//	
	// 房型列表
//	@RequestMapping(value = "/getAllRoomtypes", method = RequestMethod.GET)
//	public String getAllRoomtypes(HttpServletRequest request, Model model) {
//		Company company = (Company) request.getSession().getAttribute("loginCompany");
//		List<Roomtype> roomtypes = roomtypeService.getAllRoomtypes(company.getCmpId());
//		model.addAttribute("roomtypes", roomtypes);
//		return "/front-end/roomtype/room_list.jsp";
//	}

	// 查看房型詳情
	@RequestMapping("/getRoomtype")
	public String getRoomtypeByCmpIdAndRoomtypeId(HttpServletRequest request, Model model) {
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		if (roomtype != null) {
			model.addAttribute("roomtype", roomtype);
			return "/front-end/roomtype/detail.jsp";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}



	// 新增房型
	@RequestMapping("/insertRoomtype")
	public String insertRoomtype(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,
			HttpSession session, Model model) throws IOException, ServletException {

		Company company = (Company) request.getSession().getAttribute("loginCompany");
		Integer cmpId = company.getCmpId();
		String roomtypeName = request.getParameter("roomtypeName");
		Integer roomtypeAmount = CommonUtils.parseInt(request.getParameter("roomtypeAmount"), 0);
		Integer roomtypePeople = CommonUtils.parseInt(request.getParameter("roomtypePeople"), 0);
		Integer totalScore = 0;
		Integer totalPeople = 0;
		Integer roomtypePrice = CommonUtils.parseInt(request.getParameter("roomtypePrice"), 0);
		String[] status = request.getParameterValues("status");
		String roomtypeStatus = status[0];
		Integer roomtypeArea = CommonUtils.parseInt(request.getParameter("roomtypeArea"), 0);
		String roomtypeIntroduce = request.getParameter("roomtypeIntroduce");

		Roomtype roomtype = new Roomtype(null, cmpId, roomtypeName, roomtypeAmount, roomtypePeople, totalScore,
				totalPeople, roomtypePrice, roomtypeStatus, roomtypeArea, roomtypeIntroduce);

		if (checkRepeatSubmit(request)) {
			System.out.println("請不要重複提交表單!");
			roomtype.setSuccessful(false);
			roomtype.setMessage("請不要重複提交表單!");
			roomtype.setUrl("/front-end/roomtype/add.jsp");
			model.addAttribute("roomtype", roomtype);
			return roomtype.getUrl();
		}

		// 在第一次處理表單之後需要清空 token, 這一步非常關鍵
		request.getSession().removeAttribute("token");


		// 獲取房型編號
		int roomtypeId = roomtypeService.getRoomtypeId() + 1;
		roomtype.setRoomtypeId(roomtypeId);

		// 創建一個集合, 之後用來保存 RoomImg
		List<RoomImg> imgs = new ArrayList<RoomImg>();
		roomtype.setRoomImgs(imgs);

		// 循環遍歷每一個檔案
		for (MultipartFile multipartFile : files) {
			// isEmpty(): 空返回 true, 有檔案返回 false
			if (!multipartFile.isEmpty()) {
				// 創建一個 roomImg
				RoomImg roomImg = new RoomImg();
				// 設置房型編號
				roomImg.setRoomtypeId(roomtypeId);
				// 設置圖片路徑
				String savePath = roomtypeService.getSavePath(multipartFile, session, company.getCmpAccount(),
						roomtypeId + "");
				roomImg.setRoomImg(savePath);

				// 實現上傳功能
				try {

					multipartFile.transferTo(new File(
							session.getServletContext().getRealPath(File.separator) + File.separator + savePath));
					// 把創建的 roomImg 物件放入集合中
					imgs.add(roomImg);
				} catch (Exception e) {
				}
			}
		}

		// 調用 service 業務方法
		boolean result = roomtypeService.insertRoomtype(roomtype);

		if (result) {
			int pageNo = CommonUtils.parseInt(request.getParameter("pageTotal"), 0);
			System.out.println("pageNo: " + pageNo);
			int pageTotalCount = CommonUtils.parseInt(request.getParameter("pageTotalCount"), 0) + 1;
			System.out.println("pageTotalCount: " + pageTotalCount);
			if (pageTotalCount - (Page.PAGE_SIZE * pageNo) > 0) {				
				pageNo += 1;
			}
			return "redirect:/roomtype/getAllRoomtypesByPage?cmpId=" + cmpId + "&pageNo=" + pageNo;
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}

	// 去往修改頁面之前, 要先找到對應的房型, 這樣才能夠在 edit.jsp 中顯示
	@RequestMapping("/editRoomtype")
	public String editRoomtype(HttpServletRequest request, Model model) {
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
//		for (RoomImg roomImg : roomtype.getRoomImgs()) {
//			System.out.println(roomImg);
//		}
		model.addAttribute("editRoomtype", roomtype);
		return "/front-end/roomtype/edit.jsp";
	}

	// 在房型修改頁面刪除已有的房型圖片
	@RequestMapping("/deleteRoomImg")
	public String deleteRoomImg(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model) throws IOException {
        if(checkRepeatSubmit(request)) {
        	System.out.println("請不要重複提交表單!");
        	response.sendRedirect(request.getHeader("Referer"));
        }
        
        // 在第一次處理表單之後需要清空 token, 這一步非常關鍵
        request.getSession().removeAttribute("token");

        
		String[] roomImgIds = request.getParameterValues("roomImgId");
		roomtypeService.deleteRoomImgInEditPage(roomImgIds, session);
		
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		for (RoomImg roomImg : roomtype.getRoomImgs()) {
			System.out.println(roomImg);
		}
		
		model.addAttribute("editRoomtype", roomtype);
		
		return "/front-end/roomtype/edit.jsp";
	}
	
	
	
//	// 在房型修改頁面刪除已有的房型圖片
//	@RequestMapping("/deleteRoomImg")
//	public String deleteRoomImg(HttpServletRequest request, HttpSession session, Model model) {
//		if (checkRepeatSubmit(request)) {
//			System.out.println("請不要重複提交表單!");
//			return "";
//		}
//		
//		// 在第一次處理表單之後需要清空 token, 這一步非常關鍵
//		request.getSession().removeAttribute("token");
//		
//		String[] roomImgIds = request.getParameterValues("roomImgId");
//		roomtypeService.deleteRoomImgInEditPage(roomImgIds, session);
//		
//		String cmpId = request.getParameter("cmpId");
//		String roomtypeId = request.getParameter("roomtypeId");
//		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
//		for (RoomImg roomImg : roomtype.getRoomImgs()) {
//			System.out.println(roomImg);
//		}
//		
//		model.addAttribute("editRoomtype", roomtype);
//		
//		return "/front-end/roomtype/edit.jsp";
//	}

	
	
//	@RequestMapping("/editRoomtypeInEditPage")
//	public String editRoomtypeInEditPage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,
//			HttpSession session, Model model) throws IOException, ServletException {
//		return "";
//	}
	
	
	// 修改房型
	@RequestMapping("/editRoomtypeInEditPage")
	public String editRoomtypeInEditPage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,
			HttpSession session, Model model) throws IOException, ServletException {
		Company company = (Company) request.getSession().getAttribute("loginCompany");

		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		System.out.println(roomtype);

		String roomtypeName = request.getParameter("roomtypeName");
		Integer roomtypeAmount = CommonUtils.parseInt(request.getParameter("roomtypeAmount"), 0);
		Integer roomtypePeople = CommonUtils.parseInt(request.getParameter("roomtypePeople"), 0);
		Integer roomtypePrice = CommonUtils.parseInt(request.getParameter("roomtypePrice"), 0);
		String[] status = request.getParameterValues("status");
		Integer roomtypeArea = CommonUtils.parseInt(request.getParameter("roomtypeArea"), 0);
		String roomtypeIntroduce = request.getParameter("roomtypeIntroduce");

		roomtype.setRoomtypeName(roomtypeName);
		roomtype.setRoomtypeAmount(roomtypeAmount);
		roomtype.setRoomtypePeople(roomtypePeople);
		roomtype.setRoomtypePrice(roomtypePeople);
		roomtype.setRoomtypeStatus(status[0]);
		roomtype.setRoomtypeArea(roomtypeArea);
		roomtype.setRoomtypeIntroduce(roomtypeIntroduce);

		System.out.println(roomtype);

		// 創建一個集合, 之後用來保存 RoomImg
		List<RoomImg> imgs = new ArrayList<RoomImg>();
		roomtype.setRoomImgs(imgs);

		// 循環遍歷每一個檔案
		for (MultipartFile multipartFile : files) {
			// isEmpty(): 空返回 true, 有檔案返回 false
			if (!multipartFile.isEmpty()) {
				// 創建一個 roomImg
				RoomImg roomImg = new RoomImg();
				// 設置房型編號
				int id = CommonUtils.parseInt(roomtypeId, 0);
				roomImg.setRoomtypeId(id);

				// 設置圖片路徑
				String savePath = roomtypeService.getSavePath(multipartFile, session, company.getCmpAccount(),
						roomtypeId + "");
				roomImg.setRoomImg(savePath);

				// 實現上傳功能
				try {

					multipartFile.transferTo(new File(
							session.getServletContext().getRealPath(File.separator) + File.separator + savePath));
					// 把創建的 roomImg 物件放入集合中
					imgs.add(roomImg);
				} catch (Exception e) {
				}
			}
		}

		// 調用 service 業務方法
		boolean result = roomtypeService.updateRoomtype(roomtype);
		System.out.println("result: " + result);
		if (result) {
			Roomtype editRoomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
			editRoomtype.setMessage("修改成功!");
			model.addAttribute("editRoomtype", editRoomtype);
			return "/front-end/roomtype/edit.jsp";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}
}
