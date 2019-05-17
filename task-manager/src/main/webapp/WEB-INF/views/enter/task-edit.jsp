<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/head.jsp"/>


<c:set var="projects" value="${requestScope.projects}"/>
<c:set var="task" value="${requestScope.task}"/>

<div class="pageTitle">Edit Task</div>

<form action="${pageContext.request.contextPath}/enter/task-edit" method="POST">
    <input name="id" class="form-control" type="hidden" value="${task.getId()}">
    <div class="form-group">
        <label for="nameInput">Task Name</label>
        <input name="name" type="text" id="nameInput" class="form-control" value="${task.getName()}">
    </div>
    <div class="form-group">
        <label for="descriptionInput">Description</label>
        <input name="description" type="text" id="descriptionInput" class="form-control"
               value="${task.getDescription()}">
    </div>
    <div class="form-group">
        <label for="startDateInput">Start Date</label>
        <input name="startDate" type="date" id="startDateInput" class="form-control" value="${task.getStartDate()}">
    </div>
    <div class="form-group">
        <label for="endDateInput">End Date</label>
        <input name="endDate" type="date" id="endDateInput" class="form-control" value="${task.getEndDate()}">
    </div>
    <div class="form-group">
        <label for="statusInput">Status</label>
        <input name="status" type="text" id="statusInput" class="form-control" value="${task.getStatus()}">
    </div>
    <div class="form-group">
        <label for="projectSelect">Project</label>
        <select name="projectId" id="projectSelect" class="form-control">
            <c:forEach var="project" items="${projects.values()}">
                <option value="${project.getId()}">${project.getName()}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>

<%--<form class="saveEntityForm" action="${pageContext.request.contextPath}/enter/task-edit" method="POST">--%>
<%--<table class="createEntityTable">--%>
<%--<tr>--%>
<%--<td><input name="id" type="hidden" value="${task.getId()}"></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Task Name:</td>--%>
<%--<td><input name="name" type="text" value="${task.getName()}"></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Task Description:</td>--%>
<%--<td><input name="description" type="text" value="${task.getDescription()}"/></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Start Date:</td>--%>
<%--<td><input name="startDate" type="date" value="${task.getStartDate()}"/></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>End Date:</td>--%>
<%--<td><input name="endDate" type="date" value="${task.getEndDate()}"/></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<td>Status:</td>--%>
<%--<td><input name="status" type="text" value="${task.getStatus()}"/></td>--%>
<%--</tr>--%>
<%--<tr>--%>
<%--<tr>--%>
<%--<td>Project Id:</td>--%>
<%--<td>--%>

<%--<select name="projectId">--%>
<%--<option disabled selected>${projects.get(task.getProjectId()).getName()}</option>--%>
<%--<c:forEach var="project" items="${projects.values()}">--%>
<%--<option value="${project.getId()}">${project.getName()}</option>--%>
<%--</c:forEach>--%>
<%--</select>--%>
<%--</td>--%>
<%--</tr>--%>
<%--<td colspan="2">--%>
<%--<button type="submit">Save</button>--%>
<%--</td>--%>
<%--</tr>--%>
<%--</table>--%>
<%--</form>--%>
<jsp:include page="/WEB-INF/views/foot.jsp"/>