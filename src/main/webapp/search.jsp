<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : search
    Created on : Mar 17, 2014, 3:20:50 PM
    Author     : mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1><sql:query var="v_books" dataSource="jdbc/bible">
            SELECT * FROM v_books
        </sql:query>

        <table border="1">
            <!-- column headers -->
            <tr>
                <c:forEach var="columnName" items="${result.columnNames}">
                    <th><c:out value="${columnName}"/></th>
                    </c:forEach>
            </tr>
            <!-- column data -->
            <c:forEach var="row" items="${result.rowsByIndex}">
                <tr>
                    <c:forEach var="column" items="${row}">
                        <td><c:out value="${column}"/></td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <form action="response.jsp">
            <form action="response.jsp">
                <strong>Select a subject:</strong>
                <select name="subject_id">
                    <c:forEach var="row" items="${v_books.rows}">
                        <option value="${row.id}">${row.book}</option>
                    </c:forEach>
                </select>
                <input type="text" name="chap_id" />  
                <input type="submit" value="submit" name="submit" />
            </form>

        </form>
    </body>
</html>
