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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Menu</h3>
        <ul>
            <li><a href="inventory">Inventory</a></li>  
            <li><a href="admin">Admin</a></li>  
            <li><a href="login?logout">Logout</a></li>  
        </ul>

        <h3><a href="category">Manage Category</a></h3>

        <h3><a href="report">Download Report</a></h3>


        <h3>Manage Users</h3>
        <table>
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
    <div >                        
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
    <br><br>
    <div >  

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

    <br>

    ${message}

</body>
</html>
