<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>news creator</title>
<style type="text/css">
#page-wrapper {
	background-color: #ced7e8 !important;
	height: 1000px
}

input[type="submit"] {
	margin-bottom: 10px;
}

input[type="submit"] {
	box-shadow: inset 0px 1px 0px 0px #ffffff;
	background: linear-gradient(to bottom, #ffffff 5%, #f6f6f6 100%);
	background-color: #ffffff;
	border-radius: 6px;
	border: 1px solid #dcdcdc;
	display: inline-block;
	cursor: pointer;
	color: #666666;
	font-family: Arial;
	font-size: 15px;
	font-weight: bold;
	padding: 6px 24px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #ffffff;
}

input[type="submit"]:hover {
	background: linear-gradient(to bottom, #f6f6f6 5%, #ffffff 100%);
}

#select_div {
	position: relative;
}

#back_index {
	position: fixed;
	right: 5%;
	bottom: 10%;
}

#back_index a {
	font-size: 20px;
	color: blue;
}

#back_index a:hover {
	color: red;
	text-decoration: none;
}

img {
	/*             border: 1px solid black;   */
	height: 200px;
	width: 200px;
}

#img_div {
	position: absolute;
	top: 20%;
	right: 10%;
}
</style>
</head>
<body>
<body>
	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">新增最新消息</h1>
				</div>
			</div>

			<div id="select_div">
				<form action="news/newsCreator" method="post" enctype="multipart/form-data">
					<div id="div1">
						<span id="span1">最新消息標題</span>
						<input id="input_title" type="text"	name="title" placeholder="請輸入標題" value="${param.title}">${errorMsgs.title}<br>
					</div>
					<div id="div4">
						<h4>最新消息內文</h4>
					</div>
					${errorMsgs.content}
					<div id="div2">
						<textarea name="content" id="content" cols="100" rows="10" style="resize: none;" placeholder="請輸入內文">${param.content}</textarea>
					</div>
					<div id="div3">
						<span id="num_content">剩餘可輸入500字</span>
					</div>
					<div id="div5">
						${errorMsgs.updateFile} 
						<input name="updateFile" id="file1"	type="file" accept=“image/*>
					</div>
					<div id="img_div">
						<img id="look_img" src="">
					</div>
					<input type="submit" value="提交">
				</form>

				<div id="back_index">
					<a href='back-end/theme/theme_index.jsp'>回熱門活動首頁</a>
				</div>
			</div>
		</div>
	</div>






	<script>
        document.querySelector('#file1').addEventListener('change',e=>{
            const url = URL.createObjectURL(file1.files[0]);
            let img=document.querySelector('#look_img');
            img.src=url;
            
        })
        $(function(){
        $('#content').on('keyup',function(){
            var txtval = $('#content').val().length;
            console.log(txtval);
            var str = parseInt(500-txtval);
            console.log(str);
                if(str > 0 ){
                $('#num_content').html('剩餘可輸入'+str+'字');
            }else{
                $('#num_content').html('剩餘可輸入0字');
                $('#content').val($('#content').val().substring(0,500)); //這裡意思是當裡面的文字小於等於0的時候，那麼字數不能再增加，只能是600個字
                }
            });
        })
        
    </script>

</body>
</html>