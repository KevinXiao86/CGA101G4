<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://kit.fontawesome.com/c95ced1229.js" crossorigin="anonymous"></script>

<title>Insert title here</title>

<style>
*{
	margin:0;
	padding:0;
}
ul, ol {
    margin: 0;
}
ul li, ol li {
    list-style: none;
}
ul{
padding:0;
}
#scrollUp {
    text-decoration: none;
    bottom: 60px;
    right: 60px;
    font-size: 13px;
    text-align: center;
    color: #878787;
    letter-spacing: 2px;
    border-bottom: 2px solid #878787;
    -webkit-transition-duration: 500ms;
    transition-duration: 500ms;
    text-transform: uppercase;
    padding: 5px 10px;
    line-height: 1;
}
/* footer */
.footer {
	background-color: #f2f2f2;
	padding: 70px 0;
}

.footer-col {
	width: 25%;
	padding: 0 15px;
}

.footer-col h4 {
	font-size: 18px;
	color: black;
	text-transform: capitalize;
	margin-bottom: 35px;
	font-weight: 500;
	position: relative;
}

.footer-col h4::before {
	content: "";
	position: absolute;
	left: 0;
	bottom: -10px;
	background-color: #e91e63;
	height: 2px;
	box-sizing: border-box;
	width: 50px;
}

.footer-col ul li:not(:last-child) {
	margin-bottom: 10px;
}

.footer-col ul li a {
	font-size: 16px;
	text-transform: capitalize;
	color: #ffffff;
	text-decoration: none;
	font-weight: 300;
	color: #bbbbbb;
	display: block;
	transition: all 0.3s ease;
}

.footer-col ul li a:hover {
	color: #000;
	padding-left: 8px;
}

.footer-col .social-links a {
	display: inline-block;
	height: 40px;
	width: 40px;
	background-color: rgba(255, 255, 255, 0.2);
	margin: 0 10px 10px 0;
	text-align: center;
	line-height: 40px;
	border-radius: 50%;
	color: #000;
	transition: all 0.5s ease;
}

.footer-col .social-links a:hover {
	color: #24262b;
	background-color: #ffffff;
}

#copyright {
	margin-top: 30px;
	text-align: center;
}
.container {
    max-width: 85%;
    margin: auto;
}
a {
  -webkit-transition-duration: 500ms;
  transition-duration: 500ms;
  text-decoration: none;
  outline: none;
  font-size: 14px;
  font-family: "helveticaneuemedium";
  color: #4f4d4d;
}

a:hover,
a:focus {
  font-family: "helveticaneuemedium";
  -webkit-transition-duration: 500ms;
  transition-duration: 500ms;
  text-decoration: none;
  outline: none;
  font-size: 14px;
}


</style>

</head>
<body>

<footer class="footer">
		<div class="container">
			<div class="row" style="display:flex;">
				<div class="footer-col">
					 <h4>台玩</h4>
                    <ul>
                        <li><a href="#">關於台玩</a></li>
                        <li><a href="#">使用者條款</a></li>
                        <li><a href="#">隱私權保護政策</a></li>
                        <li><a href="<%=request.getContextPath()%>/front-end/rejest/custmomer_reject.jsp">會員註冊</a></li>
                    </ul>
                </div>
                <div class="footer-col">
					<h4>旅人&合作夥伴</h4>
					<ul>
						<li><a href="#">關於合作夥伴</a></li>
						<li><a href="#">成為供應商</a></li>
						<li><a href="#">供應商登入</a></li>
						<li><a href="#">人才招募</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>常見問題</h4>
					<ul>
						<li><a href="#">FAQ</a></li>
						<li><a href="#">三大保證</a></li>
						<li><a href="#">聯絡客服</a></li>
					</ul>
				</div>
				<div class="footer-col">
					<h4>關注我們</h4>
					<div class="social-links">
						<a href="#"><i class="fab fa-facebook-f"></i></a>
						<a href="#"><i class="fab fa-instagram"></i></a> 
						<a href="#"><i class="fa-brands fa-line"></i></a> 
						<a href="#"><i class="fab fa-github"></i></a>
					</div>
				</div>
			</div>
		</div>
		<div id="copyright">
			Copyright &copy; 2022 All rights reserved | This template is made
			with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a
				href="https://colorlib.com" target="_blank">Colorlib</a>
		</div>

	</footer>
	<!-- ##### Footer Area End ##### -->
	
	
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>



</body>
</html>