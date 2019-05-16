<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/head.jsp"/>

<c:set var="tasks" value="${requestScope.tasks}"/>
<c:set var="projects" value="${requestScope.projects}"/>

<table class="entityTable">
    <caption>Tasks</caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
        <th>Project</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="task" items="${tasks}" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td width="10%">${task.getId()}</td>
            <td>${task.getName()}</td>
            <td>${task.getDescription()}</td>
            <td>${task.getStartDate()}</td>
            <td>${task.getEndDate()}</td>
            <td>${task.getStatus()}</td>
            <td>${projects.get(task.getProjectId()).getName()}</td>
            <td>
                <a href="${pageContext.request.contextPath}/enter/task-remove?id=${task.getId()}">&#x292B;</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/enter/task-edit?id=${task.getId()}">&#x2710;</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<form class="createEntityForm" action="${pageContext.request.contextPath}/enter/task-create" method="get">
    <button type="submit">Create new Task</button>
</form>

<jsp:include page="/WEB-INF/pages/foot.jsp"/>