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
          <style>
            input[type=text], select {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }

            input[type=submit] {
                width: 100%;
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            .formm {
                border-radius: 5px;
                background-color: #f2f2f2;
                padding: 20px;
            }
            .forms{
                display:flex;

            }

            table {
                font-family: Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
                height:100px;
                margin: 10px;
            }

            table td, table th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            table tr:nth-child(even){background-color: #f2f2f2;}

            table tr:hover {background-color: #ddd;}

            table th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #04AA6D;
                color: white;
            }







            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover {
                background-color: #4CAF50;
            }
        </style>
    </head>
    <body>
         <ul>
        <h1 style="text-align: center; color:white;">Edit Account Information</h1>
         </ul>
        <div class="forms">  
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
