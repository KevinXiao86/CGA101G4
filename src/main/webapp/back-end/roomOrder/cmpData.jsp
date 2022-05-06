<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
#page-wrapper {
/* 	background-color: rgb(221, 221, 241) !important; */
/* 	height: 1000px; */
}

/* #cmpInfo { */
/* 	padding-right: 30px; */
/* 	position: relative; */
/* } */

/* label { */
/* 	margin-left: 10px; */
/* } */

#back_index {
	position: fixed;
	right: 10%;
	bottom: 10%;
}

#back_index a {
	font-size: 20px;
	color: blue;
}

#back_index a:hover {
	color: red;
	text-decoration: none;
}

#pre {
	margin-left: 35%;
}

/*         img{ */
/*         	width: 200px; */
/*         	height: 200px; */
/*         } */
/* #img_div { */
/* 	position: absolute; */
/* 	right: 20%; */
/* 	top: 10%; */
/* } */
</style>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">廠商詳情</h1>
				</div>
			</div>
			<div id="cmpInfo">
				<div class="form-group">
					<label for="cmpName">廠商名稱</label> 
					<span>${errorMsgs.cmpName}</span>
					<input  type="text" id="cmpName" name="cmpName"
						required="required" readonly="readonly" value="${company.cmpName}" />
				</div>
				<div class="form-group">
					<label for="cmpTel">廠商電話</label> <span>${errorMsgs.cmpTel}</span>
					<input  type="text" id="cmpTel" name="cmpTel"
						required="required" value="${company.cmpTel}" />
				</div>

				<div class="form-group">
					<label for="bankAccount">銀行帳號</label> <span>${errorMsgs.headBank}${errorMsgs.endBank}</span><br>
					<input type="text" id="headBank" name="headBank" placeholder="銀行代號"
						required style="margin-bottom: 10px;"
						value="${company.bankAccount.substring(0, 3)}" /><br> <input
						type="text" name="endBank" id="endBank" placeholder="銀行帳號"
						required value="${company.bankAccount.substring(4)}" /><br>
				</div>

				<div class="form-group">
					<label for="cmpMail">信箱</label> <span>${errorMsgs.cmpMail}</span><br>
					<input type="text" id="cmpMail" name="cmpMail" required="required"
						value="${company.cmpMail}" />
				</div>

				<div class="form-group">
					<label for="cmper">廠商負責人</label> <span>${errorMsgs.cmper}</span><br>
					<input type="text" id="cmper" name="cmper" required="required"
						value="${company.cmper}" />
				</div>

				<div class="form-group">
					<label for="serialNo">旅宿登記證</label><br> <img
						src="${company.serialNo}" width="120" height="120">
				</div>


				<div class="form-group">
					<label for="cmpIntroduce">飯店介紹</label> <span>${errorMsgs.cmpIntroduce}</span><br>
					<textarea rows="6" cols="40" name="cmpIntroduce" id="cmpIntroduce"
						required>${company.cmpIntroduce}</textarea>
				</div>

				<div class="form-group">
					<label for="checkinTime">入住時間</label> <span>${errorMsgs.checkinTime}</span><br>
					<input type="time" id="checkinTime" name="checkinTime" min="00:00"
						max="24:00" required="required" value="${company.checkinTime}" />
				</div>

				<div class="form-group">
					<label for="checkoutTime">退房時間</label> <span>${errorMsgs.checkoutTime}</span><br>
					<input type="time" id="checkoutTime" name="checkoutTime"
						min="00:00" max="24:00" required="required"
						value="${company.checkoutTime}" />
				</div>

				<div class="form-group">
					<label>地點</label> <span>${errorMsgs.city}${errorMsgs.town}${errorMsgs.road}</span><br>
					<input type="text" id="city" name="city" placeholder="縣市"
						required="required" style="margin-bottom: 10px;"
						value="${company.location.substring(0, 3)}" /><br> <input
						type="text" id="town" name="town" placeholder="城鎮"
						required="required" style="margin-bottom: 10px;"
						value="${company.location.substring(3, 6)}" /><br> <input
						type="text" id="road" name="road" placeholder="街道"
						required="required" style="margin-bottom: 10px;"
						value="${company.location.substring(6)}" />
				</div>

				<div class="form-group">
					<label for="notice">購買須知</label> <span>${errorMsgs.notice}</span><br>
					<textarea rows="6" cols="40" name="notice" id="notice" required>${company.notice}</textarea>
					<br>
				</div>

				<div class="form-group">
					<label for="canx">取消政策</label><br> <span>${errorMsgs.canx}</span>
					<textarea rows="6" cols="40" name="canx" id="canx" required>${company.canx}</textarea>
					<br>
				</div>
				<button id="pre">上一頁</button>

				<div id="back_index">
					<a href='back-end/roomOrder/roomOrder_index.jsp'>回到訂房訂單首頁</a>
				</div>
			</div>
		</div>
	</div>


	<script>
        const button1=document.querySelector('#pre');
        button1.addEventListener('click',e=>{history.back();});
    </script>

</body>
</html>