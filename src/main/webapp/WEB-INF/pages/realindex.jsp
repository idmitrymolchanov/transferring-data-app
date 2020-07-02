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

</head>
<body>
<div>
    <h3>${pageContext.request.userPrincipal.name}</h3>
    <sec:authorize access="!isAuthenticated()">
        <div>
            <form>
                <div class="col"></div>
                <div class="col-2">
                    <a href="/login" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Войти</a>
                    <a href="/registration" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Зарегистрироваться</a>
                </div>
            </form>
        </div>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <div>
            <form>
                <div class="col"></div>
                <div class="col-2">
                    <a href="/logout" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Выйти</a>
                    <a href="/sqlPageExist" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Задать соединение</a>
                    <a href="/main" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Новая таблица</a>
                    <a href="/main" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Текущие таблицы</a>
                    <a href="/main" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Пользователи</a>
                    <a href="/main" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Добавить заметку</a>
                    <a href="/scheduler" class="btn btn-light mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Last page, okay?</a>
                </div>
            </form>
        </div>
    </sec:authorize>

</div>
</body>
</html>
