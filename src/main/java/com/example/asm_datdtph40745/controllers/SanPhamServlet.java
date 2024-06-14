package com.example.asm_datdtph40745.controllers;


import com.example.asm_datdtph40745.entities.DanhMuc;
import com.example.asm_datdtph40745.entities.SanPham;
import com.example.asm_datdtph40745.repositories.DanhMucRepository;
import com.example.asm_datdtph40745.repositories.SanPhamRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@WebServlet(name = "SanPhamServlet", value = {
        "/san-pham/hien-thi",
        "/san-pham/add",
        "/san-pham/delete",
        "/san-pham/detail",
        "/san-pham/update",
})
public class SanPhamServlet extends HttpServlet {
    SanPhamRepository repository = new SanPhamRepository();
    DanhMucRepository danhMucRepository = new DanhMucRepository();
    ArrayList<DanhMuc> listDanhMuc = new ArrayList<>();

    ArrayList<SanPham> listSanPham = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/san-pham/hien-thi")) {
            this.hienthi(request, response);
        } else if (uri.equals("/san-pham/delete")) {
            this.delete(request, response);
        } else if (uri.equals("/san-pham/detail")) {
            System.out.println("doGet of sanpham-detail");
            this.detail(request, response);
        }else if (uri.equals("/san-pham/update")) {
            System.out.println("doget /san-pham/updat");
            this.update(request, response);
        }
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("s", repository.getByID(Integer.parseInt(request.getParameter("id"))));
        listDanhMuc = danhMucRepository.getAll();
        request.setAttribute("listDanhMuc", listDanhMuc);
        request.getRequestDispatcher("/views/san_pham/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        repository.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/san-pham/hien-thi");
    }


    @SneakyThrows
    private void hienthi(HttpServletRequest request, HttpServletResponse response) {
        listSanPham = repository.getAll();
        listDanhMuc = danhMucRepository.getAll();
        request.setAttribute("listDanhMuc", listDanhMuc);
        request.setAttribute("list", listSanPham);
        request.getRequestDispatcher("/views/san_pham/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("/san-pham/add")) {
            this.add(request, response);
        } else if (uri.equals("/san-pham/update")) {
            System.out.println("doPost /san-pham/updat");
            this.update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SanPham sp = new SanPham();
        sp.setId(Integer.parseInt(request.getParameter("id")));
        String ma = request.getParameter("maSanPham");
        String ten = request.getParameter("tenSanPham");
        String trangThai = request.getParameter("trangThai");
        Integer idDanhMuc = Integer.parseInt(request.getParameter("danhMuc"));
        sp.setMaSanPham(ma);
        sp.setTenSanPham(ten);
        sp.setTrangThai(trangThai);
        sp.setNgayTao(new Date());
        sp.setNgaySua(new Date());
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(idDanhMuc);
        sp.setDanhMuc(danhMuc);
        repository.update(sp);
        response.sendRedirect("/san-pham/hien-thi");
    }


    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // lấy thông tin trên form
        String ma = request.getParameter("maSanPham");
        String ten = request.getParameter("tenSanPham");
        String trangThai = request.getParameter("trangThai");
        Integer idDanhMuc = Integer.parseInt(request.getParameter("danhMuc"));
        // tao ra ddoiso tuong
        SanPham sp = new SanPham();
        sp.setMaSanPham(ma);
        sp.setTenSanPham(ten);
        sp.setTrangThai(trangThai);
        sp.setNgayTao(new Date());
        sp.setNgaySua(new Date());
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(idDanhMuc);
        sp.setDanhMuc(danhMuc);
        repository.add(sp);
        response.sendRedirect("/san-pham/hien-thi");

    }

}
