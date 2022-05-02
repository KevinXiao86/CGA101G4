<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="/common/head.jsp"%>
<meta charset="UTF-8">
<title>ticket creator</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
 <style>

        .box {
            width: 1000px;
            height: 120px;
            margin: 0 auto;
            border: 1px solid #ddd;
            margin-top: 20px;
            box-sizing: border-box;
            padding: 10px;
        }

        .upload {
            width: 200px;
            height: 200px;
            float: left;
            position: relative;
            overflow: hidden;
        }

        .upload input {
            position: absolute;
            z-index: 1000;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
        }

        .upload a {
            display: block;
            width: 100%;
        }

        .upload img {
            display: block;
            width: 100%;
            height: 100%;
        }

        .imgList {
            float: left;
            overflow: hidden;
        }

        .imgList .item {
            width: 100px;
            height: 100px;
            margin-right: 20px;
            float: left;
            position: relative;
        }

        .imgList .item img {
            display: block;
            width: 100%;
            height: 100%;
        }

        .imgList .item span {
            position: absolute;
            top: 0;
            right: 0;
            width: 100%;
            background: #99a5b7;
            color: #fff;
            height: 20px;
            width: 20px;
            text-align: center;
            border-radius: 50%;
            cursor: pointer;
        }
    </style>
</head>
<body>
	



	 <form action="ticket/ticketCreator" method="post" enctype="multipart/form-data">
        <span>票券名稱</span><input name="tktName" type="text" autofocus  placeholder="請輸入票券名稱" value="${param.tktName}">${errorMsgs.tktName}<br>
        <span>票券數量</span><input name="originalAmount" type="number" step="10" min="0" 
            placeholder="請輸入票券數量" value="${param.originalAmount}">${errorMsgs.originalAmount}<br>
        <span>票券價格</span><input name="price" type="number" step="10" min="0"  placeholder="請輸入票券價格" value="${param.price}">${errorMsgs.price}<br>
        <span>票券開始日期</span><input name="startdate" id="startdate" type="datetime-local" value="2022-01-01T08:00"><br>
        <span>票券結束日期</span><input name="enddate" id="enddate" type="datetime-local" value="2022-12-31T22:00">${errorMsgs.timeError}<br>
        <span>種類</span>
        <select name="kind" id="kind" >
            <option value="景點門票">景點門票</option>
            <option value="主題樂園">主題樂園</option>
            <option value="博物館展覽">博物館展覽</option>
            <option value="特色表演">特色表演</option>
        </select><br>
        <span>票券所在縣市</span>
        <div id="twzipcode">
        </div>
        <input type="text" name="address" placeholder="請輸入地址" style="width: 306px;" value="${param.address}">${errorMsgs.address}<br>
        <span>介紹</span><br>
        <textarea name="instruction" id="instruction" cols="100" rows="10" style="resize:none;" placeholder="請輸入票券介紹"
            >${param.instruction}</textarea>${errorMsgs.instruction}<br>
        <span>注意事項</span><br>
        <textarea name="notice" id="notice" cols="100" rows="10" style="resize:none;" placeholder="請輸入注意事項"
            ></textarea>${errorMsgs.notice}<br>
        <span>如何使用</span><br>
        <textarea name="howuse" id="howuse" cols="100" rows="10" style="resize:none;" placeholder="請輸入如何使用"
            >${param.hoeuse}</textarea>${errorMsgs.howuse}<br>
        <span>取消政策</span><br>
        <textarea name="canxpolicy" id="canxpolicy" cols="100" rows="10" style="resize:none;" placeholder="請輸入取消政策"
            >${param.canxpolicy}</textarea>${errorMsgs.canxpolicy}<br>
         	<div class="box">
        	<!-- 放圖片的位置 -->
        <div class="imgList" id="imgList"></div>
        	<!-- 上傳按鈕 -->
        	<div class="upload">
            	<input type="file" name="file" value="" multiple accept="image/*" onchange="uploadImg(this);">
            	<a href="javascript:void(0)" rel="external nofollow"><img src="" alt="">上傳圖片</a>
        	</div>
   		</div>       
        <input type="submit" value="提交">

    </form>
    <script>
        // $("#twzipcode").twzipcode();
        $("#twzipcode").twzipcode({
            countyName: "city", // 自訂城市 select 標籤的 name 值
            districtName: "town" // 自訂地區 select 標籤的 name 值
        });

       
        function uploadImg(obj) {
            //獲取上傳文件後的 fileList
            var files = obj.files;
            //聲明空陣列用來接收上傳完成後的圖片
            var imgList = [];
            for (var i = 0; i < files.length; i++) {
                //將文件轉換成 base64 URL格式
                var imgUrl = window.URL.createObjectURL(files[i]);

                //將 url 壓入到陣列中, 如果需要圖片統一選擇完畢後, 點擊上傳按鈕統一提交, 那麼直接拿這個 imgList 給後臺傳遞即可
                imgList.push(imgUrl);

                //循環創建 img 容器用來放置 url 在頁面上顯示
                var img = document.createElement('img')
                img.setAttribute("src", imgList[i]);

                //刪除按鈕
                var close = document.createElement('span')
                close.innerHTML = "x"
                close.className = 'close'
                close.setAttribute('onclick', "imgRemove(this)")

                //創建放置img的盒子
                var item = document.createElement('div');
                item.className = 'item';
                item.append(img)
                item.append(close)
                var box = document.getElementById("imgList");
                box.append(item);
                //ajax向后台发送请求
            }
        }
        //刪除
        function imgRemove(obj) {
            obj.parentNode.remove()
        }


    </script>

</body>
</html>