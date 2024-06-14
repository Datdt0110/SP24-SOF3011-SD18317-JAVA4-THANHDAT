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
<%@include file="/views/master-layout/header.jsp" %>
<div class="container">
    <h3 class="mt-3 text-center"> Chi Tiết Sản Phẩm</h3>

    <form action="/ctsp/update" method="POST">
        <input type="hidden" name="id" value="${s.id}">
        <div class="col-md">
            <label class="form-label">Tên Sản Phẩm</label>
            <select class="form-select" name="sanPham">
                <c:forEach var="dm" items="${listSanPham}">
                    <option value="${dm.id}" ${s.sanPham.id == dm.id? "selected":""} >${dm.tenSanPham}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md">
            <label class="form-label">Tên Màu Sắc</label>
            <select class="form-select" name="mauSac">
                <c:forEach var="dm" items="${listMauSac}">
                    <option value="${dm.id}" ${s.mauSac.id == dm.id? "selected":""}>${dm.tenMauSac}</option>
                </c:forEach>
            </select>
        </div>
        <div class="col-md">
            <label class="form-label">Tên Size</label>
            <select class="form-select" name="size">
                <c:forEach var="dm" items="${listSize}">
                    <option value="${dm.id}" ${s.size.id == dm.id? "selected":""}>${dm.tenSize}</option>
                </c:forEach>
            </select>
        </div>

        <div class="mb-3">
            <label class="form-label">Giá Bán:</label>
            <input type="text" class="form-control" id="giaBan" name="giaBan" value="${s.giaBan}">
        </div>
        <div class="mb-3">
            <label class="form-label">Số Lượng Tồn:</label>
            <input type="text" class="form-control" id="so_luong_ton" name="soLuongTon" value="${s.soLuongTon}">
        </div>
        <div class="col-md">
            <label class="form-label">Trạng Thái</label>
            <select class="form-select" name="trangThai" value="${s.trangThai}">
                <option value="Đã Thanh toán" ${s.trangThai== "Đã Thanh toán"?"selected":""}>Đã Thanh toán</option>
                <option value="Chưa Thanh toán" ${s.trangThai== "Chưa Thanh toán"?"selected":""}>Chưa Thanh toán
                </option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Update</button>
    </form>


</div>
<%@include file="/views/master-layout/footer.jsp" %>
</body>
</html>
