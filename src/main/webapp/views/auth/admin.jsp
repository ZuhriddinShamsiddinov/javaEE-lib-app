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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo01"
            aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>
<form class="form-inline">
    <input class="form-control mr-sm-2" name="search" type="search" value="${search}" placeholder="Search"
           aria-label="Search">
    <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
</form>
<div class="m-4">
    <%--For displaying Previous link except for the 1st page --%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages+1}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a btn btn-primary href="?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
    <br>
    <c:if test="${currentPage != 1}">
    <td><a class="btn btn-primary" href="page=${currentPage - 1}&search=${search}">Previous</a></td>
    </c:if>
    <c:if test="${currentPage lt noOfPages+1}">
    <td><a class="btn btn-success" href="?page=${currentPage + 1}&search=${search}">Next</a></td>
    </c:if>
    <br>
    <br>
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
                    <a href="/download?path=${book.cover.path}">Download Cover</a>
                    <br/>
                    <a href="/download?path=${book.file.path}">Download File</a>
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
<a class="btn btn-danger m-4" href="/logout">Logout</a>
</body>
</html>
