<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>台玩</title>

  <!-- Favicon title的小圖 -->
<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">

<!-- Style CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/classy-nav-first.css">


    <!-- ===========搜尋bar============= -->
    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Cardo:700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">

    <link type="text/css" rel="stylesheet" href="css/bootstrap.min2.css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.css"
    integrity="sha512-UTNP5BXLIptsaj5WdKFrkFov94lDx+eBvbKyoe1YAfjeRPC+gT5kyZ10kOHCfNZqEui1sxmqvodNUx3KbuYI/A=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css"
    integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
    <script src="https://code.jquery.com/jquery-1.12.4.min.js"
    integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ=" crossorigin="anonymous"></script>

    <script src="https://kit.fontawesome.com/c95ced1229.js" crossorigin="anonymous"></script>
    <style type="text/css">
        .customer_bar{
            margin: 10px;
            border-bottom: 1px solid black;
        }
    </style>
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
        <div class="top-header" id="headerFixed" style="z-index: 3;">
            <div class="container h-100">
                <div class="row h-100 align-items-center"><img src="img/ticket-img/logo.jpg" alt="" id="bear"
                    style="height:65px;">
                    <!-- Breaking News Area -->
                    <div class="col-12 col-sm-5">

                        <div class="breaking-news-area">
                            <div id="breakingNewsTicker" class="ticker">
                                <ul>
                                    <li><a href="#">Tibame</a></li>
                                    <li><a href="#">CGA101</a></li>
                                    <li><a href="#">台玩</a></li>
                                    <li><a href="#">SAY HI!</a></li>
                                    <li><a href="#">Hello!</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <!-- Top Social Area -->
                    <div class="col-12 col-sm-5" style="margin-left:20px;">
                        <div class="top-social-area" style="padding-top:20px;">
                            <a href="#" data-toggle="tooltip" data-placement="bottom" title="購物車" style="" >
                                <i class="fa-solid fa-cart-shopping" aria-hidden="true"></i></a>

                            </div>
                        </div>
                    </div>
                </div>
            </div> 
            <div  id="picture" href="#" data-toggle="tooltip" data-placement="bottom" style="position: fixed;right: 10px;top: 10px;z-index: 4;height:50px;text-decoration: none;overflow: hidden;">
                <div class="picture" title="cow" style="width:45px;height:45px;border-radius: 100%;background-color: #000;padding:0;overflow:hidden;background-size: cover;background-image:url('img/ticket-img/t1.jpg'); margin-left:50px">
                </div>
                <div style="box-shadow: 2px 2px 4px black;background-color: #ccc;margin:10px;padding:5px;">
                    <span id="customer_data" class="customer_bar" style="display:block">
                        <a href="<%=request.getContextPath()%>/cust/CustomerInformation">會員功能</a>
                    </span>
                    <span class="customer_bar" >
                       <a href="<%=request.getContextPath()%>/cust/Logout">登出</a>
                    </span>
                </div>
            </div>
            <script type="text/javascript">
                document.querySelector('#picture').onclick=function(e){
                    document.querySelector('#picture').style.height='150px';
                };
            </script>
            <!-- ##### Hero Area Start ##### -->
            <div class="hero-area">
                <!-- Hero Slides Area -->
                <div class="hero-slides owl-carousel">
                    <!-- Single Slide -->
                    <div class="single-hero-slide bg-img"
                    style="background-image: url(img/bg-img/h4.jpg); width: 616px;height: 670px;">
                    <div class="container h-100">
                        <div class="row h-100 align-items-center">
                            <div class="col-12">
                                <div class="slide-content text-center">
                                    <div class="post-tag">
                                        <a href="#" data-animation="fadeInUp">lifestyle</a>
                                    </div>
                                    <h2 data-animation="fadeInUp" data-delay="250ms">
                                        <a href="single-post.html">Take a lookat last night’s party!</a>
                                    </h2>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Single Slide -->
                <div class="single-hero-slide bg-img"
                style="background-image: url(img/bg-img/h2.jpg); width: 616px;height: 670px;">
                <div class="container h-100">
                    <div class="row h-100 align-items-center">
                        <div class="col-12">
                            <div class="slide-content text-center">
                                <div class="post-tag">
                                    <a href="#" data-animation="fadeInUp">lifestyle</a>
                                </div>
                                <h2 data-animation="fadeInUp" data-delay="250ms">
                                    <a href="single-post.html">Take a lookat last night’s party!</a>
                                </h2>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Single Slide -->
            <div class="single-hero-slide bg-img"
            style="background-image: url(img/bg-img/h3.jpg); width: 616px;height: 670px;">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <div class="slide-content text-center">
                            <div class="post-tag">
                                <a href="#" data-animation="fadeInUp">lifestyle</a>
                            </div>
                            <h2 data-animation="fadeInUp" data-delay="250ms">
                                <a href="single-post.html">Take a lookat last night’s party!</a>
                            </h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- ##### Hero Area End ##### -->

