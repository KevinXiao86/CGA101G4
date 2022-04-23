<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>後臺廠商</title>
	<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
</head>
<body>
	<table>
		<tr>
			<td>廠商編號</td>
			<td>廠商名稱</td>
			<td>廠商電話</td>
			<td>廠商電子信箱</td>
			<td>廠商負責人</td>
			<td>廠商負責人電話</td>
			<td>廠商狀態</td>
			<td>停權/復權</td>
			<td>廠商審核狀態</td>
			<td>詳情</td>
		</tr>

		<c:forEach items="${requestScope.companies}" var="Company">
			<tr>
				<td>${Company.cmpId}</td>
				<td>${Company.cmpName}</td>
				<td>${Company.cmpTel}</td>
				<td>${Company.cmpMail}</td>
				<td>${Company.cmper}</td>
				<td>${Company.cmperTel}</td>
				<td>${Company.cmpStatus}</td>
				<td>
					<c:choose>
						<c:when test="${Company.cmpStatus == '正常'}">
							<a href="manager/company/updateStatus?cmpId=${Company.cmpId}&status=停權">停權</a>
						</c:when>

						<c:when test="${Company.cmpStatus == '停權'}">
							<a href="manager/company/updateStatus?cmpId=${Company.cmpId}&status=正常">複權</a>
						</c:when>
					</c:choose>
				</td>		
				<td>${Company.auditStatus}</td>
				<td><a href="manager/company/companyDetail?cmpId=${Company.cmpId}">查看詳情</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>