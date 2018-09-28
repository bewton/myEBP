<%@ page language="java" import="java.util.*,com.hgd.ebp.domain.User,com.hgd.ebp.util.WebUtil" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>用户管理</title>
	</head>
	<body>
		<table border="0" cellspacing="0" width="100%" height="560">
			<tr align="center" valign="middle" height="120">
				<td colspan="2">
					<h1>
						电子商务平台 — 后台
					</h1>
				</td>
			</tr>
			<tr align="center" valign="middle" height="1">
				<td colspan="2">
					<hr width="100%" height="1"/>
				</td>
			</tr>
			<tr>
				<td width="200">
					<table width="170">
						<tr align="center" valign="middle">
							<td>
								<a href="AdminIndex.jsp">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a><br/><br/>
								<a href="TicketManage.html">票项管理</a><br/><br/>
								<a href="UserListServlet">用户管理</a><br/><br/>
								<a href="OrderListServlet">订单管理</a><br/><br/>
								<a href="AdminLoginServlet">退&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出</a>
							</td>
							<td>
								<table style="height:410px;border-color:000000;border-left-style:solid;border-width:1px">
									<tr>
										<td valign="top"></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td>
					<!-- 不换行的form表单 -->
					<form action="UserListServlet" method="post" style="margin:0px;display:inline;">
						起始日期：<input type="text" name="starttime" value="${param.starttime}" size="8">
						&nbsp;&nbsp;&nbsp;&nbsp;
						终止日期：<input type="text" name="endtime" value="${param.endtime}" size="8">
						&nbsp;&nbsp;
						<input type="submit" name="searchUser" value="   查 询   ">
						<c:if test="${errMap['starttime'] != null}">
           					<font color=red size='-1'>${errMap['starttime']}</font>
           				</c:if>
           				<c:if test="${errMap['endtime'] != null}">
           					<font color=red size='-1'>${errMap['endtime']}</font>
           				</c:if>
					</form>
					<br/><br/>
					<form action="" method="post" style="margin:0px;display:inline;">
						姓名/用户名：<input type="text" name="userName" value="" size="4">
						&nbsp;&nbsp;&nbsp;&nbsp;
						身份证号：<input type="text" name="userIdNum" value="" size="12">
						&nbsp;&nbsp;&nbsp;&nbsp;
						联系电话：<input type="text" name="userPhone" value="" size="8">
						&nbsp;&nbsp;&nbsp;
						<input type="button" name="searchUser" value="   查 询   ">
					</form>
					<br/><br/>
					<table border="1" cellspacing="0" width="680">
						<tr align="center" valign="middle" height="50">
							<td width="6%"><font size="-1"><b>编号</b></font></td>
							<td width="6%"><font size="-1"><b>姓名</b></font></td>
							<td width="8%"><font size="-1"><b>用户名</b></font></td>
							<td width="6%"><font size="-1"><b>性别</b></font></td>
							<td width="20%"><font size="-1"><b>身份证号</b></font></td>
							<td width="6%"><font size="-1"><b>年龄</b></font></td>
							<td width="8%"><font size="-1"><b>头像</b></font></td>
							<td width="8%"><font size="-1"><b>联系电话</b></font></td>
							<td width="12%"><font size="-1"><b>注册时间</b></font></td>
							<td width="6%"><font size="-1"><b>账户余额</b></font></td>
							<td width="4%"><font size="-1"><b>状态</b></font></td>
							<td width="8%"></td>
						</tr>
						<c:forEach var="user" items="${userList}">
						<tr align="center" valign="middle">
							<td><font size="-1">${user.uid}</font></td>
							<td><font size="-1">${user.name}</font></td>
							<td><font size="-1">${user.username}</font></td>
							<td><font size="-1">${user.gender}</font></td>
							<td><font size="-1">${user.idcard}</font></td>
							<td><font size="-1">18</font></td>
							<td><font size="-1"><img src="../images/tomcat.gif" width="100%"></font></td>
							<td><font size="-1">${user.telno}</font></td>
							<td><font size="-1">${user.regtime}</font></td>
							<td><font size="-1" color="red">${user.balance}</font></td>
							<td><font size="-1">活动</font></td>
							<td>
								<input type="button" name="userStateChange" value="禁用">
							</td>
						</tr>
						</c:forEach>
					</table>
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href=UserListServlet?page=0><font size="-1" color="blue"><u>首页</u></a>
					&nbsp;&nbsp;
					<a href=UserListServlet?page=prev><font size="-1" color="blue"><u>上一页</u></font></a>
					&nbsp;&nbsp;
					<a href=UserListServlet?page=next><font size="-1" color="blue"><u>下一页</u></font></a>
					&nbsp;&nbsp;
					<a href=UserListServlet?page=last><font size="-1" color="blue"><u>末页</u></font></a>			
				</td>
			</tr>
		</table>
	</body>
</html>