package com.taiwan.service.roomOrder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taiwan.beans.Customer;
import com.taiwan.beans.RoomOrder;
import com.taiwan.utils.CommonUtils;

import mybatis.mapper.CustomerMapper;
import mybatis.mapper.RoomOrderMapper;

@Service
public class RoomOrderServicePlus {

	@Autowired
	private RoomOrderMapper roomOrderMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	
	@Transactional(readOnly = true)
	public Integer getTotalPriceByCmpId(Integer cmpId, String year, String month) {
		Integer totalPrice = roomOrderMapper.getTotalPrice(cmpId, year, month);
		return totalPrice;
	}
	
	
	@Transactional(readOnly = true)
	public List<RoomOrder> getCustomersByName(String name){
		List<Customer> customers = customerMapper.getCustomerByName(name);
		List<RoomOrder> roomOrders = new ArrayList<RoomOrder>();
		
		for(Customer customer : customers) {
			List<RoomOrder> list = customer.getRoomOrders();
			for(int i = 0; i < list.size(); i++) {
				roomOrders.add(list.get(i));
			}
		}
		return roomOrders;
	}
	
	
	@Transactional
	public boolean updateRoomOrderStatus(String roomOrderId, String status) {
		System.out.println("訪問成功");
		Integer id = CommonUtils.parseInt(roomOrderId, 0);
		System.out.println("id: " + id);
		int result = roomOrderMapper.updateStatus(id, status);
		if (result > 0) {
			return true; 
		}else {
			return false;
		}
	}
}
