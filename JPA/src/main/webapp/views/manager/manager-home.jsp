<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manager Dashboard</title>
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

    <!-- Bảng danh sách Category của Manager -->
    <div class="card">
        <div class="card-header d-flex justify-content-between align-items-center">
            <h4 class="mb-0">Categories của bạn</h4>
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addCategoryModal">
                + Thêm Category
            </button>
        </div>
        <div class="card-body">
            <table class="table table-bordered table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Tên</th>
                        <th>Code</th>
                        <th>Hình ảnh</th>
                        <th>Trạng thái</th>
                        <th>Hành động</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${categories}" var="category">
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
                                <button class="btn btn-warning btn-sm" onclick="editCategory(${category.id})">
                                    <i class="fas fa-edit"></i> Sửa
                                </button>
                                <button class="btn btn-danger btn-sm" onclick="deleteCategory(${category.id})">
                                    <i class="fas fa-trash"></i> Xóa
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal thêm mới Category -->
    <div class="modal fade" id="addCategoryModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Thêm Category mới</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form action="${pageContext.request.contextPath}/manager/category/add" method="POST">
                    <div class="modal-body">
                        <div class="mb-3">
                            <label class="form-label">Tên Category</label>
                            <input type="text" class="form-control" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mã Code</label>
                            <input type="text" class="form-control" name="code" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">URL Hình ảnh</label>
                            <input type="text" class="form-control" name="images">
                        </div>
                        <div class="form-check mb-3">
                            <input type="checkbox" class="form-check-input" name="status" checked>
                            <label class="form-check-label">Kích hoạt</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Lưu Category</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Modal chỉnh sửa Category -->
    <div class="modal fade" id="editCategoryModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Chỉnh sửa Category</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <form id="editCategoryForm" method="POST">
                    <div class="modal-body">
                        <input type="hidden" id="editCategoryId" name="id">
                        <div class="mb-3">
                            <label class="form-label">Tên Category</label>
                            <input type="text" class="form-control" id="editCategoryName" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Mã Code</label>
                            <input type="text" class="form-control" id="editCategoryCode" name="code" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">URL Hình ảnh</label>
                            <input type="text" class="form-control" id="editCategoryImages" name="images">
                        </div>
                        <div class="form-check mb-3">
                            <input type="checkbox" class="form-check-input" id="editCategoryStatus" name="status">
                            <label class="form-check-label">Kích hoạt</label>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Đóng</button>
                        <button type="submit" class="btn btn-primary">Cập nhật</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://kit.fontawesome.com/your-code.js" crossorigin="anonymous"></script>
    <script>
        function editCategory(id) {
            fetch('${pageContext.request.contextPath}/manager/category/' + id)
                .then(response => response.json())
                .then(category => {
                    document.getElementById('editCategoryId').value = category.id;
                    document.getElementById('editCategoryName').value = category.name;
                    document.getElementById('editCategoryCode').value = category.code;
                    document.getElementById('editCategoryImages').value = category.images;
                    document.getElementById('editCategoryStatus').checked = category.status;
                    document.getElementById('editCategoryForm').action = '${pageContext.request.contextPath}/manager/category/edit/' + id;
                    new bootstrap.Modal(document.getElementById('editCategoryModal')).show();
                });
        }

        function deleteCategory(id) {
            if (confirm('Bạn có chắc chắn muốn xóa category này?')) {
                fetch('${pageContext.request.contextPath}/manager/category/delete/' + id, { 
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    if (response.ok) {
                        window.location.reload();
                    } else {
                        alert('Có lỗi xảy ra khi xóa category');
                    }
                });
            }
        }
    </script>
</body>
</html>