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
<title>Insert title here</title>
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

</head>
<body>
<div id="wrapper">

       <%@ include file="/common/back-end-index-bar.jsp" %>
        <!-- Page Content -->
        <div id="page-wrapper">
            <div class="container-fluid">
				<div class="row">
                 	<div class="col-lg-12" style="margin-top:40px;">
                 		<table>
                 			<tr>
								<th>會員編號</th>
								<td>${customer.custId}</td>
							</tr>
							<tr>
								<th>姓名</th>
								<td>${customer.name}</td>
							</tr>
							<tr>
								<th>性別</th>
								<td>${(customer.sex=="f")?'女':'男'}</td>
							</tr>
							<tr>
								<th>電話</th>
								<td>${customer.tel}</td>
							</tr>
							<tr>
								<th>電子信箱</th>
								<td>${customer.email}</td>
							</tr>
							<tr>
								<th>聯絡地址</th>
								<td>${(customer.address==null)?'無聯絡地址':customer.address}</td>
							</tr>
							<tr>
								<th>身分證字號</th>
								<td>${customer.idCard}</td>
							</tr>
							<tr>
								<th>生日</th>
								<td>${customer.birth}</td>
							</tr>
							<tr>
								<th>帳號</th>
								<td>${customer.account}</td>
							</tr>
							<tr>
								<th>圖片</th>
								<td style="height: 80px"><img style="height: 100%;"
									src="${customer.img}"></td>
							</tr>
							<tr>
								<th>信用卡卡號</th>
								<td>${(customer.card==null)?'您尚未輸入信用卡卡號':customer.card}</td>
							</tr>
							<tr>
								<th>會員權限</th>
								<td>${customer.custRight}</td>
							</tr>
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