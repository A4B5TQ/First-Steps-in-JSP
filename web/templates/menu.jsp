<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Bookhut</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="/">Home</a></li>
                <c:set var="username" value="${user.getUsername()}" scope="session"/>
                <c:choose>
                    <c:when test="${username != null}">
                        <li><a href="/signout">Sign Out <span>(${username})</span></a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/signin">Sign In</a></li>
                        <li><a href="/signup">Sign Up</a></li>
                    </c:otherwise>
                </c:choose>
                <li><a href="/add">Add Book</a></li>
                <li><a href="/shelves">Shelves</a></li>

            </ul>
        </div>
    </div>
</nav>
</body>
</html>
