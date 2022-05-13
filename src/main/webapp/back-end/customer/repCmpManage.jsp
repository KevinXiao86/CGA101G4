<%@page import="com.taiwan.dao.customer.impl.CustomerJNDIDAO"%>
<%@page import="com.taiwan.service.customer.impl.CustomerServiceImpl"%>
<%@page import="com.taiwan.beans.CustomerVO"%>
<%@page import="java.util.List"%>
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
<style>
/* Form CSS */
.group {
  position: relative;
  z-index: 1;
  margin-bottom: 30px;
}
.group input,
.group textarea {
  font-size: 12px;
  font-style: italic;
  padding: 10px;
  display: block;
  width: 100%;
  height: 35px;
  border: none;
  border-bottom: 2px solid;
  border-color: #f1f1f1;
}
.group textarea {
  min-height: 90px;
  min-width:100%;
  max-width:100%;
}
.group input:focus,
.group textarea:focus {
  outline: none;
  box-shadow: none;
}
.group label {
  color: #878787;
  font-style: italic;
  font-size: 12px;
  font-weight: normal;
  position: absolute;
  pointer-events: none;
  left: 5px;
  top: 10px;
  transition: 0.5s ease all;
}

.original-btn {
  position: relative;
  z-index: 1;
  padding: 0 30px;
  min-width: 175px;
  height: 60px;
  border: 1px solid;
  border-color: #dcdcdc;
  font-size: 14px;
  border-radius: 0;
  line-height: 59px;
  text-transform: uppercase;
  letter-spacing: 3px;
  font-family: "Open Sans", sans-serif;
  box-shadow: 0 0 30px rgba(0, 0, 0, 0.1);
  color: #000000;
}
.original-btn:hover,
.original-btn:focus {
  font-family: "Open Sans", sans-serif;
  color: #ffffff;
  border-color: #30336b;
  background-color: #30336b;
}
.group .highlight {
  position: absolute;
  height: 60%;
  width: 100px;
  top: 25%;
  left: 0;
  pointer-events: none;
  opacity: 0.5;
}
.group .bar {
  position: relative;
  display: block;
  width: 100%;
}
.group .bar:before,
.group .bar:after {
  content: "";
  height: 2px;
  width: 0;
  bottom: 0;
  position: absolute;
  background-color: #30336b;
  transition: 0.5s ease all;
}
.group .bar:before {
  left: 50%;
}
.group .bar:after {
  right: 50%;
}
input:focus ~ .bar:before,
textarea:focus ~ .bar:before,
input:focus ~ .bar:after,
textarea:focus ~ .bar:after {
  width: 50%;
}
input:focus ~ label,
textarea:focus ~ label,
input:valid ~ label,
textarea:valid ~ label {
  top: -15px;
  font-size: 12px;
  color: #30336b;
}  
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

		<%@ include file="/common/back-end-index-bar.jsp"%>
		<!-- Page Content -->
		<div id="page-wrapper">
			<div class="container-fluid">

				<div class="row">
					<div class="col-lg-12" style="margin-top: 40px;">
						<table border="2px" class="table table-striped table-hover">
							<thead>
								<tr>
									<td>會員編號</td>
									<td>商家名稱</td>
									<td>檢舉房型</td>
									<td>管理員編號</td>
									<td>檢舉原因</td>
									<td>檢舉時間</td>
									<td>狀態</td>
									<td>結果</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="repCmpVO" items="${list}">
									<tr>
									
										<td class="custId">${repCmpVO.custId}</td>
										<td class="cmpName">${repCmpVO.getCmpName()}</td>
										<td class="roomTypeName">${repCmpVO.getRoomtypeName()}</td>
										<td class="empId">${(repCmpVO.empId==0)?'尚未有管理員處理':repCmpVO.empId}</td>
										<td class="reason">${repCmpVO.reason}</td>
										<td class="date">${repCmpVO.repCmpDate}</td>
										<td class="status">${repCmpVO.status}</td>
										<td class="result">${repCmpVO.result}</td>
										<td class="repCmpId" style="display:none;">${repCmpVO.repCmpId}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div id="pop">	
						</div>
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
	<script>
	//把所有的tr抓起來用迴圈幫每個tr註冊事件監聽器，當click每一個tr時都會跳出自己的信件內容小視窗
	let tr=document.querySelectorAll('tr');
	for(let item of tr){
		console.log('into for loop');
		item.onclick=function(){		
			let custId=item.querySelector('.custId').textContent;
			let date=item.querySelector('.date').textContent;
			let cmpName=item.querySelector('.cmpName').textContent;
			let roomTypeName=item.querySelector('.roomTypeName').textContent;
			let empId=item.querySelector('.empId').textContent;
			let reason=item.querySelector('.reason').textContent;
			let result=item.querySelector('.result').textContent;
			let status=item.querySelector('.status').textContent;
			let repCmpId=item.querySelector('.repCmpId').textContent;
			let fixedDiv=document.createElement('div');
			fixedDiv.style.position="fixed";
			fixedDiv.style.top="20%";
			fixedDiv.style.left="25%";
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
			if(status!='未處理'){
				exX.innerHTML=`<div style="width:600px;max-height:450px;border:1px solid red;padding:20px;margin:15px 15px;background-color:lightYellow;box-shadow:2px 2px 4px black;overflow:auto;">
									檢舉人<span style="padding:5px;font-size:20px"><% out.print("${custId}"); %></span>   <span style="float:right"><% out.print("${date}"); %></span> <br>
									被檢舉房型<span style="padding:5px;font-size:20px"><% out.print("${cmpName}"); %></span><span style="padding:5px;font-size:20px"><% out.print("${roomTypeName}"); %></span> <br>
									管理員<span style="padding:5px;font-size:20px"><% out.print("${empId}"); %></span><br>
									狀態<span style="padding:5px;font-size:20px"><% out.print("${status}"); %></span><br>
									<div style="margin-top:20px;">檢舉原因</div>
									<pre style="margin:20px;border:1px solid blue;width:450px;padding:10px;overflow:auto;background-color:lightYellow;"><% out.print("${reason}"); %></pre>
									<div>審核結果</div>
									<pre style="margin:20px;border:1px solid blue;width:450px;padding:10px;overflow:auto;background-color:lightYellow;"><% out.print("${result}"); %></pre>
							   </div>`;
			}else{	
				exX.innerHTML=`<div id="all" style="width: 600px;height:350px;margin: 15px 15px;background-color: #ffc;padding:20px;overflow:auto;box-shadow:2px 2px 4px black,-2px -2px 4px black;" >
							        <div class="post-a-comment-area mt-70" style="margin-top:10px;">					            
								        檢舉人<span style="padding:5px;font-size:20px"><% out.print("${custId}"); %></span>   <span style="float:right"><% out.print("${date}"); %></span> <br>
										被檢舉房型<span style="padding:5px;font-size:20px"><% out.print("${cmpName}"); %></span><span style="padding:5px;font-size:20px"><% out.print("${roomTypeName}"); %></span> <br>
										<div style="margin-top:20px;">檢舉原因</div>
										<pre style="margin:20px;border:1px solid blue;width:450px;padding:10px;overflow:auto;background-color:lightYellow;"><% out.print("${reason}"); %></pre>           
						            
							            <h4 style="margin-bottom:20px;">處理檢舉</h4>
							            <!-- Reply Form -->
							            <form action="custManage/SetResult" method="post">
							                <div class="row" style="margin-left:10px;">
							                    <div class="col-12">
							                       	 	<span>狀態</span>
								                        <select name="status">
									                        <option>檢舉成功</option>
									                        <option>檢舉失敗</option>
								                        </select>
								                        <br>
								                        
								                        <div class="group" style="margin-top:20px;">							                           
								                        <textarea style="margin-top:0px;border:1px solid black" name="result" id="message" required></textarea>
								                        <span class="highlight"></span>
								                        <span class="bar"></span>
								                        <label>輸入檢舉審核結果</label>
							                        </div>
								               	</div>
							                	<div class="col-12">
							                    	<button type="submit" id="id1" class="btn original-btn" style="margin-bottom:20px;box-shadow:2px 2px 10px #000;font-size:20px">送出審核結果</button>
							                	</div>
							             	</div>
							             	<input type="hidden" name="repCmpId" value="<% out.print("${repCmpId}"); %>">
							        	</form>
							        </div>
							    </div>`;
	   		 }
		
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
	</script>
</body>
</html>