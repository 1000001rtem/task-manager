<%@ page import="ru.eremin.tm.model.dto.TaskDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>edit</h1>
<%
    final Object projectsObject = request.getAttribute("projects");
    final List<String> projects = (ArrayList<String>) projectsObject;
    final Object taskObject = request.getAttribute("task");
    final TaskDTO task = (TaskDTO) taskObject;
%>
<form action = "/enter/task-edit" method="POST">

      <table>
        <tr>
            <td><input name="id" type="hidden" value="<%=task.getId()%>"></td>
        </tr>
        <tr>
                    <td>Task Name:</td>
                    <td><input name="name" type="text" value="<%=task.getName()%>"></td>
                </tr>
        <tr>
            <td>Task Description:</td>
            <td><input name="description" type="text" value="<%=task.getDescription()%>"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input name="startDate" type="date" value="<%=task.getStartDate()%>"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input name="endDate" type="date" value="<%=task.getEndDate()%>"/></td>
        </tr>
        <tr>
                    <td>Status:</td>
                    <td><input name="status" type="text" value="<%=task.getStatus()%>"/></td>
                </tr>
        <tr>
        <tr>
                     <td>Project Id:</td>
                     <td>

                     <select name="projectId">
                     <option disabled><%=task.getProjectId()%></option>
                     <%for (final String project : projects) {%>
                             <option><%=project%></option>

                         <%}%>
                     </select>

                     </td>
                </tr>
            <td colspan="2">
              <input type="submit" value="Save" />
            </td>
        </tr>
      </table>
  </form>