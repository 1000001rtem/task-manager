<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/head.jsp"/>
<div class="loginBox">
    <form class="loginForm" action="${pageContext.request.contextPath}/registration" method="post">
        <div class="groupBox">
            <div class="formGroup">
                <label for="inputLogin">Login</label>
                <div>
                    <input type="text" id="inputLogin" placeholder="Login" name="login">
                </div>
            </div>
            <div class="formGroup">
                <label for="inputPassword">Password</label>
                <div>
                    <input type="password" id="inputPassword" placeholder="Password" name="password">
                </div>
            </div>
            <div class="formGroup">
                <div class="buttonBox">
                    <button type="submit">Registration</button>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/views/foot.jsp"/>
