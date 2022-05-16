<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>台玩</title>
<!--  Favicon title 小圖 -->
<link rel ="icon" href="<%=request.getContextPath() %>/static/img/core-img/favicon.ico">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
<style>

</style>
</head>
<body>
	 <!-- Header -->
<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
 <jsp:include page="/front-end/cust/side-bar.jsp"></jsp:include>
 
  <main id="main" class="main" style="padding-left: 70px; height: 1200px; padding-top: 40px;">
  <div class="pagetitle "
   style="background-color: #eee; margin: 50px auto 30px;">
   
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/addOrder/updateEvaluate"
		name="form1">
		<table>
			<tr>
				<td>評價分數</td>
				<td>
				<select name="evaluateScore">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select></td>
				<td>${errorMsgs.reason}</td>
			</tr>
			<tr>
				<td>評價內文</td>
				<td><textarea class="form-control" aria-label="With textarea" name="evaluateMsg"
					 maxlength="100" ></textarea></td><td>${errorMsgs.reason}</td>
			</tr>
			<tr>
			<td><input type="hidden" name="roomItemId" value="${roomItemId}"></td>
			<td> <input type="submit" value="發表評論">${errorMsgs.repCmp}	</td>
			</tr>
		</table>

	</FORM>
	</div>
	</main>
		 <!-- #### Footer start #### -->
 <jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>
</body>

</html>