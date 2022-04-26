<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>ticket creator</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
</head>
<body>
	
	<%-- 	<%--錯誤列表 --%> 
<%-- 	<c:if test="${not empty errorMsgs}"> --%>
<!-- 		<font style="color: red">請修正以下錯誤:</font> -->
<!-- 		<ul> -->
<%-- 			<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 				<li style="color: red">${message}</li> --%>
<%-- 			</c:forEach> --%>
<!-- 		</ul> -->
<%-- 	</c:if> --%>


	 <form action="ticket/ticketCreator" method="post">
        <span>票券名稱</span><input name="tktName" type="text" autofocus  placeholder="請輸入票券名稱" value="${param.tktName}">${errorMsgs.tktName}<br>
        <span>票券數量</span><input name="originalAmount" type="number" step="10" min="0" 
            placeholder="請輸入票券數量" value="${param.originalAmount}">${errorMsgs.originalAmount}<br>
        <span>票券價格</span><input name="price" type="number" step="10" min="0"  placeholder="請輸入票券價格" value="${param.price}">${errorMsgs.price}<br>
        <span>票券開始日期</span><input name="startdate" id="startdate" type="datetime-local" value="2022-01-01T08:00"><br>
        <span>票券結束日期</span><input name="enddate" id="enddate" type="datetime-local" value="2022-12-31T22:00">${errorMsgs.timeError}<br>
        <span>種類</span>
        <select name="kind" id="kind" >
            <option value="景點門票">景點門票</option>
            <option value="主題樂園">主題樂園</option>
            <option value="博物館展覽">博物館展覽</option>
            <option value="特色表演">特色表演</option>
        </select><br>
        <span>票券所在縣市</span>
        <div id="twzipcode">
        </div>
        <input type="text" name="address" placeholder="請輸入地址" style="width: 306px;" value="${param.address}">${errorMsgs.address}<br>
        <span>介紹</span><br>
        <textarea name="instruction" id="instruction" cols="100" rows="10" style="resize:none;" placeholder="請輸入票券介紹"
            >${param.instruction}</textarea>${errorMsgs.instruction}<br>
        <span>注意事項</span><br>
        <textarea name="notice" id="notice" cols="100" rows="10" style="resize:none;" placeholder="請輸入注意事項"
            >${param.notice}</textarea>${errorMsgs.notice}<br>
        <span>如何使用</span><br>
        <textarea name="howuse" id="howuse" cols="100" rows="10" style="resize:none;" placeholder="請輸入如何使用"
            >${param.howuse}</textarea>${errorMsgs.howuse}<br>
        <span>取消政策</span><br>
        <textarea name="canxpolicy" id="canxpolicy" cols="100" rows="10" style="resize:none;" placeholder="請輸入取消政策"
            >${param.canxpolicy}</textarea>${errorMsgs.canxpolicy}<br>
        <input type="submit" value="提交">
    </form>
		<span>請輸入照片</span>
        <input type="file" accept="image/*">
        <input type="file" accept="image/*">
        <input type="file" accept="image/*">
        <br>
    <script>
        // $("#twzipcode").twzipcode();
        $("#twzipcode").twzipcode({
            countyName: "city", // 自訂城市 select 標籤的 name 值
            districtName: "town", // 自訂地區 select 標籤的 name 值
            countySel: "${param.city}", // 城市預設值, 字串一定要用繁體的 "臺", 否則抓不到資料
            districtSel: "${param.town}", // 地區預設值
//            'countySel': ${param.city},
//            'districtSel': ${param.town}
        });

    </script>

</body>
</html>