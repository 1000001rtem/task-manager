<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
</head>
<body>
<header>
    <div class="linkBox">
        <a href="${pageContext.request.contextPath}/index">MAIN</a>
        |
        <a href="${pageContext.request.contextPath}/enter/project-list">PROJECTS</a>
        |
        <a href="${pageContext.request.contextPath}/enter/task-list">TASKS</a>
    </div>
    <div class="actionBox">
        <c:if test="${sessionScope.userRole.toString().equals('ADMIN')}">
            <a href="${pageContext.request.contextPath}/enter/admin/user-list">USERS</a>
            |
        </c:if>
        <c:if test="${sessionScope.userId != null}">
            <a href="${pageContext.request.contextPath}/logout">LOGOUT</a>
        </c:if>
        <c:if test="${sessionScope.userId == null}">
            <a href="${pageContext.request.contextPath}/registration">REGISTRATION</a>
        </c:if>

    </div>
</header>
<div class="content">