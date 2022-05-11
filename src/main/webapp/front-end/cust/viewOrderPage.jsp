<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">


</head>
<body>
	<div>
		<form method="post" action="showViewPage"
			style="display: inline-block; margin: 20px;">
			<input type="hidden" name="pageChange" value="room">
			<button type="submit" class="btn btn-primary">訂房訂單</button>
		</form>

		<form method="post" action="showViewPage"
			style="display: inline-block; margin: 20px;">
			<input type="hidden" name="pageChange" value="ticket">
			<button type="submit" class="btn btn-primary">票券訂單</button>
		</form>

	</div>
	<!-- /.col-lg-6 -->
	<div class="col-lg-6">
		<div class="panel panel-default">
			<div class="panel-heading">Context Classes</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th>店家</th>
								<th>房型</th>
								<th>下訂日期</th>
								<th>總金額</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>凱達大飯店</td>
								<td>精緻雙人房</td>
								<td>2020-01-15 09:39:30</td>
								<td>30000</td>
							</tr>
							<tr>
								<td>凱達大飯店</td>
								<td>海景套房</td>
								<td>2020-01-20 09:30:30</td>
								<td>2500</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /.panel -->
	</div>
	<!-- /.col-lg-6 -->
	<script type="text/javascript">
		let tr = document.querySelector('table').querySelector('tbody')
				.querySelectorAll('tr');
		for (let i = 0; i < tr.length; i++) {
			if (i % 2 === 1) {
				tr[i].classList = 'info';
			} else {
				tr[i].classList = 'success';
			}
		}
	</script>
	
</body>
</html>