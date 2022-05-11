<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  //EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<title>員工資料 </title>

<style>
#page-wrapper {
	background-color: rgb(221, 221, 241) !important;
	height: 800px; 
}

table {
	width: 1100px;
	background-color: rgb(221, 221, 241) !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 3px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>



</head>
<body >


	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">員工資料</h1>
				</div>
			</div>
		 
	

<table>
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工密碼</th>
		<th>啟用狀態</th>
		<th>創建日期</th>
	</tr>
	<tr>
		<td>${employeeVO.empId}</td>
			<td>${employeeVO.empName}</td>
			<td>${employeeVO.empPassword}</td>
			<td>${employeeVO.empStatus}</td>
			<td>${employeeVO.hiredate}</td>
	</tr>
</table>
<div>
				<a href="back-end/emp/emp_index.jsp">回到員工首頁</a>
			</div>
		</div>
	</div>
</body>
</html>