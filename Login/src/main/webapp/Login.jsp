<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Đăng Nhập Vào Hệ Thống</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    body {
      background: #f9f9f9;
      font-family: Arial, sans-serif;
    }
    .login-box {
      width: 400px;
      margin: 80px auto;
      background: #fff;
      padding: 25px;
      border-radius: 5px;
      box-shadow: 0px 0px 10px #ccc;
    }
    .login-box h2 {
      text-align: center;
      margin-bottom: 25px;
    }
    .remember {
      margin-top: 10px;
      margin-bottom: 15px;
    }
    .bottom-text {
      text-align: center;
      margin-top: 20px;
    }
    .btn-login {
      width: 100%;
      background-color: #2196f3;
      color: white;
    }
  </style>
</head>
<body>

<div class="login-box">
  <h2>Đăng Nhập Vào Hệ Thống</h2>
  
  <form action="login" method="post">
    <!-- Tài khoản -->
    <div class="input-group" style="margin-bottom:15px;">
      <span class="input-group-addon"><i class="fa fa-user"></i></span>
      <input type="text" name="username" class="form-control" placeholder="Tài khoản">
    </div>
    <!-- Mật khẩu -->
    <div class="input-group" style="margin-bottom:15px;">
      <span class="input-group-addon"><i class="fa fa-lock"></i></span>
      <input type="password" name="password" class="form-control" placeholder="Mật khẩu">
    </div>
    
    <div class="row remember">
      <div class="col-xs-6">
        <label><input type="checkbox"> Nhớ tôi</label>
      </div>
      <div class="col-xs-6 text-right">
        <a href="#">Quên mật khẩu?</a>
      </div>
    </div>
    
    <button type="submit" class="btn btn-login">Đăng nhập</button>
    
    <div class="bottom-text">
      Nếu bạn chưa có tài khoản trên hệ thống, thì hãy <a href="#">Đăng ký</a>
    </div>
  </form>
</div>

</body>
</html>