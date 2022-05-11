<%@page import="com.taiwan.beans.CouponVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
CouponVO couponVO = (CouponVO) request.getAttribute("couponVO");
%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>

<meta charset="UTF-8">
<title>優惠券資料修改</title>
<style>
        #page-wrapper {
/*             background-color: rgb(221, 221, 241) !important; */
/*             height: 600px;  */
        }

        label {
            font-size: 16px;
        }

        img {
            /* border: 1px solid black; */
            height: 200px;
            width: 200px;
        }

        #img_div {
            position: absolute;
            top: 40%;
            right: 10%;
        }

        #img_Odiv {
            position: absolute;
            top: -10%;
            right: 10%;
        }

        input[type="text"] {
            width: 220px;
        }

        input[type="number"] {
            width: 190px;
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

        #coupon_form {
            position: relative;
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
    </style>
</head>
<body>


	<div id="page-wrapper">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">優惠券修改</h1>
                    </div>
                </div>
				<!-- 錯誤表列 -->
                <c:if test="${not empty errorMsgs}">
                    <font style="color: red">請修正以下錯誤:</font>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li style="color: red">${message}</li>
                        </c:forEach>
                    </ul>
                </c:if>
                <div id="coupon_form">
                    <form action="coupon/couponUpdate" method="post" enctype="multipart/form-data">
                        <label>優惠券編號</label><input type="text" name="copId" value="${couponVO.copId}"><br> 
						<label>優惠券名稱</label><input type="text" name="copName" autofocus placeholder="請輸入優惠券名稱" value="${couponVO.copName}"><br> 
						<label>優惠券折價金額</label><input type="number" name="discount" step="10" min="0"  placeholder="請輸入折價金額" value="${couponVO.discount}"><br>
						<label>優惠券開始日期</label><input id="startdate" name="startdate" type="datetime-local" value="<fmt:formatDate value="${couponVO.startdate}" pattern="yyyy-MM-dd'T'HH:mm"/>"><br> 
						<label>優惠券結束日期</label><input id="enddate" name="enddate" type="datetime-local" value="<fmt:formatDate value="${couponVO.enddate}" pattern="yyyy-MM-dd'T'HH:mm"/>"><br> 
						<label>優惠券介紹</label><br>
						<textarea name="introduce" id="content" cols="100" rows="10" style="resize: none;" placeholder="請輸入介紹"  >${couponVO.introduce}</textarea><br>
                    	<input type="hidden" name="img" value="${couponVO.img}">
                    	<label>請輸入照片</label> <input type="file" name="uploadFile" accept="image/*"><br>
                    <div id="img_Odiv">
                        <img src="${couponVO.img}" />
                    </div>
                    <div id="img_div">
                        <img id="look_img" src="">
                    </div>
                    <input type="submit" value="提交">
                    </form>
                </div>

                <div id="back_index">
                    <a href='back-end/coupon/cop_index.jsp'>回到優惠券首頁</a>
                </div>
            </div>
        </div>


        <script type="text/javascript">
            document.querySelector('#file1').addEventListener('change', e => {
                const url = URL.createObjectURL(file1.files[0]);
                let img = document.querySelector('#look_img');
                img.src = url;
            })
        </script>


</body>
</html>