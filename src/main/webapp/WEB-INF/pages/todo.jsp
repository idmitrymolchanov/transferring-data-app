<%@ include file="header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <div class="panel-heading">Add TODO</div>
                <div class="panel-body">
                    <form:form method="post" modelAttribute="todo">
                        <form:hidden path="id" />
                        <fieldset class="form-group">
                            <form:label path="stringValue">Column name</form:label>
                            <form:input path="stringValue" type="text" class="form-control"
                                        required="required" />
                            <form:errors path="stringValue" cssClass="text-warning" />
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="stringType">Type</form:label>
                            <form:select path="stringType" class="form-control" id="sel1">
                                <%@include  file="/resources/static/htmlResources/forMainPaige.html" %>
                            </form:select>

                            <form:errors path="stringType" cssClass="text-warning" />
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="footer.jsp"%>
