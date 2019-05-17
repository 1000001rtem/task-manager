<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/head.jsp"/>

<div class="loginBox col-md-4 col-md-offset-4">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/registration" method="POST">
        <div class="form-group">
            <label for="login" class="col-sm-4 control-label">Login</label>
            <div class="col-sm-8">
                <input name="login" class="form-control" id="login" placeholder="Login">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">Password</label>
            <div class="col-sm-8">
                <input name="password" type="password" class="form-control" id="password" placeholder="Password">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-10">
                <button type="submit" class="btn btn-default">Registration</button>
            </div>
        </div>
    </form>
</div>

<jsp:include page="/WEB-INF/views/foot.jsp"/>
