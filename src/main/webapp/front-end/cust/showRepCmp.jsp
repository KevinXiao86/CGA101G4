<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台玩</title>
<%@ include file="/common/head.jsp"%>

<!-- Favicon title的小圖 -->
<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<!-- Style CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/classy-nav-first.css">

<!-- 搜尋bar -->
<!-- Google font -->
<link href="https://fonts.googleapis.com/css?family=Cardo:700" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css"
	integrity="sha512-UTNP5BXLIptsaj5WdKFrkFov94lDx+eBvbKyoe1YAfjeRPC+gT5kyZ10kOHCfNZqEui1sxmqvodNUx3KbuYI/A=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
	integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://code.jquery.com/jquery-1.12.4.min.js"
	integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
	crossorigin="anonymous"></script>

</head>
<body>
	<%@ include file="/front-end/homepage/header.jsp" %>
	<%@ include file="/front-end/cust/side-bar.jsp" %>
	
	<main id="main" class="main" style="padding-left:70px;padding-top:40px;overflow:auto;">

    <div class="pagetitle">
      <h1>瀏覽檢舉</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item">首頁</li>
          <li class="breadcrumb-item">會員功能</li>
          <li class="breadcrumb-item active">瀏覽檢舉</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
	
    <section class="section">
      <div class="row">
          <div class="card">
            <div class="card-body">
              <h5 class="card-title">檢舉列表</h5>

              <!-- Table with stripped rows -->
              <table class="table table-striped">
                <thead>
                  <tr>
                    <th scope="col">商家名稱</th>
                    <th scope="col">檢舉房型</th>
                    <th scope="col">檢舉原因</th>
                    <th scope="col">檢舉時間</th>
                    <th scope="col">狀態</th>
                    <th scope="col">結果</th>
                    <th scope="col">刪除</th>
                  </tr>
                </thead>
                <tbody>
               	<c:forEach var="repCmpVO" items="${list}">
                  <tr>
                    <td>${repCmpVO.getCmpName()}</td>
                   	<td>${repCmpVO.getRoomtypeName()}</td>
                   	<td>${repCmpVO.reason}</td>
                  	<td>${repCmpVO.repCmpDate}</td>
                    <td>${repCmpVO.status}</td>
                    <td>${repCmpVO.result}</td>
                    <td>
						<form method="post" action="cust/DeleteRepCmp">
						<button type="submit" class="btn btn-primary" ${(repCmpVO.status=='未處理')?'':'disabled'}>刪除</button>
						
							<input type="hidden" value="${repCmpVO.repCmpId}" name="repCmpId">
							<input type="hidden" value="${repCmpVO.custId}" name="custId">
						</form>
					</td>
                  </tr>
                 </c:forEach>
                 </tbody>
                </table>
                <!-- End Table with stripped rows -->
              </div>
            </div>

        </div>
      </section>

    </main><!-- End #main -->
  	<%@ include file="/front-end/homepage/footer.jsp" %>
<!--  	<div style="display: inline-block; vertical-align: middle">
	<table>
		<thead>
			<tr>
				<td>商家名稱</td>
				<td>檢舉房型</td>
				<td>檢舉原因</td>
				<td>檢舉時間</td>
				<td>狀態</td>
				<td>結果</td>
				<td>刪除</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="repCmpVO" items="${list}">
				<tr>
					<td>${repCmpVO.getCmpName()}</td>
					<td>${repCmpVO.getRoomtypeName()}</td>
					<td>${repCmpVO.reason}</td>
					<td>${repCmpVO.repCmpDate}</td>
					<td>${repCmpVO.status}</td>
					<td>${repCmpVO.result}</td>
					<td>
						<form method="post" action="cust/DeleteRepCmp">
							<input type="submit" value="刪除"
								${(repCmpVO.status=='未處理')?'':'disabled'}> <input
								type="hidden" value="${repCmpVO.repCmpId}" name="repCmpId">
							<input type="hidden" value="${repCmpVO.custId}" name="custId">
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div> 
	 -->
</body>
</html>