package com.taiwan.controller.roomtype;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.taiwan.beans.Company;
import com.taiwan.beans.RoomImg;
import com.taiwan.beans.Roomtype;
import com.taiwan.service.company.CompanyService;
import com.taiwan.service.roomtype.RoomtypeService;
import com.taiwan.utils.CommonUtils;

@Controller
@RequestMapping("/roomtype")
public class RoomtypeController {

	@Autowired
	private RoomtypeService roomtypeService;

	// 房型列表
	@RequestMapping(value = "/getAllRoomtypes", method = RequestMethod.GET)
	public String getAllRoomtypes(HttpServletRequest request, Model model) {
		Company company = (Company) request.getSession().getAttribute("loginCompany");
		List<Roomtype> roomtypes = roomtypeService.getAllRoomtypes(company.getCmpId());
		model.addAttribute("roomtypes", roomtypes);
		return "/front-end/roomtype/room_list.jsp";
	}

	// 修改房型的狀態
	@RequestMapping("/updateRoomtypeStatus")
	public String updateRoomtypeStatus(HttpServletRequest request, Model model) {
		System.out.println("修改房型的狀態");
		int cmpId = CommonUtils.parseInt(request.getParameter("cmpId"), 0);
		int roomtypeId = CommonUtils.parseInt(request.getParameter("roomtypeId"), 0);
		String status = request.getParameter("status");
		boolean result = roomtypeService.updateStatusByCmpIdAndRoomtypeId(cmpId, roomtypeId, status);
		System.out.println("result: " + result);
		if (result) {
			return "redirect:/roomtype/getAllRoomtypes";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}

	// 修改房型價錢
	@RequestMapping("/updateRoomtypePrice")
	public String updateRoomtypePrice(HttpServletRequest request, Model model) {
		int cmpId = CommonUtils.parseInt(request.getParameter("cmpId"), 0);
		int roomtypeId = CommonUtils.parseInt(request.getParameter("roomtypeId"), 0);
		int price = CommonUtils.parseInt(request.getParameter("price"), 0);
		boolean result = roomtypeService.updatePriceByCmpIdAndRoomtypeId(cmpId, roomtypeId, price);
		if (result) {
			return "redirect:/roomtype/getAllRoomtypes";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}

	// 查看房型詳情
	@RequestMapping("/getRoomtype")
	public String getRoomtypeByCmpIdAndRoomtypeId(HttpServletRequest request, Model model) {
		int cmpId = CommonUtils.parseInt(request.getParameter("cmpId"), 0);
		int roomtypeId = CommonUtils.parseInt(request.getParameter("roomtypeId"), 0);
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
		Integer totalScore = CommonUtils.parseInt(request.getParameter("totalScore"), 0);
		Integer totalPeople = CommonUtils.parseInt(request.getParameter("totalPeople"), 0);
		Integer roomtypePrice = CommonUtils.parseInt(request.getParameter("roomtypePrice"), 0);
		String roomtypeStatus = request.getParameter("roomtypeStatus");
		Integer roomtypeArea = CommonUtils.parseInt(request.getParameter("roomtypeArea"), 0);
		String roomtypeIntroduce = request.getParameter("roomtypeIntroduce");

		Roomtype roomtype = new Roomtype(null, cmpId, roomtypeName, roomtypeAmount, roomtypePeople, totalScore,
				totalPeople, roomtypePrice, roomtypeStatus, roomtypeArea, roomtypeIntroduce);

		//創建一個集合, 之後用來保存 RoomImg
		List<RoomImg> imgs = new ArrayList<RoomImg>();
		roomtype.setRoomImgs(imgs);
//		System.out.println(roomtype);
		
		//循環遍歷每一個檔案
		for (MultipartFile multipartFile : files) {
			//isEmpty(): 空返回 true, 有檔案返回 false
			if (!multipartFile.isEmpty()) {
				RoomImg roomImg = new RoomImg();
				
				// 獲取上傳的文件名
				String fileName = multipartFile.getOriginalFilename();

				// 處理文件重名問題
				String hzName = fileName.substring(fileName.lastIndexOf("."));
				fileName = UUID.randomUUID().toString() + hzName;
//				System.out.println(fileName);
				
				// 獲取服務器中 images/Company/廠商帳號/房型編號 目錄的路徑
				//獲取當前的 roomtypeId, 並使其加一, 從而得到下一個自動編號
				int roomtypeId = roomtypeService.getRoomtypeId() + 1;
				//為 RoomImg 設置 roomtypeId
				roomImg.setRoomtypeId(roomtypeId);
				roomtype.setRoomtypeId(roomtypeId);
//				System.out.println(roomtypeId);
				ServletContext servletContext = session.getServletContext();
				String roomtypePath = servletContext.getRealPath("/images/Company/" + company.getCmpAccount() + File.separator + roomtypeId);
//				System.out.println(roomtypePath);
				
				//創建目錄
				File file2 = new File(roomtypePath);
				if (!file2.exists()) {
					file2.mkdir();
				}
				
				//最終路徑
				String finalPath = roomtypePath + File.separator + fileName;
//				System.out.println(finalPath);
				
				// 實現上傳功能
				try {
					multipartFile.transferTo(new File(finalPath));
//					System.out.println("images" + File.separator + "Company" + File.separator + company.getCmpAccount() + File.separator + roomtypeId + File.separator + fileName);
					//為 RoomImg 設置保存路徑
					String savePath = "images" + File.separator + "Company" + File.separator + company.getCmpAccount() + File.separator + roomtypeId + File.separator + fileName;
					roomImg.setRoomImg(savePath);
					imgs.add(roomImg);
				} catch (Exception e) {
				}
			}
		}
		
		System.out.println(roomtype);
//		System.out.println("----------------------------------------------");
//		for(RoomImg roomImg : roomtype.getRoomImgs()) {
//			System.out.println(roomImg);
//		}
		
		
		//調用 service 業務方法
		boolean result = roomtypeService.insertRoomtype(roomtype);
//		System.out.println(result);
		
		if (result) {
			return "redirect:/roomtype/getAllRoomtypes";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}


	
	
	// 查看房型詳情(這是給修改使用的, 之後再做完善, 先測試運行就好)
	@RequestMapping("/getRoomtype2")
	public String getRoomtypeByCmpIdAndRoomtypeId2(HttpServletRequest request, Model model) {
		int cmpId = CommonUtils.parseInt(request.getParameter("cmpId"), 0);
		int roomtypeId = CommonUtils.parseInt(request.getParameter("roomtypeId"), 0);
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		if (roomtype != null) {
			model.addAttribute("roomtype", roomtype);
			return "/front-end/roomtype/edit.jsp";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}
	
	// 這個方法會提前所有的目標方法先運行, 要特別小心
	// @RequestParam(value = "cmpId", required = false): 並不是每個請求都需要先查詢廠商, 所以我這邊設置為
	// false
	@ModelAttribute
	public void getCompanyByModelAttribute(@RequestParam(value = "cmpId", required = false) String cmpId,
			@RequestParam(value = "roomtypeId", required = false)String roomtypeId , Model model) {
		int idCmp = 0;
		int idRoomtype = 0;
		// 做這個判斷是為了防止空指針異常
		if (cmpId != null) {
			// 1. 數據校驗
			idCmp = CommonUtils.parseInt(cmpId, 0);
		}
		if (roomtypeId != null) {
			//1. 數據校驗
			idRoomtype = CommonUtils.parseInt(roomtypeId, 0);
		}

		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(idCmp, idRoomtype);
		model.addAttribute("roomtype", roomtype);
		System.out.println("ModelAttribute" + roomtype);
	}
	
	
	// 房型資料修改
	@RequestMapping("/editRoomtype")
	public String editCompany(@RequestParam(value = "cmpId", required = false) String cmpId, @RequestParam(value = "roomtypeId", required = false)String roomtypeId ,
			Model model, HttpServletRequest request, @ModelAttribute("roomtype")Roomtype roomtype) {
		System.out.println(roomtype);
		return "";
	}
	
	// 在房型修改頁面刪除已有的房型圖片
	@RequestMapping("/deleteRoomImg")
	public String deleteRoomImg(HttpServletRequest request) {
		String[] roomImgIds = request.getParameterValues("roomImgId");
		roomtypeService.deleteRoomImg(roomImgIds);
//		for(String str : roomImgIds) {
//			System.out.println(str);
//		}
//		return "redirect:/roomtype/getRoomtype2";
		return "";
	}
}
