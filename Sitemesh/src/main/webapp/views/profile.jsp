<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<div class="container">
    <h2 class="mt-4">Update Profile</h2>
    <form action="${pageContext.request.contextPath}/profile/update"
          method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label>Fullname</label>
            <input type="text" name="fullname" class="form-control" value="${user.fullname}">
        </div>
        <div class="mb-3">
            <label>Phone</label>
            <input type="text" name="phone" class="form-control" value="${user.phone}">
        </div>
        <div class="mb-3">
            <label>Profile Image</label><br>
            <c:if test="${not empty user.image}">
                <img src="${pageContext.request.contextPath}/uploads/${user.image}" width="100">
            </c:if>
            <input type="file" name="image" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
</body>
</html>
