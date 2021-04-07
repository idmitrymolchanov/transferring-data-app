<%@ include file="header.jsp"%>

<%@include  file="/resources/static/htmlResources/horizontal_menu.html" %>

<div class="container">
    <div>
        <a type="button" class="btn btn-primary btn-md" href="/add-todo">Add column</a>
        <a type="button" class="btn btn-primary btn-md" href="/add-all-todo">Add ALL columns and types</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Column's names and types</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="40%">Column name</th>
                    <th width="40%">Type</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.stringValue}</td>
                        <td>${todo.stringType}</td>
                        <td><a type="button" class="btn btn-info"
                               href="/update-todo?id=${todo.id}">Update</a>
                            <a type="button" class="btn btn-danger"
                               href="/delete-todo?id=${todo.id}">Delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <a href="/unique_values_page" class="btn btn-outline-primary mt-4 mb-4 d-block mx-auto border border-info" role="button" aria-pressed="true">Next Step</a>


    <div class="panel panel-primary">
        <blockquote class="blockquote text-center">
            <div class="panel-heading">
                <h4>list of table columns for the current connection</h4>
            </div>
        </blockquote>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="20%"></th>
                    <th width="30%">column name</th>
                    <th width="30%">column type</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${names}" var="todo">
                    <tr>
                        <td></td>
                        <td>${todo}</td>
                        <td>${types.get(names.indexOf(todo))}</td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


</div>
<%@ include file="footer.jsp"%>