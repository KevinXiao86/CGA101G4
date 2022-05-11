<%@page import="com.taiwan.beans.Company"%>
<%@page import="com.taiwan.service.impl.CompanyServiceImpl12"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:useBean id="repCustSvc" scope="page"
		class="com.taiwan.service.impl.RepCustServiceImpl" />
	<table border="2">
		<thead>
			<tr>
				<th>檢舉編號</th>
				<th>被檢舉會員編號</th>
				<th>檢舉原因</th>
				<th>檢舉日期</th>
				<th>檢舉狀態</th>
				<th>處理結果</th>
				<th>取消檢舉</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="repCustVO" items="${repCustSvc.searchAllRepCust()}">
				<tr>
					<td>${repCustVO.repCustId}</td>
					<td>${repCustVO.custId}</td>				
					<td>${repCustVO.repCustReason}</td>
					<td>${repCustVO.repCustDate}</td>
					<td>${repCustVO.repCustStatus}</td>
					<td>${repCustVO.repCustResult}</td>
					<td>
					<c:if test="${repCustVO.repCustStatus == '未處理'}">
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/repCust12/DoRepCust" >
					<input type="hidden" name="repCustId" value="${repCustVO.repCustId}" >
					<input type="submit" value="處理檢舉">
					</FORM>
					</c:if>
					</td>
				</tr>
			</c:forEach>



		</tbody>
	</table>
</body>
</html>