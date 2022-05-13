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
	
<style>
#xButton{
border:1px solid black;
background-color:#999;
}
#xButton:hover{
background-color:#ccc;
}
button:hover{
background-color:#ccc;
}
#xButton:active{
background-color:#f0f0f0;
}
button:active{
background-color:#f0f0f0;
}
table{
margin:0 auto;
max-width:800px;
border-collapse:collapse;
}
table tr{
border-bottom:1px solid black;
height:10px;
}
table td{
padding:8px 15px;
}
tbody tr:hover{
box-shadow:0 2px 4px black;
}
</style>	
</head>
<body>
	<%@ include file="/front-end/homepage/header.jsp" %>
	<%@ include file="/front-end/cust/side-bar.jsp" %>		
	
<!-- 		<form method="post" action="cust/ShowEmail"> -->
<!-- 			<input type="submit" value="與台玩聯繫"> -->
<!-- 		</form> -->
		
<!-- 		<form method="post" action="cust/ShowRoomMail"> -->
<!-- 			<input type="submit" value="住宿信箱"> -->
<!-- 		</form> -->
	<main id="main" class="main" style="padding-left:70px;padding-top:40px;overflow:auto;">
		
	    <div class="pagetitle">
	      <h1>與平台聯絡</h1>
	      <nav>
	        <ol class="breadcrumb">
	          <li class="breadcrumb-item">首頁</li>
	          <li class="breadcrumb-item">會員功能</li>
	          <li class="breadcrumb-item">訊息管理</li>
	          <li class="breadcrumb-item active">與平台聯絡</li>
	        </ol>
	      </nav>
	    </div><!-- End Page Title -->
	    
	    <section class="section">
	      <div class="row">
	          <div class="card">
	            <div class="card-body">	
	            	<button id="plus" style="float:right;margin-top:10px;margin-right:20px;width:50px;height:50px;border-radius:100%;padding:0px;">➕</button>
	            	<h5 class="card-title" style="border-bottom:1px solid #ccc;padding-bottom:30px;margin-bottom:20px;">信箱</h5>
	            	
				<c:if test="${!list.isEmpty()}">
					<table>						
						<tbody>
						<c:forEach var="custPlatMail" items="${list}">
							<tr>
								<td class="who" style="font-weight:bolder;">${(custPlatMail.who>=30000)?'台玩':customer.account}</td>
								<td class="msg" style="max-width:350px;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;">${custPlatMail.msg}</td>
								<td class="time" style="max-width:200px;">${custPlatMail.custPlatTime}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>		
				</c:if>
				
				<c:if test="${list.isEmpty()}">
					<h1>您尚未與台玩有任何聯繫</h1>
				</c:if>
				
		    	</div>
            </div>

        </div>
      </section>
	</main><!-- End #main -->
	
	  <%@ include file="/front-end/homepage/footer.jsp" %>
	  
	<div id="pop">	
	</div>	
	
	<script>
	//把所有的tr抓起來用迴圈幫每個tr註冊事件監聽器，當click每一個tr時都會跳出自己的信件內容小視窗
	let tr=document.querySelectorAll('tr');
	for(let item of tr){
		console.log('into for loop');
		item.onclick=function(){		
			let who=item.querySelector('.who').textContent;
			let msg=item.querySelector('.msg').textContent;
			let time=item.querySelector('.time').textContent;
			let fixedDiv=document.createElement('div');
			fixedDiv.style.position="fixed";
			fixedDiv.style.top="35%";
			fixedDiv.style.left="40%";
			//創造一個X按鈕可以讓會員把小視窗關掉
			let xButton=document.createElement('button');
			xButton.id='xButton';
			xButton.textContent='X';
			xButton.style.height='30px';
			xButton.style.width='30px';
			xButton.style.borderRadius='100%';
			xButton.style.float='right';
			//把按鈕放進fixedDiv裡面
			fixedDiv.append(xButton);
			//創造要擺訊息內容的div(因為是在fixedDiv裡面，但是不包括X按鈕，所以叫exX)
			let exX=document.createElement('div');
			exX.innerHTML=`<div style="width:500px;height:300px;border:1px  red;padding:20px;margin:10px;background-color:lightYellow;box-shadow:2px 2px 4px black;">
								寄件人<span style="padding:5px;font-size:20px"><% out.print("${who}"); %></span>  <span style="float:right"><% out.print("${time}"); %></span>
								<pre style="margin:20px 5px;border:1px  blue;width:450px;height:200px;padding:10px;overflow:auto;background-color:#fffff3;border-radius:20px;white-space:pre-wrap;"><% out.print("${msg}"); %></pre>			
						   </div>`;
			//把exX放到fixedDiv裡面
			fixedDiv.append(exX);
			//把fixedDiv放到原本就存在的#pop裡面，在頁面上呈現出來
			document.querySelector('#pop').textContent='';
			document.querySelector('#pop').append(fixedDiv);
			//幫X按鈕註冊監聽器，讓click時可以把小視窗關閉
			xButton.onclick=function(){
				document.querySelector('#pop').textContent='';
			};
		};
	}
	
	//抓到➕按鈕然後註冊監聽器，click的時候可以跳出寫信的視窗
	document.querySelector('#plus').onclick=function(){
		let fixedDiv=document.createElement('div');
		fixedDiv.style.position='fixed';
		fixedDiv.style.top='30%';
		fixedDiv.style.left='35%';
		//創造一個X按鈕可以讓會員把小視窗關掉
		let xButton=document.createElement('button');
		xButton.id='xButton';
		xButton.textContent='X';
		xButton.style.height='30px';
		xButton.style.width='30px';
		xButton.style.borderRadius='100%';
		xButton.style.float='right';
		//把按鈕放進fixedDiv裡面
		fixedDiv.append(xButton);
		//創造要擺寫信的form表單的div(因為是在fixedDiv裡面，但是不包括X按鈕，所以叫exX)
		let exX=document.createElement('div');
		exX.innerHTML= `<div id="all" style="width:600px;height:360px;margin:15px;background-color: #ffc;padding:20px;box-shadow:2px 2px 4px black;overflow:auto;">
       						<div class="post-a-comment-area mt-70">
        						<h5 style="margin-bottom:20px;font-weight: bold;">與台玩聯絡</h5>
        						<!-- Reply Form -->
       	 						<form action="cust/SendCustPlatMail" method="post">
            						<div class="row">
										<div class="col-12">
                    						<div class="group">
                        						<textarea style="border:1px solid black" name="msg"	id="message" required></textarea>
                        						<span class="highlight"></span>
                        						<span class="bar"></span>
                        						<label>想跟台玩說的話</label>
                    						</div>
                						</div>
                						<div class="col-12">
                    						<button type="submit" id="id1" class="btn original-btn" style="margin-bottom:20px;box-shadow:2px 2px 10px #000;font-size:20px">發送訊息</button>
                						</div>
            						</div>
        						</form>
    						</div>
						</div>`;
		//把exX放到fixedDiv裡面
		fixedDiv.append(exX);
		//把fixedDiv放到原本就存在的#pop裡面，在頁面上呈現出來
		document.querySelector('#pop').textContent='';
		document.querySelector('#pop').append(fixedDiv);
		//幫X按鈕註冊監聽器，讓click時可以把小視窗關閉
		xButton.onclick=function(){
			document.querySelector('#pop').textContent='';
		};
	};
	</script>
</body>
</html>