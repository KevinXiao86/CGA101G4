<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>

<title>台玩</title>

<!-- Favicon title的小圖 -->
<link rel="icon"
	href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

<style>
	table {
		text-align: center;
	}
	.clearfix:after {
		display: block;
		content: "";
		clear: both;
		height: 0;
	}
	.fl {
		float: left;
	}
	.fr {
		float: right;
	}
	.scoreEvaluation {
		width: 25%;
	}
	.scoringDevice img {
		width: 15%;
		margin-right: 5%;
		height: auto;
		cursor: pointer;
	}
</style>

</head>
<body>

	<!-- Header -->
	<jsp:include page="/front-end/homepage/header.jsp"></jsp:include>
	<jsp:include page="/front-end/cust/side-bar.jsp"></jsp:include>
	<main id="main" class="main"
		style="padding-left: 70px; height: 1200px; padding-top: 40px;">

		<div class="pagetitle">
			<h1>票券明細</h1>
			<nav>
				<ol class="breadcrumb">
					<li class="breadcrumb-item">首頁</li>
					<li class="breadcrumb-item">瀏覽訂單</li>
					<li class="breadcrumb-item active">票券訂單</li>
				</ol>
			</nav>
		</div>
		
		<FORM METHOD="post" ACTION="tktOrder/getDate" >
	        <b>查詢訂單:</b><font color=red>${errorMsgs.date}</font><br>
	        <label for="from">From:  </label>
			<input type="date" id="startdate" name="startdate" value="${param.startdate}" required style="width:110px;margin: 10px;">
			<label for="to" style="font-size:16px;">to: </label>
			<input type="date" id="enddate" name="enddate" value="${param.enddate}" required style="width:110px;margin: 10px;">
	        <input type="hidden" name="action" value="get_date">
	        <input type="submit" value="送出" style="width:60px;background-color: lightcoral;color: white;margin-left: 25px;">
	    </FORM>

		<div class="w-80 p-3 "
			style="background-color: #ced7e8; margin: 50px auto 30px;">

			<table border="2" class="table table-hover">
				<thead>
					<tr>
						<th>訂單編號</th>
						<th>訂購者</th>
						<th>電話</th>
						<th>信箱</th>
						<th>票券名稱</th>
						<th>數量</th>
						<th>評價分數</th>
						<th>評價內文</th>
						<th>回饋</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tktItem" items="${sessionScope.itemList}">
						<tr>
							<td>${tktItem.tktOrderId}</td>
							<td>${tktOrder.orderName}</td>
							<td>${tktOrder.orderMobile}</td>
							<td>${tktOrder.orderEmail}</td>
							<td>${(tktName == null)? tktItem.ticket.tktName : tktName}</td>
							<td>${tktItem.amount}張</td>
							<td>${(tktItem.score == null)? "": tktItem.score}</td>
							<td>${(tktItem.content == null)? "": tktItem.content}</td>
							<c:if test="${tktItem.score == null}">
								<td><input type="submit" value="立即給予評分" onclick="show()"></td>
								<div id="showdiv" style="display: none">
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/tktItem/content"
										style="margin-bottom: 0px;">
										評分:
										<div>${errorMsgs.score}</div>
<!-- 										<input type="number" value="1" min="1" max="5" name="score"	style="width: 40px;" required><br>  -->
										<div class="scoreEvaluation">
											<div class="scoringDevice clearfix">
												<img src="front-end/tktItem/images/sss.png" class="scoreImg fl" data-index='1' onclick="scoreFunc(event)" />
												<img src="front-end/tktItem/images/sss.png" class="scoreImg fl" data-index='2' onclick="scoreFunc(event)" />
												<img src="front-end/tktItem/images/sss.png" class="scoreImg fl" data-index='3' onclick="scoreFunc(event)" />
												<img src="front-end/tktItem/images/sss.png" class="scoreImg fl" data-index='4' onclick="scoreFunc(event)" />
												<img src="front-end/tktItem/images/sss.png" class="scoreImg fl" data-index='5' onclick="scoreFunc(event)" />
											</div>
										</div>
										評價內容:
										<div>${errorMsgs.content}</div>
										<textarea name="content" style="width: 400px;" placeholder="請輸入500字以內🦒🐘🦦🐥🐇🦧" required>${param.content}</textarea>

										<input type="hidden" name="score" id="score">
										<input type="hidden" name="tktOrderId" value="${tktItem.tktOrderId}"> 
										<input type="hidden" name="tktId" value="${tktItem.tktId}"> 
										<input type="hidden" name="action" value="insert"> 
										<input type="submit" value="送出" id="go"
											style="width: 80px; background-color: lightcoral; color: white; margin-bottom: 30px;">
									</FORM>
								</div>
							</c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</main>

	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>

	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
		function show() {
			if (document.getElementById('showdiv').style.display == 'none') {
				document.getElementById('showdiv').style.display = 'block';
			}
			// 			else {
			// 				document.getElementById('showdiv').style.display = 'none';
			// 			}
		}
		
		
		//評分使用的變數
		ScoreBl = true;
		scoreI = null;
		scoreImgs = [];
		nowI = 0;       //點擊後的分數存放在此變數中
		//點擊進行評分
		function scoreFunc(event) {
			if (ScoreBl) {
				scoreImgs = document.getElementsByClassName('scoreImg');
			}
			let e = event || window.event;
			let target = e.target || e.srcElement;
			let x = target.getAttribute('data-index');
			//10進位轉換
			nowI = parseInt(x, 10);
			let i;
			if (nowI != scoreI) {
				scoreI = nowI;
				for (i = 0, len = scoreImgs.length; i < len; i++) {
					scoreImgs[i].src = "front-end/tktItem/images/sss.png";
				}
				for (i = 0; i < nowI; i++) {
					scoreImgs[i].src = "front-end/tktItem/images/images.png";
				}
			//最小要給一分
			} else {
				scoreI = 0;
				for (i = 0, len = scoreImgs.length; i < len; i++) {
					scoreImgs[i].src = "front-end/tktItem/images/sss.png";
				}
			}
			//將值設定回value
			$('#score').val(nowI);
			
		}
		
		$('#go').click(function(){
			if(nowI === 0){
				Swal.fire({
					icon: 'error',
					title: '🙇‍♀️ 請給我們一點鼓勵💦💧💧，'
				})
				return false;
			}
		});
		
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
	        let lastday = year + "-" + month2 + "-" + day;  

        	$('#startdate').attr('value', today);
            $('#startdate').attr('max', today);
            $('#startdate').blur(e => {
                $('#enddate').attr({
                    'min': $('#startdate').val(),
                    'max': today
                })
            })	
        });

	</script>
</body>

</html>