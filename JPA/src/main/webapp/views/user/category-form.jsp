<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Category Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h2 class="mb-3">
        <c:choose>
            <c:when test="${category != null}">Cập nhật Category</c:when>
            <c:otherwise>Thêm mới Category</c:otherwise>
        </c:choose>
    </h2>

    <form method="post" action="${action}">
        <c:if test="${category != null}">
            <input type="hidden" name="id" value="${category.id}" />
        </c:if>

        <div class="mb-3">
            <label class="form-label">Tên Category</label>
            <input type="text" class="form-control" name="categoryname"
                   value="${category != null ? category.categoryname : ''}" required />
        </div>

        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${category != null}">Cập nhật</c:when>
                <c:otherwise>Thêm mới</c:otherwise>
            </c:choose>
        </button>

        <a href="${pageContext.request.contextPath}/user/categories" class="btn btn-secondary">Hủy</a>
    </form>

</body>
</html>
