<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
button:hover{
background-color:#ccc;
}
button:active{
background-color:#f0f0f0;
}
table{
width:700px;
border-collapse:collapse;
}
table tr{
border-bottom:1px solid black;
}
table td{
padding:5px 15px;
}
tbody tr:hover{
box-shadow:0 2px 4px black;
}</style>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<div style="display: inline-block; border: 2px solid blue; vertical-align: middle">
		<form method="post" action="cust/CustomerInformation">
			<input type="submit" value="會員基本資料"> 
			<input type="hidden" name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowFollow">
			<input type="submit" value="關注廠商"> 
			<input type="hidden" name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowCustCoupon">
			<input type="submit" value="管理優惠劵">
		</form>

		<form method="post" action="cust/showRepCmp">
			<input type="submit" value="瀏覽檢舉">
			<input type="hidden" name="customer" value="${customer.custId}">
		</form>
		<form method="post" action="cust/ShowEmail">
			<input type="submit" value="訊息管理">
		</form>
		<form method="post">
			<input type="submit" value="瀏覽訂單">
		</form>
	</div>
	
	<div style="display: inline-block; vertical-align: middle">
		<form method="post" action="cust/ShowEmail">
			<input type="submit" value="與台玩聯繫">
		</form>
		
		<form method="post" action="cust/ShowRoomMail">
			<input type="submit" value="住宿信箱">
		</form>
		
		<c:if test="${!list.isEmpty()}">	
			<table>
				<thead>
					<tr>	
						<th>寄件人</th>
						<th>訊息</th>	
						<th>收件人</th>
						<th>發送時間</th>
					</tr>	
				</thead>
				<tbody>
				<c:forEach var="roomMail" items="${list}">
					<tr>
						<td class="who">${(roomMail.roomMailIdWho>=20000)?roomMail.getCmpNameById():customer.account}</td>
						<td class="msg">${roomMail.roomMailMsg}</td>
						<td class="receive">${(roomMail.roomMailIdWho>=20000)?customer.account:roomMail.getCmpNameById()}</td>
						<td class="time">${roomMail.roomMailTime}</td>
						<td class="hiddenWho" style="display:none;">${roomMail.roomMailIdWho}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</c:if>
	
		<c:if test="${list.isEmpty()}">
			<h1>您的信箱是空的</h1>
		</c:if>
	</div>
		<div id="pop">	
	</div>
	<button id="plus" style="position:fixed;top:25%;right:20%;width:50px;height:50px;border-radius:100%;padding:0px;">➕</button>
	
	<script>
	//把所有的tr抓起來用迴圈幫每個tr註冊事件監聽器，當click每一個tr時都會跳出自己的信件內容小視窗
	let tr=document.querySelectorAll('tr');
	for(let item of tr){
		console.log('into for loop');
		item.onclick=function(){		
			let who=item.querySelector('.who').textContent;
			let msg=item.querySelector('.msg').textContent;
			let receive=item.querySelector('.receive').textContent;
			let time=item.querySelector('.time').textContent;
			let hiddenWho=parseInt(item.querySelector('.hiddenWho').textContent);
			let fixedDiv=document.createElement('div');
			fixedDiv.style.position="fixed";
			fixedDiv.style.top="25%";
			fixedDiv.style.left="20%";
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
			if(hiddenWho>=20000){
			exX.innerHTML=`<div id="all" style="width: 600px;height:350px;margin: 15px 15px;background-color: #ffc;padding:20px;overflow:auto;box-shadow:2px 2px 4px black,-2px -2px 4px black;" >
						        <div class="post-a-comment-area mt-70" style="margin-top:10px;">					            
					                寄件人<span style="padding:5px;font-size:20px"><% out.print("${who}"); %></span>  <span style="float:right"><% out.print("${time}"); %></span>
					                <pre style="margin:20px auto;border:1px solid blue;width: 80%;height:200px;padding:10px;overflow:auto;background-color:#ffc;"><% out.print("${msg}"); %></pre>           
					            
						            <h5 style="margin-bottom:20px;">回復</h5>
						            <!-- Reply Form -->
						            <form action="cust/ReplyRoomMail" method="post">
						                <div class="row">
						                    <div class="col-12">
						                        <div class="group">
						                            <textarea style="border:1px solid black" name="msg" id="message" required></textarea>
						                            <span class="highlight"></span>
						                            <span class="bar"></span>
						                            <label>輸入關於訂房的問題</label>
						                        </div>
						                    </div>
						                    <div class="col-12">
						                        <button type="submit" id="id1" class="btn original-btn" style="margin-bottom:20px;box-shadow:2px 2px 10px #000;font-size:20px">發送訊息</button>
						                    </div>
						                </div>
						                <input type="hidden" name="hiddenWho" value="<% out.print("${hiddenWho}"); %>">
						            </form>
						        </div>
						    </div>`;
	    }else{
	    	exX.innerHTML=`<div style="width:500px;height:300px;border:1px solid red;padding:20px;margin:10px;background-color:lightYellow;box-shadow:2px 2px 4px black;">
								寄件人<span style="padding:5px;font-size:20px"><% out.print("${who}"); %></span> 收件人<span style="padding:5px;font-size:20px"><% out.print("${receive}"); %></span>  <span style="float:right"><% out.print("${time}"); %></span>
								<pre style="margin:20px;border:1px solid blue;width:450px;height:200px;padding:10px;overflow:auto;background-color:lightYellow;"><% out.print("${msg}"); %></pre>			
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
	
	//抓到➕按鈕然後註冊監聽器，click的時候可以跳出寫信的視窗
	document.querySelector('#plus').onclick=function(){
		let fixedDiv=document.createElement('div');
		fixedDiv.style.position='fixed';
		fixedDiv.style.top='30%';
		fixedDiv.style.left='25%';
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
		exX.innerHTML= `<div id="all" style="width:500px;margin:10px;background-color: #ffc;padding:20px;box-shadow:2px 2px 4px black">
       						<div class="post-a-comment-area mt-70">
       						<!-- Reply Form -->
       	 						<form action="cust/SendRoomMail" method="post">
        							<span style="display:inline-block;margin-bottom:20px;font-weight: bold;">收件人</span>
        						
        							<select name="cmpId" style="height:30px;">
	        						<c:forEach var="cmp" items="${companyList}">
	        							<option value="${cmp.cmpId}">${cmp.cmpName}</option>
	        						</c:forEach>
	        						</select>
        						
            						<div class="row">
										<div class="col-12">
                    						<div class="group">
                        						<textarea style="border:1px solid black" name="msg"	id="message" required></textarea>
                        						<span class="highlight"></span>
                        						<span class="bar"></span>
                        						<label>輸入住宿相關問題</label>
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