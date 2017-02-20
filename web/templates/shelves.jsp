<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="header.jsp"/>
<body>
<jsp:include page="menu.jsp"/>
<div class="bs-component">
    <table class="table table-striped table-hover ">
        <thead>
        <tr>
            <th>#</th>
            <th>Title</th>
            <th>Author</th>
            <th>Pages</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.getId()}</td>
                <td>${book.getTitle()}</td>
                <td>${book.getAuthor()}</td>
                <td>${book.getPages()}</td>
                <td><a class="btn btn-primary" href="${requestScope['javax.servlet.forward.request_uri']}/edit/${book.getId()}">Edit</a></td>
                <td><a class="btn btn-primary" href="${requestScope['javax.servlet.forward.request_uri']}/delete/${book.getId()}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div id="source-button" class="btn btn-primary btn-xs" style="display: none;">&lt; &gt;</div></div>
</body>
</html>
