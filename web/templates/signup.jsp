<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<jsp:include page="menu.jsp"/>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div>
                <h1 class="page-header" align="center" style="margin-top: 40%;">Bookhut</h1>
            </div>
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Please Sign Up</h3>
                </div>
                <div class="panel-body">
                    <form role="form"  method="POST">
                        <c:forEach var="entry" items="${errors}">
                            <p style="color: RED;">${entry.getValue()}</p>
                        </c:forEach>
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" autofocus="autofocus" placeholder="Username" name="username" type="text" />
                            </div>
                            <div class="form-group">
                                <input class="form-control" autofocus="autofocus" placeholder="Password" name="password" type="password"
                                       value="" />
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="Remember Me" />Remember Me
                                </label>
                            </div>
                            <input class="btn btn-lg btn-primary btn-block" type="submit" name="signup" value="Sign Up"/>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
