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
    <form action="/mau-sac/update" method="post">
        <h3 class="mt-3 text-center">Chi Tiết Màu Sắc</h3>
        <div class="mb-3">
            <input type="hidden" class="form-control" name="id" value="${s.id}">
            <label class="form-label">Mã Màu Sắc</label>
            <input type="text" class="form-control" name="maMauSac" value="${s.maMauSac}">
        </div>
        <div class="mb-3">
            <label class="form-label">Tên Màu Sắc</label>
            <input type="text" class="form-control" name="tenMauSac"  value="${s.tenMauSac}">
        </div>
        <div class="mb-3">
            <label class="form-label">Trạng Thái</label>
            <select class="form-select" name="trangThai">
                <option value="Hoạt động"${s.trangThai=="Hoạt động"?"selected":""}>Hoạt động</option>
                <option value="Ngừng hoạt động"${s.trangThai=="Ngừng hoạt động"?"selected":""}>Ngừng hoạt động</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>

</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
