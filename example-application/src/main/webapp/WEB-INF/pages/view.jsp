<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

<head>
    <title>Books Details</title>
</head>

<body>
    <h2>Books Details</h2>

    <c:if test="${Book no null}">
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</tr>
            <th>Author</th>
            <th>Year of Public</th>
        </tr>

        <tr>
            <td>${Book.ID}</td>
            <td>${Book.Title}</td>
            <td>${Book.Author}</td>
            <td>${Book.yearOfPublic}</td>
        </tr>

    </table>

    </c:if>
    <br>
    
    <a href="${pageContext.request.contextPath}/Book/list">Back to List</a>
    
</body>

</html>