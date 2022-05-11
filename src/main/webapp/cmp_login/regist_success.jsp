<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商註冊成功頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="cmp_login/css/style.css">
<style type="text/css">
h1 {
	text-align: center;
	margin-top: 200px;
}

h1 a {
	color: red;
}
</style>
<script type="text/javascript">
	// 	$(() => {
	// 		$(".um_span").html("");
	// 	});
</script>
</head>
<body>
		<div id="header">
				<a href="#">
					<img class="logo_img" alt="" src="cmp_login/imgs/logo.jpg" height="100px" width="150px"/>
				</a>
				<span class="wel_word"></span>
				<div>
					<span>感謝 <span class="um_span">${requestScope.cmpName}</span> 加入我們的廠商會員</span>
				</div>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="#">回到首頁</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				台玩旅遊平台.Copyright &copy;2022
			</span>
		</div>
</body>
</html>