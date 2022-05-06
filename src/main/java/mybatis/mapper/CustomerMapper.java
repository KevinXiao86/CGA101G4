package mybatis.mapper;

import com.taiwan.beans.Customer;

public interface CustomerMapper {

	//根據 id 查詢會員
//	public CustomerVO getCustomerVOByCustId(Integer custId);
	
	//註冊會員
	public int insertCustomer(Customer customer);
	
	//根據會員帳號查詢廠商
//	public CustomerVO queryCustomerByCustAccount(String Account);
}













	

