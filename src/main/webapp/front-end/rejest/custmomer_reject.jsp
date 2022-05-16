<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>

<html>
<head>
<meta charset="UTF-8">
<title>會員註冊頁面</title>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" href="front-end/rejest/style.css">
<script src='front-end/rejest/jquery.min.js'></script>

<script src="front-end/rejest/script.js"></script>

<script src="https://demeter.5fpro.com/tw/zipcode-selector.js"></script>
</head>
<body>
	<div class="form-toggle"></div>

	<div class="form-panel one">
		<div class="form-header">
			<h1>會員註冊</h1>
		</div>
		<div class="form-content">
			<form action="cust/regist" method="post"
				enctype="multipart/form-data">
				<span id="error">${requestScope.registCustomercustomerVO.message}</span>
				<div class="form-group">
				
					<label for="account">帳號:</label> <input type="text" id="account"
						name="account" id="account"
						value="${requestScope.registCustmomer.account}">${requestScope.errorInfo.account}<br>
					
				</div>

				<div class="form-group">
					<label for="password">密碼:</label> <input type="password"
						name="password" id="password" required="required" />${requestScope.errorInfo.password}<br>
				</div>
				<div class="form-group">
					<label for="name">姓名:</label> <input type="text" name="name"
						id="cmp_name" value="${requestScope.registCustmomer.name}"
						required="required">${requestScope.errorInfo.name}<br>
				</div>

				<div class="form-group">
					<label for="sex">性別:</label> 
					<input type="radio" name="sex" id="sex"
						value="m"  required="required" style="position: relative; right:570px;top:17px;">男${requestScope.errorInfo.sex}
						<input type="radio" name="sex" id="sex"
						value="f"  required="required"  style="position: relative; right:570px;top:17px;" >女${requestScope.errorInfo.sex}<br>
				</div>
				<div class="form-group">
					<label for="tel">手機號碼:</label> <input type="text" name="tel"
						id="tel" maxlength=10 minlength=10 value="${requestScope.registCustmomer.tel}">${requestScope.errorInfo.tel}<br>
				</div>


				<div class="form-group">
					<label for="email">電子信箱:</label> <input type="text" name="email"
						id="email" value="${requestScope.registCustmomer.email}">${requestScope.errorInfo.email}<br>
				</div>
<div class="form-group">
				<label for="address">地址:</label> 
					<span>${requestScope.errorInfo.address}</span>
					
						<input type="text" id="city" name="city" placeholder="縣市" required="required" style="margin-bottom: 10px;" />
						<span>${requestScope.errorInfo.town}</span>
						<input type="text" id="town" name="town" placeholder="鄉鎮/區" required="required" style="margin-bottom: 10px;" /> 
						<span>${requestScope.errorInfo.road}</span>
						<input type="text" id="road" name="road" placeholder="街道" required="required" style="margin-bottom: 10px;" />
		
				</div>

				<div class="form-group">
					<label for="idCard">身分證字號:</label> <input type="text" name="idCard"
						id="idCard" maxlength=10 value="${requestScope.registCustmomer.idCard}" required="required">${requestScope.errorInfo.idCard}<br>
				</div>

				<div class="form-group">
					<label for="birth">生日:</label> <input type="date" id="f_date1"
						name="birth" required="required"
						value="${requestScope.registCustmomer.birth}${requestScope.loginCompany.checkinTime}" >${requestScope.errorInfo.checkinTime}<br>
				</div>

				<div class="form-group">
					<label for="uploadFile">會員照片:</label> <input type="file" required="required"
						name="uploadFile" id="uploadFile">${requestScope.errorInfo.uploadFile}<br>
				</div>


				<div class="form-group">
					<label for="card">信用卡卡號:</label> <input type="text" name="card"
						id="card" maxlength=16 value="${requestScope.registCustmomer.card}"><br>
				</div>
				
				<div class="form-group">
					<button type="submit" id="btn">註冊</button>
				</div>

				
			</form>
		</div>
	</div>
	
</body>




<%-- 	<span id="error_msg">${requestScope.registCustmomer.message}</span> --%>
<!-- 	<form action="cust/regist" method="post" enctype="multipart/form-data"> -->

<!-- 		<label for="account">帳號:</label> -->
<%-- 		<input type="text" name="account" id="account" value="${requestScope.registCustmomer.account}">${requestScope.errorInfo.account}<br> --%>

<!-- 		<label for="password">密碼:</label> -->
<%-- 		<input type="text" name="password" id="password">${requestScope.errorInfo.password}<br> --%>

<!-- 		<label for="name">姓名:</label> -->
<%-- 		<input type="text" name="name" id="cmp_name" value="${requestScope.registCustmomer.name}">${requestScope.errorInfo.name}<br> --%>

<!-- 		<label for="sex">性別:</label> -->
<%-- 		<input type="text" name="sex" id="sex" value="${requestScope.registCustmomer.sex}">${requestScope.errorInfo.sex}<br> --%>

<!-- 		<label for="tel">手機號碼:</label> -->
<%-- 		<input type="text" name="tel" id="tel" value="${requestScope.registCustmomer.tel}">${requestScope.errorInfo.tel}<br> --%>

<!-- 		<label for="email">電子信箱:</label> -->
<%-- 		<input type="text" name="email" id="email" value="${requestScope.registCustmomer.email}">${requestScope.errorInfo.email}<br> --%>

<!-- 		<label for="address">地址:</label> -->
<%-- 		<input type="text" id="address" name="address" value="${requestScope.registCustmomer.address}">${requestScope.errorInfo.address}<br> --%>

<!-- 		<label for="idCard">身分證字號:</label> -->
<%-- 		<input type="text" name="idCard" id="idCard" value="${requestScope.registCustmomer.idCard}">${requestScope.errorInfo.idCard}<br> --%>

<!-- 		<label for="birth">生日:</label> -->
<%-- 		<input type="date" id="f_date1" name="birth"  value="${requestScope.registCustmomer.birth}${requestScope.loginCompany.checkinTime}">${requestScope.errorInfo.checkinTime}<br> --%>

<!-- 		<label for="uploadFile">大頭貼:</label> -->
<%-- 		<input type="file" name="uploadFile" id="uploadFile">${requestScope.errorInfo.uploadFile}<br> --%>


<!-- 		<label for="card">信用卡卡號:</label> -->
<%-- 		<input type="text" name="card" id="card" value="${requestScope.registCustmomer.card}"><br> --%>
<!-- 		<button id="btn">註冊</button> -->
<!-- 	 </form> -->
<!--             </div> -->
<!--         </div> -->
<!--     </div> -->



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