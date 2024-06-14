package com.example.asm_datdtph40745.controllers;

import com.example.asm_datdtph40745.entities.*;
import com.example.asm_datdtph40745.repositories.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ChiTietSanPhamServlet", value = {
        "/ctsp/hien-thi",
        "/ctsp/add",
        "/ctsp/delete",
       "/ctsp/detail",
       "/ctsp/update",

})
public class ChiTietSanPhamServlet extends HttpServlet {
    ChiTietSanPhamRepository chiTietSanPhamRepository = new ChiTietSanPhamRepository();
    ArrayList<ChiTietSanPham> list = new ArrayList<>();
    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    ArrayList<SanPham> listSanPham = new ArrayList<>();
    ArrayList<MauSac> listMauSac = new ArrayList<>();
    MauSacRepository mauSacRepository = new MauSacRepository();

    ArrayList<Size> listSize = new ArrayList<>();
    SizeRepository sizeRepository = new SizeRepository();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/ctsp/hien-thi")) {
            this.hienThi(request, response);
        }else if (uri.equals("/ctsp/delete")) {
            this.delete(request, response);
        } else if (uri.equals("/ctsp/detail")) {
            this.detail(request, response);
        }
    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("s", chiTietSanPhamRepository.getByID(Integer.parseInt(request.getParameter("id"))));
        ArrayList<ChiTietSanPham> list = chiTietSanPhamRepository.getAll();
        listSanPham = sanPhamRepository.getAll();
        listMauSac = mauSacRepository.getAll();
        listSize = sizeRepository.getAll();
        request.setAttribute("listCTSP", list);
        request.setAttribute("listSanPham", listSanPham);
        request.setAttribute("listMauSac", listMauSac);
        request.setAttribute("listSize", listSize);
        request.getRequestDispatcher("/views/chi_tiet_san_pham/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {

            chiTietSanPhamRepository.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("/ctsp/hien-thi");

    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            ArrayList<ChiTietSanPham> list = chiTietSanPhamRepository.getAll();
            listSanPham = sanPhamRepository.getAll();
            listMauSac = mauSacRepository.getAll();
            listSize = sizeRepository.getAll();
            request.setAttribute("listCTSP", list);
            request.setAttribute("listSanPham", listSanPham);
            request.setAttribute("listMauSac", listMauSac);
            request.setAttribute("listSize", listSize);
            request.getRequestDispatcher("/views/chi_tiet_san_pham/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/ctsp/add")) {
            this.add(request, response);
        } else if (uri.equals("/ctsp/update")) {
            this.update(request,response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer idSanPham = Integer.parseInt(request.getParameter("sanPham"));
            Integer idMauSac = Integer.parseInt(request.getParameter("mauSac"));
            Integer idSize = Integer.parseInt(request.getParameter("size"));
            BigDecimal giaBan = new BigDecimal(request.getParameter("giaBan"));
            Integer soLuongTon = Integer.parseInt(request.getParameter("soLuongTon"));
            String trangThai = request.getParameter("trangThai");

            SanPham sanPham = sanPhamRepository.getByID(idSanPham);
            MauSac mauSac = mauSacRepository.getByID(idMauSac);
            Size size = sizeRepository.getByID(idSize);

            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setId(Integer.parseInt(request.getParameter("id")));
            chiTietSanPham.setSanPham(sanPham);
            chiTietSanPham.setMauSac(mauSac);
            chiTietSanPham.setSize(size);
            chiTietSanPham.setGiaBan(giaBan.doubleValue());
            chiTietSanPham.setSoLuongTon(soLuongTon);
            chiTietSanPham.setTrangThai(trangThai);

            chiTietSanPhamRepository.update(chiTietSanPham);

            response.sendRedirect("/ctsp/hien-thi");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("update chi tiết sản phẩm thất bại!");
        }
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Integer idSanPham = Integer.parseInt(request.getParameter("sanPham"));
            Integer idMauSac = Integer.parseInt(request.getParameter("mauSac"));
            Integer idSize = Integer.parseInt(request.getParameter("size"));
            BigDecimal giaBan = new BigDecimal(request.getParameter("giaBan"));
            Integer soLuongTon = Integer.parseInt(request.getParameter("soLuongTon"));
            String trangThai = request.getParameter("trangThai");

            SanPham sanPham = sanPhamRepository.getByID(idSanPham);
            MauSac mauSac = mauSacRepository.getByID(idMauSac);
            Size size = sizeRepository.getByID(idSize);

            ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
            chiTietSanPham.setSanPham(sanPham);
            chiTietSanPham.setMauSac(mauSac);
            chiTietSanPham.setSize(size);
            chiTietSanPham.setGiaBan(giaBan.doubleValue());
            chiTietSanPham.setSoLuongTon(soLuongTon);
            chiTietSanPham.setTrangThai(trangThai);

            chiTietSanPhamRepository.add(chiTietSanPham);

            response.sendRedirect("/ctsp/hien-thi");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Thêm chi tiết sản phẩm thất bại!");
        }
    }

}
