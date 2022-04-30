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
    background-color: rgb(221, 221, 241) !important;
    height: 1000px; 
}
#cmpInfo{
	padding-right: 30px;
	position: relative;
}
label{
	margin-left: 10px;
}
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
        #pre{
        	margin-left: 35%;
        }
        
/*         img{ */
/*         	width: 200px; */
/*         	height: 200px; */
/*         } */
		
		#img_div{
			position:absolute;
			right: 20%;
			top :10%;
		}
        
</style>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">廠商詳情</h1>
				</div>
			</div >
				<div id="cmpInfo">
					<label for="cmp_name">廠商名稱:</label>
					<input type="text" name="cmpName" id="cmp_name" value="${company.cmpName}" readonly="readonly"><br>
						
					<label for="cmp_status">廠商狀態:</label>
					<input type="text" name="cmpStatus" id="cmp_status" value="${company.cmpStatus}" readonly="readonly"><br>
				
					<label for="cmp_tel">廠商電話:</label>
					<input type="tel" name="cmpTel" id="cmp_tel" value="${company.cmpTel}" readonly="readonly"><br>
					
					<label for="bank_account">銀行帳號:</label>
					<input type="tel" name="bankAccount" id="bank_account" value="${company.bankAccount}" readonly="readonly"><br>
					
					<label for="cmp_mail">廠商電子信箱:</label>
					<input type="email" name="cmpMail" id="cmp_mail" value="${company.cmpMail}" readonly="readonly"><br>
			
					<label for="cmper">廠商負責人:</label>
					<input type="text" name="cmper" id="cmper" value="${company.cmper}" readonly="readonly"><br>
			
					<label for="cmper_tel">廠商負責人電話:</label>
					<input type="tel" name="cmperTel" id="cmper_tel" value="${company.cmperTel}" readonly="readonly"><br>
			
					<label for="serial_no">旅館登記證:</label><br>
					<div id="img_div">
						<img id="serial_no" alt="" src="${company.serialNo}"/><br>
					</div>
					
						
					<label for="cmp_account">帳號:</label>
					<input type="text" name="cmpAccount" id="cmp_account" value="${company.cmpAccount}" readonly="readonly"><br>
				
					<label for="cmp_introduce">飯店介紹:</label>
					<textarea rows="6" cols="40" name="cmpIntroduce" id="cmp_introduce" readonly="readonly" style="resize: none;">${company.cmpIntroduce} </textarea><br>
				
					<label for="checkin_time">入住時間:</label>
					<input type="time" id="checkin_time" name="checkinTime" min="00:00" max="24:00" value="${company.checkinTime}" readonly="readonly"><br>
				
					<label for="checkout_time">退房時間:</label>
					<input type="time" id="checkout_time" name="checkoutTime" min="00:00" max="24:00" value="${company.checkoutTime}" readonly="readonly"><br>
				
					<label for="location">地點:</label>
					<input type="text" id="location" name="location" value="${company.location}" readonly="readonly"><br>
				
					<label for="notice">購買須知:</label>
					<textarea rows="6" cols="40" name="notice" id="notice" readonly="readonly" style="resize: none;">${company.notice}</textarea><br>
				
					<label for="canx">取消政策:</label>
					<textarea rows="6" cols="40" name="canx" id="canx" readonly="readonly" style="resize: none;">${company.canx}</textarea><br>	
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