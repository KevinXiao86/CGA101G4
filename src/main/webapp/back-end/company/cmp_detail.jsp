<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商修改頁面</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<<<<<<< HEAD

=======
>>>>>>> m1a2st
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>後台 - 查看廠商詳情頁面</title>

<!-- Bootstrap Core CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="common/css/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="common/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="common/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

<script>
        $(() => {
        	
            $("#sendEmail").click(() => {
    	    	var cmpId = $("#cmpId").val();
        		
                $.ajax({
                    url: "http://localhost:8081/CGA101G4/manager/company/sendEmail",
                    type: "get",
                    //如果返回的內容是 JSON，jQuery 會自動幫你解析成一個 JavaScript object
                    dataType: "json",
                    data: {
						cmpId: cmpId
                    },
                    success: function (resp) {
                     	$("#errorMsg").html(resp);  
                    }
                });
            });
        })
    </script>
</head>
<body>
	<div id="wrapper">

		<!-- Navigation -->
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<a class="navbar-brand" href="index.html">台玩 - 旅遊平台</a>
			</div>
			
			<div class="navbar-default sidebar" role="navigation">
				<div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
<%-- 						<%@ include file="/common/back-end-index-bar.jsp"%> --%>
					</ul>
				</div>
			</div>
		</nav>

		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">審核廠商頁面</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">基本資料</div>
							<div class="panel-body">
								<div class="row">

									<div class="form-group">
										<a href="javascript:history.go(-1)"
											style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; margin-left: 20px; cursor: pointer;">
											返回列表 </a>
									</div>

									<div class="col-lg-6">
										<form role="form">
											<!-- 因為修改操作都需要 id, 所以使用隱藏域直接帶上 id -->
											<input type="hidden" name="cmpId" id="cmpId"
												value="${requestScope.detailCompany.cmpId}"> <span
												id="errorMsg" style="color: red; font-size: 20px"></span>

											<div class="form-group">
												<label for="cmpName">廠商名稱</label> <span>${requestScope.errorInfo.cmpName}</span>
												<input class="form-control" type="text" id="cmpName"
													name="cmpName" required="required"
													value="${requestScope.detailCompany.cmpName}" />
											</div>
											<div class="form-group">
												<label for="cmpTel">廠商電話</label> <span>${requestScope.errorInfo.cmpTel}</span>
												<input class="form-control" type="text" id="cmpTel"
													name="cmpTel" required="required"
													value="${requestScope.detailCompany.cmpTel}" />
											</div>

											<div class="form-group">
												<label for="bankAccount">銀行帳號</label> <span>${requestScope.errorInfo.headBank}${requestScope.errorInfo.endBank}</span><br>
												<input type="text" id="headBank" name="headBank"
													placeholder="銀行代號" required style="margin-bottom: 10px;"
													value="${requestScope.detailCompany.bankAccount.substring(0, 3)}" /><br>
												<input type="text" name="endBank" id="endBank"
													placeholder="銀行帳號" required
													value="${requestScope.detailCompany.bankAccount.substring(4)}" /><br>
											</div>

											<div class="form-group">
												<label for="cmpMail">信箱</label> <span>${requestScope.errorInfo.cmpMail}</span><br>
												<input type="text" id="cmpMail" name="cmpMail"
													required="required"
													value="${requestScope.detailCompany.cmpMail}" />
											</div>

											<div class="form-group">
												<label for="cmper">廠商負責人</label> <span>${requestScope.errorInfo.cmper}</span><br>
												<input type="text" id="cmper" name="cmper"
													required="required"
													value="${requestScope.detailCompany.cmper}" />
											</div>

											<div class="form-group">
												<label for="serialNo">旅宿登記證</label><br> <img
													src="${requestScope.detailCompany.serialNo}" width="120"
													height="120">
											</div>


											<div class="form-group">
												<label for="cmpIntroduce">飯店介紹</label> <span>${requestScope.errorInfo.cmpIntroduce}</span><br>
												<textarea rows="6" cols="40" name="cmpIntroduce"
													id="cmpIntroduce" required>${requestScope.detailCompany.cmpIntroduce}</textarea>
											</div>

											<div class="form-group">
												<label for="checkinTime">入住時間</label> <span>${requestScope.errorInfo.checkinTime}</span><br>
												<input type="time" id="checkinTime" name="checkinTime"
													min="00:00" max="24:00" required="required"
													value="${requestScope.detailCompany.checkinTime}" />
											</div>

											<div class="form-group">
												<label for="checkoutTime">退房時間</label> <span>${requestScope.errorInfo.checkoutTime}</span><br>
												<input type="time" id="checkoutTime" name="checkoutTime"
													min="00:00" max="24:00" required="required"
													value="${requestScope.detailCompany.checkoutTime}" />
											</div>

											<div class="form-group">
												<label>地點</label> <span>${requestScope.errorInfo.city}${requestScope.errorInfo.town}${requestScope.errorInfo.road}</span><br>
												<input type="text" id="city" name="city" placeholder="縣市"
													required="required" style="margin-bottom: 10px;"
													value="${requestScope.detailCompany.location.substring(0, 3)}" /><br>
												<input type="text" id="town" name="town" placeholder="城鎮"
													required="required" style="margin-bottom: 10px;"
													value="${requestScope.detailCompany.location.substring(3, 6)}" /><br>
												<input type="text" id="road" name="road" placeholder="街道"
													required="required" style="margin-bottom: 10px;"
													value="${requestScope.detailCompany.location.substring(6)}" />
											</div>

											<div class="form-group">
												<label for="notice">購買須知</label> <span>${requestScope.errorInfo.notice}</span><br>
												<textarea rows="6" cols="40" name="notice" id="notice"
													required>${requestScope.detailCompany.notice}</textarea>
												<br>
											</div>

											<div class="form-group">
												<label for="canx">取消政策</label><br> <span>${requestScope.errorInfo.canx}</span>
												<textarea rows="6" cols="40" name="canx" id="canx" required>${requestScope.detailCompany.canx}</textarea>
												<br>
											</div>

											<c:choose>
												<c:when
													test="${requestScope.detailCompany.auditStatus == '待審核'}">
													<a
														href="manager/company/audit?cmpId=${requestScope.detailCompany.cmpId}&auditStatus=審核通過"
														style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; cursor: pointer;">
														審核通過 </a>

													<a
														href="manager/company/audit?cmpId=${requestScope.detailCompany.cmpId}&auditStatus=審核未通過"
														style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; cursor: pointer;">
														審核未通過 </a>
													<%--             											<a href="manager/company/audit?cmpId=${requestScope.detailCompany.cmpId}&auditStatus=審核通過"></a> --%>
													<%--             											<a href="manager/company/audit?cmpId=${requestScope.detailCompany.cmpId}&auditStatus=審核未通過">審核未通過</a> --%>
												</c:when>

												<c:when
													test="${requestScope.detailCompany.auditStatus == '審核通過'}">
													<a href="#"
														style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; cursor: not-allowed;">
														審核完成 </a>
												</c:when>

												<%--有時間在做這個功能 --%>
												<c:when
													test="${requestScope.detailCompany.auditStatus == '審核未通過'}">
													<%--         												<a id="sendEmail" href="manager/company/sendEmail?cmpId=${requestScope.detailCompany.cmpId}"  --%>
													<a id="sendEmail"
														style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; cursor: pointer;">
														告知補件 </a>
												</c:when>
											</c:choose>
										</form>
									</div>
								</div>
								<!-- /.row (nested) -->
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
			<!-- /.container-fluid -->
		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->

	<!-- jQuery -->
	<script src="common/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="common/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="common/js/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="common/js/startmin.js"></script>
</body>
</html>