<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import='java.util.*, com.hgd.ebp.util.*' %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
    <head>
        <title>管理员登录</title>
    </head>
    <body>
        <h3>管理员登录</h3>
        <c:if test="${errMap['GLOBAL'] != null}">
            <p><font color=red size='-1'><i>${errMap['GLOBAL']}</i></font></p>
        </c:if>
	    
	    <form action='./LoginCtrl' method=POST>
	        <table>
	            <tr>
	                <td align=right>用户名:<br/><br/></td>
	                <td>
	                    <input type=text name='username' value='${param.username}' />
	                    <br/>
	                </td>
	                <td>
	                    <font color=red size='-1'>
	                        <i>${errMap['username']}</i>
	                    </font><br/><br/>
	                </td>
	            </tr>
	            <tr>
	                <td align=right>密&nbsp;&nbsp;&nbsp;&nbsp;码:<br/><br/></td>
	                <td>
	                    <input type=password name='password' value='' />
	                    <br/><br/>
	                </td>
	                <td>
	                    <font color=red size='-1'>
	                        <i>${errMap['password']}</i>
	                    </font><br/><br/>
	                </td>
	            </tr>
	            <tr>
	                <td align=right></td>
	                <td><input type=submit value='登 录' /></td>
	            </tr>
	        </table>
	    </form>
    </body>
</html>

