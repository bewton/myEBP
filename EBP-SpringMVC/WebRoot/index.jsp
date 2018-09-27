<%@ page import="java.util.*, com.hgd.ebp.domain.League" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>足球联赛首页</title>
  </head>
  
  <body>
    <p>球&nbsp;&nbsp;员</p>
    <p><a href='./ListLeaguesCtrl'>列出所有联赛</a></p>
    <p><a href='./ShoppingCart.jsp'>购买球衣</a></p>
    
    <c:set var="loginStr">
        <a href='./admin/Login.jsp'>登 录</a>
    </c:set>

    <c:set var="logoutStr">
        <b>当前用户：</b>${sessionScope.adminUser.username} &nbsp;&nbsp
        <a href='./admin/LogoutCtrl'>退 出</a>
    </c:set>
    
    <p>管理员&nbsp;&nbsp;<font size=-1>（${sessionScope.adminUser == null ? 
        loginStr : logoutStr}）</font></p>
    
    <p><a href='./admin/AddLeague.jsp'>添加新联赛</a></p>
  </body>
</html>
