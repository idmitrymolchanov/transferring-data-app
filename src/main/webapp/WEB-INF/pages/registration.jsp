<%--
  Created by IntelliJ IDEA.
  User: idmit
  Date: 19.05.2020
  Time: 16:35
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>LogIn</title>
    <meta charset="utf-8">
    <link rel = "stylesheet" href="/static/css/main.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/registration.css"/>"/>
</head>

<body>

<div>
    <form:form method="POST" modelAttribute="userForm" cssClass="my-form-style">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h2 mt-4 mb-4">registration</p>
        </blockquote>
        <div class="form-group">
        <div class="form-row mb-2">
            <div class="col"></div>
            <label for="login" class="col-2 col-form-label">login</label>
            <div class="col-5">
                <form:input type="text" path="username" placeholder="login" class="form-control input-sm border border-info" id="login"></form:input>
                <form:errors path="username"></form:errors>
                    ${usernameError}
            </div>
            <div class="col"></div>
        </div>
        <div class="form-row mb-2">
            <div class="col"></div>
            <label for="password" class="col-2 col-form-label">password</label>
            <div class="col-5">
                <form:input type="text" path="password" placeholder="password" class="form-control input-sm border border-info" id="password"></form:input>
            </div>
            <div class="col"></div>
        </div>
        <div class="form-row mb-2">
            <div class="col"></div>
            <label for="conf-pass" class="col-2 col-form-label">confirm</label>
            <div class="col-5">
                <input type="text" placeholder="confirm your password" class="form-control input-sm border border-info" id="conf-pass">
            </div>
            <div class="col"></div>
        </div>
        <div class="form-col">
            <div class="col">
                <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">Submit</button>
            </div>
        </div>
        <div class="form-row">
            <div class="col-3"></div>
            <div class="col">
                <u><a class="d-block mx-auto" href="/">main page</a></u>
            </div>
        </div>
        </div>
    </form:form>
</div>
</body>
</html>

