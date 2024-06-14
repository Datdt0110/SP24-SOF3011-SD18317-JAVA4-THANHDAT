<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark justify-content-center">
    <div class="container">
        <a class="navbar-brand" href="trangchu.html">
            <img src="<c:url value='/img/ki.png'/>" class="img-fluid" style="max-height: 40px;" alt="Logo">
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">


                <li class="nav-item">
                    <a class="nav-link active" href=" <c:url value="/ban-hang/trang-chu" />">QUẢN LÝ BÁN HÀNG</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" href=" <c:url value="/danhmucservlet" />">QUẢN LÝ DANH MỤC</a>
                </li>

                <li class="nav-item">
                    <a class="nav-link active" href=" <c:url value="/san-pham/hien-thi" />">QUẢN LÝ SẢN PHẨM</a>
                </li>



                <li class="nav-item">
                    <a class="nav-link active" href=" <c:url value="/ctsp/hien-thi" />">QUẢN LÝ CHI TIẾT SẢN PHẨM</a>
                </li>


                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/hoa-don/hien-thi" />">QUẢN LÝ HOÁ ĐƠN</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/khach-hang/hien-thi" />"> QUẢN LÝ KHÁCH HÀNG</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" href="<c:url value="/mausacservlet" />"><button><i class="fa-solid fa-palette"></i></button></a>
                </li>
            </ul>

            <!-- Khu vực thông tin user -->
            <!-- Trạng thái chưa đăng nhập -->

        </div>
    </div>
</nav>
