<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>

	<h1>this is coupon index</h1>
	<form action="coupon/findAll" method="post">
		<input type="submit" value="搜尋全部">
	</form>
	
	<div>
		<a href='coupon/cop_create.jsp'>新增</a>一筆優惠券
	</div>
	
	
	<%--錯誤列表 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	
	<div>
	<FORM METHOD="post" ACTION="coupon/selectById" >
        <b>輸入優惠券編號來做查詢:</b>
        <input type="text" name="copId">
        <input type="submit" value="送出">
    </FORM>
    </div>
    <div>
    <FORM METHOD="post" ACTION="coupon/selectByStatus" >
        <b>輸入優惠券標題來做查詢:</b>
        <input type="text" name="copName">
        <input type="submit" value="送出">
    </FORM>
    </div>
    


</body>
</html>