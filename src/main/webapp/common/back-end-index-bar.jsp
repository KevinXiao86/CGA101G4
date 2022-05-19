<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.taiwan.dao.employee.impl.*"%>
<%@ page import="com.taiwan.controll.employee.*"%>
<%@ page import="com.taiwan.service.employee.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>

 <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <div class="navbar-header">
                <a class="navbar-brand" href="back-login/login/login-back-end-index.jsp">後臺首頁</a>
            </div>

            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <!-- Top Navigation: Left Menu -->
            <ul class="nav navbar-nav navbar-left navbar-top-links">
                <li><a href="back-login/login/login-back-end-index.jsp"><i class="fa fa-home fa-fw"></i> </a></li>
            </ul>

            <!-- Top Navigation: Right Menu -->
            <ul class="nav navbar-right navbar-top-links">

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>${sessionScope.employeeVO.empName} <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
<!--                         <li><a href="#"><i class="fa fa-user fa-fw"></i> 基本資料</a> -->
<!--                         </li> -->
<!--                         <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a> -->
<!--                         </li> -->
<!--                         <li class="divider"></li> -->
                        <li><a href="<%=request.getContextPath()%>/empLogout" id="logout"><i class="fa fa-sign-out fa-fw"></i> 登出</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <script type="text/javascript">
                document.querySelector('#logout').onclick=function(){
                    location.href="EmployeeServlet?action=logOut";
                };
            </script>
            <!-- Sidebar -->
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse" >

                    <ul class="nav" id="side-menu">

                        <li id=emp>
                        <c:if test="${sessionScope.employeeVO.funcID==1}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>員工管理<span class="fa arrow"></span></a>
                                	</c:if>
                                	<c:if test="${sessionScope.employeeVO.funcID==6}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>員工管理<span class="fa arrow"></span></a></c:if>
                            		
                            		<c:if test="${sessionScope.employeeVO.funcID != 6 && sessionScope.employeeVO.funcID !=1 }">
                                		<a href="back-login/login/login-back-end-index.jsp" style="pointer-events: none"><i class="fa fa-sitemap fa-fw"></i>員工管理<span class="fa arrow"></span></a>
                                		</c:if>
                            <ul class="nav nav-second-level">
                            
                                <li >
                                	<c:if test="${sessionScope.employeeVO.funcID==1}">
                                		<a href="back-end/emp/emp_index.jsp">員工權限管理</a>
<%--                                  	   <a href="${sessionScope.employeeVO.funcID==1  ?'back-end/emp/emp_index.jsp':'back-login/login/login-back-end-index.jsp'}" >員工資料管理</a> --%>
                                	</c:if>
                                	
                                	<c:if test="${sessionScope.employeeVO.funcID==6}">
                                		<a href="back-end/emp/emp_index.jsp">員工權限管理</a></c:if>
                                	
                                	<c:if test="${sessionScope.employeeVO.funcID != 6 && sessionScope.employeeVO.funcID !=1 }">
                                		<a href="back-login/login/login-back-end-index.jsp">員工權限管理</a>
                                		</c:if>
                                		
                                </li>
<!--                                 <li> -->
<!--                                     <a href="back-end/emp/authority/authorityIndex.jsp">員工權限管理</a> -->
<!--                                 </li> -->
                            </ul>
                        </li >
<!--                                 ==================================================網頁分隔線================================================== -->
                        
                        <li id=web>
                         <c:if test="${sessionScope.employeeVO.funcID==5}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>網頁管理<span class="fa arrow"></span></a>
                                	</c:if>
                        <c:if test="${sessionScope.employeeVO.funcID==6}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>網頁管理<span class="fa arrow"></span></a>
                                	</c:if>
                                	
                        <c:if test="${sessionScope.employeeVO.funcID != 6 && sessionScope.employeeVO.funcID !=5 }">
                                		<a href="back-login/login/login-back-end-index.jsp" style="pointer-events: none"><i class="fa fa-sitemap fa-fw"></i>網頁管理<span class="fa arrow"></span></a>
                                		</c:if>
                        
<!--                             <a href="#"><i class="fa fa-sitemap fa-fw"></i>網頁管理<span class="fa arrow"></span></a> -->

                            <ul class="nav nav-second-level">
                                <li>                               
                                    <a href="back-end/coupon/cop_index.jsp">管理優惠券</a>
                                </li>
                                <li>
                                    <a href="back-end/theme/theme_index.jsp">熱門活動管理</a>
                                </li>
                                <li>
                                    <a href="back-end/news/news_index.jsp">最新消息管理</a>
                                </li>
<!--                                 <li> -->
<!--                                     <a href="back-end/faq/faq_index.jsp">FAQ管理</a> -->
<!--                                 </li>                  -->
                            </ul>
                        </li>
<!--                         ==========================================廠商管理分隔線================================================== -->
                        <li id=com>
                        <c:if test="${sessionScope.employeeVO.funcID==4}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>廠商管理<span class="fa arrow"></span></a>
                                	</c:if>
                        <c:if test="${sessionScope.employeeVO.funcID==6}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>廠商管理<span class="fa arrow"></span></a>
                                	</c:if>
                        
                        <c:if test="${sessionScope.employeeVO.funcID != 6 && sessionScope.employeeVO.funcID !=4 }">
                                		<a href="back-login/login/login-back-end-index.jsp" style="pointer-events: none"><i class="fa fa-sitemap fa-fw"></i>廠商管理<span class="fa arrow"></span></a>
                                		</c:if>

