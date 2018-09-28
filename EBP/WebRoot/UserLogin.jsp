<%@ page language="java" import="java.util.*,com.hgd.ebp.util.WebUtil" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
     <title>用户登录</title>
</head>
<body>
   <div id="div_form" style="width:500px;height:400px;border:solid 1px;margin-left:190px;margin-top:110px">
     <form method="post" action="UserLoginServlet">
	    <div style="position:absolute;left:320px;top:220px">  
           <div id="div_uname" style="position:relative;float:left;margin:20px">用户名:&nbsp&nbsp&nbsp&nbsp<INPUT  type="text" name="username" value="${param.username}">
           <c:if test="${errMap['username'] != null}">
           <font color=red size='-1'>${errMap['username']}</font>
           </c:if>
           <c:if test="${errMap['global'] != null}">
           <font color=red size='-1'>${errMap['global']}</font>
           </c:if>
           </div>
           <br>
		   
           <div id="div_upwd"  style="position:relative;float:left;margin:20px">密&nbsp&nbsp&nbsp码:&nbsp&nbsp&nbsp&nbsp<INPUT  type="password" name="password" value="${param.password}">
           <c:if test="${errMap['password'] != null}">
           <font color=red size='-1'>${errMap['password']}</font>
           </c:if>
           <c:if test="${errMap['account'] != null}">
           <font color=red size='-1'>${errMap['account']}</font>
           </c:if>
           </div>
           <br>
           
           <div id="div_uidcode" style="position:relative;float:left;margin-top:20px;margin-bottom:20px;margin-left:20px;margin-right:20px;">验证码:&nbsp&nbsp&nbsp&nbsp<INPUT  type="text" name="vertifycode"></div>
           <br>
           
           <div style="position:relative;float:left;margin-left:20px;margin-top:10px">
           <INPUT type="submit"  value="登录">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
           <INPUT type="reset"  value="重 置">&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
           <a href="UserRegister.jsp"><input type="button" value="注册" /></a>
           </div>
          </div>	
       </form>	
	 </div> 
</body>
</html>