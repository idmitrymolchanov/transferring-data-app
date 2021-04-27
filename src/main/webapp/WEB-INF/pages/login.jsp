<%--
  Created by IntelliJ IDEA.
  User: idmit
  Date: 19.05.2020
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>LogIn</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/login.css"/>"/>
</head>

<body>
<sec:authorize access="isAuthenticated()">
    <% response.sendRedirect("/"); %>
</sec:authorize>
<div>
    <form method="POST" class="my-form-style border border-info rounded mybtn" action="/login">
        <blockquote class="blockquote text-center">
        <p class="mb-0 h2 mt-4">Log In</p>
        </blockquote>
        <div class="form-group">
            <div class="form-row">
                <div class="col"></div>
                <div class="col-10">
                    <input name="username" class="form-control input-sm mt-4" id="inputlg" type="text" placeholder="Username" path="username">
                </div>
                <div class="col"></div>
            </div>
            <div class="form-row">
                <div class="col"></div>
                    <div class="col-50">
                        <blockquote class="blockquote text-center">
                        <small id="emailHelp" class="form-text text-muted mb-4">We ll never share ur personal data with anyone</small>
                        </blockquote>
                    </div>
                <div class="col"></div>
            </div>
            <div class="form-row">
                <div class="col">
                </div>
                <div class="col-10">
                    <input name="password" class="form-control input-sm mb-4" id="inputlg" type="text" placeholder="Password" path="password">
                </div>
                <div class="col">
                </div>
            </div>

            <div class="form-col">
                <div class="col">
                <button type="submit" class="btn btn-primary mb-4 d-block mx-auto">Log In</button>
                </div>
            </div>
            <div class="form-col">
                <div class="col">
                    <u><a class="d-block mx-auto" href="/registration">sign up</a></u>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
