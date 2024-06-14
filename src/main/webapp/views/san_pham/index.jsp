<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@include file="/views/master-layout/header.jsp"%>
<div class="container">

    <h3 style="text-align: center;margin-top:15px;">Thêm thông tin Sản Phẩm</h3>
    <form action="/san-pham/add" method="post">
        <div class="col-md">
            <label class="form-label">Mã</label>
            <input type="text" class="form-control" name="maSanPham">
        </div>
        <div class="col-md">
            <label class="form-label">Tên</label>
            <input type="text" class="form-control" name="tenSanPham">
        </div>
        <div class="col-md">
            <label class="form-label">Danh Mục</label>
            <select class="form-select" name="danhMuc">
                <c:forEach var="dm" items="${listDanhMuc}">
                    <option value="${dm.id}">${dm.tenDanhMuc}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md">
            <label class="form-label">Trạng Thái</label>
            <select class="form-select" name="trangThai">
                <option value="Hoạt động">Hoạt động</option>
                <option value="Ngừng hoạt động">Ngừng hoạt động</option>
            </select>
        </div>
        <div class="row mt-4" style="justify-content: start">

            <button type="submit" class="btn btn-success col-1 m-3">Add</button>
        </div>
    </form>
    <h3>Quản lý Sản Phẩm</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>Id</td>
            <td>Mã</td>
            <td>Tên</td>
            <td>Trạng Thái</td>
            <td>Ngày Tạo</td>
            <td>Ngay Sua</td>
            <td>Danh Mục</td>
            <td>Chức Năng</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="sp">
            <tr>
                <td>${sp.id}</td>
                <td>${sp.maSanPham}</td>
                <td>${sp.tenSanPham}</td>
                <td>${sp.trangThai}</td>
                <td>${sp.ngayTao}</td>
                <td>${sp.ngaySua}</td>
                <td>${sp.danhMuc.tenDanhMuc}</td>
                <td>
                    <a href="/san-pham/detail?id=${sp.id}" class="btn btn-warning">Chi Tiet</a>
                    <a href="/san-pham/delete?id=${sp.id}" class="btn btn-dark">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
