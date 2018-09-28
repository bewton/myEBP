<%@ page language="java" import="java.util.*,com.hgd.ebp.util.WebUtil" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>用户注册</title>
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
					<hr width="100%"></hr>
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
					<form action="UserRegisterServlet" method="post">
					<table border="0" cellspacing="0">
						<tr>
							<td width="150" rowspan="3" >
							<div style="padding-left:30px;"><img src="./images/tomcat.gif" width="150" height="135"></img></div>
							</td>
							<td width="350" height="45" align="right" >
							<span style="padding-right:50px;">用&nbsp&nbsp户&nbsp名:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="text" name="username" value="${param.username}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['username'] != null}">
           					<font color=red size='-1'>${errMap['username']}</font>
           					</c:if>
							</td>
						</tr>
						<tr >
							<td width="350" height="45" align="right">
							<span style="padding-right:50px;">密&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp码:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="password" name="password" value="${param.password}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['password'] != null}">
           					<font color=red size='-1'>${errMap['password']}</font>
           					</c:if>
							</td>
						</tr>
						<tr >
							<td width="350" height="45" align="right">
							<span style="padding-right:50px;">密码确认:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="password" name="repassword" value="${param.repassword}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['repassword'] != null}">
           					<font color=red size='-1'>${errMap['repassword']}</font>
           					</c:if>
							</td>
						</tr>
						<tr>
						    <td colspan="3" height="45" >								
							<hr></hr>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="45" align="right">
							<span style="padding-right:50px;">真实姓名:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="text" name="name" value="${param.name}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['name'] != null}">
           					<font color=red size='-1'>${errMap['name']}</font>
           					</c:if>
							</td>
						</tr>
						<tr>
						<tr>
						    
							<td colspan="2" height="45" align="right">
							<div style="padding-right:100px;">性&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp别:&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="radio" name="gender" value="男" ${param.gender == '男' ? 'checked' : ''}>男
							&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="radio" name="gender" value="女" ${param.gender == '女' ? 'checked' : ''}>女
							</td>
							<td>
							<c:if test="${errMap['gender'] != null}">
           					<font color=red size='-1'>${errMap['gender']}</font>
           					</c:if>
							</div>
							</td>
						</tr>   
							<td colspan="2" height="45" align="right">
							<span style="padding-right:50px;">身份证号:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="text" name="idcard" value="${param.idcard}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['idcard'] != null}">
           					<font color=red size='-1'>${errMap['idcard']}</font>
           					</c:if>
							</td>
						</tr>
						</tr>   
							<td colspan="2" height="45" align="right">
							<span style="padding-right:50px;">年&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp龄:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="text" name="age" value="${param.age}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['age'] != null}">
           					<font color=red size='-1'>${errMap['age']}</font>
           					</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="45" align="right">
							<span style="padding-right:50px;">通讯地址:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="text" name="address" value="${param.address}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['address'] != null}">
           					<font color=red size='-1'>${errMap['address']}</font>
           					</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="45" align="right">
							<span style="padding-right:50px;">联系电话:&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="text" name="telno" value="${param.telno}" style="width:200"></span>
							</td>
							<td>
							<c:if test="${errMap['telno'] != null}">
           					<font color=red size='-1'>${errMap['telno']}</font>
           					</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="2" height="45" align="right">
							<span style="padding-right:90px;"><INPUT  type="submit" style="width:65;height:30px;background-color:#FF4500;" value="注册">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
							<INPUT  type="reset" style="width:65;height:30px;background-color:#FF4500;" value="重置"></span>
							</td>
							</td>
							<td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
		
	</body>
</html>