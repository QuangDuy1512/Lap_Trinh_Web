<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row topbar">
    <%
        Object account = session.getAttribute("account");
        if (account == null) {
    %>
        <!-- Nếu chưa đăng nhập -->
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    <a href="<%= request.getContextPath() %>/login">Đăng nhập</a> | 
                    <a href="<%= request.getContextPath() %>/register">Đăng ký</a>
                </li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    <%
        } else {
            // Giả sử account là object User có phương thức getFullName()
            Model.User user = (Model.User) account;
    %>
        <!-- Nếu đã đăng nhập -->
        <div class="col-sm-6">
            <ul class="list-inline right-topbar pull-right">
                <li>
                    <a href="<%= request.getContextPath() %>/member/myaccount">
                        Xin chào, <%= user.getFullName() %>
                    </a> | 
                    <a href="<%= request.getContextPath() %>/logout">Đăng xuất</a>
                </li>
                <li><i class="search fa fa-search search-button"></i></li>
            </ul>
        </div>
    <%
        }
    %>
</div>
