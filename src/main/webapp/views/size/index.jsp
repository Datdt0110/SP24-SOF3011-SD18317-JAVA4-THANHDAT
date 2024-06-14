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
<div class="container">
    <form action="/size/add" method="post">
        <h3 class="mt-3 text-center">Thêm Mới Kích Cỡ    </h3>
        <div class="mb-3">
            <label class="form-label">Mã Kích Cỡ</label>
            <input type="text" class="form-control" name="maSize">
        </div>
        <div class="mb-3">
            <label class="form-label">Tên Kích Cỡ</label>
            <input type="text" class="form-control" name="tenSize">
        </div>
        <div class="mb-3">
            <label class="form-label">Trạng Thái</label>
            <select class="form-select" name="trangThai" >
                <option value="Hoạt động">Hoạt động</option>
                <option value="Ngừng hoạt động">Ngừng hoạt động</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <h3 class="mt-3 text-center">Danh Sách Kích Cỡ</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <td>Mã</td>
            <td>Tên</td>
            <td>Trạng Thái</td>
            <td>Ngày Tạo</td>
            <td>Ngày Sửa</td>
            <td>Chức Năng</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listSize}" var="s">
            <tr>
                <td>${s.maSize}</td>
                <td>${s.tenSize}</td>
                <td>${s.trangThai}</td>
                <td>${s.ngayTao}</td>
                <td>${s.ngaySua}</td>
                <td>
                    <a href="/size/detail?id=${ms.id}" class="btn btn-warning">Chi Tiết</a>
                    <a href="/size/delete?id=${ms.id}" class="btn btn-dark">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
