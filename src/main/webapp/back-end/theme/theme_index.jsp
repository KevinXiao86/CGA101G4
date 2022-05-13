<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>theme_index</title>
 <style>
        #page-wrapper {
            background-color: #ced7e8 !important;
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
                        <h1 class="page-header">熱門活動管理首頁</h1>
                    </div>
                </div>
                <div id="create_div">
                    <a id="create" href='back-end/theme/theme_create.jsp'>新增一筆熱門活動</a>
                </div>
                <div id="finAll_div">
                    <form action="theme/findAll" method="post">
                        <input type="submit" value="搜尋全部">
                    </form>
                </div>
                <%--錯誤列表 --%>
                    <c:if test="${not empty errorMsgs}">
                        <font style="color: red">請修正以下錯誤:</font>
                        <ul>
                            <c:forEach var="message" items="${errorMsgs}">
                                <li style="color: red">${message}</li>
                            </c:forEach>
                        </ul>
                    </c:if>

                <div>
                    <FORM METHOD="post" ACTION="theme/selectById">
                        <b>輸入熱門活動編號來做查詢:</b>
                        <input type="text" name="themeId"> <input type="submit" value="送出">
                    </FORM>
                </div>
                <div id="title_div">
                    <FORM METHOD="post" ACTION="theme/selectByTitle">
                        <b>輸入熱門活動標題來做查詢:</b> <input type="text" name="title"> 
                        <input type="submit" value="送出">
                    </FORM>
                </div>

            </div>
        </div>

</body>
</html>