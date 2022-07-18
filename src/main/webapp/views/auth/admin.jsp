<%@ page import="java.util.Date" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.util.Objects" %>
<%--
  Created by IntelliJ IDEA.
  User: jl
  Date: 7/12/2022
  Time: 10:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<div class="row m-4">
    <c:forEach items="${books}" var="book">
        <div class="col-2">
            <div class="card p-2" style="width: 100%">
                <img class="card-img-top" src="/display?img=${book.cover.path}" width="140" height="250"
                     alt="Image cover">
                <div class="card-body">
                    <h5 class="card-title"> ${book.name}</h5>
                    <i style="display:block;">author : ${book.author} </i>
                    <i style="display:block;">description : ${book.description}</i>
                    <i style="display:block;">genre : ${book.genre}</i>
                    <i style="display:block;">language : ${book.language}</i>
                    <i style="display:block;">pageCount : ${book.pageCount}</i>
                    <i style="display:block;">downloadCount : ${book.downloadCount}</i>
                    <i style="display:block;">status : ${book.status}</i>
                    <c:if test="${book.status=='CANCELED'}">
                        <form method="post" action="/status?name=${book.name}">
                            <input hidden name="status" value="pinned">
                            <button class="btn btn-success" type="submit">Pin book</button>
                        </form>
                    </c:if>
                    <c:if test="${book.status=='PINNED'}">
                        <form method="post" action="/status?name=${book.name}">
                            <input hidden name="status" value="canceled">
                            <button class="btn btn-danger" type="submit">Unpin book</button>
                        </form>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>


</body>
</html>
