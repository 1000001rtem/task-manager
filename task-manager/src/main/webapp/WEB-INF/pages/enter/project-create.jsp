<h1>create</h1>
<form action="${pageContext.request.contextPath}/enter/project-create" method="POST">
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
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>