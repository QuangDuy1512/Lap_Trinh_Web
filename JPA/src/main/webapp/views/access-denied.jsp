<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Truy cập bị từ chối</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    body {
      background: #f2f2f2;
      font-family: Arial, sans-serif;
    }
    .denied-box {
      width: 600px;
      margin: 100px auto;
      background: #fff;
      padding: 30px;
      border-radius: 8px;
      box-shadow: 0 0 15px rgba(0,0,0,0.2);
      text-align: center;
    }
    .denied-box h1 {
      font-size: 80px;
      color: #d9534f;
      margin-bottom: 20px;
    }
    .denied-box p {
      font-size: 18px;
      margin-bottom: 25px;
    }
    .btn-back {
      background: #0275d8;
      color: #fff;
      padding: 10px 20px;
      text-decoration: none;
      border-radius: 5px;
    }
    .btn-back:hover {
      background: #025aa5;
    }
  </style>
</head>
<body>
<div class="denied-box">
  <h1><i class="fa fa-ban"></i> 403</h1>
  <p>Bạn không có quyền truy cập vào trang này.</p>
  <a href="${pageContext.request.contextPath}/login.jsp" class="btn-back">
    <i class="fa fa-home"></i> Quay về trang đăng nhập
  </a>
</div>
</body>
</html>
