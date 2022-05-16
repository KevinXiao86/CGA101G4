<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台玩</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<!--  Favicon title 小圖 -->
<link rel ="icon" href="<%=request.getContextPath() %>/static/img/core-img/favicon.ico">
<%
//假設會員10000已登入
// int i = 10000;
// CustomerServiceImpl custSvc = new CustomerServiceImpl();
// CustomerVO custVO = custSvc.getAll(i);
// request.setAttribute("custVO", custVO);
%>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">

</head>
<body>
	 <!-- Header -->
<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
 <jsp:include page="/front-end/cust/side-bar.jsp"></jsp:include>
 <main id="main" class="main" style="padding-left: 70px; height: 1200px; padding-top: 40px;">
  <div class="pagetitle "
   style="background-color: #eee; margin: 50px auto 30px;">
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/addRepCmp/addRepCmp"
		name="form1">
		<table>
			<tr>
				<td>檢舉原因</td>
				<td><textarea class="form-control" aria-label="With textarea" name="reason"
					 maxlength="300" ></textarea></td><td>${errorMsgs.reason}</td>
			</tr>
				<tr>
			<td><input type="hidden" name="roomtypeId" value="${roomtypeId}"></td>
			<td> <input type="hidden" name="custId" value="${customer.custId}">	</td>
			<td><input type="submit" value="檢舉廠商">${errorMsgs.repCmp}</td>
			</tr>
		</table>
	

	</FORM>
	</div>
	</main>
		 <!-- #### Footer start #### -->
 <jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>
</body>
</html>