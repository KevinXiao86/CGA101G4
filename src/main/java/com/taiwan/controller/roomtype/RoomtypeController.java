package com.taiwan.controller.roomtype;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taiwan.beans.Company;
import com.taiwan.beans.Roomtype;
import com.taiwan.service.roomtype.RoomtypeService;

@Controller
@RequestMapping("/roomtype")
public class RoomtypeController {

	@Autowired
	private RoomtypeService roomtypeService;
	
	
	//獲取所有房型
	@RequestMapping("/getAllRoomtypes")
	public String getAllRoomtypes(Model model, HttpSession session) {
		//1. 從 session 中獲取到登入的廠商訊息
		Company loginCompany = (Company) session.getAttribute("loginCompany");
//		System.out.println("getAllRoomtypes:" + loginCompany.getCmpId());
		if (loginCompany == null) {
			//跳到錯誤頁面
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";	
		}
		
		//2. 調用業務方法
		List<Roomtype> roomtypes = roomtypeService.getAllRoomtypesByCmpId(loginCompany.getCmpId());
		
		//3. 判斷是不是 null
		if (roomtypes == null) {
			//跳到錯誤頁面
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
		
		//4. 有數據就放到 Model 中到頁面進行顯示
		model.addAttribute("roomtypes", roomtypes);
		return "/front-end/roomtype/roomtype.jsp";
	}
	
	
	//更新房型的狀態
	@RequestMapping("/updateRoomtypeStatus")
	public String updateRoomtypeStatus(@RequestParam("cmpId")String cmpId, @RequestParam("roomtypeId")String roomtypeId,
			@RequestParam("status")String status, Model model) {
		//1. 調用業務方法
		boolean result = roomtypeService.updateRoomtypeStatus(cmpId, roomtypeId, status);
		//2. 判斷
		if (result) {
			//重定向回房型列表
			return "redirect:/roomtype/getAllRoomtypes";
		}else {
			//跳到錯誤頁面
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";			
		}
	}
}
