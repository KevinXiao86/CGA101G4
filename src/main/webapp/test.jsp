<%@page import="com.taiwan.beans.RoomImg"%>
<%@page import="com.taiwan.beans.Roomtype"%>
<%@page import="com.taiwan.service.roomtype.RoomtypeService"%>
<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.company.CompanyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>測試頁面</title>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
<!-- 		圖片顯示區及刪除 -->
	<form action="company/doFormServlet.do" method="post">
        <!-- 隐藏域保存服务端token -->
        <input type="hidden" name="token" value="<%=session.getAttribute("token")%>" />
        姓名：<input type="text" name="username" />
        <input id="submitBtn" type="submit" value="提交">
    </form>
</body>
</html>