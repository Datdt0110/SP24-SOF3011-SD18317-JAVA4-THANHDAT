package com.example.asm_datdtph40745.controllers;

import com.example.asm_datdtph40745.entities.*;
import com.example.asm_datdtph40745.repositories.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@WebServlet(name = "BanHangServlet", value = {
        "/ban-hang/trang-chu",
        "/ban-hang/hoa-don",
        "/hoa-don/chon",
        "/ctsp/chon-mua",
        "/hdct/xoa",
        "/khach-hang/search"
})
public class BanHangServlet extends HttpServlet {

    ArrayList<ChiTietSanPham> listCTSP;
    CTSP_Repository CTSP_Repository = new CTSP_Repository();

    ArrayList<HoaDonChiTiet> listHDCT;
    HDCT_Repository HDCT_Repository = new HDCT_Repository();

    ArrayList<HoaDon> listHD;
    HoaDonRepository hoaDonRepository = new HoaDonRepository();
    HoaDonRepository hoaDonRepository1 =  new HoaDonRepository();

    ArrayList<KhachHang> listKH;
    KhachHang1Repository khachHangRepository = new KhachHang1Repository();
    ArrayList<HoaDon> listHoaDon = new ArrayList<>();
    HoaDon hoaDonDetail;
    KhachHang khachHangDetail;
    Integer idHoaDon;
    double tongTien;

    ArrayList<HoaDon> listHDTK;

    public BanHangServlet() {
        listCTSP = new ArrayList<>();
        listHDCT = new ArrayList<>();
        listHD = new ArrayList<>();
        listHoaDon = new ArrayList<>();
        listKH = new ArrayList<>();
        listHDTK = new ArrayList<>();
        hoaDonDetail = new HoaDon();
        idHoaDon = 1;
        tongTien = 0;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/ban-hang/trang-chu")) {
            this.hienThi(request, response);
        } else if (uri.equals("/hoa-don/chon")) {
            this.chonHoaDon(request, response);
        } else if (uri.contains("/ctsp/chon-mua")) {
            this.chonMuaCTSP(request, response);
        } else if (uri.contains("/hdct/xoa")) {
            this.xoaHDCT(request, response);
        }else if (uri.equals("/khach-hang/search")) {
            this.find(request,response);
        }

    }

    private void xoaHDCT(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer id = Integer.parseInt(request.getParameter("idHDCT"));

        HoaDonChiTiet hoaDonCTDetail = HDCT_Repository.detail(id);
        System.out.println("hdctDeleted" + hoaDonCTDetail);
        int soLuongDaXoa = hoaDonCTDetail.getSoLuongMua();
        System.out.println(soLuongDaXoa + "Lay ra so luong xoa");

        int idCTSP = hoaDonCTDetail.getChiTietSanPham().getId();

        System.out.println(soLuongDaXoa + "So luong da xoa ");
        ChiTietSanPham spct = CTSP_Repository.detail(idCTSP);

        int soLuongMoi = soLuongDaXoa;

        spct.setSoLuongTon(soLuongMoi);

        HDCT_Repository.delete(hoaDonCTDetail);

        CTSP_Repository.updateSoLuong(spct);

        listHDCT = HDCT_Repository.getListIdHoaDon(idHoaDon);
        tongTien = 0;
        for (HoaDonChiTiet hoaDonCT : listHDCT) {
            tongTien += hoaDonCT.getTongTien();
        }
        response.sendRedirect("/ban-hang/trang-chu");
    }

    private void chonMuaCTSP(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Integer idCTSP = Integer.parseInt(request.getParameter("idCTSP"));
        System.out.println("ID: duoc chon " + idCTSP);
        ChiTietSanPham chiTietSanPhamDetail = CTSP_Repository.detail(idCTSP);
        boolean daCoTrongHoaDon = false;
        for (HoaDonChiTiet hoaDonCT : listHDCT) {
            System.out.println("ID: danh sach id" + hoaDonCT.getChiTietSanPham().getId());

            if (Objects.equals(hoaDonCT.getChiTietSanPham().getId(), idCTSP)) {
                System.out.println("ID: " + idCTSP);
                System.out.println("ID thay: " + hoaDonCT.getChiTietSanPham().getId());
                int newSoLuong = hoaDonCT.getSoLuongMua() + 1;
                hoaDonCT.setSoLuongMua(newSoLuong);
                hoaDonCT.setTongTien(newSoLuong * hoaDonCT.getGiaBan());

                System.out.println("So luong sau khi duoc cong don: " + newSoLuong);
                System.out.println("Tong tien sau khi duoc cong don: " + newSoLuong * hoaDonCT.getGiaBan());

                chiTietSanPhamDetail.setSoLuongTon(chiTietSanPhamDetail.getSoLuongTon() - 1);
                CTSP_Repository.update(chiTietSanPhamDetail, idCTSP);

                if (chiTietSanPhamDetail.getSoLuongTon() == 0) {
                    chiTietSanPhamDetail.setTrangThai("Hết hàng");
                    CTSP_Repository.update(chiTietSanPhamDetail, idCTSP);
                }

                HDCT_Repository.updateSoLuong(hoaDonCT);
                daCoTrongHoaDon = true;
            }
        }

        if (!daCoTrongHoaDon) {
            HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
            HoaDon hoaDon = new HoaDon();
            hoaDon.setId(idHoaDon);

            hoaDonChiTiet.setHoaDon(hoaDon);
            hoaDonChiTiet.setChiTietSanPham(chiTietSanPhamDetail);
            hoaDonChiTiet.setGiaBan(chiTietSanPhamDetail.getGiaBan());
            hoaDonChiTiet.setSoLuongMua(1);
            hoaDonChiTiet.setNgaySua(new Date());
            hoaDonChiTiet.setNgayTao(new Date());
            hoaDonChiTiet.setTrangThai("Chưa thanh toán");
            hoaDonChiTiet.setTongTien(chiTietSanPhamDetail.getGiaBan() * 1);

            HDCT_Repository.add(hoaDonChiTiet);

            chiTietSanPhamDetail.setSoLuongTon(chiTietSanPhamDetail.getSoLuongTon() - 1);
            CTSP_Repository.update(chiTietSanPhamDetail, idCTSP);

        }

        listHDCT = HDCT_Repository.getListIdHoaDon(idHoaDon);
        tongTien = 0;
        for (HoaDonChiTiet hoaDonCT : listHDCT) {
            tongTien += hoaDonCT.getTongTien();
        }
        System.out.println(tongTien);
        response.sendRedirect("/ban-hang/trang-chu");

    }

    private void find(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String   sdt = request.getParameter("sdt");
        if(sdt.isEmpty()|| sdt==null) {
            sdt="0987654321";
        }
       khachHangDetail= khachHangRepository.getFind(sdt);
        response.sendRedirect("/ban-hang/trang-chu");
    }



    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sdtKH = request.getParameter("sdtKH");
        System.out.println(sdtKH);
        if (sdtKH == null || sdtKH.isEmpty()) {
            String trangThai = "Chưa thanh toán";
            listHD = hoaDonRepository.getListTT(trangThai);
            request.setAttribute("listHD", listHD);
        } else {
            listHDTK = hoaDonRepository.getListTheoSDT(sdtKH);
            request.setAttribute("listHD", listHDTK);
        }

        listCTSP = CTSP_Repository.getList();
        request.setAttribute("listCTSP", listCTSP);

        listHDCT = HDCT_Repository.getListIdHoaDon(idHoaDon);
        request.setAttribute("listHDCT", listHDCT);

