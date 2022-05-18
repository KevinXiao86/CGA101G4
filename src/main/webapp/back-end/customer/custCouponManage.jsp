<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                 		<span>${param.custId}</span>  <span>${param.account}</span>
                 	
                 	<c:if test="${list.isEmpty()}">
                 		<h2>此會員沒有領取任何優惠券</h2>
                 	</c:if>
                 	
                 	<c:if test="${!list.isEmpty()}">
						<table border="2px" class="table table-striped table-hover">
							<thead>
								<tr>
									<td>會員優惠券流水號</td>
									<td>優惠券名稱</td>
									<td>領取日期</td>
									<td>使用日期</td>
									<td>搭配使用</td>
									<td>優惠券金額</td>
									<td>狀態</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="custCoupon" items="${list}">
									<tr>
										<td>${custCoupon.custCopId}</td>
										<td>${custCoupon.copId}</td>
										<td>${custCoupon.getdate}</td>
										<td>${custCoupon.usedate}</td>
										<td>${(custCoupon.roomOrderId==0)?((custCoupon.tktOrderId==0)?'':custCoupon.tktOrderId):custCoupon.roomOrderId}</td>
										<td>${custCoupon.discount}</td>
										<td>${custCoupon.status}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
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