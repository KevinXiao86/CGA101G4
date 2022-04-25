<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型管理</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<script type="text/javascript">
// 給輸入框綁定onchange內容發生改變事件
// 如果是使用 blur 失去焦點事件, 還需要自己判斷數值有無改變, 所以推薦使用 onchange事件
$("#updatePrice").change(function () {
	// 獲取商品名稱 
	var name = $(this).parent().parent().find("td:first").text();
	// 獲取商品編號
	var id = $(this).attr('roomtypeId');
	// 獲取商品數量
	var count = this.value;
	//數據校驗
	if(count < 0){
		count = this.defaultValue;
	}
	if ( confirm("你確定要將【" + name + "】房型價格修改為: " + count + " 嗎?") ) {
		//發起請求給服務器修改
		location.href = "";
	} else {
		// defaultValue屬性是表單項Dom對象的屬性。他表示默認的value屬性值[也就是一開始還沒有修改的值]
		this.value = this.defaultValue;
	}
});
</script>
</head>
<body>
	${sessionScope.loginCompany.cmpId}
	<span id="error_msg">${requestScope.managerCompany.message}</span>
	<table>
		<tr>
			<td>房型名稱</td>
			<td>房型數量</td>
			<td>入住人數</td>
			<td>總評分</td>
			<td>評分總人數</td>
			<td>價格</td>
			<td>狀態</td>
			<td>平方公尺</td>
			<td>上架/下架</td>
			<td>詳情</td>
		</tr>

		<c:forEach items="${requestScope.roomtypes}" var="Roomtype">
			<tr>
				<td>${Roomtype.roomtypeName}</td>
				<td>${Roomtype.roomtypeAmount}</td>
				<td>${Roomtype.roomtypePeople}</td>
				<td>${Roomtype.totalScore}</td>
				<td>${Roomtype.totalPeople}</td>
				<td><input id="updatePrice" style="width: 80px;"
					roomtypeId="${Roomtype.roomtypeId}" type="text"
					value="${Roomtype.roomtypePrice}">
				</td>
				<td>${Roomtype.roomtypeStatus}</td>
				<td>${Roomtype.roomtypeArea}</td>
				<td><c:choose>
						<c:when test="${Roomtype.roomtypeStatus == '上架'}">
							<a
								href="roomtype/updateRoomtypeStatus?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}&status=下架">下架房型</a>
						</c:when>

						<c:when test="${Roomtype.roomtypeStatus == '下架'}">
							<a
								href="roomtype/updateRoomtypeStatus?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}&status=上架">上架房型</a>
						</c:when>
					</c:choose></td>
				<td><a href="#">查看房型詳情</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>