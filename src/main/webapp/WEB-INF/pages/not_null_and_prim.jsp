<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>MAIN</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/registration.css"/>"/>
    <style>
        .my-form-style {
            margin-top: 4%;
        }

        .my-form-style-2 {
            margin-top: 4%;
        }

        .own-class-for-h7{
            color: dimgrey;
        }
    </style>
</head>
<body>








<div class="form-check col-auto">
    <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
    <label class="form-check-label" for="defaultCheck1">
        NOT NULL
    </label>
</div>
<div class="form-check col-auto">
    <input class="form-check-input" type="checkbox" value="" id="defaultCheck">
    <label class="form-check-label" for="defaultCheck1">
        PRIMARY KEY
    </label>
</div>

</body>
</html>
