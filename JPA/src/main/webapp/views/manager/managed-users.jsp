<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Dashboard - Managed Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Manager Dashboard</h2>
        <div>
            <span class="me-3">Welcome ${user.username}</span>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-danger">Đăng xuất</a>
        </div>
    </div>

    <div class="row">
        <!-- Left sidebar - User list -->
        <div class="col-md-3">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">Managed Users</h5>
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <c:forEach items="${managedUsers}" var="managedUser">
                            <li class="list-group-item ${selectedUser.id == managedUser.id ? 'active' : ''}">
                                <a href="?userId=${managedUser.id}" class="text-decoration-none ${selectedUser.id == managedUser.id ? 'text-white' : 'text-dark'}">
                                    ${managedUser.username}
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>

        <!-- Right content - Categories -->
        <div class="col-md-9">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <h5 class="mb-0">Categories for ${selectedUser.username}</h5>
                    <c:if test="${not empty selectedUser}">
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCategoryModal">
                            + Add Category
                        </button>
                    </c:if>
                </div>
                <div class="card-body">
                    <c:if test="${empty selectedUser}">
                        <div class="alert alert-info">Select a user to view their categories</div>
                    </c:if>
                    <c:if test="${not empty selectedUser}">
                        <table class="table table-bordered table-hover">
                            <thead class="table-dark">
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Code</th>
                                    <th>Image</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${userCategories}" var="category">
                                    <tr>
                                        <td>${category.id}</td>
                                        <td>${category.name}</td>
                                        <td>${category.code}</td>
                                        <td>
                                            <c:if test="${not empty category.images}">
                                                <img src="${category.images}" alt="${category.name}" style="max-width: 50px;">
                                            </c:if>
                                        </td>
                                        <td>
                                            <span class="badge ${category.status ? 'bg-success' : 'bg-danger'}">
                                                ${category.status ? 'Active' : 'Inactive'}
                                            </span>
                                        </td>
                                        <td>
                                            <div class="btn-group" role="group">
                                                <button type="button" class="btn btn-warning btn-sm" 
                                                        onclick="editCategory(${category.id})">Edit</button>
                                                <button type="button" class="btn btn-danger btn-sm"
                                                        onclick="deleteCategory(${category.id})">Delete</button>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>

    <!-- Add Category Modal -->
    <div class="modal fade" id="addCategoryModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Add New Category</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="${pageContext.request.contextPath}/manager/category/add" method="POST">
                    <div class="modal-body">
                        <input type="hidden" name="userId" value="${selectedUser.id}">
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input type="text" class="form-control" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Code</label>
                            <input type="text" class="form-control" name="code" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Image URL</label>
                            <input type="text" class="form-control" name="images">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="status" checked>
                            <label class="form-check-label">Active</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save Category</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function editCategory(id) {
            window.location.href = '${pageContext.request.contextPath}/manager/category/edit/' + id;
        }

        function deleteCategory(id) {
            if (confirm('Are you sure you want to delete this category?')) {
                window.location.href = '${pageContext.request.contextPath}/manager/category/delete/' + id;
            }
        }
    </script>
</body>
</html>