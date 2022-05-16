<%@page import="com.taiwan.beans.EmployeeVO"%>
<%@page import="com.taiwan.service.employee.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台玩</title>
<!--  Favicon title 小圖 -->
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">
<%
//假設員工10000已登入
int i = 30001;
EmployeeService empSvc = new EmployeeService();
EmployeeVO empVO = empSvc.getOneEmp(i);
request.setAttribute("empVO", empVO);
%>

<%@ include file="back-end-index.jsp"%>

<style>
table {
	background-color: #ced7e8 !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
</head>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">處理廠商檢舉資料</h1>
					<h2>${errorMsgs.reason}</h2>
				</div>
			</div>
			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/repCust12/UpdateRepCust"
				name="form1">
				<table border="2px" class="table table-striped table-hover">
					<tr>
						<th>廠商編號:</th>
						<th>${repCustVO.cmpId}</th>
					</tr>
					<tr>
						<td>被檢舉會員編號:</td>
						<td>${repCustVO.custId}</td>
					</tr>
					<tr>
						<td>檢舉原因:</td>
						<td>${repCustVO.repCustReason}</td>
					</tr>
					<tr>
						<td>檢舉日期:</td>
						<td>${repCustVO.repCustDate}</td>
					</tr>
					<tr>
						<td>檢舉結果</td>
						<td><select name="repCustStatus">
								<option value="檢舉成功">檢舉成功</option>
								<option value="檢舉失敗">檢舉失敗</option>
						</select></td>
					</tr>
					<tr>
						<td>處理結果</td>
						<td><textarea class="form-control" aria-label="With textarea"
								name=repCustResult maxlength="150"></textarea></td>
					</tr>

				</table>


				<input type="hidden" name="empId" value="${employeeVO.empId}">
				<input type="hidden" name="repCustId" value="${repCustVO.repCustId}">
				<input type="submit" value="處理檢舉">${errorMsgs.repCmp}

			</FORM>
		</div>
	</div>
</body>
</html>