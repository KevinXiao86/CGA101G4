<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="com.taiwan.service.employee.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    EmployeeService empSvc = new EmployeeService();
    List<EmployeeVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<%@ include file="back-end-index.jsp"%>
<html>
<head>
<title>所有員工資料 - listAllEmp.jsp</title>

<style>
#page-wrapper {
/*      background-color: rgb(221, 221, 241) !important;  */
/*     height: 500x;   */

}

table {
	width: 1100px;
	background-color: rgb(221, 221, 241) !important; 
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
/* 	border: 3px solid #CCCCFF; */
}

th, td {
	padding: 5px;
/*  	text-align: center;  */
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
					<h1 class="page-header">所有員工</h1>
				</div>
</div>






<!-- 		 <h3>所有員工資料 - listAllEmp.jsp</h3> -->
<!-- 		 <h4><img src="back-end/emp/images/back1.gif"  -->
<!-- 		 width="100" height="32" border="0">回首頁</a></h4> -->
<!-- 	</td></tr> -->


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message.key} : ${message.value}</li>
		</c:forEach>
	</ul>
</c:if>

<table border="2px" class="table table-striped table-hover">
	<tr>
		<th>員工編號</th>
		<th>員工姓名</th>
		<th>員工密碼</th>
		<th>員工權限</th>
		<th>啟用狀態</th>
		<th>創建日期</th>
		<th></th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="employeeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${employeeVO.empId}</td>
			<td>${employeeVO.empName}</td>
			<td>${employeeVO.empPassword}</td>
			<td>${employeeVO.employeeFunctionVO.funcName}</td>
			<td>${employeeVO.empStatus}</td>
			<td>${employeeVO.hiredate}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="empId"  value="${employeeVO.empId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empId"  value="${employeeVO.empId}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<div><a href="back-end/emp/emp_index.jsp">回員工首頁</div>
</body>
</html>