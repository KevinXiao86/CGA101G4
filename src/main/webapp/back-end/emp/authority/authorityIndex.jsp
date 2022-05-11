<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<title>員工權限</title>



</head>
<body >

	<div id="page-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">員工權限</h1>
				</div>
			</div>
	
	<div id="findAll_div">
				<a href='back-end/emp/authorit/authorityListAllEmp.jsp'>List</a> 全部員工 <br>
			</div>
	


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

		<div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet">
				<b>選擇員工編號:</b> <select size="1" name="empId">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empId}">${employeeVO.empId}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</div>

		<div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmployeeServlet">
				<b>選擇員工姓名:</b> <select size="1" name="empId">
					<c:forEach var="employeeVO" items="${empSvc.all}">
						<option value="${employeeVO.empId}">${employeeVO.empName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</div>
	<


		<li><a href='back-end/emp/addEmp.jsp'>增加</a> 新員工.</li>
	</ul>
</div>
</div>
</body>
</html>