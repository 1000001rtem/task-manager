<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set var="projects" value="${requestScope.projects}"/>

<c:forEach var="project" items="${projects}">
    <h5>${project.getId()}</h5>
    <h5>${project.getName()}</h5>
    <h5>${project.getDescription()}</h5>
    <h5>${project.getStartDate()}</h5>
    <h5>${project.getEndDate()}</h5>
    <h5>${project.getStatus()}</h5>
    <a href="${pageContext.request.contextPath}/enter/project-remove?id=${project.getId()}">Remove</a>
    <a href="${pageContext.request.contextPath}/enter/project-edit?id=${project.getId()}">Edit</a>
</c:forEach>

<a href="${pageContext.request.contextPath}/enter/project-create">Create</a>
<a href="${pageContext.request.contextPath}/enter/task-list">Tasks</a>
