<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>config</title>
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
        <form>
            <blockquote class="blockquote text-center">
                <p class="mb-0 h2 mb-1">Configure JNDI DataSource</p>
                <p class="mb-0 h7 mt-1 mb-4 own-class-for-h7">target</p>
            </blockquote>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="text" class="form-control" aria-describedby="helpBlock3" placeholder="driver class name">
                    <small id="helpBlock3" class="form-text text-muted">
                        Example: org.apache.tomcat.jdbc.pool.DataSourceFactory
                    </small>
                </div>
                <div class="col-2"></div>
            </div>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="text" class="form-control" aria-describedby="helpBlock4" placeholder="url">
                    <small id="helpBlock4" class="form-text text-muted">
                        Example: com.mysql.jdbc.Driver
                    </small>
                </div>
                <div class="col-2"></div>
            </div>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="text" class="form-control" aria-describedby="helpBlock5" placeholder="username">
                    <small id="helpBlock5" class="form-text text-muted">
                        Example: jdbc:mysql://localhost/test
                    </small>
                </div>
                <div class="col-2"></div>
            </div>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="password" class="form-control" aria-describedby="helpBlock6" placeholder="password">
                    <small id="helpBlock6" class="form-text text-muted">
                        Example: username
                    </small>
                </div>
                <div class="col-2"></div>
            </div>
        </form>
    </div>

    <div>
        <form>
            <blockquote class="blockquote text-center">
                <p class="mb-0 h7 mt-1 mb-4 own-class-for-h7">source</p>
            </blockquote>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="text" class="form-control" aria-describedby="helpBlock23" placeholder="driver class name">
                    <small id="helpBlock23" class="form-text text-muted">
                        Example: org.apache.tomcat.jdbc.pool.DataSourceFactory
                    </small>
                </div>
                <div class="col-2"></div>
            </div>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="text" class="form-control" aria-describedby="helpBlock24" placeholder="url">
                    <small id="helpBlock24" class="form-text text-muted">
                        Example: com.mysql.jdbc.Driver
                    </small>
                </div>
                <div class="col-2"></div>
            </div>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="text" class="form-control" aria-describedby="helpBlock25" placeholder="username">
                    <small id="helpBlock25" class="form-text text-muted">
                        Example: jdbc:mysql://localhost/test
                    </small>
                </div>
                <div class="col-2"></div>
            </div>

            <div class="form-row mb-2">
                <div class="col-2"></div>
                <div class="col">
                    <input type="password" class="form-control" aria-describedby="helpBlock26" placeholder="password">
                    <small id="helpBlock26" class="form-text text-muted">
                        Example: username
                    </small>
                </div>
                <div class="col-2"></div>
            </div>
            <button type="submit" class="btn btn-primary mt-4 mb-4 d-block mx-auto">Enter</button>
        </form>
    </div>

</body>
</html>
