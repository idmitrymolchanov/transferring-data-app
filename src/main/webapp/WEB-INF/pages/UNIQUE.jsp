<%--
  Created by IntelliJ IDEA.
  User: idmit
  Date: 12.06.2020
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html>
<head>
    <title>sql</title>
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
    <%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>
    <form:form method="POST" modelAttribute="tableNameExist" cssClass="my-form-style">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h7 mt-4 mb-4">Enter a full table name</p>
        </blockquote>
        <div class="form-group">
            <div class="form-row mb-2">
                <div class="col"></div>
                <div class="col-4">
                    <form:input type="text" path="table_name"  placeholder="enter a table-name here" class="form-control input-sm border border-info"></form:input>
                </div>
                <div class="col"></div>
            </div>
            <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">submit</button>
        </div>
    </form:form>
</div>

<c:if test="${notn == 1}">
    <div>
        <form:form method="POST" cssClass="my-form-style">
            <c:if test="${count > 0}">
                <blockquote class="blockquote text-center">
                    <p class="mb-0 h7 mt-4 mb-4">NOT NULL and UNIQUE</p>
                </blockquote>
            </c:if>
            <c:if test="${count > 0}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder="${entity.value1}">
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCheck1">
                            <label class="form-check-label" for="autoSizingCheck1">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChec1">
                            <label class="form-check-label" for="autoSizingChec1">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 1}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder="${entity.value2}">
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCheck">
                            <label class="form-check-label" for="autoSizingCheck">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChec">
                            <label class="form-check-label" for="autoSizingChec">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 2}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value3}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh3">
                            <label class="form-check-label" for="autoSizingCh3">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe3">
                            <label class="form-check-label" for="autoSizingChe3">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 3}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value4}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh4">
                            <label class="form-check-label" for="autoSizingCh4">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe4">
                            <label class="form-check-label" for="autoSizingChe4">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 4}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value5}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh5">
                            <label class="form-check-label" for="autoSizingCh5">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe5">
                            <label class="form-check-label" for="autoSizingChe5">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 5}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value6}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh6">
                            <label class="form-check-label" for="autoSizingCh6">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe6">
                            <label class="form-check-label" for="autoSizingChe6">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 6}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value7}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh7">
                            <label class="form-check-label" for="autoSizingCh7">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe7">
                            <label class="form-check-label" for="autoSizingChe7">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 7}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value8}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh8">
                            <label class="form-check-label" for="autoSizingCh8">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe8">
                            <label class="form-check-label" for="autoSizingChe8">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 8}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value9}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh9">
                            <label class="form-check-label" for="autoSizingCh9">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe9">
                            <label class="form-check-label" for="autoSizingChe9">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 9}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value10}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh10">
                            <label class="form-check-label" for="autoSizingCh10">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe10">
                            <label class="form-check-label" for="autoSizingChe10">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 10}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value11}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh11">
                            <label class="form-check-label" for="autoSizingCh11">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe11">
                            <label class="form-check-label" for="autoSizingChe11">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 11}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value12}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh12">
                            <label class="form-check-label" for="autoSizingCh12">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe12">
                            <label class="form-check-label" for="autoSizingChe12">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 12}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value13}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh13">
                            <label class="form-check-label" for="autoSizingCh13">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe13">
                            <label class="form-check-label" for="autoSizingChe13">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 13}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value14}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh14">
                            <label class="form-check-label" for="autoSizingCh14">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe14">
                            <label class="form-check-label" for="autoSizingChe14">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 14}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value15}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh15">
                            <label class="form-check-label" for="autoSizingCh15">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe15">
                            <label class="form-check-label" for="autoSizingChe15">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 15}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value16}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh16">
                            <label class="form-check-label" for="autoSizingCh16">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe16">
                            <label class="form-check-label" for="autoSizingChe16">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 16}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value17}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh17">
                            <label class="form-check-label" for="autoSizingCh17">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe17">
                            <label class="form-check-label" for="autoSizingChe17">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 17}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value18}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh18">
                            <label class="form-check-label" for="autoSizingCh18">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe18">
                            <label class="form-check-label" for="autoSizingChe18">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 18}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value19}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh19">
                            <label class="form-check-label" for="autoSizingCh19">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe19">
                            <label class="form-check-label" for="autoSizingChe19">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 19}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value20}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh20">
                            <label class="form-check-label" for="autoSizingCh20">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe20">
                            <label class="form-check-label" for="autoSizingChe20">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 20}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value21}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh21">
                            <label class="form-check-label" for="autoSizingCh21">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe21">
                            <label class="form-check-label" for="autoSizingChe21">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 21}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value22}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh22">
                            <label class="form-check-label" for="autoSizingCh22">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe22">
                            <label class="form-check-label" for="autoSizingChe22">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 22}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col">
                        <fieldset disabled>
                            <input type="text" class="form-control mb-2" placeholder=${entity.value23}>
                        </fieldset>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingCh">
                            <label class="form-check-label" for="autoSizingCh">NOT NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2">
                            <input class="form-check-input" type="checkbox" id="autoSizingChe">
                            <label class="form-check-label" for="autoSizingChe">UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 0}">
                <div class="form-row align-items-center">
                    <div class="col-2"></div>
                    <div class="col-1">
                        <div class="form-check mb-2 mt-2">
                            <input class="form-check-input" type="checkbox" id="autoS">
                            <label class="form-check-label" for="autoS">Select none NOT_NULL</label>
                        </div>
                    </div>
                    <div class="col-1">
                        <div class="form-check mb-2 mt-2">
                            <input class="form-check-input" type="checkbox" id="auto">
                            <label class="form-check-label" for="auto">Select none UNIQUE</label>
                        </div>
                    </div>
                    <div class="col-2"></div>
                </div>
            </c:if>
            <c:if test="${count > 0}">
                <div>
                    <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">submit</button>
                </div>
            </c:if>
        </form:form>
    </div>
</c:if>

<c:if test="${notn == 1}">
    <a href="/sqlPageExist" class="btn btn-outline-primary mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Next Step</a>
</c:if>

</body>
</html>
