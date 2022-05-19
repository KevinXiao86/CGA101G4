package com.taiwan.controller.roomOrder;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taiwan.beans.Company;
import com.taiwan.service.roomOrder.RoomOrderServicePlus;

@Controller
@RequestMapping("/roomOrder")
public class RoomOrderController {

	@Autowired
	private RoomOrderServicePlus roomOrderServicePlus;
	
	
	@PostMapping("/updateStatus")
	public String updateStatus(@RequestParam("roomOrderId")String roomOrderId, HttpSession session, Model model,
		@RequestParam("status")String status, @RequestParam(value = "custName",required =  false)String custName) {
		System.out.println("訪問成功");
		System.out.println("roomOrderId: " + roomOrderId);
		System.out.println("status: " + status);
		System.out.println("custName: " + custName);
		boolean result = roomOrderServicePlus.updateRoomOrderStatus(roomOrderId, status);
		boolean flag = "".equals(custName); 
		
		if (result && !flag) {
			model.addAttribute("error", "取消成功");
			return "/front-end/cmpRoomOrder/cmp_findOrder.jsp?name=" + custName;
		}else if(!result){
			model.addAttribute("error", "取消失敗");
			return "/roomOrder/cmpFindOrder";
		}else {
			model.addAttribute("error", "取消成功");
			return"/roomOrder/cmpFindOrder";
		}
	}
}
