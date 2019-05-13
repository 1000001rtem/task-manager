<%@ page import="ru.eremin.tm.model.dto.ProjectDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>edit</h1>
<%
    final Object projectObject = request.getAttribute("project");
    final ProjectDTO project = (ProjectDTO) projectObject;
%>
<h5><%=project.getName()%></h5>
<form action = "/enter/project-edit" method="POST">

      <table>
        <tr>
            <td><input name="id" type="hidden" value="<%=project.getId()%>"></td>
        </tr>
        <tr>
                    <td>Project Name:</td>
                    <td><input name="name" type="text" value="<%=project.getName()%>"></td>
                </tr>
        <tr>
            <td>Project Description:</td>
            <td><input name="description" type="text" value="<%=project.getDescription()%>"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input name="startDate" type="date" value="<%=project.getStartDate()%>"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input name="endDate" type="date" value="<%=project.getEndDate()%>"/></td>
        </tr>
        <tr>
                    <td>Status:</td>
                    <td><input name="status" type="text" value="<%=project.getStatus()%>"/></td>
                </tr>
        <tr>
            <td colspan="2">
              <input type="submit" value="Save" />
            </td>
        </tr>
      </table>
  </form>