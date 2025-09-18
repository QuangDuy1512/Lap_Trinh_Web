<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Category List</title>
</head>
<body>
    <h2>Category List</h2>
    <c:choose>
        <c:when test="${not empty categories}">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                </tr>
                <c:forEach var="cate" items="${categories}">
                    <tr>
                        <td>${cate.id}</td>
                        <td>${cate.categoryname}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No categories found.</p>
        </c:otherwise>
    </c:choose>
</body>
</html>