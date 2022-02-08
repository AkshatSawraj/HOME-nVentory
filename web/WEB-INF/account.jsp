<%-- 
    Document   : account
    Created on : Nov 24, 2021, 1:36:02 PM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Setting Page</title>
    </head>
    <body>
        <h1>Edit Account Information</h1>
        <div>  
            <form method="post" >
                <h2>Edit User</h2>
                <br>
                <label >Email:  </label>
                <input type="text" name="editemail" value="${editemail}">
                <br>
                <label >Password: </label>
                <input type="text" name="editpassword" value="${editpassword}">
                <br>
                
                <label >First Name: </label>
                <input type="text" name="editfirstname" value="${editfirstname}">
                <br>
                <label >Last Name: </label>
                <input type="text" name="editlastname" value="${editlastname}">
                <br>
                <input type="submit" value="Save">
                <input type="hidden" name="action" value="saveEdit">
                <br>
                
                <br>
                
            </form>
                <form method="post">
                    <input type="submit" name="action" value="Deactivate">
                </form>
        </div>
                <br>
                ${message}
                
    </body>
</html>
