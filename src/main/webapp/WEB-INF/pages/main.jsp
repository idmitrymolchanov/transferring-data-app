<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
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

<div>
    <form:form action="/main" method="post" modelAttribute="theTempBean" cssClass="my-form-style">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h2 mb-1">enter the number of columns</p>
            <p class="mb-0 h7 mt-1 mb-4 own-class-for-h7">(minimum = 1, maximum = 40)</p>
        </blockquote>
    <div class="form-group">
        <div class="form-row mb-2">
            <div class="col"></div>
            <div class="col-5">
        <form:input type="text" path="count"  placeholder="id" autofocus="true" class="form-control input-sm border border-info" id="id2"></form:input>
            </div>
            <div class="col"></div>
        </div>
        <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">Enter</button>
    </div>
    </form:form>

    <form:form method="POST" modelAttribute="userForm" cssClass="my-form-style-2">
        <c:if test="${myVar > 0}">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h2 mt-4 mb-4">enter the number of columns</p>
        </blockquote>
        </c:if>
        <div class="form-group">
        <c:if test="${myVar > 0}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type1" class="form-control" id="sel1">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
            <form:input type="text" path="value1" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
        </c:if>
            <c:if test="${myVar > 1}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type2" class="form-control" id="sel2">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value2" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 2}">
                <div class="form-row mb-1">
            <div class="col-3">
                <form:select path="type3" class="form-control" id="sel3">
                    <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                </form:select>
            </div>
            <div class="col">

                    <form:input type="text" path="value3" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 3}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type4" class="form-control" id="sel4">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value4" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 4}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type5" class="form-control" id="sel5">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value5" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 5}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type6" class="form-control" id="sel6">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value6" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 6}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type7" class="form-control" id="sel7">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value7" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 7}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type8" class="form-control" id="sel8">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value8" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 8}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type9" class="form-control" id="sel9">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value9" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 9}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type10" class="form-control" id="sel10">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value10" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 10}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type11" class="form-control" id="sel11">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value11" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 11}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type12" class="form-control" id="sel12">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value12" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 12}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type13" class="form-control" id="sel13">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value13" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 13}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type14" class="form-control" id="sel14">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value14" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 14}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type15" class="form-control" id="sel15">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value15" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 15}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type16" class="form-control" id="sel16">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value16" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 16}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type17" class="form-control" id="sel17">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value17" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 17}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type18" class="form-control" id="sel18">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value18" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 18}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type19" class="form-control" id="sel19">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value19" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 19}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type20" class="form-control" id="sel20">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value20" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 20}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type21" class="form-control" id="sel21">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value21" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 21}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type22" class="form-control" id="sel22">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value22" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 22}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type23" class="form-control" id="sel23">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value23" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 23}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type24" class="form-control" id="sel24">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value24" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 24}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type25" class="form-control" id="sel25">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value25" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 25}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type26" class="form-control" id="sel26">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value26" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 26}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type27" class="form-control" id="sel27">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value27" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 27}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type28" class="form-control" id="sel28">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value28" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 28}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type29" class="form-control" id="sel29">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value29" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 29}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type30" class="form-control" id="sel30">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value30" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 30}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type31" class="form-control" id="sel31">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value31" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 31}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type32" class="form-control" id="sel32">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value32" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 32}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type33" class="form-control" id="sel33">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value33" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 33}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type34" class="form-control" id="sel34">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value34" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 34}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type35" class="form-control" id="sel35">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value35" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 35}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type36" class="form-control" id="sel36">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value36" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 36}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type37" class="form-control" id="sel37">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value37" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 37}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type38" class="form-control" id="sel38">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value38" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 38}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type39" class="form-control" id="sel39">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value39" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 39}">
            <div class="form-row mb-1">
                <div class="col-3">
                    <form:select path="type40" class="form-control" id="sel40">
                        <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                    </form:select>
                </div>
                <div class="col">
                    <form:input type="text" path="value40" placeholder="name of column" class="form-control input-sm border border-info" id=""></form:input>
                </div>
            </div>
            </c:if>
            <c:if test="${myVar > 0}">
                <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">Сохранить данные и закрыть</button>
                <u><a class="d-block mx-auto" href="/sqlPage">sqlPage</a></u>
        </c:if>
            </div>
    </form:form>
</div>
</body>
</html>
