package com.taiwan.controller.roomtype;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taiwan.beans.Roomtype;
import com.taiwan.service.roomtype.RoomtypeService;

@Controller
@RequestMapping("/roomtype")
public class RoomtypeController {

	@Autowired
	private RoomtypeService roomtypeService;
	
	@RequestMapping("/getAllRoomtypes")
	public String getAllRoomtypes(@RequestParam("cmpId")String cmpId, Model model) {
		//1. 調用業務方法
		List<Roomtype> roomtypes = roomtypeService.getAllRoomtypesByCmpId(cmpId);
		//2. 判斷是不是 null
		if (roomtypes == null) {
			//跳到錯誤頁面
			model.addAttribute("errorInfo", "word 很大, 你忍一下 !!");
			return "/error/error.jsp";
		}
		//3. 有數據就放到 Model 中到頁面進行顯示
		model.addAttribute("roomtypes", roomtypes);
		return "/front-end/roomtype/roomtype.jsp";
	}
}
