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
    </head>
    <body>
        <h1>Edit Category</h1>

        <table>
            <tr>
                <th>
                    Category ID
                </th>
                <th>
                    Category Name
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
    <form  method="post">
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

    <form method="post" >
        <h2>Edit Category</h2>
        <label >Category Name:  </label>
        <input type="text" name="editname" value="${editname}">
        <br>
        
        <input type="submit" value="Save">
        <input type="hidden" name="action" value="saveEdit">
    </form>
${message}
</body>
</html>