<!-- Nav Area -->
<div class="original-nav-area" id="stickyNav">
    <div class="classy-nav-container breakpoint-off">
        <div class="container">
            <!-- Classy Menu -->
            <nav class="classy-navbar justify-content-between">

                <!-- Navbar Toggler -->
                        <!-- <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div> -->

                        <!-- Menu -->
                        <div class="classy-menu" id="originalNav">
                            <!-- close btn -->
                            <!-- <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div> -->

                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul>
                                    <li><a href="#">最新消息</a></li>
                                    <li><a href="#">活動主題</a></li>
                                    <li><a href="#">About Us</a></li>
                                    <li><a href="#">Megamenu</a></li>
                                    <li><a href="#">FAQ</a></li>
                                </ul>

                                <!-- Search Form  -->
                                <div id="search-wrapper">
                                    <form action="#">
                                        <input type="text" id="search" placeholder="Search something...">
                                        <div id="close-icon"></div>
                                        <input class="d-none" type="submit" value="">
                                    </form>
                                </div>
                            </div>
                            <!-- Nav End -->
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>
    <!-- ##### Header Area End ##### -->

    <!-- 搜尋標題 -->
    <!-- Nav Area -->
    <div class="original-nav-area" id="stickyNav">
        <div class="classy-nav-container breakpoint-off">
            <div class="container">
                <!-- Classy Menu -->
                <nav class="classy-navbar justify-content-between">
                    <!-- Navbar Toggler -->
                    <!-- <div class="classy-navbar-toggler">
                        <span class="navbarToggler"><span></span><span></span><span></span></span>
                    </div> -->

                    <!-- Menu -->
                    <div class="classy-menu" id="originalNav">
                        <!-- close btn -->
                        <div class="classycloseIcon">
                            <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                        </div>

                        <!-- Nav Start -->
                        <div class="classynav">
                            <ul>
                                <li><a href="#" id="bookButton">訂房</a>
                                    <div id="bookDiv">
                                        <div class="megamenu">
                                            <div class="section-center">
                                                <div class="container">
                                                    <div class="row">
                                                        <div class="booking-form">
                                                            <div class="form-header">
                                                                <h2>Make your reservation</h2>
                                                            </div>
                                                            <form>
                                                                <div class="row">
                                                                    <div>
                                                                        <div class="formDest">
                                                                            <span class="form-label">Destination</span>
                                                                            <input class="form-control" type="text"
                                                                            required id="destination">
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <div class="form-group">
                                                                            <span class="form-label">Check In</span>
                                                                            <input class="form-control" type="date"
                                                                            required id="checkIn">
                                                                        </div>
                                                                        <span class="in-out hidden-xs hidden-sm"
                                                                        style="margin-bottom: -12px;">&#8652;</span>
                                                                    </div>
                                                                    <div class="col-md-6">
                                                                        <div class="form-group">
                                                                            <span class="form-label">Check out</span>
                                                                            <input class="form-control" type="date"
                                                                            required id="checkOut">
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="row">
                                                                    <div class="col-md-3">
                                                                        <div class="form-group">
                                                                            <span class="form-label">No of rooms</span>
                                                                            <select class="form-control">
                                                                                <option>1</option>
                                                                                <option>2</option>
                                                                                <option>3</option>
                                                                            </select>
                                                                            <span class="select-arrow"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3">
                                                                        <div class="form-group">
                                                                            <span class="form-label">Adults</span>
                                                                            <select class="form-control">
                                                                                <option>1</option>
                                                                                <option>2</option>
                                                                                <option>3</option>
                                                                            </select>
                                                                            <span class="select-arrow"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3">
                                                                        <div class="form-group">
                                                                            <span class="form-label">Children</span>
                                                                            <select class="form-control">
                                                                                <option>0</option>
                                                                                <option>1</option>
                                                                                <option>2</option>
                                                                            </select>
                                                                            <span class="select-arrow"></span>
                                                                        </div>
                                                                    </div>
                                                                    <div class="col-md-3">
                                                                        <div class="form-btn">
                                                                            <button class="submit-btn">Check
                                                                            availability</button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li><a href="#" id="ticketButton">票券</a>
                                    <div id="ticketDiv">
                                        <div class="megamenu">
                                            <ul class="single-mega cn-col-4">
                                                <li class="title">景點門票</li>
                                                <li><a href="#">台北 | 木柵動物園</a></li>
                                                <li><a href="#">桃園 | Xpark</a></li>
                                                <li><a href="#">苗栗 | 飛牛牧場</a></li>
                                                <li><a href="#">嘉義 | 太平雲梯</a></li>
                                                <li><a href="#">花蓮 | 山度空間</a></li>
                                                <li><a href="#">台東 | 初鹿牧場</a></li>
                                                <li><a href="#">Read More</a></li>
                                            </ul>
                                            <ul class="single-mega cn-col-4">
                                                <li class="title">主題樂園</li>
                                                <li><a href="#">台北 | 兒童新樂園</a></li>
                                                <li><a href="#">新竹 | 六福村</a></li>
                                                <li><a href="#">南投 | 九族文化村</a></li>
                                                <li><a href="#">台中 | 麗寶</a></li>
                                                <li><a href="#">雲林 | 劍湖山世界</a></li>
                                                <li><a href="#">高雄 | 義大遊樂世界</a></li>
                                                <li><a href="#">Read More</a></li>
                                            </ul>
                                            <ul class="single-mega cn-col-4">
                                                <li class="title">博物館、展覽</li>
                                                <li><a href="#">台北 | 會動的文藝復興</a></li>
                                                <li><a href="#">台北 | 小王子的藝想世界</a></li>
                                                <li><a href="#">台北 | 大昆蟲展</a></li>
                                                <li><a href="#">台中 | 島嶼溯遊</a></li>
                                                <li><a href="#">台南 | 小胖子</a></li>
                                                <li><a href="#">高雄 | 屁屁偵探特展</a></li>
                                                <li><a href="#">Read More</a></li>
                                            </ul>
                                            <ul class="single-mega cn-col-4">
                                                <li class="title">特色表演</li>
                                                <li><a href="#">台北 | 愛要怎麼說</a></li>
                                                <li><a href="#">台北 | 鐘樓怪人</a></li>
                                                <li><a href="#">新竹 | 突發異聞</a></li>
                                                <li><a href="#">台中 | 來福週末夜</a></li>
                                                <li><a href="#">台南 | 歡樂屬於你</a></li>
                                                <li><a href="#">高雄 | 獅子王</a></li>
                                                <li><a href="#">Read More</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- Nav End -->
                    </div>
                </nav>
            </div>
        </div>
    </div>

    <div style="padding-top: 380px;"></div>
    <h3 class="alllisttitle">熱門票券</h3>

    <!-- 三格 -->
    <!-- ##### Blog Wrapper Start ##### -->
    <div class="blog-wrapper section-padding-100-0 clearfix" style="padding-top:5px; margin-bottom: -100px;">
        <div class="container" style="height:480px;">
            <div class="row" style="height:480px;">
                <!-- Single Blog Area  -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-blog-area blog-style-2 mb-100">
                        <div class="single-blog-thumbnail">
                            <img src="img/blog-img/t2.jpg" style="height:218px;" alt="">
                            <div class="post-date">
                                <a href="#">2022 <span>march</span></a>
                            </div>
                        </div>
                        <!-- Blog Content -->
                        <div class="single-blog-content mt-30" style="margin-top: 5;">
                            <a href="#" class="post-tag">Lifestyle</a>
                            <h4><a href="#" class="post-headline">【櫻花祭】台中 | 武陵農場</a></h4>
                            <p>最美的櫻花祭，大人小孩千萬不要錯過</p>
                        </div>
                    </div>
                </div>
                <!-- Single Blog Area  -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-blog-area blog-style-2 mb-100">
                        <div class="single-blog-thumbnail">
                            <img src="img/blog-img/t1.jpg" alt="">
                            <div class="post-date">
                                <a href="#">2022 <span>march</span></a>
                            </div>
                        </div>
                        <!-- Blog Content -->
                        <div class="single-blog-content mt-30" style="margin-top: 5;">
                            <a href="#" class="post-tag">Lifestyle</a>
                            <h4><a href="#" class="post-headline">【澎湖花火節】熱門獨木舟</a></h4>
                            <p>立即體驗絕美的獨木舟，拍出網美照</p>
                        </div>
                    </div>
                </div>
                <!-- Single Blog Area  -->
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-blog-area blog-style-2 mb-100">
                        <div class="single-blog-thumbnail">
                            <img src="img/blog-img/t3.jpg" alt="">
                            <div class="post-date">
                                <a href="#">10 <span>march</span></a>
                            </div>
                        </div>
                        <!-- Blog Content -->
                        <div class="single-blog-content mt-30" style="margin-top: 5;">
                            <a href="#" class="post-tag">Lifestyle</a>
                            <h4><a href="#" class="post-headline">【大家都愛】台東 | 初鹿牧場</a></h4>
                            <p>不論親子旅遊或情侶都喜愛的項目，還不快來體驗看看!</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Blog Wrapper End ##### -->

    <section class="rooms">
        <div class="container top">
            <div class="heading">
                <h3>推薦精選飯店</h3>
                <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Eligendi iste rerum ad modi dignissimos in
                sapiente dolore laborum magnam sed.</p>
            </div>
            <div class="content mtop">
                <div class="owl-carousel owl-carousel1 owl-theme">
                    <div class="items">
                        <div class="image">
                            <img src="img/blog-img/room1.jpg" alt="">
                        </div>
                        <div class="text">
                            <h2>super rooms</h2>
                            <div class="rate flex">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                            <div class="button flex">
                                <button class="primary-btn">book now</button>
                                <h3>$250 <span> <br> per night</span></h3>
                            </div>
                        </div>
                    </div>
                    <div class="items">
                        <div class="image">
                            <img src="img/blog-img/room2.jpg" alt="">
                        </div>
                        <div class="text">
                            <h2>super rooms</h2>
                            <div class="rate flex">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                            <div class="button flex">
                                <button class="primary-btn">book now</button>
                                <h3>$250 <span> <br> per night</span></h3>
                            </div>
                        </div>
                    </div>
                    <div class="items">
                        <div class="image">
                            <img src="img/blog-img/room3.jpg" alt="">
                        </div>
                        <div class="text">
                            <h2>super rooms</h2>
                            <div class="rate flex">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                            <div class="button flex">
                                <button class="primary-btn">book now</button>
                                <h3>$250 <span> <br> per night</span></h3>
                            </div>
                        </div>
                    </div>
                    <div class="items">
                        <div class="image">
                            <img src="img/blog-img/room4.jpg" alt="">
                        </div>
                        <div class="text">
                            <h2>super rooms</h2>
                            <div class="rate flex">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                            <div class="button flex">
                                <button class="primary-btn">book now</button>
                                <h3>$250 <span> <br> per night</span></h3>
                            </div>
                        </div>
                    </div>
                    <div class="items">
                        <div class="image">
                            <img src="img/blog-img/room5.jpg" alt="">
                        </div>
                        <div class="text">
                            <h2>super rooms</h2>
                            <div class="rate flex">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
                            <div class="button flex">
                                <button class="primary-btn">book now</button>
                                <h3>$250 <span> <br> per night</span></h3>
                            </div>
                        </div>
                    </div>
                    <div class="items">
                        <div class="image">
                            <img src="img/blog-img/room6.jpg" alt="">
                        </div>
                        <div class="text">
                            <h2>super rooms</h2>
                            <div class="rate flex">
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                                <i class="fa fa-star"></i>
                            </div>
                            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. </p>
                            <div class="button flex">
                                <button class="primary-btn">book now</button>
                                <h3>$250 <span> <br> per night</span></h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
    integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw=="
    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.js"
    integrity="sha512-gY25nC63ddE0LcLPhxUJGFxa2GoIyA5FLym4UJqHDEMHjp8RET6Zn/SHo1sltt3WuVtqfyxECP38/daUc/WVEA=="
    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script>
        $('.owl-carousel1').owlCarousel({
            loop: true,
            margin: 40,
            nav: true,
            dots: false,
            navText: ['<i class="fa-solid fa-chevron-left"></i', '<i class="fa-solid fa-chevron-right"></i>'],
            responsive: {
                0: {
                    items: 1
                },
                768: {
                    items: 2,
                    margin: 10,
                },
                1000: {
                    items: 3
                }
            }
        })
    </script>


    <!-- ##### Blog Wrapper Start ##### -->
    <div class="blog-wrapper section-padding-100 clearfix">
        <div class="container">
            <div class="row">
                <div class="col-12 col-lg-9">

                    <!-- Single Blog Area  -->
                    <div class="single-blog-area blog-style-2 mb-50 wow fadeInUp">
                        <div class="row align-items-center">
                            <div class="col-12 col-md-6">
                                <div class="single-blog-thumbnail">
                                    <img src="img/blog-img/a2.jpeg" alt="">
                                    <div class="post-date">
                                        <a href="#">12 <span>march</span></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-md-6">
                                <!-- Blog Content -->
                                <div class="single-blog-content">
                                    <div class="line"></div>
                                    <a href="#" class="post-tag">Lifestyle</a>
                                    <h4><a href="#" class="post-headline">澎湖花火節開跑！</a></h4>
                                    <p>Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus.
                                        Nullam vestibulum convallis risus vel condimentum. Nullam auctor lorem in libero
                                    luctus, vel volutpat quam tincidunt.</p>
                                    <div class="post-meta">
                                        <p>By <a href="#">james smith</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Single Blog Area  -->
                    <div class="single-blog-area blog-style-2 mb-50 wow fadeInUp">
                        <div class="row align-items-center">
                            <div class="col-12 col-md-6">
                                <div class="single-blog-thumbnail">
                                    <img src="img/blog-img/a1.jpg" alt="">
                                    <div class="post-date">
                                        <a href="#">12 <span>march</span></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-md-6">
                                <!-- Blog Content -->
                                <div class="single-blog-content">
                                    <div class="line"></div>
                                    <a href="#" class="post-tag">Lifestyle</a>
                                    <h4><a href="#" class="post-headline">適合親子度假</a></h4>
                                    <p>Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus.
                                        Nullam vestibulum convallis risus vel condimentum. Nullam auctor lorem in libero
                                    luctus, vel volutpat quam tincidunt.</p>
                                    <div class="post-meta">
                                        <p>By <a href="#">james smith</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Single Blog Area  -->
                    <div class="single-blog-area blog-style-2 mb-50 wow fadeInUp">
                        <div class="row align-items-center">
                            <div class="col-12 col-md-6">
                                <div class="single-blog-thumbnail">
                                    <img src="img/blog-img/a1.jpg" alt="">
                                    <div class="post-date">
                                        <a href="#">12 <span>march</span></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-12 col-md-6">
                                <!-- Blog Content -->
                                <div class="single-blog-content">
                                    <div class="line"></div>
                                    <a href="#" class="post-tag">Lifestyle</a>
                                    <h4><a href="#" class="post-headline">夏水趣</a></h4>
                                    <p>Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus.
                                        Nullam vestibulum convallis risus vel condimentum. Nullam auctor lorem in libero
                                    luctus, vel volutpat quam tincidunt.</p>
                                    <div class="post-meta">
                                        <p>By <a href="#">james smith</a></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- Load More -->
                    <div class="load-more-btn mt-100 wow fadeInUp">
                        <a href="#" class="btn original-btn" style="margin-top: -40px;">Read More</a>
                    </div>
                </div>

                <!-- ##### Sidebar Area ##### -->
                <div class="col-12 col-md-4 col-lg-3">
                    <div class="post-sidebar-area">

                        <!-- Widget Area -->
                        <div class="sidebar-widget-area">
                            <h5 class="title">最新消息</h5>

                            <div class="widget-content">

                                <!-- Single Blog Post -->
                                <div class="single-blog-post d-flex align-items-center widget-post">
                                    <!-- Post Thumbnail -->
                                    <div class="post-thumbnail">
                                        <img src="img/blog-img/lp1.jpg" alt="">
                                    </div>
                                    <!-- Post Content -->
                                    <div class="post-content">
                                        <a href="#" class="post-tag">Lifestyle</a>
                                        <h4><a href="#" class="post-headline">領取優惠券</a></h4>
                                        <div class="post-meta">
                                            <p><a href="#">票券皆可使用</a></p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single Blog Post -->
                                <div class="single-blog-post d-flex align-items-center widget-post">
                                    <!-- Post Thumbnail -->
                                    <div class="post-thumbnail">
                                        <img src="img/blog-img/lp2.jpg" alt="">
                                    </div>
                                    <!-- Post Content -->
                                    <div class="post-content">
                                        <a href="#" class="post-tag">Lifestyle</a>
                                        <h4><a href="#" class="post-headline">系統維護</a></h4>
                                        <div class="post-meta">
                                            <p><a href="#">110/05/20早上3點至5點將維護系統</a></p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single Blog Post -->
                                <div class="single-blog-post d-flex align-items-center widget-post">
                                    <!-- Post Thumbnail -->
                                    <div class="post-thumbnail">
                                        <img src="img/blog-img/lp3.jpg" alt="">
                                    </div>
                                    <!-- Post Content -->
                                    <div class="post-content">
                                        <a href="#" class="post-tag">全台最奢華防疫旅館</a>
                                        <h4><a href="#" class="post-headline">先訂先贏，唯一一家</a></h4>
                                        <div class="post-meta">
                                            <p><a href="#">5/2</a></p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Single Blog Post -->
                                <div class="single-blog-post d-flex align-items-center widget-post">
                                    <!-- Post Thumbnail -->
                                    <div class="post-thumbnail">
                                        <img src="img/blog-img/lp4.jpg" alt="">
                                    </div>
                                    <!-- Post Content -->
                                    <div class="post-content">
                                        <a href="#" class="post-tag">台玩正式上線囉</a>
                                        <h4><a href="#" class="post-headline">將帶給大家旅遊及訂房服務</a></h4>
                                        <div class="post-meta">
                                            <p><a href="#">5/1正式上線</a></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Widget Area -->
                        <div class="sidebar-widget-area">
                            <h5 class="title" style="margin-bottom: 20px;margin-top: 90px;">Advertisement</h5>
                            <a href="#"><img src="img/bg-img/add.gif" alt=""></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- ##### Blog Wrapper End ##### -->

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
                <img src="img/instagram-img/f1.jpg" alt="" style="height: 168px;">
                <!-- Hover Effects -->
                <div class="hover-effects">
                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                        class="fa fa-instagram"></i></a>
                    </div>
                </div>
                <!-- Single Insta Feed -->
                <div class="single-insta-feed">
                    <img src="img/instagram-img/f2.jpg" alt="">
                    <!-- Hover Effects -->
                    <div class="hover-effects">
                        <a href="#" class="d-flex align-items-center justify-content-center"><i
                            class="fa fa-instagram"></i></a>
                        </div>
                    </div>
                    <!-- Single Insta Feed -->
                    <div class="single-insta-feed">
                        <img src="img/instagram-img/f3.jpg" alt="">
                        <!-- Hover Effects -->
                        <div class="hover-effects">
                            <a href="#" class="d-flex align-items-center justify-content-center"><i
                                class="fa fa-instagram"></i></a>
                            </div>
                        </div>
                        <!-- Single Insta Feed -->
                        <div class="single-insta-feed">
                            <img src="img/instagram-img/f4.jpg" alt="">
                            <!-- Hover Effects -->
                            <div class="hover-effects">
                                <a href="#" class="d-flex align-items-center justify-content-center"><i
                                    class="fa fa-instagram"></i></a>
                                </div>
                            </div>
                            <!-- Single Insta Feed -->
                            <div class="single-insta-feed">
                                <img src="img/instagram-img/f5.jpg" alt="">
                                <!-- Hover Effects -->
                                <div class="hover-effects">
                                    <a href="#" class="d-flex align-items-center justify-content-center"><i
                                        class="fa fa-instagram"></i></a>
                                    </div>
                                </div>
                                <!-- Single Insta Feed -->
                                <div class="single-insta-feed">
                                    <img src="img/instagram-img/f6.jpg" alt="">
                                    <!-- Hover Effects -->
                                    <div class="hover-effects">
                                        <a href="#" class="d-flex align-items-center justify-content-center"><i
                                            class="fa fa-instagram"></i></a>
                                        </div>
                                    </div>
                                    <!-- Single Insta Feed -->
                                    <div class="single-insta-feed">
                                        <img src="img/instagram-img/f7.jpg" alt="">
                                        <!-- Hover Effects -->
                                        <div class="hover-effects">
                                            <a href="#" class="d-flex align-items-center justify-content-center"><i
                                                class="fa fa-instagram"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- ##### Instagram Feed Area End ##### -->

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
                                                <h4>follow us</h4>
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
	<script	src="<%=request.getContextPath()%>/static/js/front-main/popper.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/bootstrap.min.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/plugins.js"></script>
	<script	src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>
                                <script>
                                    let date = new Date();
                                    let day = date.getDate();
                                    let month = date.getMonth() + 1;
                                    let month2 = date.getMonth() + 2;
                                    let year = date.getFullYear();
                                    if (month < 10)
                                        month = "0" + month;
                                    if (month2 < 10)
                                        month2 = "0" + month2;
                                    if (day < 10)
                                        day = "0" + day;
                                    let today = `${year}-${month}-${day}`;
                                    let lastday = `${year}-${month2}-${day}`;
                                    $('#checkIn').attr('value', today);
                                    $('#checkIn').attr('min', today);
                                    $('#checkIn').attr('max', lastday);
                                    $('#checkIn').change(e => {
                                        $('#checkOut').attr({
                                            'min': $('#checkIn').val(),
                                            'max': lastday
                                        })
                                    })
                                </script>
</body>
</html>