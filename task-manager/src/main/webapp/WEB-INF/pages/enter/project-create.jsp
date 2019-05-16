<jsp:include page="/WEB-INF/pages/head.jsp"/>
<h1>Project create</h1>
<form class="saveEntityForm" action="${pageContext.request.contextPath}/enter/project-create" method="POST">
    <table>
        <tr>
            <td>Project Name:</td>
            <td><input name="name" type="text" value="project"/></td>
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
            <td colspan="2">
                <button type="submit">Save</button>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="/WEB-INF/pages/foot.jsp"/>