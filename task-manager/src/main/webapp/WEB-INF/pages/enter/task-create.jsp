<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%
    final Object projectsObject = request.getAttribute("projects");
    final List<String> projects = (ArrayList<String>) projectsObject;
%>

<h1>create</h1>
<form action = "/enter/task-create" method="POST">
      <table>
        <tr>
            <td>Project Name:</td>
            <td><input name="name" type="text" value="task"/></td>
        </tr>
        <tr>
            <td>Project Description:</td>
            <td><input name="description" type="text" value="description"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input name="startDate" type="date" value="start"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input name="endDate" type="date" value="end"/></td>
        </tr>
        <tr>
             <td>Project Id:</td>
             <td>

             <select name="projectId">
             <%for (final String project : projects) {%>
                     <option><%=project%></option>

                 <%}%>
             </select>

             </td>
        </tr>
        <tr>
            <td colspan="2">
              <input type="submit" value="Save" />
            </td>
        </tr>
      </table>
  </form>