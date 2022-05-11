<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="front-end/company/css/style.css">
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
					<img class="logo_img" alt="" src="front-end/company/imgs/logo.jpg" height="100px" width="150px"/>
				</a>
				<span class="wel_word"></span>
				<div>
					<span>感謝 <span class="um_span">${requestScope.cmpName}</span> 加入我們的會員</span>
				</div>
		</div>
		
		<div id="main">
		
			<h1>註冊成功! <a href="front-end/cust/CustomerLogin.jsp">回到首頁</a></h1>
	
		</div>
		
		<div id="bottom">
			<span>
				台玩旅遊平台.Copyright &copy;2022
			</span>
		</div>
</body>
</html>
