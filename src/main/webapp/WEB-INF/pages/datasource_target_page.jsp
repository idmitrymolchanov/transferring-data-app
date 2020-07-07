<%@ include file="bootstrap4_header.jsp"%>
<body>
<%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>
<div>
    <form:form modelAttribute="datasourceEntity" cssClass="my-form-style">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h2 mb-1">Configure DataSource</p>
            <p class="mb-0 h7 mt-1 mb-4 own-class-for-h7">target</p>
        </blockquote>

        <div class="form-row mb-2">
            <div class="col-2"></div>
            <div class="col">
                <form:input path="driver_name" type="text" class="form-control" aria-describedby="helpBlock23" placeholder="driver class name"></form:input>
                <small id="helpBlock23" class="form-text text-muted">
                    Example: org.apache.tomcat.jdbc.pool.DataSourceFactory
                </small>
            </div>
            <div class="col-2"></div>
        </div>

        <div class="form-row mb-2">
            <div class="col-2"></div>
            <div class="col">
                <form:input path="url" type="text" class="form-control" aria-describedby="helpBlock24" placeholder="url"></form:input>
                <small id="helpBlock24" class="form-text text-muted">
                    Example: com.mysql.jdbc.Driver
                </small>
            </div>
            <div class="col-2"></div>
        </div>

        <div class="form-row mb-2">
            <div class="col-2"></div>
            <div class="col">
                <form:input path="username" type="text" class="form-control" aria-describedby="helpBlock25" placeholder="username"></form:input>
                <small id="helpBlock25" class="form-text text-muted">
                    Example: jdbc:mysql://localhost/test
                </small>
            </div>
            <div class="col-2"></div>
        </div>

        <div class="form-row mb-2">
            <div class="col-2"></div>
            <div class="col">
                <form:input path="password" type="password" class="form-control" aria-describedby="helpBlock26" placeholder="password"></form:input>
                <small id="helpBlock26" class="form-text text-muted">
                    Example: username
                </small>
            </div>
            <div class="col-2"></div>
        </div>
        <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">Enter</button>
    </form:form>
</div>

        <c:if test="${success > 0}">
            <blockquote class="blockquote text-center">
                <p class="mb-0 h7 mt-4 mb-4">Saved</p>
            </blockquote>
            <div>
                <a href="${pageContext.request.contextPath}/menu_page" class="btn btn-outline-primary mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Back menu</a>
            </div>
        </c:if>
</body>
</html>
