<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

		#page-wrapper {
		    background-color: #ced7e8 !important;
		    height: 1000px; 
		}
		#custInfo{
			position:relative;
			padding-right: 50px;
		}
		
		label{
			margin-left: 10px;
		}
		#back_index {
            position: fixed;
            right: 10%;
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
        #pre{
        	margin-left: 30%;
        }
        
         img{ 
         	width: 200px; 
         	height: 200px; 
        } 
        
        #img_div{
			position:absolute;
			right: 50%;
			top :10%;
		}
		input[type="text"]{
			margin-left: 50px;		
		
		}

</style>

</head>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">會員資料</h1>
				</div>
			</div>
				<div id="custInfo">
					<div class="form-group">
						<label for="cmpName">會員編號:</label><br> 
						<input  type="text" id="cmpName" name="cmpName" readonly="readonly" value="${customer.custId}" />
					</div>
					<div class="form-group">
						<label for="cmpName">會員姓名:</label><br>
						<input  type="text" id="cmpName" name="cmpName" readonly="readonly" value="${customer.name}" />
					</div>
					<div class="form-group">
						<label for="cmpName">會員電話:</label> <br>
						<input  type="text" id="cmpName" name="cmpName" readonly="readonly" value="${customer.tel}" />
					</div>
					<div class="form-group">
						<label for="cmpName">會員電子信箱:</label> <br>
						<input  type="text" id="cmpName" name="cmpName" readonly="readonly" value="${customer.email}" />
					</div>
					<div class="form-group">
						<label for="cmpName">聯絡地址:</label> <br>
						<input  type="text" id="cmpName" name="cmpName" readonly="readonly" value="${customer.address}" />
					</div>
					<div class="form-group">
						<label for="cmpName">身分證字號:</label> <br>
						<input  type="text" id="cmpName" name="cmpName" readonly="readonly" value="${customer.idCard}" />
					</div>
					<div class="form-group">
						<label for="cmpName">帳號:</label> <br>
						<input  type="text" id="cmpName" name="cmpName" readonly="readonly" value="${customer.account}" />
					</div>
					<div id="img_div">
						<img id="img" alt="" src="${customer.img}"/><br>
					</div>
						<button id="pre">上一頁</button>
					</div>
				</div>
			</div>




	

	<script>
        const button1=document.querySelector('#pre');
        button1.addEventListener('click',e=>{history.back();});
    </script>

</body>
</html>