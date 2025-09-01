<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Model.Category" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Danh sách Category</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f9f9f9;
      padding: 20px;
    }
    .container {
      background: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0px 0px 10px #ccc;
    }
     .topbar-container {
  		background-color: #00FF00;
  		color: white;
  		text-align: right;
  		padding: 20px;
	}
    table {
      margin-top: 20px;
    }
  </style>
</head>
<body>
<div class="topbar-container">
    <jsp:include page="/topbar.jsp"></jsp:include>
</div>
<div class="container">
  <h2>Danh sách Category của bạn</h2>
  <a href="<%= request.getContextPath() %>/add" class="btn btn-primary">+ Thêm Category</a>
  <table class="table table-bordered table-striped">
    <thead>
      <tr>
        <th>ID</th>
        <th>Tên Category</th>
        <th>Icon</th>
        <th>Thao tác</th>
      </tr>
    </thead>
    <tbody>
      <%
        List<Category> cateList = (List<Category>) request.getAttribute("cateList");
        if (cateList != null && !cateList.isEmpty()) {
          for (Category cate : cateList) {
      %>
        <tr>
          <td><%= cate.getId() %></td>
          <td><%= cate.getName() %></td>
          <td>
            <% if (cate.getIcon() != null && !cate.getIcon().isEmpty()) { %>
              <img src="<%= request.getContextPath() %>/images/<%= cate.getIcon() %>" alt="icon" width="40" height="40">
            <% } %>
          </td>
          <td>
            <a href="<%= request.getContextPath() %>/edit?id=<%= cate.getId() %>" 
               class="btn btn-sm btn-warning">Sửa</a>
            <a href="<%= request.getContextPath() %>/delete?id=<%= cate.getId() %>" 
               class="btn btn-sm btn-danger"
               onclick="return confirm('Bạn có chắc chắn muốn xóa?');">Xóa</a>
          </td>
        </tr>
      <%
          }
        } else {
      %>
        <tr>
          <td colspan="4" class="text-center">Chưa có category nào</td>
        </tr>
      <%
        }
      %>
    </tbody>
  </table>
</div>

</body>
</html>
