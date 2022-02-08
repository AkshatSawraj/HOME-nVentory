<%-- 
    Document   : register
    Created on : Nov 24, 2021, 10:53:28 AM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <form method="post">
            <label>Email: </label><input type="text" name="inputEmail">
            <br>
            <label>Password: </label><input type="password" name="inputPassword">
            <br>
            <label>First Name: </label><input type="text" name="inputFirst">
            <br>
            <label>Last Name: </label><input type="text" name="inputLast">
            <br>
            <input type="submit" name="action" value="Save">
        </form>
        <br>
        ${message}
        <br>
        <a href="login">Go Back to login</a>
    </body>
</html>
