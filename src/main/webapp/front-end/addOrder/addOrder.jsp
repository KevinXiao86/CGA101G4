<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.service.coupon.CouponService"%>
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

</head>
<body>
	<%
		CouponService couponService = ControllerUtil.getBean(CouponService.class);
		request.setAttribute("couponService", couponService);
		System.out.println(couponService);
	%>
	<%
//假設會員10000已登入
int i = 10000;
CustomerServiceImpl custSvc = new CustomerServiceImpl();
CustomerVO custVO = custSvc.getAll(i);
request.setAttribute("custVO", custVO);
%>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/roomOrder12/roomOrder"
		name="form1">


		<table>
			<jsp:useBean id="RTSvc" scope="page" class="com.taiwan.service.impl.ReservationServiceImpl12" />
			<jsp:useBean id="CustCouponSvc12" scope="page" class="com.taiwan.service.impl.CustCouponServiceImpl12" />
			<%--<jsp:useBean id="cmpSvc" scope="page" class="com.taiwan.service.company.impl.CompanyServiceImpl12" />--%>
			<tr>
				<td>會員:</td>
				<td><input type="hidden" name="custId" size="45"
					value="${custVO.custId}" /></td>
				<td>${errorMsgs.custId}</td>
			</tr>

			<tr>
				<td>房型:</td>
				<td><select name="roomId" onChange="renew(this.selectedIndex);">
						<c:forEach var="roomtypeVO" items="${roomtypeVOs}">
							<option value="${roomtypeVO.roomtypeId }"
								${(param.roomId==roomtypeVO.roomtypeId)? 'selected':'' }>${roomtypeVO.roomtypeName}$${roomtypeVO.roomtypePrice}</option>
						</c:forEach>
				</select></td>
				<td>${errorMsgs.empName}</td>
			</tr>
			<tr>
				<td>房數:</td>
				<td><select name="amount">
						<c:forEach var="amount" begin="1"
							end="${roomtypeVOs[0].roomtypeAmount }">
							<option value="${amount}"
								${(param.amount==amount)? 'selected':'' }>${amount}</option>
						</c:forEach>
				</select></td>
				<td>${errorMsgs.amount}</td>
			</tr>
			<tr>
				<td>開始日期:</td>
				<td><input type="date" id="ckin" name="ckin"
					value="${dateMap.ckin}"></td>
				<td>${errorMsgs.ckin}</td>

			</tr>
			<tr>
				<td>結束日期:</td>
				<td><input type="date" id="ckout" name="ckout"
					value="${dateMap.ckout}"></td>

				<td>${errorMsgs.ckout}</td>
			</tr>
			<tr>
				<td>開始日期:</td>
				<td><input type="date" class="ckin2" name="ckin2"
					value="${dateMap.ckin}"></td>
				<td>${errorMsgs.ckin}</td>

			</tr>
			<tr>
				<td>結束日期:</td>
				<td><input type="date" class="ckin2" name="ckout2"
					value="${dateMap.ckout}"></td>

				<td>${errorMsgs.ckout}</td>
			</tr>
			<tr>
				<td>優惠券:</td>
				<td><select name="custCopId">
				<option value="0">請選擇優惠券</option>
						<c:forEach var="CustCouponVO" items="${CustCouponSvc12.searchCustCoupon(custVO.custId)}">
							<option value="${CustCouponVO.custCopId }"
								${(param.copId==CustCouponVO.custCopId)? 'selected':'' }>${couponService.findById(CustCouponVO.copId).copName}$${couponService.findById(CustCouponVO.copId).discount}</option>
						</c:forEach>
				</select>
				<td></td>
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
<%--amount[0]=[1, 2, 3];	
amount[1]=[1, 2, 3];	
amount[2]=[1, 2];			
amount[3]=[1,2,3,4];	--%>			
var cmpRoomtype =0;
<c:forEach var="roomtypeVO" items="${roomtypeVOs}">
amount[cmpRoomtype]=[1];

	for(i=2;i<="${roomtypeVO.roomtypeAmount }";i++){
		amount[cmpRoomtype].push(i);
	}
	<%--
	<c:forEach var="i" begin="1" end="${roomtypeVO.roomtypeAmount }">
	amount[cmpRoomtype].push(${i});
	console.log(${i});
	console.log(amount[cmpRoomtype]);
	</c:forEach>
	
	--%>
cmpRoomtype=cmpRoomtype+1;

</c:forEach> 




function renew(index){
	console.log(index);
	console.log(amount[0][1]);

	console.log(amount[1]);

	console.log(amount[index]);
	for(var i=0;i<amount[index].length;i++)
		document.form1.amount.options[i]=new Option(amount[index][i], amount[index][i]);	<%--// 設定新選項--%>
	document.form1.amount.length=amount[index].length;	<%--// 刪除多餘的選項--%>
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

            $('#ckin').attr('min', today);
            $('#ckin').attr('max', lastmonth);

            
            let start;
            let startMonth;
            let startval;
            let startDay;
            
                start = new Date(Date.parse($('#ckin').val()) + 24*60*60*1000);
                startMonth = start.getMonth() +1;
                startDay=start.getDate();
                if (startMonth < 10)
                	startMonth = "0" + startMonth;
                if (startDay < 10)
                	startDay = "0" + startDay;
                startval = start.getFullYear() + "-" + startMonth + "-" + startDay;

                    $('#ckout').attr('min', startval);
                    $('#ckout').attr('max', lastmonth);

                    
               
            $('#ckin').change(function(){
            start = new Date(Date.parse($('#ckin').val()) + 24*60*60*1000);
            startMonth = start.getMonth() +1;
            startDay=start.getDate();
            if (startMonth < 10)
            	startMonth = "0" + startMonth;
            if (startDay < 10)
            	startDay = "0" + startDay;
            startval = start.getFullYear() + "-" + startMonth + "-" + startDay;
            $('.ckin2').val($('#ckin').val());
            })
            
            $('#ckin').blur(e => {
            	console.log(start);
            	console.log(startMonth);
            	console.log(startval);
            	console.log(lastmonth);
            	

                $('#ckout').attr('min', startval);
                $('#ckout').attr('max', lastmonth);

                
            })	
        });
    </script>
</html>