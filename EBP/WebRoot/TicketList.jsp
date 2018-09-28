<%@ page language="java" import="java.util.*,com.hgd.ebp.domain.Ticket,com.hgd.ebp.util.WebUtil" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>实时订票</title>
	</head>
	<body>
		<table border="0" cellspacing="0" width="100%" height="560">
			<tr align="center" valign="middle" height="80">
				<td colspan="2">
					<br/>
					<h1>
						电子商务平台
					</h1>
				</td>
			</tr>
			<tr align="right" valign="middle" height="1">
				<td colspan="2">
					<c:set var="loginStr">
        			<a href="./UserLogin.jsp"><font size="-1">登&nbsp;&nbsp;录</font></a>
					&nbsp;&nbsp;
					<a href="./UserRegister.jsp"><font size="-1">注&nbsp;&nbsp;册</font></a>
    				</c:set>
					<c:set var="logoutStr">
       				<font size="-1" color="blue">当前账户：${sessionScope.user.username}</font>
					&nbsp;&nbsp;
					<a href="./UserLoginServlet"><font size="-1" color="blue">退&nbsp;&nbsp;出</font></a>
    				</c:set>
    				${sessionScope.user == null ? loginStr : logoutStr}
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
								<a href="ClientIndex.jsp">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a><br/><br/>
								<a href="TicketListServlet">实时订票</a><br/><br/>
								<a href="./user/OrderList.html">我的订单</a><br/><br/>
								<a href="./user/UserInformation.html">会员信息</a><br/><br/>
								<a href="./user/Recharge.html">账户充值</a>
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
				<form action="TicketListServlet" method="post" style="margin:0px;display:inline;">
					请选择起始日期（查询7天以内票项）：<input type="text" name="starttime" value="${param.starttime}" size="8" />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" name="searchTicket" value="   查 询   " />
					<c:if test="${errMap['starttime'] != null}">
           					<font color=red size='-1'>${errMap['starttime']}</font>
           			</c:if>
				</form>
				<br/><br/>
					<form action="url" method="post">
						<table border="1" cellspacing="0" width="680">
							<tr align="center" valign="middle" height="50">
								<td width="6%"><font size="-1"><b>编号</b></font></td>
								<td width="20%"><font size="-1"><b>描&nbsp;&nbsp;&nbsp;&nbsp;述</b></font></td>
								<td width="12%"><font size="-1"><b>时&nbsp;&nbsp;间</b></font></td>
								<td width="12%"><font size="-1"><b>总票数</b></font></td>
								<td width="10%"><font size="-1"><b>剩余票数</b></font></td>
								<td width="10%"><font size="-1"><b>单价(元)</b></font></td>
								<td width="8%"><font size="-1"><b>状态</b></font></td>
								<td width="8%"><font size="-1"><b>购票数</b></font></td>
								<td width="18%"></td>
							</tr>
							<c:forEach var="ticket" items="${ticketList}">
	        					<tr align="center" valign="middle">
	            					<td><font size="-1">${ticket.tid}</font></td>
									<td><font size="-1">${ticket.des}</font></td>
									<td><font size="-1">${ticket.starttime}</font></td>
									<td><font size="-1">${ticket.amount}</font></td>
									<td><font size="-1">${ticket.balance}</font></td>
									<td><font size="-1">${ticket.price}</font></td>
									<td><font size="-1">售票中</font></td>
									<td><input type="text" size="1"></td>
									<td><input type="button" name="AddInCart" value="加入购物车"></td>
	        					</tr>
        					</c:forEach>
						</table>
						<br>
						<table>
							<tr border="0" align="left" valign="middle">
								<td width="200"><a href="./user/ShoppingCart.html"><input type="button" name="SeeShCart" value="查看购物车/结账" /></a></td>
								<td width="50"><a href="TicketListServlet?page=0"><font color="blue">首页</font></a></td>
								<td width="70"><a href="TicketListServlet?page=prev"><font color="blue">上一页</font></a></td>
								<td width="70"><a href="TicketListServlet?page=next"><font color="blue">下一页</font></a></td>
								<td width="70"><a href="TicketListServlet?page=${requestScope.lastPage}"><font color="blue">末页</font></a></td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
</html>