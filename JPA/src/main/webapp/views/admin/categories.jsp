<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quản lý Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .navbar {
            background-color: #2c3e50 !important;
            box-shadow: 0 2px 4px rgba(0,0,0,.1);
        }
        .navbar-brand {
            color: white !important;
            font-weight: bold;
        }
        .nav-welcome {
            color: #ecf0f1;
        }
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0,0,0,.08);
        }
        .card-header {
            background-color: #fff;
            border-bottom: 2px solid #f8f9fa;
            padding: 1rem;
        }
        .table {
            margin-bottom: 0;
        }
        .table thead th {
            border-top: none;
            background-color: #f8f9fa;
            color: #2c3e50;
            font-weight: 600;
        }
        .btn-add {
            background-color: #2ecc71;
            border: none;
            padding: 0.5rem 1rem;
        }
        .btn-add:hover {
            background-color: #27ae60;
        }
        .badge {
            padding: 0.5em 1em;
        }
        .btn-group .btn {
            margin: 0 2px;
        }
        .btn-edit {
            background-color: #f1c40f;
            border: none;
            color: white;
        }
        .btn-edit:hover {
            background-color: #f39c12;
            color: white;
        }
        .btn-delete {
            background-color: #e74c3c;
            border: none;
        }
        .btn-delete:hover {
            background-color: #c0392b;
        }
        .btn-assign {
            background-color: #3498db;
            border: none;
        }
        .btn-assign:hover {
            background-color: #2980b9;
        }
        .modal-content {
            border: none;
            border-radius: 10px;
        }
        .modal-header {
            background-color: #2c3e50;
            color: white;
            border-radius: 10px 10px 0 0;
        }
        .modal-title {
            font-weight: 600;
        }
        .btn-close {
            filter: brightness(0) invert(1);
        }
        .form-label {
            font-weight: 500;
            color: #2c3e50;
        }
        .category-image {
            border-radius: 5px;
            border: 1px solid #dee2e6;
            padding: 2px;
        }
        .status-badge {
            font-size: 0.85em;
            padding: 0.5em 0.85em;
        }
        .table-hover tbody tr:hover {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
    <!-- Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark mb-4">
        <div class="container">
            <a class="navbar-brand" href="#">
                <i class="fas fa-tasks me-2"></i>
                Category Management System
            </a>
            <div class="d-flex align-items-center">
                <span class="nav-welcome me-3">
                    <i class="fas fa-user-circle me-1"></i>
                    Welcome, ${user.username}
                </span>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-outline-light btn-sm">
                    <i class="fas fa-sign-out-alt me-1"></i>
                    Đăng xuất
                </a>
            </div>
        </div>
    </nav>

    <div class="container">
        <!-- Main Content Card -->
        <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
                <h5 class="mb-0">
                    <i class="fas fa-list me-2"></i>
                    Danh sách Categories
                </h5>
                <button type="button" class="btn btn-add" data-bs-toggle="modal" data-bs-target="#addCategoryModal">
                    <i class="fas fa-plus me-1"></i>
                    Thêm Category
                </button>
            </div>
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Tên</th>
                                <th>Code</th>
                                <th>Hình ảnh</th>
                                <th>Trạng thái</th>
                                <c:if test="${user.roleid == 3 || user.roleid == 2}">
                                    <th>Người dùng</th>
                                </c:if>
                                <th>Hành động</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${categories}" var="category">
                                <tr>
                                    <td>${category.id}</td>
                                    <td>${category.name}</td>
                                    <td><code>${category.code}</code></td>
                                    <td>
                                        <c:if test="${not empty category.images}">
                                            <img src="${category.images}" alt="${category.name}" 
                                                 class="category-image" style="max-width: 50px;">
                                        </c:if>
                                    </td>
                                    <td>
                                        <span class="badge status-badge ${category.status ? 'bg-success' : 'bg-danger'}">
                                            <i class="fas ${category.status ? 'fa-check-circle' : 'fa-times-circle'} me-1"></i>
                                            ${category.status ? 'Active' : 'Inactive'}
                                        </span>
                                    </td>
                                    <c:if test="${user.roleid == 3 || user.roleid == 2}">
                                        <td>
                                            <i class="fas fa-user me-1"></i>
                                            ${category.user.username}
                                        </td>
                                    </c:if>
                                    <td>
                                        <div class="btn-group" role="group">
                                            <button type="button" class="btn btn-edit btn-sm" 
                                                    onclick="editCategory(${category.id}, '${category.name}', '${category.code}', '${category.images}', ${category.status})">
                                                <i class="fas fa-edit"></i>
                                            </button>
                                            <form action="${pageContext.request.contextPath}${user.roleid == 3 ? '/admin' : user.roleid == 2 ? '/manager' : '/user'}/category/delete/${category.id}" 
                                                  method="POST" 
                                                  style="display: inline;"
                                                  onsubmit="return confirm('Bạn có chắc muốn xóa category này?');">
                                                <button type="submit" class="btn btn-delete btn-sm">
                                                    <i class="fas fa-trash-alt"></i>
                                                </button>
                                            </form>
                                            <c:if test="${user.roleid == 2}">
                                                <button type="button" class="btn btn-assign btn-sm" 
                                                        onclick="showAssignModal(${category.id})">
                                                    <i class="fas fa-user-plus"></i>
                                                </button>
                                            </c:if>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <!-- Add Category Modal -->
    <div class="modal fade" id="addCategoryModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <i class="fas fa-plus-circle me-2"></i>
                        Thêm Category Mới
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form method="POST" action="${pageContext.request.contextPath}${user.roleid == 3 ? '/admin' : user.roleid == 2 ? '/manager' : '/user'}/category/add">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label class="form-label">Tên</label>
                            <input type="text" class="form-control" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Code</label>
                            <input type="text" class="form-control" name="code" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">URL Hình ảnh</label>
                            <input type="text" class="form-control" name="images">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="status" checked>
                            <label class="form-check-label">Active</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                            <i class="fas fa-times me-1"></i>
                            Đóng
                        </button>
                        <button type="submit" class="btn btn-add">
                            <i class="fas fa-save me-1"></i>
                            Lưu
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Edit Category Modal -->
    <div class="modal fade" id="editCategoryModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">
                        <i class="fas fa-edit me-2"></i>
                        Sửa Category
                    </h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form id="editCategoryForm" method="POST">
                    <div class="modal-body">
                        <input type="hidden" name="id" id="editCategoryId">
                        <div class="mb-3">
                            <label class="form-label">Tên</label>
                            <input type="text" class="form-control" name="name" id="editCategoryName" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Code</label>
                            <input type="text" class="form-control" name="code" id="editCategoryCode" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">URL Hình ảnh</label>
                            <input type="text" class="form-control" name="images" id="editCategoryImages">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" name="status" id="editCategoryStatus">
                            <label class="form-check-label">Active</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                            <i class="fas fa-times me-1"></i>
                            Đóng
                        </button>
                        <button type="submit" class="btn btn-warning">
                            <i class="fas fa-save me-1"></i>
                            Cập nhật
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Assign User Modal -->
    <c:if test="${user.roleid == 2}">
        <div class="modal fade" id="assignCategoryModal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">
                            <i class="fas fa-user-plus me-2"></i>
                            Gán Category cho User
                        </h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <form method="POST" action="${pageContext.request.contextPath}/manager/category/assign">
                        <input type="hidden" name="categoryId" id="assignCategoryId">
                        <div class="modal-body">
                            <div class="mb-3">
                                <label class="form-label">Chọn User</label>
                                <select class="form-select" name="userId" required>
                                    <c:forEach items="${managedUsers}" var="managedUser">
                                        <option value="${managedUser.id}">${managedUser.username}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                <i class="fas fa-times me-1"></i>
                                Đóng
                            </button>
                            <button type="submit" class="btn btn-assign">
                                <i class="fas fa-user-plus me-1"></i>
                                Gán
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </c:if>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function editCategory(id, name, code, images, status) {
            document.getElementById('editCategoryId').value = id;
            document.getElementById('editCategoryName').value = name;
            document.getElementById('editCategoryCode').value = code;
            document.getElementById('editCategoryImages').value = images || '';
            document.getElementById('editCategoryStatus').checked = status;

            const baseUrl = '${pageContext.request.contextPath}';
            const role = ${user.roleid};
            const rolePath = role === 3 ? '/admin' : role === 2 ? '/manager' : '/user';
            document.getElementById('editCategoryForm').action = baseUrl + rolePath + '/category/edit/' + id;
            
            new bootstrap.Modal(document.getElementById('editCategoryModal')).show();
        }

        function showAssignModal(categoryId) {
            document.getElementById('assignCategoryId').value = categoryId;
            new bootstrap.Modal(document.getElementById('assignCategoryModal')).show();
        }
    </script>
</body>
</html>