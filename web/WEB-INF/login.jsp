<%-- 
    Document   : login
    Created on : Nov 16, 2021, 11:21:38 PM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h2>Login</h2>
        <form method="POST" action="login">
            <label for="email">Email:</label> <input name="email" type="text">
            <br>
            <label for="password">Password:</label> <input name="password" type="password" >
            <br>
            <input type="submit" value="Log in">

        </form>
        ${failed}

        <br><a href="register">Register Now</a>
    </body>
</html>
