<%--
  Created by IntelliJ IDEA.
  User: ThanhDat
  Date: 28/03/2024
  Time: 2:52 CH
  To change this template use File | Settings | File Templates.
--%>
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

    <h3 style="text-align: center;margin-top:15px;">Thông tin Sản Phẩm</h3>
    <form action="/san-pham/update"  method="post">
        <div class="col-md">
            <input type="hidden" class="form-control" name="id" value="${s.id}">
            <label class="form-label">Mã</label>
            <input type="text" class="form-control" name="maSanPham" value="${s.maSanPham}">
        </div>
        <div class="col-md">
            <label class="form-label">Tên</label>
            <input type="text" class="form-control" name="tenSanPham" value="${s.tenSanPham}">
        </div>
        <div class="col-md">
            <label class="form-label">Danh Mục</label>
            <select class="form-select" name="danhMuc" value="${s.danhMuc}">
                <c:forEach var="dm" items="${listDanhMuc}">
                    <option value="${dm.id}"${s.danhMuc.id == dm.id? "selected":""}    >${dm.tenDanhMuc}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md">
            <label class="form-label">Trạng Thái</label>
            <select class="form-select" name="trangThai" value="${s.trangThai}">
                <option value="Hoạt động" ${s.trangThai== "Hoạt động"?"selected":""}>Hoạt động</option>
                <option value="Ngừng hoạt động" ${s.trangThai== "Ngừng hoạt động"?"selected":""}>Ngừng Hoạt Động</option>
            </select>
        </div>
        <div class="row mt-4" style="justify-content: center">
            <button class="btn btn-primary" type="submit">Update</button>
        </div>

    </form>
</div>
    <%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
