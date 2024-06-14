<%--
  Created by IntelliJ IDEA.
  User: MSI 15
  Date: 03/27/2024
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bán Hàng</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<%@include file="/views/master-layout/header.jsp"%>
<div class="container mt-3">
<div class="row">
    <div class="col-7">
        <h2>Danh sách hoá đơn</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Tên khách hàng</td>
                <td>Ngày tạo</td>
                <td>Tổng tiền</td>
                <td>Trạng Thái</td>
                <td>Chức năng</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="hoaDon" items="${listHD}" varStatus="i">
                <tr>
                    <td>${i.index +1}</td>
                    <td>${hoaDon.id}</td>
                    <td>${hoaDon.khachHang.ten}</td>
                    <td>${hoaDon.ngayTao}</td>
                    <td>${hoaDon.tongTien}</td>
                    <td>${hoaDon.trangThai}</td>
                    <td>
                        <a href="/hoa-don/chon?idHoaDon=${hoaDon.id}" class="btn btn-dark">Chọn</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <h2>Danh sách hoá đơn chi tiết</h2>
        <table class="table">
            <thead>
            <tr>
                <td>STT</td>
                <td>ID</td>
                <td>Ten san pham</td>
                <td>So luong</td>
                <td>Gia ban</td>
                <td>Tong tien</td>
                <td>Chuc nang</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="HDCT" items="${listHDCT}" varStatus="i">
                <tr>
                    <td>${i.index +1}</td>
                    <td>${HDCT.id}</td>
                    <td>${HDCT.chiTietSanPham.sanPham.tenSanPham}</td>
                    <td>${HDCT.soLuongMua}</td>
                    <td>${HDCT.giaBan}</td>
                    <td>${HDCT.tongTien}</td>
                    <td>
                        <a href="/hdct/xoa?idHDCT=${HDCT.id}" class="btn btn-dark">Xóa</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="col-5">
        <form action="/ban-hang/trang-chu" method="get">

            <div class="mb-3">
                <label class="form-lable">Tìm kiếm hóa đơn</label>
                <input type="text" class="form-control" name="sdtKH">
            </div>
            <div class="mb-3">
                <button class="btn btn-warning" type="submit">Search</button>
            </div>
        </form>
        <form action="/ban-hang/hoa-don" method="post">
            <h2>Tạo hoá đơn</h2>
            <div class="row">
                <div>

                </div>
                <div class="mb-3">
                    <label class="form-label">Tên khách hàng</label>
                    <select class="form-select" name="khachHang">
                        <c:forEach items="${listKH}" var="s">
                            <option value="${s.id}"
                                    <c:if test="${s.id ==hoaDonDetail.khachHang.id}">selected</c:if>
                            >${s.ten}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="mb-3">
                    <label class="form-lable">ID Hóa đơn</label>
                    <input type="text" readonly class="form-control" name="idHoaDon"   value="${hoaDonDetail.id}">
                </div>
                <div class="mb-3">
                    <label class="form-lable">Tổng tiền</label>
                    <input type="text" class="form-control" readonly name="tongTien" value="${tongTien}">
                </div>
                <div>
                    <div>
                        <button class="btn btn-dark" type="submit" id="createInvoiceBtn">Tạo hoá đơn</button>
                        <button class="btn btn-dark"  type="submit" id="confirmPaymentBtn">Thanh toán</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<div>
    <h2>Danh sách chi tiết sản phẩm</h2>
    <table class="table">
        <thead>
        <tr>
            <td>STT</td>
            <td>ID CTSP</td>
            <td>Tên sản phẩm</td>
            <td>Màu sắc</td>
            <td>Size</td>
            <td>Giá bán</td>
            <td>Số lượng tồn</td>
            <td>Chức năng</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="CTSP" items="${listCTSP}" varStatus="i">
            <tr>
                <td>${i.index +1}</td>
                <td>${CTSP.id}</td>
                <td>${CTSP.sanPham.tenSanPham}</td>
                <td>${CTSP.mauSac.tenMauSac}</td>
                <td>${CTSP.size.tenSize}</td>
                <td>${CTSP.giaBan}</td>
                <td>${CTSP.soLuongTon}</td>
                <td>

                    <a href="/ctsp/chon-mua?idCTSP=${CTSP.id}" class="btn btn-dark">Chọn mua</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</div>

<script>
    document.getElementById("confirmPaymentBtn").addEventListener("click", function() {
        if (confirm("Bạn có chắc chắn muốn thanh toán?")) {
            document.getElementById("paymentForm").submit();
        }
    });
</script>
<%@include file="/views/master-layout/footer.jsp"%>
</body>
</html>
