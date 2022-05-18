<%@page import="com.taiwan.dao.customer.impl.CustomerJNDIDAO"%>
<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% System.out.println("我在custInfoManage裡");
	CustomerJNDIDAO dao=new CustomerJNDIDAO();
	List<CustomerVO> list=dao.getAllNoMatterWho();
	System.out.println("我在custInfoManage裡"+list);
	request.setAttribute("list",list);%>
<!DOCTYPE html>
<html>
<%@ include file="/common/head.jsp"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>台玩後臺</title>
<!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="css/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/startmin.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="css/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<style>
#page-wrapper {
/*     background-color: rgb(221, 221, 241) !important; */
/*     height: 1000px;  */
}

table {
	width: 1100px;
	background-color: #ced7e8 !important;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
/* 	border: 3px solid #CCCCFF; */
}

th, td {
	padding: 5px;
	text-align: center;
}

img {
	width: 150px;
	height: 150px;
}
</style>

</head>
<body>
	<div id="wrapper">

       <%@ include file="/common/back-end-index-bar.jsp" %>
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">

                <div class="row">
                 	<div class="col-lg-12" style="margin-top:40px;">
                       <table border="2px" class="table table-striped table-hover">
                       		<thead>
                       			<tr>
                       				<th>會員編號</th>
                       				<th>會員帳號</th>
                       				<th>姓名</th>
                       				<th>性別</th>
                       				<th>電話</th>
                       				<th>電子信箱</th>
                       				<th>身分證字號</th>                      				
                       				<th>會員權限</th>
                       				<th>查詢會員資料</th>
                       				<th>查看優惠券</th>
                       			</tr>
                       		</thead>
                       		<tbody>
						   		<c:forEach var="customerVO" items="${list}">
						   		<tr>
						   			<td>${customerVO.custId}</td>
						   			<td>${customerVO.account}</td>
						   			<td>${customerVO.name}</td>
						   			<td>${(customerVO.sex=='f')?'女':'男'}</td>
						   			<td>${customerVO.tel}</td>
						   			<td>${customerVO.email}</td>
						   			<td>${customerVO.idCard}</td>					  
						   			<td>
						   				<form method="post" action="custManage/CustInfoManage">
						   					<input type="submit" name="custRight" value="${(customerVO.custRight=='正常')?'停權':'復權'}">
						   					<input type="hidden" name="custId" value="${customerVO.custId}">
						   					<input type="hidden" name="action" value="setCustRight">
						   				</form>
						   			</td>
						   			<td>
						   				<form method="post" action="custManage/CustInfoManage">
						   					<input type="submit" value="查詢基本資料">
						   					<input type="hidden" name="custId" value="${customerVO.custId}">
						   					<input type="hidden" name="action" value="showCustomerInformation">
						   				</form>
						   			</td>
							   		<td>
							   			<form method="post" action="custManage/CustInfoManage">
							   				<input type="submit" value="查看優惠券">
							   				<input type="hidden" name="custId" value="${customerVO.custId}">
							   				<input type="hidden" name="account" value="${customerVO.account}">
							   				<input type="hidden" name="action" value="showCustCoupon">
							   			</form>
							   		</td>
						   		<tr>
						  		</c:forEach>
					  		</tbody>
						</table>
					 </div>
                </div>

                <!-- ... Your content goes here ... -->

            </div>
        </div>
		
    </div>

    <!-- jQuery -->
    <script src="js/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="js/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="js/startmin.js"></script>
</body>
</html>