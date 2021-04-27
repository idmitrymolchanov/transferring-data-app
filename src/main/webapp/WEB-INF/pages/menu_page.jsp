<%--
  Created by IntelliJ IDEA.
  User: idmit
  Date: 19.05.2020
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
<head>
    <title>Главная</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/realindex.css"/>"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <style>
        h3 {
            margin-top: 2%;
            margin-left: 2%;
        }
        .left {
            margin-left: 2%;
        }
    </style>
</head>
<body>
<div>
    <h3>->   Hello, ${pageContext.request.userPrincipal.name}!</h3>
    <sec:authorize access="!isAuthenticated()">
        <div>
            <form>
                <div class="col"></div>
                <div class="col-2">
                    <a href="/login" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">sign in</a>
                    <a href="/registration" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">sign up</a>
                </div>
            </form>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div>
            <form class="left">
                <div class="col"></div>
                <div class="col-2">
                    <a href="/logout" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Logout</a>
                    <a href="/datasource_setting" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Sources and Connections</a>
                    <a href="/select_connection" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Add new Table</a>
                    <a href="/all_tables_page" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Current Tables</a>
                    <a href="/reports_and_users_page" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Users and Reports</a>
                </div>
            </form>
        </div>
    </sec:authorize>

</div>
</body>
</html>
