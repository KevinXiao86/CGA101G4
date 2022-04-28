<%@page import="com.taiwan.service.roomtype.RoomtypeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ page import="com.taiwan.beans.*"%>
<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.roomtype.*"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AddOrder</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%--@ include file="/common/head.jsp"--%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<%--
		RoomtypeService roomtypeService = ControllerUtil.getBean(RoomtypeService.class);
		request.setAttribute("roomtypeSVC", roomtypeService);
		System.out.println(roomtypeService);
	--%>
	
	 <FORM METHOD="post" ACTION="http://localhost:8081/CGA101G4/roomOrder"
		name="form1">
	
	 
		<table>
			<%--<jsp:useBean id="cmpSvc" scope="page" class="com.taiwan.service.company.impl.CompanyServiceImpl12" />--%>
			<tr>
				<td>會員:</td>
				<td><input type="TEXT" name="custId" size="45" 
			 value="${param.custId}"/></td><td>${errorMsgs.custId}</td>
			</tr>
			
			<tr>
				<td>房型:</td>
				<td><select name="roomId" onChange="renew(this.selectedIndex);">
				<c:forEach var="roomtypeVO" items="${roomtypeVOs}">
						<option  value="${roomtypeVO.roomtypeId }" 
						${(param.roomId==roomtypeVO.roomtypeId)? 'selected':'' }>${roomtypeVO.roomtypeName}$${roomtypeVO.roomtypePrice}</option>
				</c:forEach> 
				</select></td><td>${errorMsgs.empName}</td>
			</tr>
			<tr>
				<td>房數:</td>
				<td><select name="amount">
				<c:forEach var="amount" begin="1" end="${roomtypeVOs[0].roomtypeAmount }">
						<option  value="${amount}" ${(param.amount==amount)? 'selected':'' }>${amount}</option>
				</c:forEach> 
				</select></td><td>${errorMsgs.empName}</td>
				<!-- 
				<td><input type="TEXT" name="amount" size="45" 
			 value="${param.amount}"/></td><td>${errorMsgs.empName}</td>
				
				 -->
			</tr>
			<tr>
				<td>開始日期:</td>
	<td><input type="date" id="ckin" name="ckin" value=""></td>
				<td>${errorMsgs.ckin}</td>

			</tr>
			<tr>
				<td>結束日期:</td>
	<td><input type="date" id="ckout" name="ckout" value=""></td>

				<td>${errorMsgs.ckout}</td>
			</tr>
			<br>

		</table>
		<input type="hidden" name="cmpId" value="${list[0].getCmpId }"> 
		<input type="submit" value="送出新增">
	</FORM>
</body>

<%--"/CGA101G4/front-end/addOrder" --%>
<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>


	
<script>
amount=new Array();
amount[0]=[1, 2, 3];	// 資訊系
amount[1]=[1, 2, 3];	// 電機系
amount[2]=[1, 2];			// 動機系
amount[3]=[1,2,3,4];				// 工科系
//var cmpRoomtype =0;
//<c:forEach var="roomtypeVO" items="${list}">
//cmpRoomtype=cmpRoomtype++;
//</c:forEach> 

//for(var i=0;i<cmpRoomtype;i++){
	//<c:forEach var="amount" begin="1" end="${roomtypeVO.roomtypeAmount }">
//amount[i].push(${amount});
	//		</c:forEach>
//}


function renew(index){
	for(var i=0;i<amount[index].length;i++)
		document.form1.amount.options[i]=new Option(amount[index][i], amount[index][i]);	// 設定新選項
	document.form1.amount.length=amount[index].length;	// 刪除多餘的選項
}
</script>
<script>
		$(function(){
	        let date = new Date();
	        let day = date.getDate();
	        let month = date.getMonth() + 1;
	        let month2 = date.getMonth() + 2;
	        let year = date.getFullYear();
	        if (month < 10)
	            month = "0" + month;
	        if (month2 < 10)
	            month2 = "0" + month2;
	        if (day < 10)
	            day = "0" + day;
	        let today = year + "-" + month + "-" + day; 
	        let lastmonth = year + "-" + month2 + "-" + day;  

        
            
            let start = new Date(Date.parse($('#ckin').val()) + 24*60*60*1000);
            let startMonth = start.getMonth() +1;
            if (startMonth < 10)
            	startMonth = "0" + startMonth;
            let startval = start.getFullYear() + "-" + startMonth + "-" + start.getDate();
            
            $('#ckin').blur(e => {
                $('#ckout').attr({
                    'min': startval,
                     'max': lastmonth  
                })
            })	
        });
    </script>
</html>