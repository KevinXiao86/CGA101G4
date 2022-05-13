<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>
<%@ include file="back-end-index.jsp"%>
<html>


<head>
<title>員工首頁</title>

<style>
#page-wrapper {
	background-color: #ced7e8 !important;
	height: 800px;
}
#page-header{

color: yelow;
}
#create_div {
/* 	border: 1px solid blue; */
	margin-bottom: 10px;
}

#findAll_div {
	font-size: 24px;
	margin-bottom: 20px;
}

#create {
	font-size: 24px;
	color: yellow;
/* 	margin-left: 5%; */
}

#create:hover {
	color: red;
	text-decoration: none;
}

input[type="submit"] {
  display: inline-block;
    text-align: center;
    vertical-align: middle;
    padding: 11px 9px;
    border: 2px solid #a170a3;
    border-radius: 86px;
    background: #ffb8ff;
    background: -webkit-gradient(linear, left top, left bottom, from(#ffb8ff), to(#a170a3));
    background: -moz-linear-gradient(top, #ffb8ff, #a170a3);
    background: linear-gradient(to bottom, #ffb8ff, #a170a3);
    -webkit-box-shadow: #ffc0ff 0px 2px 16px 0px;
    -moz-box-shadow: #ffc0ff 0px 2px 16px 0px;
    box-shadow: #ffc0ff 0px 2px 16px 0px;
    text-shadow: #735075 0px 0px 1px;
    font: italic normal bold 12px tahoma;
    color: #ffffff;
    text-decoration: none;
}

input[type="submit"]:hover {
	background: linear-gradient(to bottom, #378de5 5%, #79bbff 100%);
	background-color: #378de5;
}

input[type="submit"]:active {
	position: relative;
	top: 1px;
}

select[name="status"] {
	height: 25px;
	border: 1px solid black;
}

b {
	font-size: 25px;
	vertical-align: middle;
}

#title_div {
	margin: 500px 0 10px 0;
	border: 1px solid black;
}
</style>
</head>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header"  style="color:#8846af;">會員管理介面</h1>
				</div>
			</div>
			<div id="create_div">
				<a id="create" href='back-end/emp/addEmp.jsp'>新增員工</a>
			</div>
			<div id="findAll_div">
				<a href='back-end/emp/listAllEmp.jsp'>所有員工</a> <br>
			</div>
			<%-- 錯誤表列 --%>
			<%-- <c:if test="${not empty errorMsgs}"> --%>
			<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
			<!-- 	<ul> -->
			<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
			<%-- 			<li style="color:red">${message.value}</li> --%>
			<%-- 		</c:forEach> --%>
			<!-- 	</ul> -->
			<%-- </c:if> --%>
			<div id="div-1">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/EmployeeServlet">
					<b>輸入員工編號:</b> <input type="text" name="empId" style='width:120px;'
						value="${param.empId}"><font color=red>${errorMsgs.empId}</font>
					<input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</FORM>
			</div>
			
			<jsp:useBean id="empSvc" scope="page"
				class="com.taiwan.service.employee.EmployeeService" />
			<!-- 			<li> -->
			<!-- 				<FORM METHOD="post" -->
			<%-- 					ACTION="<%=request.getContextPath()%>/EmployeeServlet"> --%>
			<!-- 					<b>選擇員工編號:</b> <select size="1" name="empId"> -->
			<%-- 						<c:forEach var="employeeVO" items="${empSvc.all}"> --%>
			<%-- 							<option value="${employeeVO.empId}">${employeeVO.empId} --%>
			<%-- 						</c:forEach> --%>
			<!-- 					</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
			<!-- 					<input type="submit" value="送出"> -->
			<!-- 				</FORM> -->
			<!-- 			</li> -->

				<div id="div-2">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/EmployeeServlet">
					<b>選擇員工姓名:</b> <select size="1.8" name="empId" style='width: 120px;'>
						<c:forEach var="employeeVO" items="${empSvc.all}">
							<option value="${employeeVO.empId}">${employeeVO.empName}
						</c:forEach>
					</select> <input type="hidden" name="action" value="getOne_For_Display">
					<input type="submit" value="送出">
				</FORM>
			</div>
		</div>
	</div>





</body>
</html>