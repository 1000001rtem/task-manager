<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/views/head.jsp"/>

<c:set var="users" value="${requestScope.users}"/>

<table class="entityTable">
    <caption>Users</caption>
    <thead>
    <tr>
        <th>№</th>
        <th>Id</th>
        <th>Login</th>
        <th>Role</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}" varStatus="loop">
        <tr>
            <td>${loop.count}</td>
            <td width="10%">${user.getId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getRole()}</td>
            <td>
                <a href="${pageContext.request.contextPath}/enter/admin/user-remove?id=${user.getId()}">&#x292B;</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="/WEB-INF/views/foot.jsp"/>