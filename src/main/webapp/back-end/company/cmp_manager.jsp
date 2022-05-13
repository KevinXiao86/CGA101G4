<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺廠商</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>後台 - 審核廠商頁面</title>

<!-- Bootstrap Core CSS -->
<link href="common/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="common/css/metisMenu.min.css" rel="stylesheet">

<!-- DataTables CSS -->
<link href="common/css/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<!-- DataTables Responsive CSS -->
<link href="common/css/dataTables/dataTables.responsive.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="common/css/startmin.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="common/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
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
						</ul>
					</div>
				</div>
		</nav>




		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">廠商資料列表</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>


				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">廠商資料如下
							
								<a
									href="manager/company/getCompaniesByPage"
									style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block;
										margin-left: 780px;">
									查詢所有
								</a>
							</div>
							
							<div class="form-group"
								style="margin-left: 20px; margin-bottom: 8px;">
								<form action="manager/company/getCompaniesByAuditStatus"
									method="get">
									<select id="auditStatus" name="auditStatus">


										<c:choose>
											<c:when test="${param.auditStatus == '待審核'}">
												<option value="待審核" selected="selected">待審核</option>
												<option value="審核未通過">審核未通過</option>
												<option value="審核通過">審核通過</option>
											</c:when>

											<c:when test="${param.auditStatus == '審核未通過'}">
												<option value="待審核">待審核</option>
												<option value="審核未通過" selected="selected">審核未通過</option>
												<option value="審核通過">審核通過</option>
											</c:when>

											<c:when test="${param.auditStatus == '審核通過'}">
												<option value="待審核">待審核</option>
												<option value="審核未通過">審核未通過</option>
												<option value="審核通過" selected="selected">審核通過</option>
											</c:when>

											<c:otherwise>
												<option value="待審核">待審核</option>
												<option value="審核未通過">審核未通過</option>
												<option value="審核通過">審核通過</option>
											</c:otherwise>
										</c:choose>
									</select>
									<button class="button"
										style="background-color: lightgray; border: none; color: white; padding: 8px; text-align: center; text-decoration: none; display: inline-block; font-size: 10px; margin: 4px 2px; cursor: pointer; border-radius: 12px;">
										查詢</button>
								</form>
							</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<!-- 因為修改操作都需要 id, 所以使用隱藏域直接帶上 id -->
									<input type="hidden" name="cmpId" id="cmpId"
										value="${requestScope.detailCompany.cmpId}">

									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example" style="width: 100%;">
										<tr>
											<!--                                             <td>廠商編號</td> -->
											<td>廠商名稱</td>
											<td>廠商電話</td>
											<td>廠商電子信箱</td>
											<td>廠商負責人</td>
											<td>廠商負責人電話</td>
											<td>廠商狀態</td>
											<td>停權/復權</td>
											<td>廠商審核狀態</td>
											<td>詳情</td>
										</tr>

										<%--                                      <c:forEach items="${requestScope.companies}" var="Company"> --%>
										<c:forEach items="${requestScope.page.items}" var="Company">
											<tr>
												<%--                                             <td>${Company.cmpId}</td> --%>
												<td>${Company.cmpName}</td>
												<td>${Company.cmpTel}</td>
												<td>${Company.cmpMail}</td>
												<td>${Company.cmper}</td>
												<td>${Company.cmperTel}</td>
												<td>${Company.cmpStatus}</td>
												<td><c:choose>
														<c:when test="${Company.cmpStatus == '正常'}">
															<a
																href="manager/company/updateStatus?cmpId=${Company.cmpId}&status=停權"
																style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block;">
																停權 </a>
														</c:when>

														<c:when test="${Company.cmpStatus == '停權'}">
															<a
																href="manager/company/updateStatus?cmpId=${Company.cmpId}&status=正常"
																style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block;">
																複權 </a>
														</c:when>
													</c:choose></td>
												<td>${Company.auditStatus}</td>
												<td><a
													href="manager/company/companyDetail?cmpId=${Company.cmpId}"
													style="background-color: gray; color: white; padding: 5px 5px; text-decoration: none; display: inline-block;">
														詳情 </a></td>
											</tr>
										</c:forEach>
									</table>


									<%--靜態包含分頁條 --%>
									<%@include file="/back-end/company/page_nav.jsp"%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>



	<!-- jQuery -->
	<script src="common/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="common/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="common/js/metisMenu.min.js"></script>

	<!-- DataTables JavaScript -->
	<script src="common/js/dataTables/jquery.dataTables.min.js"></script>
	<script
		src="common/js/dataTables/dataTables.bootstrap.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="common/js/startmin.js"></script>

	<!-- Page-Level Demo Scripts - Tables - Use for reference -->
	<script>
		$(document).ready(function() {
			$('#dataTables-example').DataTable({
				responsive : true
			});
		});
	</script>
</body>
</html>