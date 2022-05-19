<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<%@ page import="com.taiwan.controll.employee.*"%>
<%@ page import="com.taiwan.service.employee.*"%>


<%
// EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料修改 - !!!!update_emp_input.jsp!!!</title>

<style>
        #page-wrapper {
            background-color: rgb(221, 221, 241) !important;
/*             height: 600px;  */
        }

        label {
            font-size: 16px;
        }

        img {
            /* border: 1px solid black; */
            height: 200px;
            width: 200px;
        }

        #img_div {
            position: absolute;
            top: 40%;
            right: 10%;
        }

        #img_Odiv {
            position: absolute;
            top: -10%;
            right: 10%;
        }

        input[type="text"] {
            width: 220px;
        }

        input[type="number"] {
            width: 190px;
        }

        #back_index {
            position: fixed;
            right: 5%;
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

        #coupon_form {
            position: relative;
        }

        input[type="submit"] {
            margin-bottom: 10px;
        }

        input[type="submit"] {
            box-shadow: inset 0px 1px 0px 0px #ffffff;
            background: linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
            background-color: #ffffff;
            border-radius: 6px;
            border: 1px solid #dcdcdc;
            display: inline-block;
            cursor: pointer;
            color: #666666;
            font-family: Arial;
            font-size: 15px;
            font-weight: bold;
            padding: 6px 24px;
            text-decoration: none;
            text-shadow: 0px 1px 0px #ffffff;
        }

        input[type="submit"]:hover {
            background: linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
        }
    </style>

</head>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">員工資料修改</h1>
				</div>
			</div>
			<div id="coupon_form">
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/EmployeeServlet" name="form1">
					員工編號:
					${param.empId}<br> 
					<label>員工姓名</label><input
						type="text" name="empName" autofocus placeholder="員工姓名"
						value="${param.empName}">
						${errorMsgs.empName}<br>
					<label>員工密碼</label><input
						type="text" name="empPassword"  size="45" autofocus placeholder="密碼"
						value="${param.empPassword}">
						${errorMsgs.empPassword}<br>
						<jsp:useBean id="eFSvc" scope="page" class="com.taiwan.service.employeeFuction.EmployeeFunctionService" />
					<label>員工權限</label>
					<select size="1" name="funcId">
			<c:forEach var="employeeFunctionVO" items="${eFSvc.all}">
				<option value="${employeeFunctionVO.funcId}" ${(param.funcId==employeeFunctionVO.funcId)? 'selected':'' } >${employeeFunctionVO.funcName}
			</c:forEach>
		</select>
					<label>啟用狀態</label>
					<select size="1" name="empStatus">
							<option value="啟用" ${(param.empStatus=="啟用")? 'selected':'' }>啟用</option>
							<option value="未啟用" ${(param.empStatus=="未啟用")? 'selected':'' }>未啟用</option>
							<option value="離職" ${(param.empStatus=="離職")? 'selected':'' }>離職</option>
						</select>
					創建日期:${param.hiredate}
							
		
				
<!-- 					<td><select size="1" name="empStatus"> -->
<%-- 							<option value="啟用" ${(param.empStatus=="啟用")? 'selected':'' }>啟用</option> --%>
<%-- 							<option value="未啟用" ${(param.empStatus=="未啟用")? 'selected':'' }>未啟用</option> --%>
<%-- 							<option value="離職" ${(param.empStatus=="離職")? 'selected':'' }>離職</option> --%>
<%-- 												<option selected="selected">${param.empStatus}</option> --%>
<!-- 					</select></td> -->
					
					<input type="hidden" name="empId" value="${param.empId}">
					<br> <input type="hidden" name="action" value="update">
					<input type="hidden" name="hiredate" value="${param.hiredate}">
					 <input type="submit" value="送出修改">
				</FORM>
				</div>
				<div id="back_index">
                   
                </div>
            </div>
        </div>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : '${param.hiredate}', // value:   new Date(),
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});
</script>
</html>