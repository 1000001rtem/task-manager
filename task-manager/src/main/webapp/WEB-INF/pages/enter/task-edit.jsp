<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/pages/head.jsp"/>

<h1>edit</h1>

<c:set var="projects" value="${requestScope.projects}"/>
<c:set var="task" value="${requestScope.task}"/>

<form action="${pageContext.request.contextPath}/enter/task-edit" method="POST">

    <table>
        <tr>
            <td><input name="id" type="hidden" value="${task.getId()}"></td>
        </tr>
        <tr>
            <td>Task Name:</td>
            <td><input name="name" type="text" value="${task.getName()}"></td>
        </tr>
        <tr>
            <td>Task Description:</td>
            <td><input name="description" type="text" value="${task.getDescription()}"/></td>
        </tr>
        <tr>
            <td>Start Date:</td>
            <td><input name="startDate" type="date" value="${task.getStartDate()}"/></td>
        </tr>
        <tr>
            <td>End Date:</td>
            <td><input name="endDate" type="date" value="${task.getEndDate()}"/></td>
        </tr>
        <tr>
            <td>Status:</td>
            <td><input name="status" type="text" value="${task.getStatus()}"/></td>
        </tr>
        <tr>
        <tr>
            <td>Project Id:</td>
            <td>

                <select name="projectId">
                    <option disabled selected>${projects.get(task.getProjectId()).getName()}</option>
                    <c:forEach var="project" items="${projects.values()}">
                        <option value="${project.getId()}">${project.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <td colspan="2">
            <input type="submit" value="Save"/>
        </td>
        </tr>
    </table>
</form>
<jsp:include page="/WEB-INF/pages/foot.jsp"/>