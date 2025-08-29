<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đăng Ký Tài Khoản</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    body {
      background: #f9f9f9;
      font-family: Arial, sans-serif;
    }
    .register-box {
      width: 500px;
      margin: 60px auto;
      background: #fff;
      padding: 25px;
      border-radius: 5px;
      box-shadow: 0px 0px 10px #ccc;
    }
    .register-box h2 {
      text-align: center;
      margin-bottom: 25px;
    }
    .btn-register {
      width: 100%;
      background-color: #4CAF50;
      color: white;
    }
    .bottom-text {
      text-align: center;
      margin-top: 20px;
    }
  </style>
</head>
<body>

<div class="register-box">
  <h2>Đăng Ký Tài Khoản</h2>
  
  <form action="register" method="post">
    <!-- Tài khoản -->
    <div class="input-group" style="margin-bottom:15px;">
      <span class="input-group-addon"><i class="fa fa-user"></i></span>
      <input type="text" name="username" class="form-control" placeholder="Tài khoản">
    </div>
    
    <!-- Email -->
    <div class="input-group" style="margin-bottom:15px;">
      <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
      <input type="email" name="email" class="form-control" placeholder="Địa chỉ email">
    </div>
    
    <!-- Họ tên -->
    <div class="input-group" style="margin-bottom:15px;">
      <span class="input-group-addon"><i class="fa fa-id-card"></i></span>
      <input type="text" name="fullname" class="form-control" placeholder="Họ và tên">
    </div>
    
    <!-- Mật khẩu -->
    <div class="input-group" style="margin-bottom:15px;">
      <span class="input-group-addon"><i class="fa fa-lock"></i></span>
      <input type="password" name="password" class="form-control" placeholder="Mật khẩu">
    </div>
    
    <!-- Số điện thoại -->
    <div class="input-group" style="margin-bottom:15px;">
      <span class="input-group-addon"><i class="fa fa-phone"></i></span>
      <input type="text" name="phone" class="form-control" placeholder="Số điện thoại">
    </div>
    
    <button type="submit" class="btn btn-register">Đăng ký</button>
    
    <div class="bottom-text">
      Nếu bạn đã có tài khoản, hãy <a href="login">Đăng nhập</a>
    </div>
  </form>
</div>

</body>
</html>
