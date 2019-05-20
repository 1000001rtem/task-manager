<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/head.jsp"/>


<c:set var="project" value="${requestScope.project}"/>
<c:set var="statuses" value="${requestScope.statuses}"/>

<div class="pageTitle">Edit Project</div>

<form action="${pageContext.request.contextPath}/enter/project-edit" method="POST">
    <input name="id" type="hidden" id="idInput" class="form-control" value="${project.getId()}">
    <div class="form-group">
        <label for="nameInput">Project Name</label>
        <input name="name" type="text" id="nameInput" class="form-control" value="${project.getName()}">
    </div>
    <div class="form-group">
        <label for="descriptionInput">Description</label>
        <input name="description" type="text" id="descriptionInput" class="form-control"
               value="${project.getDescription()}">
    </div>
    <div class="form-group">
        <label for="startDateInput">Start Date</label>
        <input name="startDate" type="date" id="startDateInput" class="form-control" value="${project.getStartDate()}">
    </div>
    <div class="form-group">
        <label for="endDateInput">End Date</label>
        <input name="endDate" type="date" id="endDateInput" class="form-control" value="${project.getEndDate()}">
    </div>
    <div class="form-group">
        <label for="statusSelect">Status</label>
        <select name="status" id="statusSelect" class="form-control">
            <c:forEach var="status" items="${statuses}">
                <option value="${status.getDisplayName()}">${status.getDisplayName()}</option>
            </c:forEach>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>

<jsp:include page="/WEB-INF/views/foot.jsp"/>