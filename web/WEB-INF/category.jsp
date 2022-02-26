<%-- 
    Document   : categoey
    Created on : Nov 25, 2021, 2:35:58 PM
    Author     : 835489
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Category Page Page</title>
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
        <h1 style="text-align: center; color:white;">Edit Category</h1>
        </ul>
<div class="forms">
    <form class="formm" method="post">
        <h3>Add Category</h3>
        <label>Category ID:  </label>
        <input type="number" name="addid">
        <br>
        <label >Category Name: </label>
        <input type="text" name="addname">
        <br>
        <input type="submit" value="Save">
        <input type="hidden" name="action" value="save">
    </form>

        <table>
            <tr>
                <th>
                    Category ID
                </th>
                <th>
                    Category Name
                </th>
                <th>
                    Edit
                </th>
            </tr>

            <c:forEach items="${categories}" var="category">
                <tr>
                    <td>${category.categoryId}</td>
                    <td>${category.categoryName}</td>
                <form method="POST" action="category">

                    <td>
                        <input type="hidden" name="categoryedit" value="${category.categoryId}">
                        <input type="hidden" name="action" value="edit">
                        <input type="submit" value="edit">
                    </td>
                </form>
            </tr>
        </c:forEach>

    </table>

    <br><br>
    
    <form class="formm" method="post" >
        <h2>Edit Category</h2>
        <label >Category Name:  </label>
        <input type="text" name="editname" value="${editname}">
        <br>
        
        <input type="submit" value="Save">
        <input type="hidden" name="action" value="saveEdit">
    </form>
    
${message}
</div>

</body>
</html>
