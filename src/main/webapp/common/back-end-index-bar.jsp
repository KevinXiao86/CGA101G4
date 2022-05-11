<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
 <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="#">後臺首頁</a>
            </div>

            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!-- Top Navigation: Left Menu -->
            <ul class="nav navbar-nav navbar-left navbar-top-links">
                <li><a href="#"><i class="fa fa-home fa-fw"></i> Website</a></li>
            </ul>

            <!-- Top Navigation: Right Menu -->
            <ul class="nav navbar-right navbar-top-links">

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> secondtruth <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> 基本資料</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="#" id="logout"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <script type="text/javascript">
                document.querySelector('#logout').onclick=function(){
                    location.href="pages/login.html";
                };
            </script>
            <!-- Sidebar -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">

                    <ul class="nav" id="side-menu">

                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i>員工管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="back-end/emp/emp_index.jsp">員工資料管理</a>
                                </li>
                                <li>
                                    <a href="back-end/emp/authority/authorityIndex.jsp">員工權限管理</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i>網頁管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="back-end/coupon/cop_index.jsp">管理優惠券</a>
                                </li>
                                <li>
                                    <a href="back-end/theme/theme_index.jsp">最新消息管理</a>
                                </li>
                                <li>
                                    <a href="back-end/faq/faq_index.jsp">FAQ管理</a>
                                </li>                 
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i>廠商管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">廠商訊息管理</a>
                                </li>
                                <li>
                                    <a href="#">廠商檢舉管理</a>
                                </li>
                                <li>
                                    <a href="#">廠商資料管理</a>
                                </li>
                                <li>
                                    <a href="back-end/roomOrder/roomOrder_index.jsp">訂房訂單管理</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i>票券管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="back-end/ticket/ticket_index.jsp">票券資料管理</a>
                                </li>
                                <li>
                                    <a href="back-end/tktOrder/tktOrder_index.jsp">票券訂單管理</a>
                                </li>                 
                            </ul>
                        </li>
                         <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i>會員管理<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">會員資料管理</a>
                                </li>
                                <li>
                                    <a href="#">會員檢舉管理</a>
                                </li>
                                <li>
                                    <a href="#">會員訊息管理</a>
                                </li>                 
                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>
</body>
</html>