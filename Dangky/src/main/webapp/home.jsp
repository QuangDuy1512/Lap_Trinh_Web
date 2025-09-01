<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Trang Chủ</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <style>
    body {
      background: #f9f9f9;
      font-family: Arial, sans-serif;
    }
    .home-box {
      width: 500px;
      margin: 100px auto;
      background: #fff;
      padding: 30px;
      border-radius: 5px;
      box-shadow: 0px 0px 10px #ccc;
      text-align: center;
    }
    .btn-logout {
      margin-top: 20px;
    }
    .topbar-container {
  		background-color: #00FF00;
  		color: white;
  		text-align: right;
  		padding: 20px;
	}
  </style>
</head>
<body>
<div class="topbar-container">
    <jsp:include page="/topbar.jsp"></jsp:include>
</div>

<div class="home-box">
  <h2>Xin chào!</h2>
  <p>Bạn đã đăng nhập thành công.</p>
  
  <a href="<%= request.getContextPath() %>/logout" class="btn btn-danger btn-logout">Đăng xuất</a>
</div>

</body>
</html>
