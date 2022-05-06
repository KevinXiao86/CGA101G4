<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>台玩</title>
	<link rel="icon" href="<%=request.getContextPath()%>/static/img/core-img/favicon.ico">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/front-main/style.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/carstyle.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/carstyle2.css" />
    <link
        href="https://fonts.googleapis.com/css?family=Source+Sans+3:200,300,regular,500,600,700,800,900,200italic,300italic,italic,500italic,600italic,700italic,800italic,900italic"
        rel="stylesheet" />
    <script src="https://kit.fontawesome.com/c95ced1229.js" crossorigin="anonymous"></script>

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
                    <div class="col-12 col-sm-5" style="margin-left:480px">
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

    <!-- 頁面 -->
    <form action="#" class="form">
        <h3 class="text-center">結帳</h3>
        <!-- Progress bar -->
        <div class="progressbar">
            <div class="progress" id="progress"></div>
            <div class="progress-step progress-step-active" data-title="商品確認"></div>
            <div class="progress-step" data-title="填寫資料"></div>
            <div class="progress-step" data-title="訂單確認"></div>
        </div>

        <div class="form-step">
            <div>
                <a href="#" class="btn btn-next width-50 ml-auto">Next</a>
            </div>
        </div>
    </form>
   
   	<!-- #### Footer start #### -->
	<jsp:include page="/front-end/homepage/footer.jsp"></jsp:include>

    <script src="<%=request.getContextPath()%>/static/js/front-main/jquery/jquery-2.2.4.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/plugins.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/active.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/front-main/script.js"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>

    <!-- ionicon link -->
    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>


    <script>
        $(function () {
            $("#sendOrder").click(function () {
                Swal.fire({
                    position: 'center',
                    icon: 'success',
                    title: '訂單已成立'
                })
                // Swal.fire({
                //     title: 'Custom width, padding, color, background.',
                //     width: 600,
                //     padding: '3em',
                //     color: '#716add',
                //     background: '#fff url(/images/trees.png)',
                //     backdrop: ` rgba(0,0,123,0.4)
                //                 url("/images/nyan-cat.gif")
                //                 left top
                //                 no-repeat
                //               `
                // })
            });
        });


        const prevBtns = document.querySelectorAll(".btn-prev");
        const nextBtns = document.querySelectorAll(".btn-next");
        const progress = document.getElementById("progress");
        const formSteps = document.querySelectorAll(".form-step");
        const progressSteps = document.querySelectorAll(".progress-step");

        let formStepsNum = 0;

        nextBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                formStepsNum++;
                updateFormSteps();
                updateProgressbar();
            });
        });

        prevBtns.forEach((btn) => {
            btn.addEventListener("click", () => {
                formStepsNum--;
                updateFormSteps();
                updateProgressbar();
            });
        });

        function updateFormSteps() {
            formSteps.forEach((formStep) => {
                formStep.classList.contains("form-step-active") &&
                    formStep.classList.remove("form-step-active");
            });

            formSteps[formStepsNum].classList.add("form-step-active");
        }

        function updateProgressbar() {
            progressSteps.forEach((progressStep, idx) => {
                if (idx < formStepsNum + 1) {
                    progressStep.classList.add("progress-step-active");
                } else {
                    progressStep.classList.remove("progress-step-active");
                }
            });

            const progressActive = document.querySelectorAll(".progress-step-active");

            progress.style.width =
                ((progressActive.length - 1) / (progressSteps.length - 1)) * 100 + "%";
        }

        // 信用卡
        $("input.card").on("keydown", function (e) {
            //console.log(e.which);
            if ((e.which >= 48 && e.which <= 57) || e.which == 8) {

                //console.log(e.target.value.length);

                if (e.target.value.length == 0 && e.which == 8) {
                    $(this).prev().focus();
                }

            } else {
                e.preventDefault();
            }
        });

        $("input.card").on("keyup", function (e) {

            // \D 代表非數字字元，g 代表全域尋找
            //let str = e.target.value;
            //console.log(e.target.value);

            let str = (e.target.value).replace(/\D/g, "");
            //console.log(str);

            $(this).val(str);

            //console.log(str.length);

            if (str.length == 4) {
                $(this).next().focus();
            }

        });
    </script>

</body>

</html>


<!-- == -->
<!-- 
訂單編號
訂單明細

寄信 -->