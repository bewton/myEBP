<%@ page import="java.util.*, com.hgd.ebp.domain.League" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>列出所有联赛</title>
  </head>
  
  <body>
    <table width=500>
        <tr>
            <td align=center>
			    <h3>列出所有联赛</h3>
			    <hr/>
			    <c:if test="${errMap['GLOBAL'] != null}">
			        <p><font color=red size='-1'><i>${errMap['GLOBAL']}</i></font></p>
			    </c:if>
            </td>
        </tr>
        <tr>
            <td align=right>
			    <form action='./ListLeaguesCtrl' method='POST'>
			        Year: <input type=text name='year' size=6/>&nbsp;
			        Season: 
			        <select name='season' >
			            <option value=''>未选择
			            <option value='Spring'>Spring
			            <option value='Summer'>Summer
			            <option value='Fall'>Fall
			            <option value='Winter'>Winter
			        </select>
			        <input type=submit value='查 询' />
			    </form>
			</td>
	    </tr>
	</table>
    <table border=1 width=500>
        <tr>
            <th>Lid</th>
            <th>Year</th>
            <th>Season</th>
            <th>Title</th>
        </tr>
        <c:forEach var="league" items="${listleagues}">
	        <tr>
	            <td>${league.lid}</td>
	            <td>${league.year}</td>
	            <td>${league.season}</td>
	            <td>${league.title}</td>
	        </tr>
        </c:forEach>
      </table><br/>
      <table width=500>
        <tr>
            <td align=center>
                <a href='./ListLeaguesCtrl?page=0'>首 页</a>&nbsp;&nbsp;&nbsp;&nbsp;  
                <a href='./ListLeaguesCtrl?page=prev'>上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href='./ListLeaguesCtrl?page=next'>下一页</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <a href='./ListLeaguesCtrl?page=${requestScope.lastPage}'>末 页</a>
            </td> 
        </tr>
      </table>
      
      <p><a href='./'>回首页</a></p>
  </body>
</html>
