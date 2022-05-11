package com.taiwan.controller.customerManage;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.taiwan.beans.EmployeeVO;
import com.taiwan.beans.RepCmpVO;
import com.taiwan.service.impl.RepCmpService12Impl;
import com.taiwan.service.repCmp.impl.RepCmpServiceImpl;

@WebServlet("/custManage/SetResult")
public class SetResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SetResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 取得管理員Id
		HttpSession session = request.getSession();
		EmployeeVO employeeVO = (EmployeeVO) session.getAttribute("employeeVO");
		Integer empId = employeeVO.getEmpId();
		// 取得要update的檢舉廠商流水號(repCmpId)
		Integer repCmpId = Integer.valueOf(request.getParameter("repCmpId"));
		// 取得狀態
		String status = request.getParameter("status");
		// 取得處理結果
		String result = request.getParameter("result");
		// 把資料送到資料庫
		RepCmpServiceImpl dao = new RepCmpServiceImpl();
		dao.setRepCmpResult(repCmpId, empId, status, result);
		// 再抓一次檢舉廠商資料送往前端
		RepCmpService12Impl repCmpService12Impl = new RepCmpService12Impl();
		List<RepCmpVO> list = repCmpService12Impl.getAllRepCmp();
		request.setAttribute("list", list);
	}

}
