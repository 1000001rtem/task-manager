<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="${pageContext.request.contextPath}/resources/packages/bootstrap-3.3.7-dist/css/bootstrap.min.css"
          rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
</head>
<body>
<header>
    <div class="container">
        <div class="menuBox">
            <div class="linkBox">
                <a href="${pageContext.request.contextPath}/">MAIN</a>
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
        </div>
    </div>
</header>
<div class="content">
    <div class="container">
