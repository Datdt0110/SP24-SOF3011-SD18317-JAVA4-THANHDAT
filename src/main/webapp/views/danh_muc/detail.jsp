<%--
  Created by IntelliJ IDEA.
  User: ThanhDat
  Date: 28/03/2024
  Time: 6:36 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <title>Title</title>
</head>
<body>
<%@include file="/views/master-layout/header.jsp"%>
<div class="container">

  <form action="/danh-muc/update" method="post">
    <h1 class="text-center mt-3">Chi Tiết Danh Mục</h1>
    <div class="mb-3">
      <input type="hidden" class="form-control" name="id" value="${s.id}">
      <label class="form-label">Mã Danh Mục</label>
      <input type="text" class="form-control" name="maDanhMuc" value="${s.maDanhMuc}">
    </div>
    <div class="mb-3">
      <label class="form-label">Tên Danh Mục</label>
      <input type="text" class="form-control" name="tenDanhMuc"  value="${s.tenDanhMuc}">
    </div>
    <div class="mb-3">
      <label class="form-label">Trạng Thái</label>
      <select class="form-select" name="trangThai" >
        <option value="Hoạt động" ${s.trangThai== "Hoạt động"?"selected":""}>Hoạt động</option>
        <option value="Ngừng hoạt động" ${s.trangThai== "Ngừng Hoạt động"?"selected":""}>Ngừng hoạt động</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary text-center">Update</button>
  </form>
</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
