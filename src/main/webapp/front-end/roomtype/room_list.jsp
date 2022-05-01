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
$(function () {
	// 給輸入框綁定onchange內容發生改變事件
	// 如果是使用 blur 失去焦點事件, 還需要自己判斷數值有無改變, 所以推薦使用 onchange事件
	$("#updatePrice").change(function () {
		// 獲取房型名稱 
		var name = $(this).parent().parent().find("td:eq(1)").text();
		// 獲取廠商編號
		var cmpId = $(this).attr('cmpId');
		// 獲取房型編號
		var roomtypeId = $(this).attr('roomtypeId');
		// 獲取價格
		var price = this.value;
		//數據校驗
		if(price < 0){
			price = this.defaultValue;
		}
		if ( confirm("你確定要將【" + name + "】房型價格修改為: " + price + " 嗎?") ) {
			//發起請求給服務器修改
			location.href = "${pageScope.basePath}roomtype/updateRoomtypePrice?cmpId=" + cmpId + "&roomtypeId=" + roomtypeId +"&price=" + price;
		} else {
			// defaultValue屬性是表單項Dom對象的屬性。他表示默認的value屬性值[也就是一開始還沒有修改的值]
			this.value = this.defaultValue;
		}
	});
});
</script>
</head>
<body>
	<a href="front-end/roomtype/add.jsp">新增房型</a>
	<span id="error_msg">${requestScope.managerCompany.message}</span>
	<table>
		<tr>
			<td>房型圖片</td>
			<td>房型名稱</td>
			<td>房型數量</td>
			<td>入住人數</td>
			<td>價格</td>
			<td>狀態</td>
			<td>操作</td>
		</tr>

		<c:forEach items="${requestScope.roomtypes}" var="Roomtype">
			<tr>
				<td>
					<c:forEach items="${Roomtype.roomImgs}" end="0" var="RoomImg">
						<img  height="128px" width="128px" src="${RoomImg.roomImg}">
					</c:forEach>
				</td>
				<td>${Roomtype.roomtypeName}</td>
				<td>${Roomtype.roomtypeAmount}</td>
				<td>${Roomtype.roomtypePeople}</td>
				<td>
					<input id="updatePrice" style="width: 50px;"
					roomtypeId="${Roomtype.roomtypeId}" cmpId="${sessionScope.loginCompany.cmpId}"
					type="text" value="${Roomtype.roomtypePrice}">
				</td>
				<td>${Roomtype.roomtypeStatus}</td>
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
				<td><a href="roomtype/getRoomtype?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}">查看詳情</a></td>
				<td><a href="roomtype/getRoomtype2?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}">修改</a></td>
				<td><a href="roomtype/getRoomtype2?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}">預約表</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>