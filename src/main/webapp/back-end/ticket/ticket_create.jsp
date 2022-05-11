<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
<%@ include file="back-end-index.jsp"%>
<meta charset="UTF-8">
<title>ticket creator</title>

<script
	src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
<style>
		img {
			height: 150px;
			width: 150px;
		}
		#page-wrapper {
           background-color: #ced7e8 !important;
/*             height: 800px;  */
        }

        #tkt_form{
           position: relative;
        }

        #img_div{
            position: absolute;
            top: 10%;
            right: 5%;    
        }
</style>
</head>
<body>

	<div id="page-wrapper">
		<div class="container-fluid">

			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">新增票券</h1>
				</div>
			</div>
			 <div id="tkt_form">
                    <form action="ticket/ticketCreator" method="post"	enctype="multipart/form-data">
                        <span>票券名稱</span><input name="tktName" type="text" autofocus	placeholder="請輸入票券名稱" value="${param.tktName}">${errorMsgs.tktName}<br>
                        <span>票券數量</span><input name="originalAmount" type="number"	step="10" min="0" placeholder="請輸入票券數量" value="${(param.originalAmount == null)? 10000: param.originalAmount}">${errorMsgs.originalAmount}<br>
                        <span>票券價格</span><input name="price" type="number" step="10" min="0"	placeholder="請輸入票券價格" value="${param.price}">${errorMsgs.price}<br>
                        <span>票券開始日期</span><input name="startdate" id="startdate"	type="datetime-local" value="2022-01-01T08:00"><br> <span>票券結束日期</span><input
                            name="enddate" id="enddate" type="datetime-local" value="2022-12-31T22:00">${errorMsgs.timeError}<br> <span>種類</span>
                        <select name="kind" id="kind">
                            <option value="景點門票" ${(param.kind=="景點門票" )? "selected" : "" }>景點門票</option>
                            <option value="主題樂園" ${(param.kind=="主題樂園" )? "selected" : "" }>主題樂園</option>
                            <option value="博物館展覽" ${(param.kind=="博物館展覽" )? "selected" : "" }>博物館展覽</option>
                            <option value="特色表演" ${(param.kind=="特色表演" )? "selected" : "" }>特色表演</option>
                        </select><br> <span>票券所在縣市</span>
                        <div id="twzipcode"></div>
                        <input type="text" name="address" placeholder="請輸入地址" style="width: 306px;" value="${param.address}">${errorMsgs.address}<br>
                        <span>介紹</span><br>
                        <textarea name="instruction" id="instruction" cols="100" rows="10"	style="resize: none;" placeholder="請輸入票券介紹">${param.instruction}</textarea>${errorMsgs.instruction}<br>
                        <span>注意事項</span><br>
                        <textarea name="notice" id="notice" cols="100" rows="10" style="resize: none;" placeholder="請輸入注意事項"></textarea>${errorMsgs.notice}<br>
                        <span>如何使用</span><br>
                        <textarea name="howuse" id="howuse" cols="100" rows="10" style="resize: none;" placeholder="請輸入如何使用">${param.hoeuse}</textarea>${errorMsgs.howuse}<br>
                        <span>園區介紹</span><br>
                        <textarea name="canxpolicy" id="canxpolicy" cols="100" rows="10" style="resize: none;" placeholder="請輸入園區介紹">${param.canxpolicy}</textarea>${errorMsgs.canxpolicy}<br>
                        <div id="img_div">
                            <div>
                                <input name="updateFile" id="file1"	type="file" accept=“image/*>
                            </div>
                            <div>
                                <img id="look_img1" src="">
                            </div>
                            <div>
                                <input name="updateFile1" id="file2" type="file" accept=“image/*>
                            </div>
                            <div>
                                <img id="look_img2" src="">
                            </div>
                            <div>
                                <input name="updateFile2" id="file3" type="file" accept=“image/*>
                            </div>
                            <div>
                                <img id="look_img3" src="">
                            </div>
                        </div>
                        <input type="submit" value="提交">
                    </form>
                </div>
		</div>
	</div>





	<script>
		// $("#twzipcode").twzipcode();
		$("#twzipcode").twzipcode({
			countyName : "city", // 自訂城市 select 標籤的 name 值
			districtName : "town" // 自訂地區 select 標籤的 name 值
		});

		 document.querySelector('#file1').addEventListener('change',e=>{
	            const url = URL.createObjectURL(file1.files[0]);
	            let img=document.querySelector('#look_img1');
	            img.src=url;
	            
	     })
	      document.querySelector('#file2').addEventListener('change',e=>{
            const url = URL.createObjectURL(file2.files[0]);
            let img=document.querySelector('#look_img2');
            img.src=url;
            
        })
         document.querySelector('#file3').addEventListener('change',e=>{
            const url = URL.createObjectURL(file3.files[0]);
            let img=document.querySelector('#look_img3');
            img.src=url;
            
        })
	</script>

</body>
</html>