<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/head.jsp"/>

<c:set var="projects" value="${requestScope.projects}"/>

<div class="pageTitle">Create new Task</div>

<form action="${pageContext.request.contextPath}/enter/task-create" method="POST">
    <div class="form-group">
        <label for="nameInput">Task Name</label>
        <input name="name" type="text" id="nameInput" class="form-control" placeholder="Task Name">
    </div>
    <div class="form-group">
        <label for="descriptionInput">Description</label>
        <input name="description" type="text" id="descriptionInput" class="form-control" placeholder="Description">
    </div>
    <div class="form-group">
        <label for="startDateInput">Start Date</label>
        <input name="startDate" type="date" id="startDateInput" class="form-control" placeholder="Start Date">
    </div>
    <div class="form-group">
        <label for="endDateInput">End Date</label>
        <input name="endDate" type="date" id="endDateInput" class="form-control" placeholder="End Date">
    </div>
    <div class="form-group">
        <label for="projectSelect">Project</label>
        <select name="projectId" id="projectSelect" class="form-control">
            <c:forEach var="project" items="${projects}">
                <option value="${project.getId()}">${project.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>

<jsp:include page="/WEB-INF/views/foot.jsp"/>