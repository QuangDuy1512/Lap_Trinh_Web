<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Quên mật khẩu</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>

<div class="container" style="max-width: 500px; margin-top: 60px;">
    <h2>Quên mật khẩu</h2>

    <c:if test="${alert != null}">
        <div class="alert alert-danger">${alert}</div>
    </c:if>

    <c:if test="${password != null}">
        <div class="alert alert-success">
            Mật khẩu của bạn là: <b>${password}</b>
        </div>
    </c:if>

    <form action="forget" method="post">
        <div class="form-group">
            <label>Tài khoản</label>
            <input type="text" class="form-control" name="username" placeholder="Nhập tài khoản">
        </div>
        <div class="form-group">
            <label>Email</label>
            <input type="email" class="form-control" name="email" placeholder="Nhập email đã đăng ký">
        </div>
        <button type="submit" class="btn btn-primary">Lấy lại mật khẩu</button>
    </form>
</div>
</body>
</html>
