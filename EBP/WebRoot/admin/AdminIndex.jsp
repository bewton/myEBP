<%@ page language="java" import="java.util.*,com.hgd.ebp.util.WebUtil" pageEncoding="utf-8"%>
<html>
	<head>
		<title>管理首页</title>
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
					<!-- Global错误显示区域 -->
					<font size="-1" color="red">
					<c:if test="${errMap['global'] != null}">
					${errMap['global']}
					</c:if>
					</font>
					<br/>
					<b><font size="4">项目目标</font></b>
					<br/>
					<li>实现一个真实的、基于RIA的电子商务平台（EBP）系统</li>
					<li>增量式开发，循序渐进完成项目</li>
					<li>掌握基于SSM架构的RIA Web应用程序的编程和调试技巧</li>
					<li>主要涉及以下知识点：</li>
					<table border="0">
						<tr>
							<td>
								<ul>
									<li>SVN版本控制+Maven项目管理</li>
									<li>Spring MVC-Spring-MyBatis架构</li>
									<li>SpringMVC标签与表单</li>
									<li>RDBMS与MongoDB数据库</li>
									<li>HTML5、AJAX、JavaScript</li>
									<li>jQuery与jQuery EasyUI框架</li>
									<li>Redis内存数据库</li>
									<li>Nginx + Tomcat集群</li>
									<li>性能与压力测试</li>
								</ul>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>