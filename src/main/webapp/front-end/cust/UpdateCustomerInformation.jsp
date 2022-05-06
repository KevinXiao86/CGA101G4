<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
<link rel="stylesheet" type="text/css"
	href="front-end/cust/datetimepicker/jquery.datetimepicker.css" />
<script src="front-end/cust/datetimepicker/jquery.js"></script>
<script
	src="front-end/cust/datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
body{
width:80%;
margin:0 auto;
}
form{
display:block;
width:60%;
margin:0 auto;
}
input {
	height: 30px;
	margin:15px 0;
	}
select{
height:30px;
width:40px;
}	
</style>
</head>

<body>
	<form action="cust/SubmitCustomerInformation" method="post"
		enctype="multipart/form-data">
		<label>姓名: </label><input type="text" name="name"
			value="${param.name}">${errorMsgs.name}
			<br> 
			<label>性別:	</label><select name="sex">
			<option value="f" ${(param.sex=='f')?'selected':''}>女</option>
			<option value="m" ${(param.sex=='m')?'selected':''}>男</option>
		</select>
		<br> 
		<label>電話: </label><input type="text" name="tel" value="${param.tel}">${errorMsgs.tel} 
		<br>
		 <label>電子信箱: 	</label><input type="text" name="email" value="${param.email}">${errorMsgs.email}
		<br> 
		<label>聯絡地址: </label><input type="text" name="address" value="${param.address}">
		<br>
		<label>身分證字號: </label><input type="text" name="idCard" value="${param.idCard}">${errorMsgs.idCard}
		<br>
		 <label>生日: </label><input id="birth" type="text" name="birth" value="${param.birth}">${errorMsgs.birth}
		  <br>
		   <label>帳號: </label><input type="text" name="account" value="${param.account}">${errorMsgs.account}
		   <br>
		<label>密碼: </label><input type="text" name="password" value="${param.password}">${errorMsgs.password}
		<br>
		 <img src="${param.imgOrigin}">
		 <!--  imgOrigin代表原本的圖片，沒有要修改的 -->
		 <input type="hidden" name="imgOrigin" value="${param.imgOrigin}">
		<div style="color: red">若選擇圖片上傳會取代原檔案</div>
		<!-- imgUpdate代表要上傳的圖片 -->
		<label>圖片: </label><input type="file" name="imgUpdate">
		 <br>
		  <label>信用卡卡號: </label><input type="text" name="card" value="${param.card}">
		   <br>
		<input type="submit" value="修改完成">
		 <input type="hidden" name="custId" value="${param.custId}">
	</form>

</body>
<link rel="stylesheet" type="text/css"
	href="datetimepicker/jquery.datetimepicker.css" />
<script src="datetimepicker/jquery.js"></script>
<script src="datetimepicker/jquery.datetimepicker.full.js"></script>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');
	$('#birth').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker: false,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d',
		//value:${param.birth},
		//value : new Date(),
	//disabledDates:    ['2022/06/08','2022/06/09','2022/06/10'], // 去除特定不含
	//startDate:	        '2022/07/10',  // 起始日
	//minDate:           '-1970-01-01', // 去除今日(不含)之前
	//maxDate:           '+1970-01-01'  // 去除今日(不含)之後
	});
</script>
</html>