//        // Tính tổng tiền
//        double tongTien = 0;
//        for (HoaDonChiTiet hdct : listHDCT) {
//            tongTien += hdct.getSoLuongMua() * hdct.getGiaBan();
//        }
   request.setAttribute("tongTien", tongTien);

        listKH = khachHangRepository.getAll();
        request.setAttribute("listKH", listKH);

        request.setAttribute("hoaDonDetail", hoaDonDetail);
        if (request.getParameter("idHD") != null) {
            request.setAttribute("hoaDonDetail", hoaDonRepository.getHoaDonById(Integer.parseInt(request.getParameter("idHD"))));
        }
        request.getRequestDispatcher("/views/ban_hang/BanHang.jsp").forward(request, response);
    }


    private void chonHoaDon(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idHD = Integer.parseInt(request.getParameter("idHoaDon"));
        idHoaDon = idHD;

        hoaDonDetail = hoaDonRepository.detail(idHD);
        listHDCT = HDCT_Repository.getListIdHoaDon(idHD);

        tongTien = 0;
        for (HoaDonChiTiet hoaDonCT : listHDCT) {
            tongTien += hoaDonCT.getTongTien();
        }
        response.sendRedirect("/ban-hang/trang-chu");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/ban-hang/hoa-don")) {
            double tongTienRequest = Double.parseDouble(request.getParameter("tongTien"));
            if (tongTienRequest == 0) {
                this.taoHoaDon(request, response);
            } else {
                Integer idHoaDon = Integer.parseInt(request.getParameter("idHoaDon"));
                HoaDon hoaDonDetail = hoaDonRepository.detail(idHoaDon);
                hoaDonDetail.setNgaySua(new Date());
                hoaDonDetail.setTrangThai("Đã thanh toán");
                hoaDonRepository.add(hoaDonDetail);
                response.sendRedirect("/ban-hang/trang-chu");
            }



        }
    }


    private void taoHoaDon(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        Integer idKH = Integer.parseInt(request.getParameter("khachHang"));
//        String sdt = request.getParameter("soDienThoai");
//        String diaChi = request.getParameter("diaChi");
//
//        KhachHang kh = new KhachHang();
//        kh.setId(idKH);
//
//        HoaDon hoaDon = new HoaDon();
//
//        hoaDon.setNgayTao(new Date());
//        hoaDon.setNgaySua(new Date());
//        hoaDon.setSoDienThoai(sdt);
//        hoaDon.setKhachHang(kh);
//        hoaDon.setDiaChi(diaChi);
//
//        hoaDon.setTrangThai("Chưa thanh toán");
//        hoaDonRepository.add(hoaDon);
//        response.sendRedirect("/ban-hang/trang-chu");
//    }
        KhachHang khachHang = khachHangRepository.getByID(4);
        if (khachHang != null) {
            HoaDon hoaDon = new HoaDon();
            hoaDon.setKhachHang(khachHang);
            hoaDon.setTrangThai("Chưa Thanh toán");
            hoaDon.setSoDienThoai(null);
            hoaDon.setDiaChi(null);
            hoaDon.setNgayTao(new Date());
            hoaDon.setNgaySua(null);
            hoaDonRepository.add(hoaDon);
        }
        response.sendRedirect("/ban-hang/trang-chu");
    }
}
