<%--
  Created by IntelliJ IDEA.
  User: idmit
  Date: 04.09.2020
  Time: 14:39
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
        .for_h3 {
            margin-left: 4%;
        }
    </style>
</head>

<body>
<div>
    <%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>
    <form:form method="POST" modelAttribute="connectionEntity" cssClass="my-form-style">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h7 mt-4 mb-4">Enter connection's id</p>
        </blockquote>
        <div class="form-group">
            <div class="form-row mb-2">
                <div class="col"></div>
                <div class="col-4">
                    <form:input type="text" path="id" placeholder="enter connection's id" class="form-control input-sm border border-info"></form:input>
                </div>
                <div class="col"></div>
            </div>
            <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">submit</button>
        </div>

        <c:if test="${success > 0}">
            <blockquote class="blockquote text-center">
                <p class="mb-0 h7 mt-4 mb-4">Saved</p>
            </blockquote>
            <div>
                <a href="/table_name_page" class="btn btn-outline-primary mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Next Step</a>
            </div>
        </c:if>

    </form:form>

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="for_h3">Available connections</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="4%"></th>
                    <th width="16%">id</th>
                    <th width="40%">source</th>
                    <th width="40%">target</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td></td>
                        <td>${todo.id}</td>
                        <td>${todo.source_url}</td>
                        <td>${todo.target_url}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
