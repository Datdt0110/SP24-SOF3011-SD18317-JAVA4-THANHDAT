<%--
  Created by IntelliJ IDEA.
  User: ThanhDat
  Date: 25/03/2024
  Time: 8:53 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@include file="/views/master-layout/header.jsp"%>
<div class="container">

    <form action="/danh-muc/add" method="post">

        <h3 class="mt-3 text-center">Thêm Mới Danh Mục    </h3>
        <div class="mb-3">
            <label class="form-label">Mã Danh Mục</label>
            <input type="text" class="form-control" name="maDanhMuc">
        </div>
        <div class="mb-3">
            <label class="form-label">Tên Danh Mục</label>
            <input type="text" class="form-control" name="tenDanhMuc">
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


    <h3 class="text-center mt-3"> Danh Sách Danh Mục</h3>

    <table class="table">
        <thead>
        <tr>
            <td>ID</td>
            <td>Mã</td>
            <td>Tên</td>
            <td>Trạng Thái</td>
            <td>Ngay Tạo</td>
            <td>Ngáy Sửa</td>
            <td>Chức Năng</td>

        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listDanhMuc}" var="dm">
            <tr>
                <td>${dm.id}</td>
                <td>${dm.maDanhMuc}</td>
                <td>${dm.tenDanhMuc}</td>
                <td>${dm.trangThai}</td>
                <td>${dm.ngayTao}</td>
                <td>${dm.ngaySua}</td>
                <td>
                    <a href="/danh-muc/detail?id=${dm.id}" class="btn btn-warning">Chi Tiết</a>
                    <a href="/danh-muc/delete?id=${dm.id}" class="btn btn-dark">Xoa</a>

                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
