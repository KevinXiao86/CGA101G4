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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	// 分頁房型列表
	@RequestMapping(value = "/getAllRoomtypesByPage", method = RequestMethod.GET)
	public String getAllRoomtypesByPage(HttpServletRequest request, Model model,
			@RequestParam(value = "pageNo", required = false)String pageNoStr, 
			@RequestParam(value = "pageSize", required = false)String pageSizeStr, 
			@RequestParam("cmpId")String cmpIdStr) {
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
		page.setUrl("roomtype/getAllRoomtypesByPage?cmpId=" + cmpId + "&");

		// 4. 將獲取的page對象放入到request域中
		model.addAttribute("page", page);

		// 4.請求轉發到列表頁面
		return "/front-end/roomtype/room_list.jsp";
	}

	// 修改房型價錢
	@GetMapping("/updateRoomtypePrice")
	@ResponseBody
	public Roomtype updateRoomtypePrice(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam("cmpId")String cmpId, @RequestParam("roomtypeId")String roomtypeId, @RequestParam("price")String price)
			throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		Roomtype roomtype = roomtypeService.updatePriceByCmpIdAndRoomtypeId(cmpId, roomtypeId, price);
		if (roomtype == null) {
			roomtype = new Roomtype();
			roomtype.setSuccessful(false);
			roomtype.setMessage("修改價錢失敗");
		}

		return roomtype;
	}

	// 修改房型的狀態
	@GetMapping("/updateRoomtypeStatus")
	public String updateRoomtypeStatus(HttpServletRequest request, Model model,
			@RequestParam("cmpId")String cmpId, @RequestParam("roomtypeId")String roomtypeId, @RequestParam("status")String status) {

		//1. 數據校驗
		if (cmpId.length() == 0 || roomtypeId.length() == 0 || status.length() == 0) {
			model.addAttribute("errorMsg", "非法請求");
			return "/front-end/roomtype/room_list.jsp";
		}
		
		boolean result = roomtypeService.updateStatusByCmpIdAndRoomtypeId(cmpId, roomtypeId, status);

		if (result) {
			return "redirect:/roomtype/getAllRoomtypesByPage?cmpId=" + cmpId;
		} else {
			model.addAttribute("errorMsg", "修改房型狀態失敗");
			return "/front-end/roomtype/room_list.jsp";
		}
	}


	// 查看房型詳情
	@GetMapping("/getRoomtype")
	public String getRoomtypeByCmpIdAndRoomtypeId(HttpServletRequest request, Model model,
			@RequestParam("cmpId")String cmpId, @RequestParam("roomtypeId")String roomtypeId) {
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
	@PostMapping("/insertRoomtype")
	public String insertRoomtype(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpSession session,
		Model model, @RequestParam("roomtypeName")String roomtypeName, @RequestParam("roomtypeAmount")String roomtypeAmountStr,
		@RequestParam("roomtypePeople")String roomtypePeopleStr, @RequestParam("roomtypePrice")String roomtypePriceStr,
		@RequestParam("status")String[] status, @RequestParam("roomtypeArea")String roomtypeAreaStr,
		@RequestParam("roomtypeIntroduce")String roomtypeIntroduce) throws IOException, ServletException {
		
		//數據校驗
		if ("".equals(roomtypeName.trim())) {
			model.addAttribute("errorMsg", "請輸入房型名稱!");
			return "/front-end/roomtype/add.jsp";
		}
		if(!roomtypeAmountStr.matches("^[0-9]*$")) {
			model.addAttribute("errorMsg", "請輸入正確的房型數量!");
			return "/front-end/roomtype/add.jsp";
		}
		if(!roomtypePeopleStr.matches("^[0-9]*$")) {
			model.addAttribute("errorMsg", "請輸入正確的入住數量!");
			return "/front-end/roomtype/add.jsp";
		}
		if(!roomtypePriceStr.matches("^[0-9]*$")) {
			model.addAttribute("errorMsg", "請輸入正確的入房型價格!");
			return "/front-end/roomtype/add.jsp";
		}
		if ("".equals(roomtypeIntroduce.trim())) {
			model.addAttribute("errorMsg", "請輸入房型介紹!");
			return "/front-end/roomtype/add.jsp";	
		}
		// 判斷文件是否為空
		if (files.length == 0) {
			System.out.println("訪問成功");
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("serialNo", "未上傳圖片");
			// 用於回顯訊息
//			model.addAttribute("registCompany", company);
			return "/front-end/roomtype/add.jsp";
		}
		
		
		
		Company company = (Company) session.getAttribute("loginCompany");
		Integer cmpId = company.getCmpId();
		
		Integer roomtypeAmount = CommonUtils.parseInt(roomtypeAmountStr, 0);
		Integer roomtypePeople = CommonUtils.parseInt(roomtypePeopleStr, 0);
		Integer totalScore = 0;
		Integer totalPeople = 0;
		Integer roomtypePrice = CommonUtils.parseInt(roomtypePriceStr, 0);
		System.out.println("roomtypePriceStr: " + roomtypePriceStr);
		System.out.println("roomtypePrice: " + roomtypePrice);
		
		String roomtypeStatus = status[0];
		Integer roomtypeArea = CommonUtils.parseInt(roomtypeAmountStr, 0);

		Roomtype roomtype = new Roomtype(null, cmpId, roomtypeName, roomtypeAmount, roomtypePeople, totalScore,
				totalPeople, roomtypePrice, roomtypeStatus, roomtypeArea, roomtypeIntroduce);

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
	@GetMapping("/editRoomtype")
	public String editRoomtype(HttpServletRequest request, Model model,
			@RequestParam("cmpId")String cmpId, @RequestParam("roomtypeId")String roomtypeId) {
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		model.addAttribute("editRoomtype", roomtype);
		return "/front-end/roomtype/edit.jsp";
	}

	// 在房型修改頁面刪除已有的房型圖片
	@PostMapping("/deleteRoomImg")
	public String deleteRoomImg(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model
			, @RequestParam("roomImgId")String[] roomImgIds, @RequestParam("cmpId")String cmpId
			, @RequestParam("roomtypeId")String roomtypeId) throws IOException {
        
//		String[] roomImgIds = request.getParameterValues("roomImgId");
		roomtypeService.deleteRoomImgInEditPage(roomImgIds, session);
		
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		for (RoomImg roomImg : roomtype.getRoomImgs()) {
			System.out.println(roomImg);
		}
		
		model.addAttribute("editRoomtype", roomtype);
		
		return "/front-end/roomtype/edit.jsp";
	}
	
	
	
	
	// 修改房型
	@PostMapping("/editRoomtypeInEditPage")
	public String editRoomtypeInEditPage(@RequestParam("file") MultipartFile[] files, HttpServletRequest request, HttpSession session,
		Model model, @RequestParam("cmpId")String cmpId, @RequestParam("roomtypeId")String roomtypeId, 
		@RequestParam("roomtypeName")String roomtypeName, @RequestParam("roomtypeAmount")String roomtypeAmountStr, 
		@RequestParam("roomtypePeople")String roomtypePeopleStr, @RequestParam("roomtypePrice")String roomtypePriceStr,
		@RequestParam("status")String[] status, @RequestParam("roomtypeArea")String roomtypeAreaStr, 
		@RequestParam("roomtypeIntroduce")String roomtypeIntroduce) throws IOException, ServletException {

		//數據校驗
		if ("".equals(roomtypeName.trim())) {
			model.addAttribute("errorMsg", "請輸入房型名稱!");
			return "/front-end/roomtype/add.jsp";
		}
		if(!roomtypeAmountStr.matches("^[0-9]*$")) {
			model.addAttribute("errorMsg", "請輸入正確的房型數量!");
			return "/front-end/roomtype/add.jsp";
		}
		if(!roomtypePeopleStr.matches("^[0-9]*$")) {
			model.addAttribute("errorMsg", "請輸入正確的入住數量!");
			return "/front-end/roomtype/add.jsp";
		}
		if(!roomtypePriceStr.matches("^[0-9]*$")) {
			model.addAttribute("errorMsg", "請輸入正確的入房型價格!");
			return "/front-end/roomtype/add.jsp";
		}
		if ("".equals(roomtypeIntroduce.trim())) {
			model.addAttribute("errorMsg", "請輸入房型介紹!");
			return "/front-end/roomtype/add.jsp";	
		}
		// 判斷文件是否為空
		if (files.length == 0) {
			System.out.println("訪問成功");
			// 註冊失敗, 回到註冊頁面並回顯訊息
			model.addAttribute("serialNo", "未上傳圖片");
			// 用於回顯訊息
//			model.addAttribute("registCompany", company);
			return "/front-end/roomtype/add.jsp";
		}
		
		Company company = (Company) session.getAttribute("loginCompany");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		System.out.println(roomtype);
		Integer roomtypeAmount = CommonUtils.parseInt(roomtypeAmountStr, 0);
		Integer roomtypePeople = CommonUtils.parseInt(roomtypePeopleStr, 0);
		Integer roomtypePrice = CommonUtils.parseInt(roomtypePriceStr, 0);
		Integer roomtypeArea = CommonUtils.parseInt(roomtypeAreaStr, 0);

		roomtype.setRoomtypeName(roomtypeName);
		roomtype.setRoomtypeAmount(roomtypeAmount);
		roomtype.setRoomtypePeople(roomtypePeople);
		roomtype.setRoomtypePrice(roomtypePrice);
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
