<%@ include file="bootstrap4_header.jsp"%>
<body>
<%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>
    <div>
        <form class="my-form-style mb-4">
            <blockquote class="blockquote text-center">
                <p class="mb-0 h2 mb-1">Configure DataSource</p>
            </blockquote>


            <div class="m-5">
                <a href="/datasource_source" class="btn btn-outline-primary mt-4 mb-5 d-block mx-auto border border-info" role="button" aria-pressed="true">ENTER DATASOURCE<img src="/resources/static/images/database.png" alt="База данных"></a></a>
            </div>
            <div class="m-5">
                <a href="/datasource_list" class="btn btn-outline-primary mt-4 mb-5 d-block mx-auto border border-info" role="button" aria-pressed="true">CHECK ALL DATASOURCE<img src="/resources/static/images/database_list.png" alt="Список баз данных"></a>
            </div>
            <div class="m-5">
                <a href="/datasource_connection" class="btn btn-outline-primary mt-4 mb-5 d-block mx-auto border border-info" role="button" aria-pressed="true">CREATE CONNECTION<img src="/resources/static/images/databases_con.png" alt="Настройка соединения"></a>
            </div>

        <div class="form-row mb-2">
            <div class="col"></div>
            <div class="col-3 text-center">
                <a href="/" class="btn btn-primary d-block mt-4 mb-5 mx-auto .text-xs-center" role="button" aria-pressed="true">back</a>
            </div>
            <div class="col"></div>
        </div>
            </form>
    </div>

</body>
</html>
