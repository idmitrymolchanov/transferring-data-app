<%@ include file="bootstrap4_header.jsp"%>
<body>
<%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>
<div>
    <form class="my-form-style mb-4">
        <blockquote class="blockquote text-center">
            <p class="mb-0 h2 mb-1">Transaction verification page</p>
        </blockquote>


        <div class="m-5">
            <a href="/report_excel_page" class="btn btn-outline-primary mt-4 mb-5 d-block mx-auto border border-info" role="button" aria-pressed="true">CREATE REPORT<img src="/resources/static/images/new_report.png" alt="новый отчет"></a></a>
        </div>
        <div class="m-5">
            <a href="/transaction_verification_page" class="btn btn-outline-primary mt-4 mb-5 d-block mx-auto border border-info" role="button" aria-pressed="true">View data on the web<img src="/resources/static/images/web.png" alt="просмотр в вебе"></a>
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
