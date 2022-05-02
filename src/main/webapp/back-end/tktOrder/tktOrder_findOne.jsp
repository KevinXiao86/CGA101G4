<%@page import="com.taiwan.beans.TktOrder"%>
<%@page import="com.taiwan.service.TktOrderService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.taiwan.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>�Ҧ��q���� - listAllOrder.jsp</title>
<%-- �R�A�]�t base����,css�˦�,jQuery��� --%>
<%@ include file="/common/head.jsp"%>
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body bgcolor='white'>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message.key}:${message.value}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>����q��s��</th>
			<th>�|���s��</th>
			<th>��l���B</th>
			<th>�q�ʤ��</th>
			<th>�`���B</th>
			<th>�|���u�f��s��</th>
			<th>�q��Ա�</th>
		</tr>
			<tr>
				<td>${tktOrder.tktOrderId}</td>
				<td>${tktOrder.custId}</td>
				<td>${tktOrder.originalPrice}</td>
				<td><fmt:formatDate value="${tktOrder.orderdate}" pattern="yyyy-MM-dd HH:mm" /></td>
				<td>${tktOrder.ttlPrice}</td>
				<td>${(tktOrder.custCopId == 0)? "���ϥ��u�f��" : tktOrder.custCopId}</td>
				<td>
					<FORM METHOD="post" ACTION="tktOrder/findAllInfo" style="margin-bottom: 0px;">
						<input type="submit" value="�Ա�"> 
						<input type="hidden"	name="tktOrderId" value="${tktOrder.tktOrderId}">
					</FORM>
				</td>
			</tr>
	</table>

</body>
</html>