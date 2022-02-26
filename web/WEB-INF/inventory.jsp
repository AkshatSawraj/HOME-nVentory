<%-- 
    Document   : inventory
    Created on : Nov 16, 2021, 11:21:22 PM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <link href="css/style.css" rel="stylesheet" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory Page</title>
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
            <li><a href="account?user=${user.email}">Account Settings</a></li>
            <li><a href="#">Inventory</a></li>  
            <li><a href="#">Admin</a></li>  
            <li><a href="login?logout">Logout</a></li>  
        <h3 style="text-align: center; color:white;">Inventory for ${username}</h3>

        </ul>
        
        <div class="forms">
            <form class="formm" method="post"><h2>Add item</h2>        <label for="category">Category: </label>
            <select name="inputCategory" id="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.categoryId}"> ${category.categoryName} </option>
                </c:forEach>
            </select>
            <br>
            <label>Item Name: </label><input type="text" name="inputName">
            <br>
            <label>Price: </label><input type="text" name="inputPrice">
            <br>
            <input type="submit" name="action" value="Save">
        </form>
        <table>
            <tr>
                <th>
                    Category
                </th>
                <th>
                    Name
                </th>
                <th>
                    Price
                </th>
                <th>
                </th>
            </tr>

            <c:forEach items="${homeItems}" var="item">
                <tr>
                    <td>${item.categoryName}</td>
                    <td>${item.itemName}</td>
                    <td>$${item.price}</td>
                <form method="post">

                    <td> 
                        <input type="hidden" name="itemID" value="${item.itemId}">
                        <input type="submit" name="action" value="delete"> 
                    </td>

                </form>
                <form method="post">

                    <td> 
                        <input type="hidden" name="itemID" value="${item.itemId}">
                        <input type="submit" name="action" value="edit"> 
                    </td>

                </form>
            </tr>
        </c:forEach>
    </table>
            <form class="formm" method="post" >
                <h2>Edit Item</h2>

                <label for="category">Category: </label>

                <select name="editcategory" id="category">
                    <option>${editcategory}</option>

                    <c:forEach items="${categories}" var="category">
                        <option value="${category.categoryName}"> ${category.categoryName} </option>
                    </c:forEach>

                </select>

                <br>
                <label >Name: </label>
                <input type="text" name="editname" value="${editname}">
                <br>
                <label >Price: </label>
                <input type="text" name="editprice" value="${editprice}">
                <br>
                <input type="submit" value="Save">
                <input type="hidden" name="action" value="saveEdit">
            </form>
                
        </div>
    <br> <c:if test="${deleted}">
        <form method="post"><input type="submit" name="action" value="Undo"></form>
    </c:if><br>


    ${invalid}



</body>
</html>
