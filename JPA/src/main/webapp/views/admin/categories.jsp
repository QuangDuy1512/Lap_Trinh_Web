<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Category List</title>
</head>
<body>
    <h2>Category Management</h2>
    <a href="category/create">Add New Category</a>
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Category Name</th>
            <th>Action</th>
        </tr>
        <c:forEach var="cate" items="${listcate}">
            <tr>
                <td>${cate.id}</td>
                <td>${cate.categoryname}</td>
                <td>
                    <a href="category/edit?id=${cate.id}">Edit</a> | 
                    <a href="category/delete?id=${cate.id}" onclick="return confirm('Delete this category?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
l>