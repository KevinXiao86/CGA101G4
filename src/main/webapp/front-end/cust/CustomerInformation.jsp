<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

	<main id="main" class="main" style="padding-left:70px;height:1200px;padding-top:40px;">

    <div class="pagetitle">
    	<h1>基本資料</h1>
    		<nav>
        		<ol class="breadcrumb">
          			<li class="breadcrumb-item">首頁</li>
          			<li class="breadcrumb-item">會員功能</li>
          			<li class="breadcrumb-item active">基本資料</li>
        		</ol>
      		</nav>
    </div><!-- End Page Title -->

    <section class="section profile" style="position:static;">
    	<div class="row">
        	<div class="col-xl-4" style="position:static;">

          		<div class="card">
            		<div class="card-body profile-card pt-4 d-flex flex-column align-items-center">

              			<img src="${customer.img}" alt="Profile" class="rounded-circle">
              			<h2>${customer.name}</h2>
              			<h3>${customer.account}</h3>
            		</div>
          		</div>

        	</div>

        	<div class="col-xl-8" style="position:static;">

          		<div class="card">
            		<div class="card-body pt-3">
              			<!-- Bordered Tabs -->
              			<ul class="nav nav-tabs nav-tabs-bordered">

               	 			<li class="nav-item">
                  				<button class="nav-link active" data-bs-toggle="tab" data-bs-target="#profile-overview" id="overview">查看基本資料</button>
                			</li>

                			<li class="nav-item">
                  				<button class="nav-link" data-bs-toggle="tab" data-bs-target="#profile-edit" id="edit">修改基本資料</button>
                			</li>

              			</ul>
              			
              			<div class="tab-content pt-2">

                			<div class="tab-pane fade show active profile-overview" id="profile-overview">

                  				<h5 class="card-title">基本資料細項</h5>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">姓名</div>
                    				<div class="col-lg-9 col-md-8">${customer.name}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">性別</div>
                    				<div class="col-lg-9 col-md-8">${(customer.sex=='f')?'女':'男'}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">電話</div>
                    				<div class="col-lg-9 col-md-8">${customer.tel}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">電子信箱</div>
                    				<div class="col-lg-9 col-md-8">${customer.email}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">聯絡地址</div>
                    				<div class="col-lg-9 col-md-8">${(customer.address==null)?'您尚未輸入聯絡地址':customer.address}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">身分證字號</div>
                    				<div class="col-lg-9 col-md-8">${customer.idCard}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">生日</div>
                    				<div class="col-lg-9 col-md-8">${customer.birth}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">帳號</div>
                    				<div class="col-lg-9 col-md-8">${customer.account}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">密碼</div>
                    				<div class="col-lg-9 col-md-8">${customer.password}</div>
                  				</div>

                  				<div class="row">
                    				<div class="col-lg-3 col-md-4 label">信用卡卡號</div>
                    				<div class="col-lg-9 col-md-8">${(customer.card==null)?'您尚未輸入信用卡卡號':customer.card}</div>
                  				</div>
                			</div>

                			<div class="tab-pane fade profile-edit pt-3" id="profile-edit">

				                  <!-- Profile Edit Form -->
				                  <form action="cust/SubmitCustomerInformation" method="post" enctype="multipart/form-data">
				                  	<div class="row mb-3">
                      					<label for="profileImage" class="col-md-4 col-lg-3 col-form-label">圖片</label>
                      					<div class="col-md-8 col-lg-9">
                        					<img src="${param.imgOrigin}" id="showNew" alt="Profile" style="max-height:200px;">
                        					<div id="showNewName"></div>
					                         <!--  imgOrigin代表原本的圖片，沒有要修改的 -->
							 				<input type="hidden" name="imgOrigin" value="${param.imgOrigin}">
					                        <div class="pt-2">
						                        <!-- imgUpdate代表要上傳的圖片 -->
						                        <label for="imgUpdate" style="font-size:0px;display:inline-block;margin-top:0px;"><a class="btn btn-primary btn-sm" title="Upload new profile image"><i class="bi bi-upload" style="color:#fff;"></i></a></label>
						                        <input type="file" id="imgUpdate" name="imgUpdate" style="font-size:10px;display:none;">
                        					</div>
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="name" class="col-md-4 col-lg-3 col-form-label">姓名</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="name" type="text" class="form-control" id="name" value="${param.name}">${errorMsgs.name}
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="sex" class="col-md-4 col-lg-3 col-form-label">性別</label>
                      					<div class="col-md-8 col-lg-9">
                        					<select name="sex" class="form-control" id="sex">
                        						<option value="f" ${(param.sex=='f')?'selected':''}>女</option>
												<option value="m" ${(param.sex=='m')?'selected':''}>男</option>
                        					</select>
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="tel" class="col-md-4 col-lg-3 col-form-label">電話</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="tel" type="text" class="form-control" id="tel" value="${param.tel}">${errorMsgs.tel}
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="email" class="col-md-4 col-lg-3 col-form-label">電子信箱</label>
	                     				<div class="col-md-8 col-lg-9">
	                        				<input name="email" type="text" class="form-control" id="email" value="${param.email}">${errorMsgs.email}
	                      				</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="address" class="col-md-4 col-lg-3 col-form-label">聯絡地址</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="address" type="text" class="form-control" id="address" value="${param.address}">
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="idCard" class="col-md-4 col-lg-3 col-form-label">身分證字號</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="idCard" type="text" class="form-control" id="idCard" value="${param.idCard}">${errorMsgs.idCard}
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="birth" class="col-md-4 col-lg-3 col-form-label">生日</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="birth" type="date" class="form-control" id="birth" value="${param.birth}">${errorMsgs.birth}
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="account" class="col-md-4 col-lg-3 col-form-label">帳號</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="account" type="text" class="form-control" id="account" value="${param.account}">${errorMsgs.account}
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="password" class="col-md-4 col-lg-3 col-form-label">密碼</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="password" type="text" class="form-control" id="password" value="${param.password}">${errorMsgs.password}
                      					</div>
                    				</div>

                    				<div class="row mb-3">
                      					<label for="card" class="col-md-4 col-lg-3 col-form-label">信用卡卡號</label>
                      					<div class="col-md-8 col-lg-9">
                        					<input name="card" type="text" class="form-control" id="card" value="${param.card}">
                      					</div>
                    				</div>

                    				<div class="text-center">
                      					<button type="submit" class="btn btn-primary">修改完成</button>
                    				</div>

                    				<input type="hidden" name="custId" value="${param.custId}">

                  				</form><!-- End Profile Edit Form -->

                			</div>

              			</div><!-- End Bordered Tabs -->

            		</div>
          		</div>

        	</div>
      	</div>
    </section>

  </main><!-- End #main -->
  
  <%@ include file="/front-end/homepage/footer.jsp" %>
	<script>
		let imgUpdate=document.querySelector('#imgUpdate');
		imgUpdate.onchange=function(){	
			let url=URL.createObjectURL(imgUpdate.files[0]);
			document.querySelector('#showNew').src=url;
			document.querySelector('#showNewName').textContent=imgUpdate.files[0].name;
		};
	</script>
	<script>
    	if(${(errorMsgs==null)||(errorMsgs.isEmpty())}){
        	console.log('null||{}');
        }else{
        	console.log('有錯');
            document.querySelector('#overview').classList.remove('active');
            document.querySelector('#edit').classList.add('active');
            document.querySelector('#profile-overview').classList.remove('show');
            document.querySelector('#profile-overview').classList.remove('active');
            document.querySelector('#profile-edit').classList.add('show');
            document.querySelector('#profile-edit').classList.add('active');
        }
	</script>	
	
</body>

</html>