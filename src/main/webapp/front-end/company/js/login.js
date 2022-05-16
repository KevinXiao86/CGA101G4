//頁面加載完成
$(() => {
    $("#login").click(() => {
        //1. 先獲取輸入框裡面的值
        var account = $("#cmpAccount").val();
        //2. 創建政則表達式
        var accountPatt = /^\w{4,20}$/;
        //3. 使用 test 方法做數據校驗
        //補充: test() 方法用於檢測一個字符串是否匹配某個模式，如果字符串中含有匹配的文本，則返回true，否則返回false。
        if (!accountPatt.test(account)) {
            //4. 提示用戶結果
            $("span.errorMsg").text("帳號必須由字母, 數字下滑線組成,並且長度為 4 ~ 20 位");
            //5. 阻止提交
            return false;
        }


        //1. 先獲取輸入框裡面的值
        var password = $("#cmpPassword").val();
        //2. 創建政則表達式
        var passwordPatt = /^\w{4,20}$/;
        //3. 使用 test 方法做數據校驗
        //補充: test() 方法用於檢測一個字符串是否匹配某個模式，如果字符串中含有匹配的文本，則返回true，否則返回false。
        if (!passwordPatt.test(password)) {
            //4. 提示用戶結果
            $("span.errorMsg").text("密碼必須由字母, 數字下滑線組成,並且長度為 4 ~ 20 位");
            //5. 阻止提交 
            return false;
        }

        // 去掉錯誤訊息
        $("span.errorMsg").text("");

        
        //檢查完數據之後, 把數據回傳給服務器
        $.ajax({
            url: "http://192.168.0.170:8081/CGA101G4/company/login",
            method: "get",
            //如果返回的內容是 JSON，jQuery 會自動幫你解析成一個 JavaScript object
            dataType: "json",
            data: {
                cmpAccount: account,
                cmpPassword: password
            },
            success: function (resp) {
                if (resp.successful) {
                    location.href = "http://192.168.0.170:8081/CGA101G4/" + resp.url;
                } else {
                    //回顯錯誤訊息
                    $("span.errorMsg").text(resp.message);
                }
            }
        });


        //先禁用默認行為,不然一點擊就不是ajax請求, 而只是超鏈接跳轉
        return false;
    });
});