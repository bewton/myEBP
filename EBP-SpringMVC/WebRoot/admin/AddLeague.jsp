<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加新联赛</title>
  </head>
  
  <body>
    <h3>添加新联赛</h3>
    <c:if test="${errMap['GLOBAL'] != null}">
        <p><font color=red size='-1'><i>${errMap['GLOBAL']}</i></font></p>
    </c:if>
    
    <form action='./AddLeagueCtrl' method=POST>
        <table>
            <tr>
                <td align=right>Year:<br/><br/></td>
                <td>
                    <input type=text name='year' value='${param.year}' />
                    <br/><br/>
                </td>
                <td>
                    <font color=red size='-1'>
                        <i>${errMap['year']}</i>
                    </font><br/><br/>
                </td>
            </tr>
            <tr>
                <td align=right>Season:<br/><br/></td>
                <td>
                    <input type=radio name='season' value='Spring' 
                        ${param.season == 'Spring' ? 'checked' : ''} />Spring
                    <input type=radio name='season' value='Summer'
                        ${param.season == 'Spring' ? 'checked' : ''} />Summer
                    <input type=radio name='season' value='Fall'
                        ${param.season == 'Fall' ? 'checked' : ''} />Fall
                    <input type=radio name='season' value='Winter'
                        ${param.season == 'Winter' ? 'checked' : ''} />Winter
                    <br/>
                </td>
                <td>
                    <font color=red size='-1'>
                        <i>${errMap['season']}</i>
                    </font><br/><br/>
                </td>
            </tr>
            <tr>
                <td align=right>Title:<br/><br/></td>
                <td>
                    <input type=text name='title' value='${param.title}' />
                    <br/><br/>
                </td>
                <td>
                    <font color=red size='-1'>
                        <i>${errMap['title']}</i>
                    </font><br/><br/>
                </td>
            </tr>
            <tr>
                <td align=right></td>
                <td><input type=submit value='添加' /></td>
            </tr>
        </table>
    </form>
  </body>
</html>
