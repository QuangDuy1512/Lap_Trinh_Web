<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>User Dashboard</h2>
        <div>
            <span class="me-3">Welcome ${user.username}</span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng xuất</a>
        </div>
    </div>

    <!-- Hiển thị danh sách Category -->
    <div class="card">
        <div class="card-header">
            <h4 class="mb-0">Danh sách Categories</h4>
        </div>
        <div class="card-body">
            <div class="row row-cols-1 row-cols-md-3 g-4">
                <c:forEach items="${categories}" var="category">
                    <div class="col">
                        <div class="card h-100">
                            <c:if test="${not empty category.images}">
                                <img src="${category.images}" class="card-img-top" alt="${category.name}" 
                                     style="height: 200px; object-fit: cover;">
                            </c:if>
                            <div class="card-body">
                                <h5 class="card-title">${category.name}</h5>
                                <p class="card-text">
                                    <strong>Code:</strong> ${category.code}<br>
                                    <strong>Trạng thái:</strong> 
                                    <span class="badge ${category.status ? 'bg-success' : 'bg-danger'}">
                                        ${category.status ? 'Active' : 'Inactive'}
                                    </span>
                                </p>
                            </div>
                            <div class="card-footer text-muted">
                                <small>Created by: ${category.user.username}</small>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>