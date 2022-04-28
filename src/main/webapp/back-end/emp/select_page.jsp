<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>員工首頁</title>
<%@ include file="/common/head.jsp"%>
<style>
p#f_title {
	font-size:150%;
	font-family:verdana;
	font-weight:900;
	color:00ff00;
}

table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td style="color:red;font-weigth:bold;"><h3>員工首頁</h3></td>
		</tr>
	</table>

	<p id="f_title">歡迎回來</p>

	<h3>資料查詢:</h3>

	<%-- 錯誤表列 --%>
	<%-- <c:if test="${not empty errorMsgs}"> --%>
	<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
	<!-- 	<ul> -->
	<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
	<%-- 			<li style="color:red">${message.value}</li> --%>
	<%-- 		</c:forEach> --%>
	<!-- 	</ul> -->
	<%-- </c:if> --%>

	<ul>
		<li><a href='back-end/emp/listAllEmp.jsp'>List</a> 全部員工 <br>
		<br></li>


		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet">
				<b>輸入員工編號 (如30000):</b> <input type="text" name="empId"
					value="${param.empId}"><font color=red>${errorMsgs.empId}</font>
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="empSvc" scope="page"
			class="com.taiwan.service.employee.EmployeeService"  />

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet">
				<b>選擇員工編號:</b> <select size="1" name="empId">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empId}">${employeeVO.empId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet">
				<b>選擇員工姓名:</b> <select size="1" name="empId">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empId}">${employeeVO.empName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>


	<h3>員工管理</h3>

	<ul>
		<li><a href='back-end/emp/addEmp.jsp'>增加</a> 新員工.</li>
	</ul>

</body>
</html>