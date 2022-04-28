<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>FAQ creator</title>
</head>
<body>
	<div id="select_div">
		<form action="faq/faqCreator" method="post">
			<div id="div1">
				<span id="span1">FAQ標題</span><input id="input_title" type="text"
					name="title" placeholder="請輸入標題" value="${param.title}">${errorMsgs.title}<br>
			</div>
			<div id="div4">
				<h4>FAQ內文</h4>
			</div>
			${errorMsgs.content}
			<div id="div2">
				<textarea name="content" id="content" cols="100" rows="10"
					style="resize: none;" placeholder="請輸入內文">${param.content}</textarea>
			</div>
			<div id="div3">
				<span id="num_content">剩餘可輸入500字</span>
			</div>
			<input type="submit" value="提交">
		</form>
	</div>
	<div>
		<a href='back-end/faq/faq_index.jsp'>回FAQ首頁</a>
	</div>

	<script>

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