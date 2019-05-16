<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="/WEB-INF/views/head.jsp"/>

<c:set var="projects" value="${requestScope.projects}"/>

<h1>Task create</h1>
<form class="saveEntityForm" action="${pageContext.request.contextPath}/enter/task-create" method="POST">
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
                    <c:forEach var="project" items="${projects}">
                        <option value="${project.getId()}">${project.getName()}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <button type="submit">Save</button>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="/WEB-INF/views/foot.jsp"/>