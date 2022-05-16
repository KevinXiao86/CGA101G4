<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>房型管理</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

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
<script type="text/javascript">
	$(function() {
		// 給輸入框綁定onchange內容發生改變事件
		// 如果是使用 blur 失去焦點事件, 還需要自己判斷數值有無改變, 所以推薦使用 onchange事件
		$("input")
				.change(
						function() {
							// 獲取房型名稱 
							var name = $(this).parent().parent().find(
									"td:eq(1)").text();
							// 獲取廠商編號
							var cmpId = $(this).attr('cmpId');
							// 獲取房型編號
							var roomtypeId = $(this).attr('roomtypeId');
							// 獲取價格
							var price = this.value;
							//數據校驗
							if (price <= 0) {
								price = this.defaultValue;
								this.value = this.defaultValue;
							}
							if (confirm("你確定要將【" + name + "】房型價格修改為: " + price
									+ " 嗎?")) {
								
								
								$.ajax({
									url:"${pageScope.basePath}roomtype/updateRoomtypePrice",
									data:{
										cmpId: cmpId,
										roomtypeId: roomtypeId,
										price: price
									},
									type:"GET",
									dataType : "json",
									success:function (data) {
										$("#updatePrice").val(data.price);
										$("#errorMsg").val(data.message);
									}
								});
							} else {
								// defaultValue屬性是表單項Dom對象的屬性。他表示默認的value屬性值[也就是一開始還沒有修改的值]
								this.value = this.defaultValue;
							}
						});
	});
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
					
						<li>
							<a href="company/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}&" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商首頁
							</a>
						</li>					
					
						<li>
							<a href="roomtype/getAllRoomtypesByPage?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								房型管理
							</a>
						</li>
						
						<li>
							<a href="company/getCompany?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								廠商資料
							</a>
						</li>
						
						<li>
							<a href="roomOrder/cmpFindOrder?cmpId=${sessionScope.loginCompany.cmpId}" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								訂單管理
							</a>
						</li>
						
						<li>
							<a href="company/logout" style="font-weight: 900;">
								<i class="fa fa-dashboard fa-fw"></i>
								登出
							</a>
						</li>

					</ul>
				</div>
				<!-- /.sidebar-collapse -->
			</div>
			<!-- /.navbar-static-side -->
		</nav>




		<div id="page-wrapper">
			<div class="container-fluid">
				<div class="row">
					<div class="col-lg-12">
						<h1 class="page-header">房型列表</h1>
					</div>
					<!-- /.col-lg-12 -->
				</div>


				<!-- /.row -->
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading" style="font-weight: 900;">已有的房型如下</div>
							<div class="form-group"
								style="margin-left: 800px; margin-top: 20px;">
								<a href="front-end/roomtype/add.jsp?pageTotal=${requestScope.page.pageTotal}&pageTotalCount=${requestScope.page.pageTotalCount}" 
									style="background-color: lightskyblue; color: white; padding: 14px 25px; text-align: center; text-decoration: none; display: inline-block; border-radius: 12px;">
									新增房型 </a>
							</div>

							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<span id="errorMsg" style="color: red; font-size: 20px">${requestScope.errorMsg}</span>
									<table class="table table-striped table-bordered table-hover"
										id="dataTables-example" style="width: 100%;">
										<tr>
											<td style="font-weight: 900;">房型圖片</td>
											<td style="font-weight: 900;">房型名稱</td>
											<td style="font-weight: 900;">房型數量</td>
											<td style="font-weight: 900;">入住人數</td>
											<td style="font-weight: 900;">價格</td>
											<td style="font-weight: 900;">狀態</td>
											<td style="font-weight: 900;">操作</td>

										</tr>
										<c:forEach items="${requestScope.page.items}" var="Roomtype">
											<tr>
												<td>
													<div id="myModal" class="modal">
														<span class="close">×</span> <img class="modal-content"
															id="img01">
														<div id="caption"></div>
													</div> <c:forEach items="${Roomtype.roomImgs}" end="0"
														var="RoomImg">
														<img height="128px" width="128px" src="${RoomImg.roomImg}">
													</c:forEach>
												</td>
												<td>${Roomtype.roomtypeName}</td>
												<td>${Roomtype.roomtypeAmount}</td>
												<td>${Roomtype.roomtypePeople}</td>


												<td><input id="updatePrice" style="width: 50px;"
													roomtypeId="${Roomtype.roomtypeId}"
													cmpId="${sessionScope.loginCompany.cmpId}" type="text"
													value="${Roomtype.roomtypePrice}"></td>

												<td>${Roomtype.roomtypeStatus}</td>

												<td><c:choose>
														<c:when test="${Roomtype.roomtypeStatus == '上架'}">
															<a
																href="roomtype/updateRoomtypeStatus?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}&status=下架"
																style="background-color: lightskyblue; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; border-radius: 12px;">
																下架房型 </a>
														</c:when>

														<c:when test="${Roomtype.roomtypeStatus == '下架'}">
															<a
																href="roomtype/updateRoomtypeStatus?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}&status=上架"
																style="background-color: lightskyblue; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; border-radius: 12px;">
																上架房型 </a>
														</c:when>
													</c:choose></td>

												<td><a
													href="roomtype/getRoomtype?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}"
													style="background-color: lightskyblue; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; border-radius: 12px;  cursor: pointer;">
														查看詳情 </a></td>


												<td><a
													href="roomtype/editRoomtype?cmpId=${sessionScope.loginCompany.cmpId}&roomtypeId=${Roomtype.roomtypeId}"
													style="background-color: lightskyblue; color: white; padding: 5px 5px; text-decoration: none; display: inline-block; border-radius: 12px;  cursor: pointer;">
														修改 </a></td>
											</tr>
										</c:forEach>
									</table>
									<%--靜態包含分頁條 --%>
									<%@include file="/front-end/roomtype/page_nav.jsp"%>
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