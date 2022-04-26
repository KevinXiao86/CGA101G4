<%@page import="com.taiwan.beans.CouponVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	CouponVO couponVO=(CouponVO)request.getAttribute("couponVO");
%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<meta charset="UTF-8">
<title>優惠券資料修改</title>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  img{
 width: 150px;
 height: 150px;
}
</style>
</head>
<body>
<h1>優惠券修改</h1>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<form action="coupon/couponUpdate" method="post" enctype="multipart/form-data">
		<label>優惠券流水號</label><input type="text" name="copId" value="${couponVO.copId}"><br> 
		<label>優惠券名稱</label><input type="text" name="copName" autofocus placeholder="請輸入優惠券名稱" value="${couponVO.copName}"><br> 
		<label>優惠券折價金額</label><input type="number" name="discount" step="10" min="0"  placeholder="請輸入折價金額" value="${couponVO.discount}"><br>
		<label>優惠券開始日期</label><input id="startdate" name="startdate" type="datetime-local" value="<fmt:formatDate value="${couponVO.startdate}" pattern="yyyy-MM-dd'T'HH:mm"/>"><br> 
		<label>優惠券結束日期</label><input id="enddate" name="enddate" type="datetime-local" value="<fmt:formatDate value="${couponVO.enddate}" pattern="yyyy-MM-dd'T'HH:mm"/>"><br> 
		<label>優惠券介紹</label><br>
		<textarea name="introduce" id="content" cols="100" rows="10" style="resize: none;" placeholder="請輸入介紹"  >${couponVO.introduce}</textarea><br>
		<label>舊照片</label><br>
		<img src="${couponVO.img}"/><br>
		<label>請輸入照片</label> <input type="file" name="uploadFile" accept="image/*"><br>
		<input type="submit" value="提交">
	</form>

</body>
</html>