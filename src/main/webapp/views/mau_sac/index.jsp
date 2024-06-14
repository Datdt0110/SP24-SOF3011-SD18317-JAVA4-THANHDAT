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
<%@include file="/views/master-layout/header.jsp"%>
<div class="container">
  <form action="/mau-sac/add" method="post">
  <h3 class="mt-3 text-center">Thêm Mới Màu Sắc    </h3>
  <div class="mb-3">
    <label class="form-label">Mã Màu Sắc</label>
    <input type="text" class="form-control" name="maMauSac">
  </div>
  <div class="mb-3">
    <label class="form-label">Tên Màu Sắc</label>
    <input type="text" class="form-control" name="tenMauSac">
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
  <h3 class="mt-3 text-center">Danh Sách Màu Sắc</h3>
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
    <c:forEach items="${listMauSac}" var="ms">
      <tr>
        <td>${ms.maMauSac}</td>
        <td>${ms.tenMauSac}</td>
        <td>${ms.trangThai}</td>
        <td>${ms.ngayTao}</td>
        <td>${ms.ngaySua}</td>
        <td>
          <a href="/mau-sac/detail?id=${ms.id}" class="btn btn-warning">Chi Tiết</a>
          <a href="/mau-sac/delete?id=${ms.id}" class="btn btn-dark">Delete</a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
