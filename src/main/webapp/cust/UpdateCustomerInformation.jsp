<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="SubmitCustomerInformation">
		<label>姓名: </label><input type="text" name="name"
			value="${param.name}">${errorMsgs.name}<br> <label>性別:
		</label><select name="sex">
			<option value="f" ${(param.sex=='f')?'selected':''}>女</option>
			<option value="m" ${(param.sex=='m')?'selected':''}>男</option>
		</select><br> <label>電話: </label><input type="text" name="tel"
			value="${param.tel}">${errorMsgs.tel} <br> <label>電子信箱:
		</label><input type="text" name="email" value="${param.email}">${errorMsgs.email}
		<br> <label>聯絡地址: </label><input type="text" name="address"
			value="${param.address}"><br> <label>身分證字號: </label><input
			type="text" name="idCard" value="${param.idCard}">
		${errorMsgs.idCard}<br> <label>生日: </label><input type="text"
			name="birth" value="${param.birth}"> <br> <label>帳號:
		</label><input type="text" name="account" value="${param.account}"><br>
		<label>密碼: </label><input type="text" name="password"
			value="${param.password}"><br> <label>圖片: </label><input
			type="text" name="img" value="${param.img}"> <br> <label>信用卡卡號:
		</label><input type="text" name="card" value="${param.card}"> <br>
		<input type="submit" value="修改完成"> <input type="hidden"
			name="custId" value="${param.custId}">
	</form>

</body>
</html>