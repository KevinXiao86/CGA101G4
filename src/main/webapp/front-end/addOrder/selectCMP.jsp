<%@page import="com.taiwan.utils.ControllerUtil"%>
<%@page import="com.taiwan.service.company.CompanyService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.beans.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<%
		CompanyService companyService = ControllerUtil.getBean(CompanyService.class);
		request.setAttribute("cmpSVC", companyService);
		System.out.println(companyService);
	%>
	<FORM METHOD="post" ACTION="http://localhost:8081/CGA101G4/roomOrder12/ROSelectCmp"
		name="form1">
		<table>
<%-- 			<jsp:useBean id="cmpSvc" scope="page" --%>
<%-- 				class="com.taiwan.service.company.impl.CompanyServiceImpl" /> --%>


			<tr>
				<td>廠商:</td>
				<td><select name="cmpId">
						<c:forEach var="cmpVO" items="${cmpSVC.allCompany}">
							<option value="${cmpVO.cmpId }"
								${(param.cmpId==cmpVO.cmpId)? 'selected':'' }>${cmpVO.cmpName}</option>
						</c:forEach>
				</select></td>
				<td>${errorMsgs.cmpId}</td>
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

		</table>
		 <input type="submit" value="送出新增">
	</FORM>
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
            
            $('#ckin').change(function(){
            start = new Date(Date.parse($('#ckin').val()) + 24*60*60*1000);
            startMonth = start.getMonth() +1;
            startDay=start.getDate();
            if (startMonth < 10)
            	startMonth = "0" + startMonth;
            if (startDay < 10)
            	startDay = "0" + startDay;
            startval = start.getFullYear() + "-" + startMonth + "-" + startDay;
       
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
</body>
</html>