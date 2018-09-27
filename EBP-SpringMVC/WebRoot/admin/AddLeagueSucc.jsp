<%@ page import="java.util.*, com.hgd.ebp.domain.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加新联赛成功</title>
  </head>
  
  <body>
    <%
        League l = (League)request.getAttribute("league");
    %>
    <h3>添加新联赛成功</h3>
    <p>Lid: ${league.lid}</p>
    <p>Year: ${league.year}</p>
    <p>Season: ${league.season}</p>
    <p>Title: ${league.title}</p>
    
    <p><a href='../'>回首页</a></p>
  </body>
</html>
