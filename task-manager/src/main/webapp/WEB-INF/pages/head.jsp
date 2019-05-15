<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/packages/styles/style.css">
</head>
<body>
<header>
    <div class="linkBox">
        <a href="/index">MAIN</a>
        |
        <a href="/enter/project-list">PROJECTS</a>
        |
        <a href="/enter/task-list">TASKS</a>
    </div>
    <div class="actionBox">
        <c:if test="${sessionScope.userRole.toString().equals('ADMIN')}">
            <a href="#">USERS</a>
            |
        </c:if>
        <a href="#">LOGOUT</a>
    </div>
</header>