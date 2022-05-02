<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商首頁</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<h1>這是廠商首頁</h1>
	<span id="error_msg">${requestScope.editCompany.message}</span>
	<a href="company/logout">登出</a>
	<br>
	<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">廠商資料</a>${requestScope.errorInfo}<br>
	<br>

	<a href="roomtype/getAllRoomtypes">房型管理</a>






















	<a href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}">查詢所有訂單</a>

	<div>
		<FORM METHOD="post" ACTION="roomOrder/cmpSelectByCustId">
			<b>輸入會員編號來做查詢:</b> <input type="text" name="custId"> <input
				type="submit" value="送出">
		</FORM>
	</div>
	<div>
		<FORM METHOD="post" ACTION="roomOrder/cmpSelectByStatus">
			<b>輸入訂房訂單狀態來做查詢:</b> <select name="roomOrderStatus">
				<option value="正常">正常</option>
				<option value="已取消">已取消</option>
				<option value="以實現">以實現</option>
			</select> <input type="submit" value="送出">
		</FORM>
	</div>

	<div>
		<FORM METHOD="post" ACTION="roomOrder/cmpSelectByDate">
			<b>根據日期來做查詢來做查詢:</b> <br> <label for="from">From</label> <input
				type="text" name="startdate" id="start_date"> <label
				for="to">to</label> <input type="text" name="enddate" id="end_date">
			<input type="submit" value="送出">
		</FORM>
	</div>
</body>
</html>