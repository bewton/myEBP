<%@ page language="java" import="java.util.*,com.hgd.ebp.util.WebUtil" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>管理员登录</title>
	</head>
	<body>
		<br/><br/><br/><br/>
		<center>
			<table border="1" cellspacing="0" cellpadding="50">
				<tr align="center" valign="middle">
					<td width="400">
						<form action="AdminLoginServlet" method="post">
							<br/><br/>
							用户名：&nbsp;<input type="text" name="username" value="${param.username}">
							<br/>
							<c:if test="${errMap['username'] != null}">
           					<font color=red size='-1'>${errMap['username']}</font>
           					</c:if>
           					<c:if test="${errMap['global'] != null}">
           					<font color=red size='-1'>${errMap['global']}</font>
           					</c:if>
							<br/><br/>
							密&nbsp;&nbsp;&nbsp;&nbsp;码：&nbsp;<input type="password" name="password" value="${param.password}">
           					<br/>
           					<c:if test="${errMap['password'] != null}">
           					<font color=red size='-1'>${errMap['password']}</font>
           					</c:if>
           					<c:if test="${errMap['account'] != null}">
           					<font color=red size='-1'>${errMap['account']}</font>
           					</c:if>
							<br/><br/>
							验证码：&nbsp;<input type="text">
							<br/><br/><br/>
							<input type="submit" value=" 登 录 ">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="reset" value=" 重 置 ">
						<form>
					</td>
				</tr>
			</table>
		</center>
	</body>
</html>