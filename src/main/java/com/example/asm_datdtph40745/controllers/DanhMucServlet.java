package com.example.asm_datdtph40745.controllers;

import com.example.asm_datdtph40745.entities.DanhMuc;
import com.example.asm_datdtph40745.entities.SanPham;
import com.example.asm_datdtph40745.repositories.DanhMucRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "DanhMucServlet", value = {
        "/danhmucservlet",
        "/danh-muc/add",
        "/danh-muc/detail",
        "/danh-muc/delete",
        "/danh-muc/update"
})
public class DanhMucServlet extends HttpServlet {
    DanhMucRepository danhMucRepository = new DanhMucRepository();
    ArrayList<DanhMuc> listDanhMuc = new ArrayList<>();

    ArrayList<SanPham> listSanPham = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/danhmucservlet")) {
            this.hienThi(request, response);
        } else if (uri.equals("/danh-muc/delete")) {
            this.delete(request, response);
        } else if (uri.equals("/danh-muc/detail")) {
            System.out.println("doGet of sanpham-detail");
            this.detail(request, response);
        }
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        danhMucRepository.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/danhmucservlet");
    }


    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<DanhMuc> list = danhMucRepository.getAll();
        request.setAttribute("listDanhMuc", list);
        request.getRequestDispatcher("/views/danh_muc/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/danh-muc/add")) {
            this.add(request, response);
        } else if (uri.equals("/danh-muc/update")) {
            this.update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        String maDanhMuc = request.getParameter("maDanhMuc");
        String tenDanhMuc = request.getParameter("tenDanhMuc");
        String trangThai = request.getParameter("trangThai");
        Integer id = Integer.parseInt(idStr);
        Date ngaySua = new Date();
        Date ngayTao = new Date();
        DanhMuc danhMuc = new DanhMuc(id, maDanhMuc, tenDanhMuc, trangThai, ngayTao, ngaySua);
        danhMucRepository.update(danhMuc);
        response.sendRedirect("/danhmucservlet");
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("s", danhMucRepository.getByID(Integer.parseInt(request.getParameter("id"))));
        listDanhMuc = danhMucRepository.getAll();
        request.setAttribute("listDanhMuc", listDanhMuc);
        request.getRequestDispatcher("/views/danh_muc/detail.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String ma = request.getParameter("maDanhMuc");
        String ten = request.getParameter("tenDanhMuc");
        String trangThai = request.getParameter("trangThai");

        DanhMuc dm = new DanhMuc();
        dm.setMaDanhMuc(ma);
        dm.setTenDanhMuc(ten);
        dm.setTrangThai(trangThai);
        dm.setNgayTao(new Date());
        dm.setNgaySua(new Date());
        danhMucRepository.add(dm);
        response.sendRedirect("/danhmucservlet");
    }
}
