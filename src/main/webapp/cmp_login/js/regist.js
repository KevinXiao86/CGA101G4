//頁面加載完成
$(() => {
	//獲取令牌
	$.ajax({
		url: "http://localhost:8081/CGA101G4/company/getToken",
		type: "get",
		//如果返回的內容是 JSON，jQuery 會自動幫你解析成一個 JavaScript object
		dataType: "json",
		success: function(resp) {
			$("#token").val(resp);
		}
	});
	
	
    $("#cmpAccount").blur(() => {
        var cmpAccount = $("#cmpAccount").val();
        $.ajax({
            url: "http://localhost:8081/CGA101G4/company/existsCmpAccount",
            method: "get",
            //如果返回的內容是 JSON，jQuery 會自動幫你解析成一個 JavaScript object
            dataType: "json",
            data: {
                cmpAccount: cmpAccount
            },
            success: function (resp) {
                if(resp){
					$("#errorCmpAccount").html("帳號已存在");
				}else{
					$("#errorCmpAccount").html("帳號可用");
				}
            }
        });
    });


    $("#regist").click(() => {
        //1. 獲取廠商名稱
        var cmpName = $("#cmpName").val();
        //2. 檢查數據
        if (cmpName.length == 0) {
            //3. 提示用戶結果
            $("#errorMsg").html("請輸入廠商名稱");
            //5. 阻止提交
            return false;
        }


        //1. 獲取廠商電話
        var cmpTel = $("#cmpTel").val();
        //2. 檢查數據
        var cmpTelPatt = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
        if (!cmpTelPatt.test(cmpTel)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入廠商電話");
            //5. 阻止提交 
            return false;
        }


        //1. 獲取銀行行號
        var headBank = $("#headBank").val();
        var endBank = $("#endBank").val();
        //2. 檢查數據
        if (headBank.length == 0 || endBank.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的銀行帳號");
            //5. 阻止提交 
            return false;
        }
        var bankAccountPatt = /^[0-9]*$/;
        if (!bankAccountPatt.test(headBank)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的銀行帳號");
            //5. 阻止提交 
            return false;
        }
        if (!bankAccountPatt.test(endBank)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入有效的銀行帳號");
            //5. 阻止提交 
            return false;
        }


        //1. 獲取信箱
        var cmpMail = $("#cmpMail").val();
        //2. 檢查數據
        var cmpMailPatt = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
        if (!cmpMailPatt.test(cmpMail)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入正確的信箱格式");
            //5. 阻止提交 
            return false;
        }


        //1. 獲取廠商負責人
        var cmper = $("#cmper").val();
        //2. 檢查數據
        if (cmper.length == 0) {
            //3. 提示用戶結果
            $("#errorMsg").html("請輸入廠商負責人");
            //5. 阻止提交
            return false;
        }


        //1. 獲取廠商負責人電話
        var cmperTel = $("#cmperTel").val();
        //2. 檢查數據
        var cmperTelPatt = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
        if (!cmperTelPatt.test(cmperTel)) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入廠商負責人電話");
            //5. 阻止提交 
            return false;
        }


        //1. 獲取旅宿登記證
        var serialNo = document.querySelector('#serialNo');
        if(serialNo.files[0] == null){
           //4. 提示用戶結果
           $("#errorMsg").html("請上傳旅宿登記證");
           //5. 阻止提交 
           return false;
        }


        //1. 獲取帳號
        var cmpAccount = $("#cmpAccount").val();
        //2. 檢查數據
        if (cmpAccount.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入帳號");
            //5. 阻止提交 
            return false;
        }
        var cmpAccountPatt = /^\w{4,20}$/;
        if (!cmpAccountPatt.test(cmpAccount)) {
            // 提示用戶結果
            $("#errorMsg").html("帳號必須由字母, 數字下滑線組成,並且長度為 4 ~ 20 位");
            // 阻止提交 
            return false;
        }


        //1. 獲取密碼
        var cmpPassword = $("#cmpPassword").val();
        //2. 檢查數據
        if (cmpPassword.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入密碼");
            //5. 阻止提交 
            return false;
        }
        var cmpPasswordPatt = /^\w{4,20}$/;
        if (!cmpPasswordPatt.test(cmpPassword)) {
            // 提示用戶結果
            $("#errorMsg").html("密碼必須由字母, 數字下滑線組成,並且長度為 4 ~ 20 位");
            // 阻止提交 
            return false;
        }


        //1. 獲取飯店介紹
        var cmpIntroduce = $("#cmpIntroduce").val();
        if (cmpIntroduce.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入飯店介紹");
            //5. 阻止提交 
            return false;
        }

        //1. 獲取入住時間
        var checkinTime = $("#checkinTime").val();
        if (checkinTime.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請選擇入住時間");
            //5. 阻止提交 
            return false;
        }

        //1. 獲取退房時間
        var checkoutTime = $("#checkoutTime").val();
        if (checkoutTime.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請選擇退房時間");
            //5. 阻止提交 
            return false;
        }


        //1. 獲取地點
        var city = $("#city").val();
        var twon = $("#town").val();
        var road = $("#road").val();
        if (city.length == 0 || twon.length == 0 || road.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入地點");
            //5. 阻止提交 
            return false;
        }


        //1. 獲取購買須知
        var notice = $("#notice").val();
        if (notice.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入購買須知");
            //5. 阻止提交 
            return false;
        }


        //1. 獲取取消政策
        var canx = $("#canx").val();
        if (canx.length == 0) {
            //4. 提示用戶結果
            $("#errorMsg").html("請輸入取消政策");
            //5. 阻止提交 
            return false;
        }

        // 去掉錯誤訊息
        $("#errorMsg").html("");
    });
})