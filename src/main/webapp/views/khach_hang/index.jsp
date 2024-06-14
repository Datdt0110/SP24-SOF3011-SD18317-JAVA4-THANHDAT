<%--
  Created by IntelliJ IDEA.
  User: ThanhDat
  Date: 25/03/2024
  Time: 8:53 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

    <title>Title</title>
</head>
<body>
<%@include file="/views"%>
<div class="container">
    <form action="/khach-hang/add" method="post">
        <h3 class="mt-3 text-center">Thêm Mới Khách Hàng  </h3>
        <div class="mb-3">
            <label class="form-label">Tên Khách Hàng</label>
            <input type="text" class="form-control" name="ten">
        </div>
        <div class="mb-3">
            <label class="form-label">Địa Chỉ</label>
            <input type="text" class="form-control" name=diaChi>
        </div>
        <div class="mb-3">
            <label class="form-label">Số Điện Thoại</label>
            <input type="text" class="form-control" name=sdt>
        </div>
        <div class="mb-3">
            <label class="form-label">Trạng Thái</label>
            <select class="form-select" name="trangThai">
                <option value="Hoạt động">Hoạt động</option>
                <option value="Ngừng hoạt động">Ngừng hoạt động</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <h3 class="mt-3 text-center">Danh Sách Khách Hàng</h3>
    <table class="table table-striped table-bordered">
        <thead>
        <tr>
            <td>STT</td>
            <td>ID</td>
            <td>Tên</td>
            <td>Địa chỉ</td>
            <td>SDT</td>
            <td>Trạng Thái</td>
            <td>Ngày Tạo</td>
            <td>Ngày Sửa</td>
            <td> Chức Năng</td>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listKhachHang}" var="kh" varStatus="i">
            <tr>
                <td>${i.index+1}</td>
                <td>${kh.id}</td>
                <td class="text-danger">${kh.ten}</td>
                <td>${kh.diaChi}</td>
                <td>${kh.sdt}</td>
                <td>${kh.trangThai}</td>
                <th>${kh.ngayTao}</th>
                <th>${kh.ngaySua}</th>
                <th>
                    <a href="/khach-hang/detail?id=${kh.id}" class="btn btn-warning">Chi Tiết</a>
                    <a  href="/khach-hang/delete?id=${kh.id}" class="btn btn-dark">Delete</a>
                </th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
