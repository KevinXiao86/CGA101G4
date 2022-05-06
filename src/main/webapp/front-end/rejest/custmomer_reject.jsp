<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員註冊頁面</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<span id="error_msg">${requestScope.registCustmomerVO.message}</span>
	<form action="cust/regist" method="post" enctype="multipart/form-data">
		
		<label for="account">帳號:</label>
		<input type="text" name="account" id="account" value="${requestScope.registCustmomerVO.account}">${requestScope.errorInfo.account}<br>

		<label for="password">密碼:</label>
		<input type="text" name="password" id="password">${requestScope.errorInfo.password}<br>
		
		<label for="name">姓名:</label>
		<input type="text" name="name" id="cmp_name" value="${requestScope.registCustmomerVO.name}">${requestScope.errorInfo.name}<br>

		<label for="sex">性別:</label>
		<input type="text" name="sex" id="sex" value="${requestScope.registCustmomerVO.sex}">${requestScope.errorInfo.sex}<br>

		<label for="tel">手機號碼:</label>
		<input type="text" name="tel" id="tel" value="${requestScope.registCustmomerVO.tel}">${requestScope.errorInfo.tel}<br>
		
		<label for="email">電子信箱:</label>
		<input type="text" name="email" id="email" value="${requestScope.registCustmomerVO.email}">${requestScope.errorInfo.email}<br>

		<label for="address">地址:</label>
		<input type="text" id="address" name="address" value="${requestScope.registCustmomerVO.address}">${requestScope.errorInfo.address}<br>
			
		<label for="idCard">身分證字號:</label>
		<input type="text" name="idCard" id="idCard" value="${requestScope.registCustmomerVO.idCard}">${requestScope.errorInfo.password}<br>

		<label for="birth">生日:</label>
		<input type="date" id="f_date1" name="birth"  value="${requestScope.registCustmomerVO.birth}${requestScope.loginCompany.checkinTime}">${requestScope.errorInfo.checkinTime}<br>

		<label for="uploadFile">大頭貼:</label>
		<input type="file" name="uploadFile" id="uploadFile">${requestScope.errorInfo.uploadFile}<br>
		<button id="btn">註冊</button>
	</form>
	</body>
	
	
	<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
	
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
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	$.datetimepicker.setLocale('zh');
	$('#f_date2').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});
	</script>

</html>