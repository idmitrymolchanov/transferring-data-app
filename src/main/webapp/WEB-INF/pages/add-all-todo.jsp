<%--
  Created by IntelliJ IDEA.
  User: Dmitry
  Date: 06.04.2021
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="header.jsp"%>

<div>
<form:form method="post">
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
    <button type="submit" class="btn btn-info d-block mx-auto border border-info">Save all</button>
</form:form>
</div>

<%@ include file="footer.jsp"%>
