<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>撰寫一張優惠券</title>
	<style>
        #page-wrapper {
            background-color: rgb(221, 221, 241) !important;
            height: 600px; 
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
            top: 35%;
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
                        <h1 class="page-header">新增一筆優惠券</h1>
                    </div>
                </div>

                <c:if test="${not empty errorMsgs}">
                    <font style="color: red">請修正以下錯誤:</font>
                    <ul>
                        <c:forEach var="message" items="${errorMsgs}">
                            <li style="color: red">${message.value}</li>
                        </c:forEach>
                    </ul>
                </c:if>
                <div id="coupon_form">
                    <form action="coupon/couponCreator" method="post" enctype="multipart/form-data">
                        <label>優惠券名稱 :</label><input type="text" name="copName" autofocus placeholder="請輸入優惠券名稱"
                            value="${param.copName}"><br>
                        <label>優惠券折價金額:</label><input type="number" name="discount" step="10" min="0"
                            placeholder="請輸入折價金額" value="${param.discount}"><br>
                        <label>優惠券開始日期:</label><input id="startdate" name="startdate" type="datetime-local"
                            value="${param.startdate}"><br>
                        <label>優惠券結束日期:</label><input id="enddate" name="enddate" type="datetime-local"
                            value="${param.enddate}"><br>
                        <label>優惠券介紹:</label><br>
                        <textarea name="introduce" id="content" cols="100" rows="10" style="resize: none;"
                            placeholder="請輸入介紹">${param.introduce}</textarea><br>
                        <label>請輸入照片</label> <input id="file1" type="file" name="uploadFile" accept="image/*"
                            required><br>
                        <input type="submit" value="提交">
                        <div id="img_div">
                            <img id="look_img" src="" alt="還未選擇上傳圖片">
                        </div>

                    </form>
                    <div id="back_index">
                        <a href='back-end/coupon/cop_index.jsp'>回到優惠券管理首頁</a>
                    </div>
                </div>

            </div>
        </div>
    
    <script type="text/javascript">
        document.querySelector('#file1').addEventListener('change', e => {
            const url = URL.createObjectURL(file1.files[0]);
            let img = document.querySelector('#look_img');
            img.src = url;
        })

//         $.datetimepicker.setLocale('zh'); // kr ko ja en
//         $(function () {
//             $('#startdate').datetimepicker({
//                 format: 'yyyy-mm-ddThh:mm',
//                 onShow: function () {
//                     this.setOptions({
//                         maxDate: $('#enddate').val() ? $('#enddate').val() : false
//                     })
//                 },
//                 timepicker: false
//             });

//             $('#enddate').datetimepicker({
//                 format: 'yyyy-mm-ddThh:mm',
//                 onShow: function () {
//                     this.setOptions({
//                         minDate: $('#startdate').val() ? $('#startdate').val() : false
//                     })
//                 },
//                 timepicker: false
//             });
//         });
    </script>
</body>
</html>