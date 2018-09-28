<%@ page language="java" import="java.util.*,com.hgd.ebp.util.WebUtil,com.hgd.ebp.domain.Order" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>订单管理</title>
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
					<form action="OrderListServlet" method="post" style="margin:0px;display:inline;">
						起始日期：<input type="text" name="starttime" value="${param.starttime}" size="8">
						&nbsp;&nbsp;&nbsp;&nbsp;
						终止日期：<input type="text" name="endtime" value="${param.endtime}" size="8">
						&nbsp;&nbsp;
						<input type="submit" name="searchOrder" value="   查 询   ">
						<c:if test="${errMap['starttime'] != null}">
           					<font color=red size='-1'>${errMap['starttime']}</font>
           				</c:if>
           				<c:if test="${errMap['endtime'] != null}">
           					<font color=red size='-1'>${errMap['endtime']}</font>
           				</c:if>
					</form>
					<br/><br/>
					<form action="" method="GET" style="margin:0px;display:inline;">
						订单号：<input type="text" name="orderNumber" value="" size="7">
						&nbsp;&nbsp;&nbsp;&nbsp;
						姓名/用户名：<input type="text" name="userName" value="" size="4">
						&nbsp;&nbsp;&nbsp;&nbsp;
						身份证号：<input type="text" name="userIdNum" value="" size="12">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" name="searchOrder" value="   查 询   ">
					</form>
					<br/><br/>
					<table border="1" cellspacing="0" width="680">
						<tr align="center" valign="middle" height="50">
							<td width="6%"><font size="-1"><b>编号</b></font></td>
							<td width="8%"><font size="-1"><b>订单号</b></font></td>
							<td width="18%"><font size="-1"><b>订单内容</b></font></td>
							<td width="12%"><font size="-1"><b>成交时间</b></font></td>
							<td width="8%"><font size="-1"><b>金额(元)</b></font></td>
							<td width="8%"><font size="-1"><b>姓名</b></font></td>
							<td width="8%"><font size="-1"><b>用户名</b></font></td>
							<td width="16%"><font size="-1"><b>身份证号</b></font></td>
						</tr>
						<c:forEach var="order" items="${orderList}">
						<tr align="center" valign="middle">
							<td><font size="-1">${order.oid}</font></td>
							<td><font size="-1">${order.oid}</font></td>
							<td><font size="-1">爆笑青春怀旧话剧《曾经》西直门剧场</font></td>
							<td><font size="-1">${order.committime}</font></td>
							<td><font size="-1">${order.account}</font></td>
							<td><font size="-1">${order.name}</font></td>
							<td><font size="-1">${order.username}</font></td>
							<td><font size="-1">${order.idcard}</font></td>
						</tr>
						</c:forEach>
					</table>
					<br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<a href=OrderListServlet?page=0><font size="-1" color="blue"><u>首页</u></a>
					&nbsp;&nbsp;
					<a href=OrderListServlet?page=prev><font size="-1" color="blue"><u>上一页</u></font></a>
					&nbsp;&nbsp;
					<a href=OrderListServlet?page=next><font size="-1" color="blue"><u>下一页</u></font></a>
					&nbsp;&nbsp;
					<a href=OrderListServlet?page=last><font size="-1" color="blue"><u>末页</u></font></a>					
				</td>
			</tr>
		</table>
	</body>
</html>