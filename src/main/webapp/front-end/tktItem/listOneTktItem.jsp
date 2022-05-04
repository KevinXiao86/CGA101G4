<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>訂單詳細資料 - listOneTktItem.jsp</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
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
	width: 600px;
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

	<table>
		<tr>
			<th>票券訂單編號</th>
			<th>票券</th>
			<th>購買數量</th>
			<th>評價分數</th>
			<th>評價內文</th>
			<th>回饋</th>
		</tr>
		<c:forEach var="tktItem" items="${itemList}">
			<tr>
				<td>${tktItem.tktOrderId}</td>
				<td>${tktItem.tktId}</td>   <%-- -${tktItem.ticketVO.tktName} --%>
				<td>${tktItem.amount}</td>
				<td>${tktItem.score}</td>
				<td>${tktItem.content}</td>
				<td><input type="submit" value="立即給予評分" onclick="show()">
				</td>
			</tr>
		</c:forEach>
	</table>
	<div id="showdiv" style="display: none">
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/tktItem/content" style="margin-bottom: 0px;">
			評分:<div>${errorMsgs.score}</div>
			<input type="text" name="score" required>${param.score}<br>
			
			評價內容:<div>${errorMsgs.content}</div>
			<textarea name="content" placeholder="請輸入500字以內" required>${param.content}</textarea>
			
			<input type="hidden" name="tktOrderId" value="${tktItem.tktOrderId}">
			<input type="hidden" name="tktId" value="${tktItem.tktId}">
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出新增">
		</FORM>
	</div>
	<script>
		function show() {
			if (document.getElementById('showdiv').style.display == 'none') {
				document.getElementById('showdiv').style.display = 'block';
			} 
// 			else {
// 				document.getElementById('showdiv').style.display = 'none';
// 			}
		}
	</script>

</body>

</html>