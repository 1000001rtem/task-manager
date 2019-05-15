<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ru.eremin.tm.model.dto.TaskDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    final Object tasksObject = request.getAttribute("tasks");
    final List<TaskDTO> tasks = (ArrayList<TaskDTO>) tasksObject;
%>

<c:forEach var="task" items="${tasks}">
    <h5>${task.getId()}</h5>
    <h5>${task.getName()}</h5>
    <h5>${task.getDescription()}</h5>
    <h5>${task.getStartDate()}</h5>
    <h5>${task.getEndDate()}</h5>
    <h5>${task.getStatus()}</h5>
    <h5>${task.getProjectId()}</h5>
    <a href="${pageContext.request.contextPath}/enter/task-remove?id=${task.getId()}">Remove</a>
    <a href="${pageContext.request.contextPath}/enter/task-edit?id=${task.getId()}">Edit</a>
</c:forEach>

<a href="/enter/task-create">Create</a>