<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Thêm Category</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h2>Thêm Category mới</h2>
  <form method="post" action="${pageContext.request.contextPath}/add">
    <div class="form-group">
      <label>Tên Category</label>
      <input type="text" name="name" class="form-control" required>
    </div>
    <div class="form-group">
      <label>Icon</label>
      <input type="text" name="icon" class="form-control">
    </div>
    <button type="submit" class="btn btn-success">Thêm</button>
    <a href="${pageContext.request.contextPath}/category/list" class="btn btn-default">Hủy</a>
  </form>
</div>
</body>
</html>
