package com.example.asm_datdtph40745.controllers;

import com.example.asm_datdtph40745.entities.KhachHang;
import com.example.asm_datdtph40745.repositories.KhachHang1Repository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "KhachHangServlet", value = {
        "/khach-hang/hien-thi",
        "/khach-hang/add",
        "/khach-hang/delete",
        "/khach-hang/detail",
        "/khach-hang/update",

})
public class KhachHangServlet extends HttpServlet {
    KhachHang1Repository khachHangRepository = new KhachHang1Repository();
    ArrayList<KhachHang> listKhachHang = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/khach-hang/hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.equals("/khach-hang/delete")) {
            this.delete(request, response);
        } else if (uri.equals("/khach-hang/detail")) {
            this.detail(request, response);
        }

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("s", khachHangRepository.getByID(Integer.parseInt(request.getParameter("id"))));
        listKhachHang = khachHangRepository.getAll();
        request.setAttribute("listKhachHang", listKhachHang);
        request.getRequestDispatcher("/views/khach_hang/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        khachHangRepository.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/khach-hang/hien-thi");
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<KhachHang> list = khachHangRepository.getAll();
        request.setAttribute("listKhachHang", list);
        request.getRequestDispatcher("/views/khach_hang/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/khach-hang/add")) {
            this.add(request, response);
        } else if (uri.equals("/khach-hang/update")) {
            this.update(request, response);
        }

    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        Integer id = Integer.parseInt(idStr);
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String sdt = request.getParameter("sdt");
        String trangThai = request.getParameter("trangThai");
        // Lấy ngày hiện tại
        Date ngayTao = new Date();
        Date ngaySua = new Date();

        KhachHang khachHang = new KhachHang(id, ten, diaChi, sdt, trangThai, ngayTao, ngaySua);

        // Gọi DAO để thêm mới KhachHang vào cơ sở dữ liệu

        khachHangRepository.update(khachHang);

        // Chuyển hướng về trang danh sách KhachHang sau khi thêm mới thành công
        response.sendRedirect("/khach-hang/hien-thi");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ten = request.getParameter("ten");
        String diaChi = request.getParameter("diaChi");
        String sdt = request.getParameter("sdt");
        String trangThai = request.getParameter("trangThai");

        // Lấy ngày hiện tại
        Date ngayTao = new Date();
        Date ngaySua = new Date();

        // Tạo đối tượng KhachHang mới
        KhachHang khachHang = new KhachHang(null, ten, diaChi, sdt, trangThai, ngayTao, ngaySua);

        // Gọi DAO để thêm mới KhachHang vào cơ sở dữ liệu

        khachHangRepository.add(khachHang);

        // Chuyển hướng về trang danh sách KhachHang sau khi thêm mới thành công
        response.sendRedirect("/khach-hang/hien-thi");
    }

}
