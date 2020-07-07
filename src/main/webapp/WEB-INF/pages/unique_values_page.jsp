<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>unique</title>
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
<%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Column's names and types</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="50%">Column name</th>
                    <th width="50%">Type</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.stringValue}</td>
                        <td>${todo.stringType}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <form:form method="post" modelAttribute="theTempBean" cssClass="my-form-style">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h2 mb-1">enter the unique value</p>
            <p class="mb-0 h7 mt-1 mb-4 own-class-for-h7">(if isn t it - fill in empty)</p>
        </blockquote>
        <div class="form-group">
            <div class="form-row mb-2">
                <div class="col"></div>
                <div class="col-5">
                    <form:input type="text" path="stringValue"  placeholder="id" autofocus="true" class="form-control input-sm border border-info" id="enter the column name"></form:input>
                </div>
                <div class="col"></div>
            </div>
            <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">Submit</button>
        </div>
    </form:form>

    <a href="/scheduler" class="btn btn-outline-primary mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Next Step</a>

</div>

</body>
</html>
