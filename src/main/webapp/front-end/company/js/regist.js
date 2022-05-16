//頁面加載完成
$(() => {
	//獲取令牌
//	$.ajax({
//		url: "http://localhost:8081/CGA101G4/company/getToken",
//		type: "get",
//		//如果返回的內容是 JSON，jQuery 會自動幫你解析成一個 JavaScript object
//		dataType: "json",
//		success: function(resp) {
//			$("#token").val(resp);
//		}
//	});
	
	
    $("#cmpAccount").blur(() => {
        var cmpAccount = $("#cmpAccount").val();
        $.ajax({
            url: "http://192.168.0.170:8081/CGA101G4/company/existsCmpAccount",
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


//	$("#cmpName").blur(() => {
//		//1. 獲取廠商名稱
//        var cmpName = $("#cmpName").val();
//        //2. 檢查數據
//        if (cmpName.length == 0) {
//            //3. 提示用戶結果
//			$("#cmpNameSpan").html("請輸入廠商名稱")
//            //5. 阻止提交
//            return false;
//        }
//	});

    
    

})