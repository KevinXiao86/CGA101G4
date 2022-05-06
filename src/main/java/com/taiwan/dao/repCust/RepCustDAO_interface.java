package com.taiwan.dao.repCust;

import java.sql.*;
import java.util.List;

import com.taiwan.beans.RepCustVO;

public interface RepCustDAO_interface {
	// �ھڬy�����d�����|
		public RepCustVO queryRepCustByRepCustId(Integer repCustId);

		// �ھڷ|��ID�d�����|
		public List<RepCustVO> queryRepCustByCustId(Integer custId);

		// �ھڼt��ID�d�����|
		public List<RepCustVO> queryRepCustByCmpId(Integer cmpId);

		// �ھں޲z��ID�d�����|
		public List<RepCustVO> queryRepCustByEmpId(Integer empId);

		// �ھڤ���d�����|
		public List<RepCustVO> queryRepCustByRepCustDate(Timestamp startDate, Timestamp endDate);

		// �ھڪ��A�d�����|
		public List<RepCustVO> queryRepCustByStatus(String status);
		
		// �d�ߥ������|
		public List<RepCustVO> queryRepCustAll();

		// �s�W�|�����|
		public Integer insertRepCust(RepCustVO repCustVO);

		// �B�z�|�����|
		public RepCustVO updateRepCust(RepCustVO repCustVO);
		public void deleteRepCust(Integer repCustId);
}
