<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



	<h1>this is roomOrder index</h1>
	<form action="roomOrder/findAll" method="post">
		<input type="submit" value="搜尋全部">
	</form>


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
		<FORM METHOD="post" ACTION="roomOrder/selectById">
			<b>輸入訂房訂單編號來做查詢:</b> 
			<input type="text" name="roomOrderId"> 
			<input type="submit" value="送出">
		</FORM>
	</div>
	<div>
		<FORM METHOD="post" ACTION="roomOrder/selectByCmpId">
			<b>輸入廠商編號來做查詢:</b> 
			<input type="text" name="cmpId"> 
			<input type="submit" value="送出">
		</FORM>
	</div>

	<div>
		<FORM METHOD="post" ACTION="roomOrder/selectByCustId">
			<b>輸入會員編號來做查詢:</b>
			<input type="text" name="custId"> 
			<input type="submit" value="送出">
		</FORM>
	</div>

	<div>
		<FORM METHOD="post" ACTION="roomOrder/selectByStatus">
			<b>輸入訂房訂單狀態來做查詢:</b> 
			<select name="roomOrderStatus">
				<option value="正常">正常</option>
				<option value="已取消">已取消</option>
				<option value="以實現">以實現</option>
			</select> 
			<input type="submit" value="送出">
		</FORM>
	</div>


	<div>
		<FORM METHOD="post" ACTION="roomOrder/selectByDate">
			<b>根據日期來做查詢來做查詢:</b> 
			<label for="from">From</label> 
			<input type="date" id="startdate" name="startdate" value=""> 
			<label for="to">to</label> 
			<input type="date" id="enddate" name="enddate" value=""> 
			<input type="submit" value="送出">
		</FORM>
	</div>
	
	<script type="text/javascript">	
	$(function(){
        let date = new Date();
        let day = date.getDate();
        let month = date.getMonth() + 1;
        let month2 = date.getMonth() + 2;
        let year = date.getFullYear();
        if (month < 10)
            month = "0" + month;
        if (month2 < 10)
            month2 = "0" + month2;
        if (day < 10)
            day = "0" + day;
        let today = year + "-" + month + "-" + day; 
        let lastday = year + "-" + month2 + "-" + day;  

    	$('#startdate').attr('value', today);
        
        let start = new Date(Date.parse($('#startdate').val()) + 24*60*60*1000);
        let startMonth = start.getMonth() +1;
        if (startMonth < 10)
        	startMonth = "0" + startMonth;
        let startval = start.getFullYear() + "-" + startMonth + "-" + start.getDate();
        
        $('#startdate').blur(e => {
            $('#enddate').attr({
                'min': startval
//                 'max': lastday   //可設可不設(區間)
            })
        })	
    });
	
	</script>



</body>
</html>