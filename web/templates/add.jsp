<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="header.jsp"/>
</head>
<body>
<jsp:include page="menu.jsp"/>
<div class="col-lg-6">
    <div class="well bs-component">
        <form class="form-horizontal" method="post">
            <fieldset>
                <legend>Add Book</legend>
                <c:forEach var="entry" items="${errors}">
                    <p style="color: RED;">${entry.getValue()}</p>
                </c:forEach>
                <div class="form-group">
                    <label for="title" class="col-lg-2 control-label">Title</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="title" placeholder="Title" name="title" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="author" class="col-lg-2 control-label">Author</label>
                    <div class="col-lg-10">
                        <input type="text" class="form-control" id="author" placeholder="Author" name="author" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="pages" class="col-lg-2 control-label">Pages</label>
                    <div class="col-lg-10">
                        <input type="number" min="1" class="form-control" id="pages" placeholder="Pages" name="pages"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <input type="reset" class="btn btn-default" value="Cancel"/>
                        <input type="submit" class="btn btn-primary" value="Add" name="add"/>
                    </div>
                </div>
            </fieldset>
        </form>
        <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div>
    </div>
</div>
</body>
</html>
