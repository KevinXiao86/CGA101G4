/**
 * 
 */
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


	$("#btnEdit").click(() => {
		//1. 獲取廠商名稱
		var cmpName = $("#cmpName").val();
		//2. 檢查數據
		if (cmpName.length == 0) {
			//3. 提示用戶結果
			$(".errorMsg").html("請輸入廠商名稱");
			//5. 阻止提交
			return false;
		}


		//1. 獲取廠商電話
		var cmpTel = $("#cmpTel").val();
		//2. 檢查數據
		var cmpTelPatt = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
		if (!cmpTelPatt.test(cmpTel)) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入廠商電話");
			//5. 阻止提交 
			return false;
		}


		//1. 獲取銀行行號
		var headBank = $("#headBank").val();
		var endBank = $("#endBank").val();
		//2. 檢查數據
		if (headBank.length == 0 || endBank.length == 0) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入有效的銀行帳號");
			//5. 阻止提交 
			return false;
		}
		var bankAccountPatt = /^[0-9]*$/;
		if (!bankAccountPatt.test(headBank)) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入有效的銀行帳號");
			//5. 阻止提交 
			return false;
		}
		if (!bankAccountPatt.test(endBank)) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入有效的銀行帳號");
			//5. 阻止提交 
			return false;
		}
		var bankAccount = headBank + endBank;

		//1. 獲取信箱
		var cmpMail = $("#cmpMail").val();
		//2. 檢查數據
		var cmpMailPatt = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;
		if (!cmpMailPatt.test(cmpMail)) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入正確的信箱格式");
			//5. 阻止提交 
			return false;
		}


		//1. 獲取廠商負責人
		var cmper = $("#cmper").val();
		//2. 檢查數據
		if (cmper.length == 0) {
			//3. 提示用戶結果
			$(".errorMsg").html("請輸入廠商負責人");
			//5. 阻止提交
			return false;
		}


		//1. 獲取廠商負責人電話
		var cmperTel = $("#cmperTel").val();
		//2. 檢查數據
		var cmperTelPatt = /^(\(\d{3,4}\)|\d{3,4}-|\s)?\d{7,14}$/;
		if (!cmperTelPatt.test(cmperTel)) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入廠商負責人電話");
			//5. 阻止提交 
			return false;
		}

		//1. 獲取飯店介紹
		var cmpIntroduce = $("#cmpIntroduce").val();
		if (cmpIntroduce.length == 0) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入飯店介紹");
			//5. 阻止提交 
			return false;
		}

		//1. 獲取入住時間
		var checkinTime = $("#checkinTime").val();
		if (checkinTime.length == 0) {
			//4. 提示用戶結果
			$(".errorMsg").html("請選擇入住時間");
			//5. 阻止提交 
			return false;
		}

		//1. 獲取退房時間
		var checkoutTime = $("#checkoutTime").val();
		if (checkoutTime.length == 0) {
			//4. 提示用戶結果
			$(".errorMsg").html("請選擇退房時間");
			//5. 阻止提交 
			return false;
		}


		//1. 獲取地點
		var city = $("#city").val();
		var twon = $("#town").val();
		var road = $("#road").val();
		var location = city + twon + road;
		if (city.length == 0 || twon.length == 0 || road.length == 0) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入地點");
			//5. 阻止提交 
			return false;
		}


		//1. 獲取購買須知
		var notice = $("#notice").val();
		if (notice.length == 0) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入購買須知");
			//5. 阻止提交 
			return false;
		}


		//1. 獲取取消政策
		var canx = $("#canx").val();
		if (canx.length == 0) {
			//4. 提示用戶結果
			$(".errorMsg").html("請輸入取消政策");
			//5. 阻止提交 
			return false;
		}

		// 去掉錯誤訊息
		$(".errorMsg").html("");

	});
});