<!--                             <a href="#"><i class="fa fa-sitemap fa-fw"></i>廠商管理<span class="fa arrow"></span></a> -->
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="back-end/doRepCust12/doRepCust.jsp">廠商檢舉管理</a>
                                </li>
                                <li>
                                    <a href="manager/company/getCompaniesByPage">廠商資料管理</a>
                                </li>
                                <li>
                                    <a href="back-end/roomOrder/roomOrder_index.jsp">訂房訂單管理</a>
                                </li>
                            </ul>
                        </li>
<!--                             ===================================票券分隔線================================================== -->
                        
                        <li id=tik>
                         <c:if test="${sessionScope.employeeVO.funcID==2}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>票券管理<span class="fa arrow"></span></a>
                                	</c:if>
                        <c:if test="${sessionScope.employeeVO.funcID==6}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>票券管理<span class="fa arrow"></span></a>
                                	</c:if>
                        
                        <c:if test="${sessionScope.employeeVO.funcID != 6 && sessionScope.employeeVO.funcID !=2 }">
                                		<a href="back-login/login/login-back-end-index.jsp" style="pointer-events: none"><i class="fa fa-sitemap fa-fw"></i>票券管理<span class="fa arrow"></span></a>
                                		</c:if>
                                		  
<!--                             <a href="#"><i class="fa fa-sitemap fa-fw"></i>票券管理<span class="fa arrow"></span></a> -->
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="back-end/ticket/ticket_index.jsp">票券資料管理</a>
                                </li>
                                <li>
                                    <a href="back-end/tktOrder/tktOrder_index.jsp">票券訂單管理</a>
                                </li>                 
                            </ul>
                        </li>
<!--                         ===================================會員分隔線================================================== -->
                         <li id=cust>
                          <c:if test="${sessionScope.employeeVO.funcID==3}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>會員管理<span class="fa arrow"></span></a>
                                	</c:if>
                        <c:if test="${sessionScope.employeeVO.funcID==6}">
                                		<a href="#"><i class="fa fa-sitemap fa-fw"></i>會員管理<span class="fa arrow"></span></a>
                                	</c:if>
                        
                        <c:if test="${sessionScope.employeeVO.funcID != 6 && sessionScope.employeeVO.funcID !=3 }">
                                		<a href="back-login/login/login-back-end-index.jsp" style="pointer-events: none"><i class="fa fa-sitemap fa-fw"></i>會員管理<span class="fa arrow"></span></a>
                                		</c:if>
<!--                             <a href="#"><i class="fa fa-sitemap fa-fw"></i>會員管理<span class="fa arrow"></span></a> -->
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="back-end/customer/custInfoManage.jsp">會員資料管理</a>
                                </li>
                                <li>
                                    <a href="custManage/ShowRepCmpManage">會員檢舉管理</a>
                                </li>
                                <li>
                                    <a href="back-end/customer/custPlatMailManage.jsp">會員訊息管理</a>
                                </li>                 
                            </ul>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>
        <script>

        document.getElementById('emp').addEventListener('click', listener);
        function listener(event){
        	if("${sessionScope.employeeVO.funcID==6 ? 'true' : 'false' }"==='true'){    		
        		
        	}else if ("${sessionScope.employeeVO.funcID!=1 ? 'true' : 'false' }"==='true'){
                alert('您沒有權限操作員工管理功能');
            }
        }
        </script>
         <script>
        document.getElementById('cust').addEventListener('click', listener);
        function listener(event){
			if("${sessionScope.employeeVO.funcID==6 ? 'true' : 'false' }"==='true'){    		
        		
        	}else if("${sessionScope.employeeVO.funcID!=3 ? 'true' : 'false' }"==='true'){
                alert('您沒有權限操作會員管理功能');
            }
        }
        </script>
        <script>
        document.getElementById('com').addEventListener('click', listener);
        function listener(event){
            if("${sessionScope.employeeVO.funcID==6 ? 'true' : 'false' }"==='true'){    		
        		
        	}else if("${sessionScope.employeeVO.funcID!=4 ? 'true' : 'false' }"==='true'){
                alert('您沒有權限操作廠商管理功能');
            }
        }  
        </script>
        <script>
        document.getElementById('tik').addEventListener('click', listener);
        function listener(event){
			if("${sessionScope.employeeVO.funcID==6 ? 'true' : 'false' }"==='true'){    		
        		
        	}else if("${sessionScope.employeeVO.funcID!=2 ? 'true' : 'false' }"==='true'){
                alert('您沒有權限操作票券管理功能');
            }
        }  
        </script>
        <script>
        document.getElementById('web').addEventListener('click', listener);
        function listener(event){
			if("${sessionScope.employeeVO.funcID==6 ? 'true' : 'false' }"==='true'){    		
        		
        	}else if ("${sessionScope.employeeVO.funcID!=5 ? 'true' : 'false' }"==='true'){
                alert('您沒有權限操作網頁管理功能');
            }
        }  
        
        </script>
        
       
</body>
</html>