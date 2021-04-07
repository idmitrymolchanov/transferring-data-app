<%--
  Created by IntelliJ IDEA.
  User: idmit
  Date: 03.07.2020
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>table_name</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/registration.css"/>"/>
    <style>
        .my-form-style {
            margin-top: 4%;
        }
    </style>
</head>
<body>
<div>
    <%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>
    <form:form method="POST" modelAttribute="theTempBean" cssClass="my-form-style">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h7 mt-4 mb-4">Enter a full table name</p>
        </blockquote>
        <div class="form-group">
            <div class="form-row mb-2">
                <div class="col"></div>
                <div class="col-4">
                    <form:input type="text" path="tableName"  placeholder="enter a table-name here" class="form-control input-sm border border-info"></form:input>
                </div>
                <div class="col"></div>
            </div>
            <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">submit</button>
        </div>

        <c:if test="${myVar > 0}">
            <blockquote class="blockquote text-center">
                <p class="mb-0 h7 mt-4 mb-4">Saved</p>
            </blockquote>
            <div>
                <a href="/list-todos" class="btn btn-outline-primary mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Next Step</a>
            </div>
        </c:if>

    </form:form>

    <div class="panel panel-primary">
        <blockquote class="blockquote text-center">
            <div class="panel-heading">
                <h4>list of tables for the current connection</h4>
            </div>
        </blockquote>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="20%"></th>
                    <th width="40%">table name</th>
                    <th width="40%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td></td>
                        <td>${todo}</td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
