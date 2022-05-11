<%@page import="com.taiwan.beans.EmployeeVO"%>
<%@page import="com.taiwan.service.employee.EmployeeService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
//假設員工10000已登入
int i = 30001;
EmployeeService empSvc = new EmployeeService();
EmployeeVO empVO = empSvc.getOneEmp(i);
request.setAttribute("empVO", empVO);
%>
</head>
<body>
	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/repCust12/UpdateRepCust"
		name="form1">
		<table>
			<tr>
				<td>廠商編號:</td>
				<td>${repCustVO.cmpId}</td>
			</tr>
			<tr>
				<td>被檢舉會員編號:</td>
				<td>${repCustVO.custId}</td>
			</tr>
			<tr>
				<td>檢舉原因:</td>
				<td>${repCustVO.repCustReason}</td>
			</tr>
			<tr>
				<td>檢舉日期:</td>
				<td>${repCustVO.repCustDate}</td>
			</tr>
			<tr>
				<td>檢舉結果</td>
				<td><select name="repCustStatus">
						<option value="檢舉成功">檢舉成功</option>
						<option value="檢舉失敗">檢舉失敗</option>
				</select></td>
				<td>${errorMsgs.reason}</td>
			</tr>
			<tr>
				<td>處理結果</td>
				<td><input type="text" name=repCustResult maxlength="200"
					size="200"></td>
				<td>${errorMsgs.reason}</td>
			</tr>
		</table>
		<input type="hidden" name="empId"	value="${empVO.empId}"> 
		<input type="hidden" name="repCustId" value="${repCustVO.repCustId}"> 
		<input type="submit" value="處理檢舉">${errorMsgs.repCmp}

	</FORM>
</body>
</html>