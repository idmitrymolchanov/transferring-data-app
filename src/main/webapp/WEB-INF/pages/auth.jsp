<%--
  Created by IntelliJ IDEA.
  User: idmit
  Date: 19.05.2020
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; encoding=utf-8"%>
<%@ page import="java.util.*" %>
<html>

<%! private boolean loggedIn; %>

<%
    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if ("user".equals(login) && "1234".equals(password))
        loggedIn = true;
    else
        loggedIn = false;

    if (!loggedIn) {
%>

<form action="2122" method="POST">
    <input type="text" name="login"><br>
    <input type="text" name="password"><br>
    <button>Send</button>
</form>

<% } else { %>

<h1>You are already logged in!</h1>

<% } %>

</html>
