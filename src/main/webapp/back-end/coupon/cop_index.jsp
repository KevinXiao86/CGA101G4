<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%-- <%@ include file="/common/head.jsp"%> --%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        #page-wrapper {
            background-color: rgb(221, 221, 241) !important;
            height: 800px;  
        }

        #create_div {
            /* border: 1px solid blue; */
            margin-bottom: 10px;
        }

        #findAll_div {
            margin-bottom: 10px;
        }

        #create {
            font-size: 24px;
            color: blue;
            /* margin-left: 5%; */
        }

        #create:hover {
            color: red;
            text-decoration: none;
        }

        input[type="submit"] {
               cursor: pointer; 
			   background-color: lightskyblue; 
			   color: black; 
			   padding: 5px 5px; 
               text-decoration: none; 
               display: inline-block; 
               border-radius: 12px;
               border:none;
               border:1px solid black;
        }

        input[type="submit"]:hover {
            background: linear-gradient(to bottom, #378de5 5%, #79bbff 100%);

        }

        input[type="submit"]:active {
            position: relative;
            top: 1px;
        }

        select[name="status"] {
            height: 25px;
            border: 1px solid black;

        }


        b {
            font-size: 25px;
            vertical-align: middle;
        }

        #title_div {
            margin: 10px 0 10px 0;
            /* border: 1px solid black; */
        }
    </style>

</head>
<body>

	

	<div id="page-wrapper">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">優惠券管理頁面</h1>
                    </div>
                </div>


                <div id="create_div">
                    <a id="create" href='back-end/coupon/cop_create.jsp'>新增一筆優惠券</a>
                </div>
                <div id="findAll_div">
                    <form action="coupon/findAll" method="post">
                        <input type="submit" value="查詢全部">
                    </form>
                </div>


                <%--錯誤列表 --%>
                    <c:if test="${not empty errorMsgs}">
                        <font style="color: red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message.value}</li>
                            </c:forEach>
                        </ul>
                    </c:if>

                <div>
                    <FORM METHOD="post" ACTION="couponController/selectById">
                        <b>輸入優惠券編號來做查詢:</b> <input type="text" name="copId"> <input type="submit" value="送出">
                    </FORM>
                </div>
                <div id="title_div">
                    <FORM METHOD="post" ACTION="couponController/selectByTitle">
                        <b>輸入優惠券標題來做查詢:</b> <input type="text" name="copName"> <input type="submit" value="送出">
                    </FORM>
                </div>

                <div id="status_div">
                    <FORM METHOD="post" ACTION="couponController/selectByStatus">
                        <b>輸入優惠券狀態來做查詢:</b> 
                        <select name="status">
                            <option value="上架">上架</option>
                            <option value="下架">下架</option>
                            <option value="已過期">已過期</option>
                        </select> 
                        <input type="submit" value="送出">
                    </FORM>
                </div>


            </div>
        </div>





</body>
</html>