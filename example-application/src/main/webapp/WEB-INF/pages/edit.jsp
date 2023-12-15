<%@ page language = "java" contentType = "text/html; charset = UTF-8" pageEncoding = "UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<html>

<head>
    <title>Edit Book</title>
</head>

<body>
    <h2>Edit Book</h2>

    <!-- "c:if" cho phép khởi chạy đoạn mã nếu đáp ứng được điều kiện --> 
    <c:if test="${Book no null}">

    <form action="${pageContext.request.contextPath}/Book/list" method="post">
        <input type="hidden" name="id" value="${Book.id}">
        <label>Title</label>
        <input type="text" name="title" value ="${Book.title}" required><br>
        <label>Author</label>
        <input type="text" name="author" value="${Book.author}"  required><br>
        <label>YearOfPublic</label>
        <input type="number" name="yearOfPublic" value="${Book.YearOfPublic}" required><br>
        <input type="submit" value="Save Changes">
    </form> 

    </c:if>

    <br>
    <a href="${pageContext.request.contextPath}/Book/list">Back to List</a>
    
</body>

</html>