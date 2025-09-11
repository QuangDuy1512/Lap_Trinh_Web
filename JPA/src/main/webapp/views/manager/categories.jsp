<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

    <h2 class="mb-3">Danh sách User bạn quản lý</h2>
    <table class="table table-bordered table-hover mb-4">
        <thead class="table-info">
            <tr>
                <th>ID</th>
                <th>Tên đăng nhập</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${managedUsers}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h2 class="mb-3">Danh sách Category</h2>

    <a href="${pageContext.request.contextPath}/manager/category/create" class="btn btn-success mb-3">+ Thêm Category</a>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
            <tr>
                <th>ID</th>
                <th>Tên Category</th>
                <th>Hành động</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${listcate}" var="cate">
                <tr>
                    <td>${cate.id}</td>
                    <td>${cate.categoryname}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/manager/category/edit?id=${cate.id}" 
                           class="btn btn-warning btn-sm">Sửa</a>
                        <a href="${pageContext.request.contextPath}/manager/category/delete?id=${cate.id}" 
                           class="btn btn-danger btn-sm"
                           onclick="return confirm('Bạn có chắc muốn xóa?');">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Nút quay lại -->
    <a href="${pageContext.request.contextPath}/views/manager/manager-home.jsp" class="btn btn-secondary">← Quay lại trang Home</a>

</body>
</html>