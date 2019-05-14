<%@ page import="ru.eremin.tm.model.dto.ProjectDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    final Object projectsObject = request.getAttribute("projects");
    final List<ProjectDTO> projects = (ArrayList<ProjectDTO>) projectsObject;
%>

<%for (final ProjectDTO project : projects) {%>
        <h5><%=project.getId()%></h5>
        <h5><%=project.getName()%></h5>
        <h5><%=project.getDescription()%></h5>
        <h5><%=project.getStartDate()%></h5>
        <h5><%=project.getEndDate()%></h5>
        <h5><%=project.getStatus()%></h5>
        <a href="/enter/project-remove?id=<%=project.getId()%>">Remove</a>
        <a href="/enter/project-edit?id=<%=project.getId()%>">Edit</a>
    <%}%>

    <a href="/enter/project-create">Create</a>
    <a href="/enter/task-list">Tasks</a>
