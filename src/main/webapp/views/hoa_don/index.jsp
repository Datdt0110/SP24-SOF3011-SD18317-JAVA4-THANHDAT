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

    <h3 class="mt-3 text-center">Danh Sách Hóa Đơn</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>STT</td>
            <td>Tên Khách Hàng</td>
            <td>Địa Chỉ</td>
            <td>Số Điện Thoại</td>
            <td>Trạng Thái</td>
            <td>Ngày Tạo</td>
            <td>Ngày Sửa</td>
            <td>Chức Năng</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listHoaDon}" var="hd" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${hd.khachHang.ten}</td>
                <td>${hd.diaChi}</td>
                <td>${hd.soDienThoai}</td>
                <td>${hd.trangThai}</td>
                <td>${hd.ngayTao}</td>
                <td>${hd.ngaySua}</td>
                <td>
                    <a href="/hoa-don/detailHDCT?id=${hd.id}" class="btn btn-warning">Xem Chi Tiết</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    <h3 class="mt-5 text-center">Danh Sách Hóa Đơn Chi Tiết</h3>
    <table class="table table-bordered">
        <thead>
        <tr>
            <td>STT</td>
            <td>ID Hóa Đơn</td>
            <td>ID CTSP</td>
            <td>Số Lượng Mua</td>
            <td>Giá Bán</td>
            <td>Tổng Tiền</td>

            <td>Ngày Tạo</td>
            <td>Ngày Sửa</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listHDCT}" var="hd" varStatus="i">
            <tr>
                <td>${i.index + 1}</td>
                <td>${hd.hoaDon.id}</td>
                <td>${hd.chiTietSanPham.id}</td>
                <td>${hd.soLuongMua}</td>
                <td>${hd.giaBan}</td>
                <td>${hd.tongTien}</td>

                <td>${hd.ngayTao}</td>
                <td>${hd.ngaySua}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
