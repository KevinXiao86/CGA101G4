<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="com.taiwan.controller.ticket.*"%>
<%@ page import="com.taiwan.service.ticket.*"%>
<%@ page import="com.taiwan.dao.ticket.*"%>


<html>
<head>
<title>搜尋票券 - ticketSelect_page.jsp</title>
<%@ include file="/common/head.jsp"%>
<style>
table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td><h3>票券</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>所有票券資料 - listAllEmp.jsp</p>

	<h3>資料查詢:</h3>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<ul>
		<li><form action="ticket/findAllFront" method="post">
		<input type="submit" value="搜尋全部">
		<br>
		</FORM></li>

		<%-- 萬用複合查詢-以下欄位-可隨意增減 --%>
		<ul>
			<li>
				<FORM METHOD="post" name="form1"
					ACTION="<%=request.getContextPath()%>/ticket/TicketSearchServlet" >
					<b><font color=blue>萬用複合查詢:</font></b> <br>
					<b>輸入票券編號:</b> 
					<input
						type="text" name="TKT_ID" value=""><br> 
					<b>輸入票券名稱:</b>
					<input type="text" name="TKT_NAME" value="鐘樓怪人"><br> 
					<b>售價:</b>
						<input type="text" name="PRICE" value=""><br> 
					<b>開始日期:</b>
						<input name="STARTDATE" id="f_date1" type="text">
					<b>結束日期:</b>
						<input name="ENDDATE" id="f_date2" type="text"> <br> 
					<b>地點:</b>
						<input type="text" name="LOCATION" value="台北"> <br> 
				
						<input
							type="submit" value="送出"> 
						<input type="hidden"
							name="action" value="listTicket_ByCompositeQuery">
				</FORM>
			</li>
		</ul>


		<h3>員工管理</h3>

		<ul>
			<li><a href='<%=request.getContextPath()%>/emp/addEmp.jsp'>Add</a>
				a new Emp.</li>
		</ul>

		<h3>
			<font color=orange>部門管理</font>
		</h3>

		<ul>
			<li><a href='<%=request.getContextPath()%>/dept/listAllDept.jsp'>List</a>
				all Depts.</li>
		</ul>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<script>
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	$.datetimepicker.setLocale('zh');
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

// 	     2.以下為某一天之後的日期無法選擇
	     var somedate2 = new Date(f_date1);
	     $('#f_date1').datetimepicker({
	         beforeShowDay: function(date) {
	       	  if (  date.getYear() >  somedate2.getYear() || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
			           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	             ) {
	                  return [false, ""]
	             }
	             return [true, ""];
	     }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>