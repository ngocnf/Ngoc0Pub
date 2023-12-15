<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>

<html>

<head>
    <!--'meta charset="UTF-8"' Thuộc tính charset chỉ định việc mã hóa ký tự cho HTML doc -->
    <meta charset="UTF-8">
    <title>List of Books</title>
</head>

<body>
    <h2>List of Books</h2>
    
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Title</tr>
            <th>Author</th>
            <th>Year of Public</th>
        </tr>

        <c:forEach var="Book" items ="${Books}">
            <tr>
                <td>${Book.ID}</td>
                <td>${Book.Title}</td>
                <td>${Book.Author}</td>
                <td>${Book.yearOfPublic}</td>
            </tr>
        </c:forEach>

    </table>

</body>

</html>