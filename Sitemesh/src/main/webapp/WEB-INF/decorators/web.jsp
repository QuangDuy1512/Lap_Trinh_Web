<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title><sitemesh:write property="title"/></title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
  <!-- Header chung -->
  <header>
    <%@ include file="/commons/header.jsp" %>
  </header>

  <!-- Nội dung trang -->
  <main class="container my-4">
    <sitemesh:write property="body"/>
  </main>

  <!-- Footer chung -->
  <footer class="bg-light text-center p-3 mt-4">
    <%@ include file="/commons/footer.jsp" %>
  </footer>

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
