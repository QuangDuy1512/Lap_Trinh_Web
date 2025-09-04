<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Category Form</title>
</head>
<body>
    <h2>${category != null ? "Edit Category" : "Create Category"}</h2>
    <form action="${category != null ? 'update' : 'store'}" method="post">
        <input type="hidden" name="id" value="${category.id}"/>
        <p>Category Name: <input type="text" name="categoryname" value="${category.categoryname}"/></p>
        <p><input type="submit" value="Save"/></p>
    </form>
    <a href="../categories">Back to list</a>
</body>
</html>
