<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="m" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>date-time</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/static/css/realindex.css"/>"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

    <!-- 1. Подключить CSS-файл Bootstrap 3 -->
    <link rel="stylesheet" href="<c:url value="/resources/static/css/bootstrap.min.css"/>"/>
    <!-- 2. Подключить CSS-файл библиотеки Bootstrap 3 DateTimePicker -->
    <link rel="stylesheet" href="<c:url value="/resources/static/css/bootstrap-datetimepicker.min.css"/>"/>

    <!-- 3. Подключить библиотеку jQuery -->
    <script src="<c:url value="/resources/static/js/jquery-3.2.1.min.js" />"></script>
    <!-- 4. Подключить библиотеку moment -->
    <script src="<c:url value="/resources/static/js/moment-with-locales.min.js" />"></script>
    <!-- 5. Подключить js-файл фреймворка Bootstrap 3 -->
    <script src="<c:url value="/resources/static/js/bootstrap.min.js" />"></script>
    <!-- 6. Подключить js-файл библиотеки Bootstrap 3 DateTimePicker -->
    <script src="<c:url value="/resources/static/js/bootstrap-datetimepicker.min.js" />"></script>

</head>
<body>
<div>
    <form:form method="POST" modelAttribute="theTempBean">
        <%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>
        <blockquote class="blockquote text-center">
            <p class="mb-0 h1 mt-4 mb-4">Scheduler setting</p>
            <p class="mb-0 h3 mt-4 mb-4">choose date and time for first run</p>
        </blockquote>

                <div class="form-group">
                    <div class="form-row mb-2">
                        <div class="col"></div>
                        <!-- элемент input с id = datetimepicker1 -->
                        <div class="input-group col-4" id="datetimepicker1">
                            <form:input type="text" class="form-control input-lg border border-info" path="dateValue"></form:input>
                            <span class="input-group-addon">
                                <span class="glyphicon glyphicon-calendar"></span>
                            </span>
                        </div>
                        <div class="col"></div>
                    </div>
                </div>

                <!-- Инициализация виджета "Bootstrap datetimepicker" -->
                <script>
                    $(function () {
                    // идентификатор элемента (например: #datetimepicker1), для которого необходимо инициализировать виджет Bootstrap DateTimePicker
                    $('#datetimepicker1').datetimepicker();
                    });
                </script>


                <blockquote class="blockquote text-center">
                    <p class="mb-0 h3 mt-4 mb-4">Select interval</p>
                </blockquote>
                <div class="form-group">
                    <div class="form-row mb-2">
                        <div class="col"></div>
                        <div class="col">
                            <form:select path="periodValue" class="form-control input-lg" id="sel1">
                                <option>1 day</option>
                                <option>2 days</option>
                                <option>3 days</option>
                                <option>4 days</option>
                                <option>5 days</option>
                                <option>6 days</option>
                                <option>every week</option>
                                <option>28 days</option>
                            </form:select>
                        </div>
                        <div class="col"></div>
                    </div>
                </div>

        <button class="btn btn-primary mt-4 mb-4 d-block mx-auto" type="submit">Submit</button>

                <c:if test="${next > 0}">
                    <a href="/table_name_page" class="btn btn-outline-primary mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Next Step</a>
                </c:if>

            </form:form>
        </body>
</html>