<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<FORM METHOD="post"
		ACTION="http://localhost:8081/CGA101G4/addOrder/updateEvaluate"
		name="form1">
		<table>
			<tr>
				<td>評價分數</td>
				<td>
				<select name="evaluateScore">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					</select></td>
				<td>${errorMsgs.reason}</td>
			</tr>
			<tr>
				<td>評價內文</td>
				<td><input type="text" name="evaluateMsg"
					 maxlength="100" size="100"></td><td>${errorMsgs.reason}</td>
			</tr>
		</table>
		<input type="hidden" name="roomItemId" value="${roomItemId}">
		 <input type="submit" value="發表評論">${errorMsgs.repCmp}

	</FORM>
</body>
</html>