<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.company.CompanyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		CompanyService companyService = ControllerUtil.getBean(CompanyService.class);
		request.setAttribute("cmpSVC", companyService);
		System.out.println(companyService);
	%>
	<FORM METHOD="post" ACTION="http://localhost:8081/CGA101G4/ROSelectCmp"
		name="form1">
		<table>
<%-- 			<jsp:useBean id="cmpSvc" scope="page" --%>
<%-- 				class="com.taiwan.service.company.impl.CompanyServiceImpl" /> --%>


			<tr>
				<td>廠商:</td>
				<td><select name="cmpId">
						<c:forEach var="cmpVO" items="${cmpSVC.allCompany}">
							<option value="${cmpVO.cmpId }"
								${(param.cmpId==cmpVO.cmpId)? 'selected':'' }>${cmpVO.cmpName}</option>
						</c:forEach>
				</select></td>
				<td>${errorMsgs.cmpId}</td>
			</tr>
			<br>

		</table>
		 <input type="submit" value="送出新增">
	</FORM>
</body>
</html>