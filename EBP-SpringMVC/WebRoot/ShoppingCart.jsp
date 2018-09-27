<%@ page import="java.util.*, com.hgd.ebp.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>购买球衣</title>
  </head>
  
  <body>
    <h3>购买球衣</h3>
    <c:if test="${requestScope.succ != null}">
        <p><i><font color=blue>${requestScope.succ}</font></i></p>
    </c:if>

    <form action='./AddShoppingCartCtrl' method=POST>
        <p>购买件数:
            <input type=radio name='num' value='1' checked />1件
            <input type=radio name='num' value='3' />3件
            <input type=radio name='num' value='5' />5件
        </p>
        <input type=submit value='添加购物车' />
    </form>
    <p><a href='./ShowShoppingCart.jsp'>查看购物车</a></p>
    <p><a href='./'>回首页</a></p>
  </body>
</html>
