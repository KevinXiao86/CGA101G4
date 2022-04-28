<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
    <title>台玩</title>
    <link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

    <!-- Style CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/classy-nav.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/post.css">
    <link
        href="https://fonts.googleapis.com/css?family=Source+Sans+3:200,300,regular,500,600,700,800,900,200italic,300italic,italic,500italic,600italic,700italic,800italic,900italic"
        rel="stylesheet" />
    <script src="https://kit.fontawesome.com/c95ced1229.js" crossorigin="anonymous"></script>

</head>

<body>
    <!-- Preloader -->
    <div id="preloader">
        <div class="preload-content">
            <div id="original-load"></div>
        </div>
    </div>

    <!-- ##### Header Area Start ##### -->
    <header class="header-area">

        <!-- Top Header Area -->
        <div class="top-header" id="headerFixed">
            <div class="container h-100">
                <div class="row h-100 align-items-center"><img src="<%=request.getContextPath()%>/static/img/ticket-img/logo.jpg" alt="" id="bear"
                        style="height:65px;">
                    <!-- Top Social Area -->
                    <div class="col-12 col-sm-5" style="margin-left:490px">
                        <div class="top-social-area">
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="購物車">
                                <i class="fa-solid fa-cart-shopping" aria-hidden="true"></i></a>
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="登入">
                                <i class="fa-regular fa-user" aria-hidden="true"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->

    <!-- ##### Single Blog Area Start ##### -->
    <div class="single-blog-wrapper section-padding-0-100">

        <!-- Single Blog Area  -->
        <div class="single-blog-area blog-style-2 mb-50">
            <div class="single-blog-thumbnail" style="margin-top: 70px;">
                <img src="<%=request.getContextPath()%>/static/img/bg-img/h1.jpg" alt="">
            </div>
            <div id="productNum">商品編號 #${tktVO.tktId}</div>
        </div>


        <div class="container">
            <div class="row">
                <!-- ##### Post Content Area ##### -->
                <div class="col-12 col-lg-9">
                    <!-- Single Blog Area  -->
                    <div class="single-blog-area blog-style-2 mb-50">
                        <!-- Blog Content -->
                        <div class="single-blog-content">
                            <div class="line"></div>
                            <div class="post-tag">Lifestyle</div>
                            <h4>
                                <div class="post-headline mb-0">${tktVO.tktName}</div>
                                <i class="fa-regular fa-heart" aria-hidden="true" style="--fa-inverse: #1da1f2;"
                                    id="love"></i>
                            </h4>
                            <div class="post-meta mb-50">
                                <p><a href="#"><i class="fa-solid fa-location-dot" aria-hidden="true" style="color: red;"></i> ${tktVO.location}</a></p>
                                <p>${tktVO.ttlScore}<i class="fa-solid fa-star" style="color: orange;"></i>評價</p>
                                <p>${tktVO.ttlPeople} comments</p>
                            </div>
                            <p>
                                <hr>
                                <i class="fa-solid fa-mobile-screen-button" aria-hidden="true"></i> 手機出示QR Code
                          		<div style="display:inline-block; width:40px;"> </div>
                                <i class="fa-solid fa-ban" aria-hidden="true"></i> 不可取消
                            </p>
                            <p>${tktVO.instruction}</p>
                        </div>
                    </div>


                    <!-- Comment Area Start -->
                    <hr>
                    <div class="comment_area clearfix mt-70">
                        <h4 class="title"><i class="fa-solid fa-earth-asia" style="color: rgb(107, 150, 207);"></i> 旅客評價</h4>

                        <ol>
                        	<c:forEach var="tktItem" items="${tktItemList}">
	                            <li class="single_comment_area">
	                                <div class="comment-content d-flex">
	                                    <div class="comment-author">
	                                        <img src="#" alt="author">
	                                    </div>
	                                    <div class="comment-meta">
	                                        <a href="#" class="post-date">March 12</a>
	                                        <p><a href="#" class="post-author">陳學有</a></p>
	                                        <p>${tktItem.content}</p>
	                                    </div>
	                                </div>
	                            </li>
                            </c:forEach>
                            <div>更多評價</div>
                        </ol>
                    </div>

                    <!-- About product -->
                    <!-- 特別注意是用comment的class -->
                    <div class="comment_area clearfix mt-70">
                        <div class="author-info">
                            <!-- <div class="line"></div> -->
                            <h4 class="title"><i class="fa-solid fa-earth-asia" style="color: rgb(107, 150, 207);"></i> 商品說明</h4>
                            <div>
                                <p>園區資訊</p>
                                <p>
                                    營業時間：08:00-17:00<br>
                                    地址：95443台東縣卑南鄉明峰村牧場一號
                                </p>
                                <p>園區介紹</p>
                                <p>幅員遼闊的初鹿牧場，牧場內規劃有放牧區、可愛動物區、森林浴區等，初鹿牧場視野遼闊，觸目可及的青青牧草，
                                    令人身心舒暢。成群乳牛悠閒的在如茵草原上漫步，雪白的圍欄、朱紅的房，在蔚藍天空和柔軟白雲襯托下，
                                    彷彿如詩如畫的歐洲莊園，讓人陶醉不己。極具異國風情，形成一幅充滿牧場野趣的優美景致。</p>
                            </div>
                            <div>
                                圖片們
                            </div>
                        </div>
                    </div>

                    <div class="comment_area clearfix mt-70">
                        <div class="author-info">
                            <h4 class="title"><i class="fa-solid fa-earth-asia" style="color: rgb(107, 150, 207);"></i> 購買須知</h4>
                            <div>
                                <p>${tktVO.notice}</p>
                            </div>
                        </div>
                    </div>

                    <div class="comment_area clearfix mt-70">
                        <div class="author-info">
                            <h4 class="title"><i class="fa-solid fa-earth-asia" style="color: rgb(107, 150, 207);"></i> 如何使用</h4>
                            <div>
                                <p>${tktVO.howuse}</p>
                            </div>
                        </div>
                    </div>


                    <div class="comment_area clearfix mt-70">
                        <div class="author-info">
                            <h4 class="title"><i class="fa-solid fa-earth-asia" style="color: rgb(107, 150, 207);"></i> 體驗地點</h4>
                            <div>
                                <iframe
                                    src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d14705.151757291362!2d121.1081636!3d22.8658194!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x95ca249c81a54f18!2z5Yid6bm_54mn5aC0!5e0!3m2!1szh-TW!2stw!4v1647886745869!5m2!1szh-TW!2stw"
                                    width="600" height="450" style="border:0;" allowfullscreen=""
                                    loading="lazy"></iframe>
                            </div>
                        </div>
                    </div>

                    <!-- 留言區 -->
                    <div class="post-a-comment-area mt-70">
                        <h5>Leave a reply</h5>
                        <!-- Reply Form -->
                        <form action="#" method="post">
                            <div class="row">
                                <div class="col-12 col-md-6">
                                    <div class="group">
                                        <input type="text" name="name" id="name" required>
                                        <span class="highlight"></span>
                                        <span class="bar"></span>
                                        <label>Name</label>
                                    </div>
                                </div>
                                <div class="col-12 col-md-6">
                                    <div class="group">
                                        <input type="email" name="email" id="email" required>
                                        <span class="highlight"></span>
                                        <span class="bar"></span>
                                        <label>Email</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="group">
                                        <input type="text" name="subject" id="subject" required>
                                        <span class="highlight"></span>
                                        <span class="bar"></span>
                                        <label>Subject</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="group">
                                        <textarea name="message" id="message" required></textarea>
                                        <span class="highlight"></span>
                                        <span class="bar"></span>
                                        <label>Comment</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <button type="submit" class="btn original-btn">Reply</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>

                <!-- Widget Area -->
                <div class="sidebar-widget-area" style="width: 260px;">
                    <div class="product-summary">
                        <h4 class="productName">TWD ${tktVO.price}</h4>
                        <span class="stars"></span>
                        <p>
                            luctus quis et est. Integer pretium
                            purus
                        </p>
                    </div>
                    <div style="text-align: center;">
                        <button class="btn btn-small addToCart" data-product-id="1" id="choice">
                            <i class="fas fa-cart-plus"></i> 加入購物車
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Single Blog Area End ##### -->

    <!-- ##### Instagram Feed Area Start ##### -->
    <div class="instagram-feed-area">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="insta-title">
                        <h5>Follow us @ Instagram</h5>
                    </div>
                </div>
            </div>
        </div>
        <!-- Instagram Slides -->
        <div class="instagram-slides owl-carousel">
            <!-- Single Insta Feed -->
            <div class="single-insta-feed">
                <img src="<%=request.getContextPath()%>/static/img/instagram-img/1.png" alt="">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                </div>
            </div>
            <!-- Single Insta Feed -->
            <div class="single-insta-feed">
                <img src="<%=request.getContextPath()%>/static/img/instagram-img/2.png" alt="">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                </div>
            </div>
            <!-- Single Insta Feed -->
            <div class="single-insta-feed">
                <img src="<%=request.getContextPath()%>/static/img/instagram-img/3.png" alt="">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                </div>
            </div>
            <!-- Single Insta Feed -->
            <div class="single-insta-feed">
                <img src="<%=request.getContextPath()%>/static/img/instagram-img/4.png" alt="">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                </div>
            </div>
            <!-- Single Insta Feed -->
            <div class="single-insta-feed">
                <img src="<%=request.getContextPath()%>/static/img/instagram-img/5.png" alt="">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                </div>
            </div>
            <!-- Single Insta Feed -->
            <div class="single-insta-feed">
                <img src="<%=request.getContextPath()%>/static/img/instagram-img/6.png" alt="">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                </div>
            </div>
            <!-- Single Insta Feed -->
            <div class="single-insta-feed">
                <img src="<%=request.getContextPath()%>/static/img/instagram-img/7.png" alt="">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Instagram Feed Area End ##### -->



	<!-- Footer Section Start -->
	<%-- <%@ include file= "/front-end/framework/footer.file" %> --%>
	
	
    <!-- #### Footer start #### -->
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="footer-col">
                    <h4>台玩</h4>
                    <ul>
                        <li><a href="#">關於台玩</a></li>
                        <li><a href="#">使用者條款</a></li>
                        <li><a href="#">隱私權保護政策</a></li>
                    </ul>
                </div>
                <div class="footer-col">
                    <h4>get help</h4>
                    <ul>
                        <li><a href="#">FAQ</a></li>
                        <li><a href="#">三大保證</a></li>
                        <li><a href="#">聯絡客服</a></li>
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
                    <h4>關注我們</h4>
                    <div class="social-links">
                        <a href="#"><i class="fab fa-facebook-f"></i></a>
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-instagram"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>
        </div>
        <div id="copyright">
            Copyright &copy; 2022 All rights reserved | This template is made with
            <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com"
                target="_blank">Colorlib</a>
        </div>

    </footer>
    <!-- ##### Footer Area End ##### -->
    

    <!-- jQuery (Necessary for All JavaScript Plugins) -->
    <script src="<%=request.getContextPath()%>/static/js/front-main/jquery/jquery-2.2.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/plugins.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

    <script>
        function setQuantity1() {
            let obj = event.target.nextSibling;
            obj.value = parseInt(obj.value) - 1;
        }
        function setQuantity2() {
            let obj = event.target.previousSibling;
            obj.value = parseInt(obj.value) + 1;
        }                        
    </script>

</body>

</html>