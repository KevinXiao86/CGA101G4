<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.dao.authority.impl.*"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="com.taiwan.service.authority.*"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<%@ page import="com.taiwan.service.employee.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
AuthorityService atisvc = new AuthorityService();
EmployeeService empSvc = new EmployeeService();
List<AuthorityVO> list = atisvc.getAll();
pageContext.setAttribute("list", list);
List<EmployeeVO> list2 = empSvc.getAll();
pageContext.setAttribute("list2",list2);
%>


<html>
<head>
<title>所有員工資料 - listAllAuthority.jsp</title>
<%@ include file="/common/head.jsp"%>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
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

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<h4>此頁練習採用 EL 的寫法取值:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有員工權限 - listAllAuthority.jsp</h3>
				<h4>
					<a href="back-end/emp/authority/authorityIndex.jsp"><img
						src="back-end/emp/images/back1.gif" width="100" height="32"
						border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message.key}:${message.value}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工管理員權限</th>
			<th>票券管理員權限</th>
			<th>會員管理員權限</th>
			<th>廠商管理員權限</th>
			<th>網頁管理員權限</th>

		</tr>
		<%@ include file="../page1.file"%>
		<c:forEach var="authorityVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${authorityVO.empId}</td>
				<td>${authorityVO.empId}</td>
				<td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/EmployeeServlet"
						style="margin-bottom: 0px;">
						<input type="submit"
							value="${(authorityVO.funcId==1)? '啟用':'未啟用' }">
					</FORM></td>
				<td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/EmployeeServlet"
						style="margin-bottom: 0px;">
						<input type="submit"
							value="${(authorityVO.funcId==2)? '啟用':'未啟用' }">
					</FORM></td>
				<td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/EmployeeServlet"
						style="margin-bottom: 0px;">
						<input type="submit"
							value="${(authorityVO.funcId==3)? '啟用':'未啟用' }">
					</FORM></td>
				<td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/EmployeeServlet"
						style="margin-bottom: 0px;">
						<input type="submit"
							value="${(authorityVO.funcId==4)? '啟用':'未啟用' }">
					</FORM></td>
				<td><FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/EmployeeServlet"
						style="margin-bottom: 0px;">
						<input type="submit"
							value="${(authorityVO.funcId==5)? '啟用':'未啟用' }">
					</FORM></td>
				
				<!-- 				<td> -->
				<!-- 					<FORM METHOD="post" -->
				<%-- 						ACTION="<%=request.getContextPath()%>/EmployeeServlet" --%>
				<!-- 						style="margin-bottom: 0px;"> -->
				<!-- 						<input type="submit" value="修改"> <input type="hidden" -->
				<%-- 							name="empId" value="${employeeVO.empId}"> <input --%>
				<!-- 							type="hidden" name="action" value="getOne_For_Update"> -->
				<!-- 					</FORM> -->
				<!-- 				</td> -->
				<!-- 				<td> -->
				<!-- 					<FORM METHOD="post" -->
				<%-- 						ACTION="<%=request.getContextPath()%>/EmployeeServlet" --%>
				<!-- 						style="margin-bottom: 0px;"> -->
				<!-- 						<input type="submit" value="刪除"> <input type="hidden" -->
				<%-- 							name="empId" value="${employeeVO.empId}"> <input --%>
				<!-- 							type="hidden" name="action" value="delete"> -->
				<!-- 					</FORM> -->
				</td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="../page2.file"%>

</body>
</html>