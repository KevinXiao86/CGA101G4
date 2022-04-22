<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--
我們必須動態的獲取路徑值,否則當使用者訪問的時候,會去用戶的電腦找資源,而這樣就會報錯 

request.getSchema()可以返回当前页面使用的协议，http 或是 https;

request.getServerName()可以返回当前页面所在的服务器的名字;

request.getServerPort()可以返回当前页面所在的服务器使用的端口號;

request.getContextPath()可以返回当前页面所在的項目的名字;
-->
<%
    String basePath = request.getScheme()
            + "://"
            + request.getServerName()
            + ":"
            + request.getServerPort()
            + request.getContextPath()
            + "/";
    pageContext.setAttribute("basePath",basePath);
%>
<!-- 寫base標籤,永遠固定相對路徑跳轉的結果 -->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>