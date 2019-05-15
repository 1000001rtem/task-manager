<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/head.jsp"/>
<c:set var="projects" value="${requestScope.projects}"/>

<table class="entityTable">
    <caption>Projects</caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Status</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach var="project" items="${projects}" varStatus="loop">
            <td>${loop.count}</td>
            <td width="10%">${project.getId()}</td>
            <td>${project.getName()}</td>
            <td>${project.getDescription()}</td>
            <td>${project.getStartDate()}</td>
            <td>${project.getEndDate()}</td>
            <td>${project.getStatus()}</td>
            <td>
                <a href="${pageContext.request.contextPath}/enter/project-remove?id=${project.getId()}">&#x292B;</a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/enter/project-edit?id=${project.getId()}">&#x2710;</a>
            </td>
        </c:forEach>
    </tr>
    </tbody>
</table>


<form class="createEntityForm" action="${pageContext.request.contextPath}/enter/project-create" method="get">
    <button type="submit">Create new Project</button>
</form>

<jsp:include page="/WEB-INF/pages/foot.jsp"/>