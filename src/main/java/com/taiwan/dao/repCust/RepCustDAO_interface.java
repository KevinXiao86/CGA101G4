package com.taiwan.dao.repCust;

import java.sql.*;
import java.util.List;

import com.taiwan.beans.RepCustVO;

public interface RepCustDAO_interface {
	// �ھڬy�����d�����|
		public RepCustVO queryRepCustByRep_cust_id(Integer rep_cust_id);

		// �ھڷ|��ID�d�����|
		public List<RepCustVO> queryRepCustByCust_id(Integer cust_id);

		// �ھڼt��ID�d�����|
		public List<RepCustVO> queryRepCustByCmp_id(Integer cmp_id);

		// �ھں޲z��ID�d�����|
		public List<RepCustVO> queryRepCustByEmp_id(Integer emp_id);

		// �ھڤ���d�����|
		public List<RepCustVO> queryRepCustByRep_cust_date(Timestamp startDate, Timestamp endDate);

		// �ھڪ��A�d�����|
		public List<RepCustVO> queryRepCustByStatus(String status);
		
		// �d�ߥ������|
		public List<RepCustVO> queryRepCustAll();

		// �s�W�|�����|
		public Integer insertRepCust(Integer cust_id, Integer room_id, String reason);

		// �B�z�|�����|
		public Integer updateRepCust(Integer rep_cust_id,Integer emp_id ,String status ,String result);
}
