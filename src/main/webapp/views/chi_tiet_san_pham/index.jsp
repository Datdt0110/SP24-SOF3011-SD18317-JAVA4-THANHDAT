<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<%@include file="/views/master-layout/header.jsp"%>
<div class="container">
    <h3 class="mt-3 text-center">Thêm Chi Tiết Sản Phẩm</h3>
    <form action="/ctsp/add" method="POST">
        <div class="col-md">
            <label class="form-label">Tên Sản Phẩm</label>
            <select class="form-select" name="sanPham">
                <c:forEach var="dm" items="${listSanPham}">
                    <option value="${dm.id}">${dm.tenSanPham}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md">
            <label class="form-label">Tên Màu Sắc</label>
            <select class="form-select" name="mauSac">
                <c:forEach var="dm" items="${listMauSac}">
                    <option value="${dm.id}">${dm.tenMauSac}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md">
            <label class="form-label">Tên Size</label>
            <select class="form-select" name="size">
                <c:forEach var="dm" items="${listSize}">
                    <option value="${dm.id}">${dm.tenSize}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Giá Bán:</label>
            <input type="text" class="form-control" id="giaBan" name="giaBan">
        </div>
        <div class="mb-3">
            <label class="form-label">Số Lượng Tồn:</label>
            <input type="text" class="form-control" id="so_luong_ton" name="soLuongTon">
        </div>
        <div class="mb-3">
        <label class="form-label">Trạng Thái</label>
        <select class="form-select" name="trangThai">
            <option value="Đã Thanh toán">Đã Thanh toán</option>
            <option value="Chưa Thanh toán">Chưa Thanh toán</option>
        </select>
</div>
        <button type="submit" class="btn btn-primary mt-3">Thêm</button>
    </form>

    <h3 class="text-center mt-3">Danh Sách  Chi Tiết Sản Phẩm</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>STT</td>
            <td>Tên Sản Phẩm</td>
            <td>Tên Màu</td>
            <td>Tên Size</td>

            <td>Giá Bán</td>
            <td>Số Lượng Tồn</td>
            <td>Trạng Thái</td>
            <td> Chức Năng</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listCTSP}" var="ctsp" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${ctsp.sanPham.tenSanPham}</td>
                <td>${ctsp.mauSac.tenMauSac}</td>
                <td>${ctsp.size.tenSize}</td>
                <td>${ctsp.giaBan}</td>
                <td>${ctsp.soLuongTon}</td>
                <td>${ctsp.trangThai}</td>
                <td>
                    <a href="/ctsp/detail?id=${ctsp.id}" class="btn btn-warning">Chi Tiết</a>
                    <a href="/ctsp/delete?id=${ctsp.id}" class="btn btn-dark">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
