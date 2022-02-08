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
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3><a href="account?user=${user.email}">Account Settings</a></h3>

        <h3>Menu</h3>
        <ul>
            <li><a href="#">Inventory</a></li>  
            <li><a href="#">Admin</a></li>  
            <li><a href="login?logout">Logout</a></li>  
        </ul>
        <h3>Inventory for ${username}</h3>

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
        <br> <c:if test="${deleted}">
    <form method="post"><input type="submit" name="action" value="Undo"></form>
    </c:if><br>

    <h2>Add item</h2>
    <form method="post">
        <label for="category">Category: </label>
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

    <div >  
        <form method="post" >
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

    ${invalid}



</body>
</html>
