<%@ page import="java.util.*, com.hgd.ebp.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看购物车</title>
  </head>
  
  <body>
    <h3>查看购物车</h3>
    <ul>
        <% int total = 0; %>
        <c:forEach var="num" items="${numList}">
            <li>${num}件球衣</li>
            <% total += (Integer)pageContext.getAttribute("num"); %>
        </c:forEach>
    </ul>
    <p>总计：<%= total %>件球衣</p>
    <p><a href='./'>回首页</a></p>
  </body>
</html>
