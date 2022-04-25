package mybatis.mapper;

import com.taiwan.beans.CustomerVO;

public interface CustomerMapper {

	//根據 id 查詢會員
	public CustomerVO getCustomerVOByCustId(Integer custId);
}
