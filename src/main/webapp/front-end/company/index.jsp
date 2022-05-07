<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商首頁</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<style type="text/css">
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

</style>


</head>
<body>

	<h1>廠商首頁</h1>
	${sessionScope.loginCompany.cmpId}
	<a href="roomOrder/getAllRoomOrders?cmpId=${sessionScope.loginCompany.cmpId}">訂單管理</a>
	<a href="reservation/getAllRoomtypes">房數管理</a>
	<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}">廠商資料</a>${requestScope.errorInfo}<br><br>
	<a href="roomtype/getAllRoomtypes">房型管理</a><br><br>



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
	
	
	<script src="datetimepicker/jquery.datetimepicker.full.js"></script>

<script type="text/javascript">
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false
	 });
	 
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_date').val()?$('#start_date').val():false
	   })
	  },
	  timepicker:false
	 });
});

</script>

</body>
</html>