<%@page import="com.taiwan.beans.News"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
News news=(News)request.getAttribute("news");
%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>最新消息資料修改</title>
<style type="text/css">
img{
	width: 150px;
	height: 150px;
}
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>
</head>
<body>
	<div id="select_div">
		<form action="news/newsUpdate" method="post" enctype="multipart/form-data">
			<div id="div1">
				<label>最新消息編號</label><input type="text" name="newsId" value="${news.newsId}" readonly="readonly" ><br>
				<span id="span1">最新消息標題</span><input id="input_title" type="text"
					name="title" placeholder="請輸入標題" value="${news.title}">${errorMsgs.title}<br>
			</div>
			<div id="div4">
				<h4>最新消息內文</h4>
			</div>
			${errorMsgs.content}
			<div id="div2">
				<textarea name="content" id="content" cols="100" rows="10"
					style="resize: none;" placeholder="請輸入內文">${news.content}</textarea>
			</div>
			<div id="div3">
				<span id="num_content">剩餘可輸入500字</span>
			</div>
			<div id="div5">
			<label>舊照片</label><br>
			<img src="${news.img}"/><br>
				${errorMsgs.updateFile}
				<input name="updateFile" id="file1" type="file" accept=“image/*>
				<img id="look_img" src="">
			</div>
			<input type="submit" value="提交">
		</form>
	</div>
	
	<div>
		<a href='back-end/news/news_index.jsp'>回最新消息首頁</a>
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