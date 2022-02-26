<%-- 
    Document   : admin
    Created on : Nov 16, 2021, 11:21:04 PM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/style.css" rel="stylesheet" type="text/css">

        <title>Admin Page</title>
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
        <h1 style="text-align: center; color:white;">Home Inventory</h1>

            <li><a href="inventory">Inventory</a></li>  
            <li><a href="admin">Admin</a></li>  
            <li><a href="login?logout">Logout</a></li>
            <li><a href="category">Manage Category</a></li>
            <li><a href="report">Download Report</a></li>
        </ul>




        <div class="forms" >
            <div class="formm">                        
                <form  method="post">
                    <h3>Add User</h3>
                    <label>Email:  </label>
                    <input type="text" name="addemail">
                    <br>
                    <label >Password: </label>
                    <input type="text" name="addpassword">
                    <br>
                    <label >First Name: </label>
                    <input type="text" name="addfirstname">
                    <br>
                    <label>Last Name: </label>
                    <input type="text" name="addlastname">
                    <br>
                    <input type="submit" value="Save">
                    <input type="hidden" name="action" value="save">
                </form>
            </div> 
            <table class="table">
                <tr>
                    <th>
                        Email
                    </th>
                    <th>
                        First Name
                    </th>
                    <th>
                        Last Name
                    </th>
                    <th>
                        Role
                    </th>
                    <th>
                        Edit
                    </th>
                    <th>
                        Delete
                    </th>



                </tr>

                <c:forEach items="${users}" var="item">
                    <tr>
                        <td>${item.email}</td>
                        <td>${item.firstName}</td>
                        <td>${item.lastName}</td>
                        <td>${item.role}</td>  
                    <form method="POST" action="admin">

                        <td>
                            <input type="hidden" name="useredit" value="${item.email}">
                            <input type="hidden" name="action" value="edit">
                            <input type="submit" value="edit">
                        </td>
                    </form>
                    <form method="POST" action="admin" >

                        <td>
                            <input type="hidden" name="userdel" value="${item.email}">
                            <input type="hidden" name="action" value="delete">
                            <input type="submit" value="delete">
                        </td>





                    </form>

                    </tr>
                </c:forEach>

            </table><br>
            <c:if test="${deleted}">
                <form method="post"><input type="submit" name="action" value="Undo"></form>
                </c:if>
            <br>

            <br><br>
            <div class="formm" >  

                <form method="post" >
                    <h2>Edit User</h2>
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
                    <label for="active">Active User: </label>
                    <select name="activeUsers" id="activeUser">
                        <option>${isActive}</option>
                        <option value="true">true</option>
                        <option value="false">false</option>
                    </select>
                    <br>
                    <br>
                    <label>Change Role: </label>
                    <select name="role" id="activeRole">
                        <option>${role}</option>
                        <option value="system admin">System Admin</option>
                        <option value="regular user">Regular User</option>
                        <option value="company admin">Company Admin</option>

                    </select>
                    <br>

                    <input type="submit" value="Save">
                    <input type="hidden" name="action" value="saveEdit">
                </form>
            </div>
        </div>
        <br>

        ${message}

    </body>
</html>
