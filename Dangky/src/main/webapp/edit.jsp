<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Model.Category" %>
<%
  Category cate = (Category) request.getAttribute("cate");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sửa Category</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h2>Sửa Category</h2>
  <form method="post" action="${pageContext.request.contextPath}/edit">
    <input type="hidden" name="id" value="<%= cate.getId() %>">
    <div class="form-group">
      <label>Tên Category</label>
      <input type="text" name="name" class="form-control" value="<%= cate.getName() %>" required>
    </div>
    <div class="form-group">
      <label>Icon</label>
      <input type="text" name="icon" class="form-control" value="<%= cate.getIcon() %>">
    </div>
    <button type="submit" class="btn btn-primary">Cập nhật</button>
    <a href="${pageContext.request.contextPath}/category/list" class="btn btn-default">Hủy</a>
  </form>
</div>
</body>
</html>
