package com.taiwan.controller.roomtype;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.el.fmt.RequestEncodingTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.taiwan.beans.Company;
import com.taiwan.beans.RoomImg;
import com.taiwan.beans.Roomtype;
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
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		String status = request.getParameter("status");
		
		boolean result = roomtypeService.updateStatusByCmpIdAndRoomtypeId(cmpId, roomtypeId, status);
		
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
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		String price = request.getParameter("price");
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

		System.out.println(roomtype);
		
		//獲取房型編號
		int roomtypeId = roomtypeService.getRoomtypeId()  + 1;
		roomtype.setRoomtypeId(roomtypeId);
		
		// 創建一個集合, 之後用來保存 RoomImg
		List<RoomImg> imgs = new ArrayList<RoomImg>();
		roomtype.setRoomImgs(imgs);
		
		// 循環遍歷每一個檔案
		for (MultipartFile multipartFile : files) {
			// isEmpty(): 空返回 true, 有檔案返回 false
			if (!multipartFile.isEmpty()) {
				//創建一個 roomImg
				RoomImg roomImg = new RoomImg();
				// 設置房型編號
				roomImg.setRoomtypeId(roomtypeId);
				// 設置圖片路徑
				String savePath = roomtypeService.getSavePath(multipartFile, session, company.getCmpAccount(), roomtypeId + "");
				roomImg.setRoomImg(savePath);
				
				// 實現上傳功能
				try {
					
					multipartFile.transferTo(new File(session.getServletContext().getRealPath(File.separator) + File.separator + savePath));
					//把創建的 roomImg 物件放入集合中
					imgs.add(roomImg);
				} catch (Exception e) {
				}
			}
		}
		
		
		// 調用 service 業務方法
		boolean result = roomtypeService.insertRoomtype(roomtype);

		if (result) {
			return "redirect:/roomtype/getAllRoomtypes";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}
	
	//去往修改頁面之前, 要先找到對應的房型, 這樣才能夠在 edit.jsp 中顯示
	@RequestMapping("/editRoomtype")
	public String editRoomtype(HttpServletRequest request, Model model) {
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		for(RoomImg roomImg : roomtype.getRoomImgs()) {
			System.out.println(roomImg);
		}
		model.addAttribute("editRoomtype", roomtype);
		return "/front-end/roomtype/edit.jsp";
	}
	
	// 在房型修改頁面刪除已有的房型圖片
	@RequestMapping("/deleteRoomImg")
	public String deleteRoomImg(HttpServletRequest request, HttpSession session, Model model) {
		String[] roomImgIds = request.getParameterValues("roomImgId");
		roomtypeService.deleteRoomImgInEditPage(roomImgIds, session);
		
		String cmpId = request.getParameter("cmpId");
		String roomtypeId = request.getParameter("roomtypeId");
		Roomtype roomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
		for(RoomImg roomImg : roomtype.getRoomImgs()) {
			System.out.println(roomImg);
		}
		
		model.addAttribute("editRoomtype", roomtype);
		
		return "/front-end/roomtype/edit.jsp";
	}
	
	
	
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
				//創建一個 roomImg
				RoomImg roomImg = new RoomImg();
				// 設置房型編號
				int id = CommonUtils.parseInt(roomtypeId, 0);
				roomImg.setRoomtypeId(id);
				
				// 設置圖片路徑
				String savePath = roomtypeService.getSavePath(multipartFile, session, company.getCmpAccount(), roomtypeId + "");
				roomImg.setRoomImg(savePath);
				
				// 實現上傳功能
				try {
					
					multipartFile.transferTo(new File(session.getServletContext().getRealPath(File.separator) + File.separator + savePath));
					//把創建的 roomImg 物件放入集合中
					imgs.add(roomImg);
				} catch (Exception e) {
				}
			}
		}
		
		// 調用 service 業務方法
		boolean result = roomtypeService.updateRoomtype(roomtype);

		if (result) {
			Roomtype editRoomtype = roomtypeService.getRoomtypeByCmpIdAndRoomtypeId(cmpId, roomtypeId);
			model.addAttribute("editRoomtype", editRoomtype);
			return "/front-end/roomtype/edit.jsp";
		} else {
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
	}
}
