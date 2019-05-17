<jsp:include page="/WEB-INF/views/head.jsp"/>

<div class="pageTitle">Create new Project</div>

<form action="${pageContext.request.contextPath}/enter/project-create" method="POST">
    <div class="form-group">
        <label for="nameInput">Project Name</label>
        <input name="name" type="text" id="nameInput" class="form-control" placeholder="Project Name">
    </div>
    <div class="form-group">
        <label for="descriptionInput">Description</label>
        <input name="description" type="text" id="descriptionInput" class="form-control" placeholder="Description">
    </div>
    <div class="form-group">
        <label for="startDateInput">Start Date</label>
        <input name="startDate" type="date" id="startDateInput" class="form-control" placeholder="Start Date">
    </div>
    <div class="form-group">
        <label for="endDateInput">End Date</label>
        <input name="endDate" type="date" id="endDateInput" class="form-control" placeholder="End Date">
    </div>
    <button type="submit" class="btn btn-primary">Save</button>
</form>
<jsp:include page="/WEB-INF/views/foot.jsp"/>