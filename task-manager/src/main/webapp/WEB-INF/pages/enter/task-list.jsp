<%@ page import="ru.eremin.tm.model.dto.TaskDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    final Object tasksObject = request.getAttribute("tasks");
    final List<TaskDTO> tasks = (ArrayList<TaskDTO>) tasksObject;
%>

<%for (final TaskDTO task : tasks) {%>
        <h5><%=task.getId()%></h5>
        <h5><%=task.getName()%></h5>
        <h5><%=task.getDescription()%></h5>
        <h5><%=task.getStartDate()%></h5>
        <h5><%=task.getEndDate()%></h5>
        <h5><%=task.getStatus()%></h5>
        <h5><%=task.getProjectId()%></h5>
        <a href="/enter/task-remove?id=<%=task.getId()%>">Remove</a>
        <a href="/enter/task-edit?id=<%=task.getId()%>">Edit</a>
    <%}%>

    <a href="/enter/task-create">Create</a>