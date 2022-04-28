<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Ticket Order: Home</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
  table#table-1 {
	width: 600px;
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
   <tr><td><h3>Ticket Order: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<h3>資料查詢:</h3>

<ul>
  <li><a href='front-end/tktOrder/listAllTktOrder.jsp'>List</a> all Ticket Order.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="tktOrder/selectById" >
        <b>輸入訂單編號:</b><font color=red>${errorMsgs.tktOrderId}</font><br>
        <input type="text" name="tktOrderId" value="${param.tktOrderId}">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
<!--  開發中 會員id-->
  <li>
    <FORM METHOD="post" ACTION="tktOrder/getCustId" >
        <b>輸入會員編號:</b><font color=red>${errorMsgs.tktOrderId}</font><br>
        <input type="text" name="custId" value="${param.custId}">
        <input type="hidden" name="action" value="get_order_cust">
        <input type="submit" value="送出">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="tktOrder/getDate" >
        <b>查詢訂單:</b><font color=red>${errorMsgs.date}</font><br>
        <label for="from">From</label>
		<input type="date" id="startdate" name="startdate" value="${param.startdate}" required>
		<label for="to">to</label>
		<input type="date" id="enddate" name="enddate" value="${param.enddate}" required>
        <input type="hidden" name="action" value="get_date">
        <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


<h3>訂單管理</h3>

	<script>
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
//             $('#startdate').attr('min', today);
            $('#startdate').blur(e => {
                $('#enddate').attr({
                    'min': $('#startdate').val()
//                     'max': lastday   //可設可不設(區間)
                })
            })	
        });
    </script>

</body>
</html>