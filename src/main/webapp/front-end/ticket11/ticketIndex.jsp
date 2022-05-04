<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Ticket Home</title>
<%-- 靜態包含 base標籤,css樣式,jQuery文件 --%>
	<%@ include file="/common/head.jsp"%>
<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>Ticket</h3><h4>( MVC )</h4></td></tr>
</table>

<p>HI</p>

<h3>資料查詢:</h3>
<ul>
	<li>
		<Form action="ticket/getAll" method="post">
	  		<input type="submit" value="all Tickets.">  <br><br>
	  	</Form>
  	</li>
  
  <li>
    <FORM METHOD="post" ACTION="ticket/selectId" >
        <b>輸入票券編號:</b><font color=red>${errorMsgs.tktId}</font><br>
        <input type="text" name="tktId" value="${param.tktId}">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>
</ul>


</body>
</html>