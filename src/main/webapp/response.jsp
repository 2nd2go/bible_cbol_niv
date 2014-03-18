<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : response
    Created on : Mar 17, 2014, 4:33:33 PM
    Author     : mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<sql:query var="result" dataSource="jdbc/bible">
    SELECT * FROM bible WHERE book=? <sql:param value="${param.subject_id}"/>
    AND CHAPTER=?<sql:param value="${param.chap_id}"/>

    ORDER BY BOOK,CHAPTER, VERSE, VERSION
</sql:query>




<c:set var="counselorDetails" value="${query.rows[0]}"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <h1>xxxxxxxxxxxxxxxxx</h1>

        <table border="0">


            <c:forEach var="row" items="${result.rowsByIndex}">
                <tr>
<!--                    <td><c:out value="${row[3]}"/></td>-->
                    <td><sup><c:out value="${row[3]}"/></sup><c:out value="${row[4]}"/><br><br></td>




                </tr>
            </c:forEach>
        </table>


    </body>
</html>
