<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

<head>
    <title>Add Book</title>
</head>

<body>
    <h2>Add Book</h2>
    
    <form action="${pageContext.request.contextPath}/Book/add" method="post" modelAttribute = "Book">
        <label>Title</label>
        <input type="text" name="title" required><br>
        <label>Author</label>
        <input type="text" name="author" required><br>
        <label>YearOfPublic</label>
        <input type="number" name="yearOfPublic" required><br>
        <input type="submit" value="Add Book">
    </form> 

    <br>
    <a href="${pageContext.request.contextPath}/Book/list">Back to List</a>
    
</body>

</html>