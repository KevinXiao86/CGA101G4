<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.beans.*"%>
<%@ page import="com.taiwan.controller.ticket.*"%>
<%@ page import="com.taiwan.service.ticket.*"%>
<%@ page import="com.taiwan.dao.ticket.*"%>


<html>
<head>
<title>�j�M���� - ticketSelect_page.jsp</title>
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
			<td><h3>����</h3>
				<h4>( MVC )</h4></td>
		</tr>
	</table>

	<p>�Ҧ������� - listAllEmp.jsp</p>

	<h3>��Ƭd��:</h3>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<ul>
		<li><form action="ticket/findAllFront" method="post">
		<input type="submit" value="�j�M����">
		<br>
		</FORM></li>

		<%-- �U�νƦX�d��-�H�U���-�i�H�N�W�� --%>
		<ul>
			<li>
				<FORM METHOD="post" name="form1"
					ACTION="<%=request.getContextPath()%>/ticket/TicketSearchServlet" >
					<b><font color=blue>�U�νƦX�d��:</font></b> <br>
					<b>��J����s��:</b> 
					<input
						type="text" name="TKT_ID" value=""><br> 
					<b>��J����W��:</b>
					<input type="text" name="TKT_NAME" value="���өǤH"><br> 
					<b>���:</b>
						<input type="text" name="PRICE" value=""><br> 
					<b>�}�l���:</b>
						<input name="STARTDATE" id="f_date1" type="text">
					<b>�������:</b>
						<input name="ENDDATE" id="f_date2" type="text"> <br> 
					<b>�a�I:</b>
						<input type="text" name="LOCATION" value="�x�_"> <br> 
				
						<input
							type="submit" value="�e�X"> 
						<input type="hidden"
							name="action" value="listTicket_ByCompositeQuery">
				</FORM>
			</li>
		</ul>


		<h3>���u�޲z</h3>

		<ul>
			<li><a href='<%=request.getContextPath()%>/emp/addEmp.jsp'>Add</a>
				a new Emp.</li>
		</ul>

		<h3>
			<font color=orange>�����޲z</font>
		</h3>

		<ul>
			<li><a href='<%=request.getContextPath()%>/dept/listAllDept.jsp'>List</a>
				all Depts.</li>
		</ul>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

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
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});
	$.datetimepicker.setLocale('zh');
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});
	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
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

// 	     2.�H�U���Y�@�Ѥ��᪺����L�k���
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

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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