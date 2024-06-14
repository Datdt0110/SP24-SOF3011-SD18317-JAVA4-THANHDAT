package com.example.asm_datdtph40745.controllers;

import com.example.asm_datdtph40745.entities.MauSac;
import com.example.asm_datdtph40745.repositories.MauSacRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "MauSacServlet", value = {
        "/mausacservlet",
        "/mau-sac/add",
        "/mau-sac/delete",
        "/mau-sac/detail",
        "/mau-sac/update",

})
public class MauSacServlet extends HttpServlet {
    MauSacRepository mauSacRepository = new MauSacRepository();
    ArrayList<MauSac> listMauSac = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/mausacservlet")) {
            this.hienThi(request, response);
        } else if (uri.equals("/mau-sac/delete")) {
            this.delete(request, response);
        } else if (uri.equals("/mau-sac/detail")) {
            this.detail(request, response);
        }

    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("s", mauSacRepository.getByID(Integer.parseInt(request.getParameter("id"))));
        listMauSac = mauSacRepository.getAll();
        request.setAttribute("listMauSac", listMauSac);
        request.getRequestDispatcher("/views/mau_sac/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        mauSacRepository.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/mausacservlet");
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MauSac> list = mauSacRepository.getAll();
        request.setAttribute("listMauSac", list);
        request.getRequestDispatcher("/views/mau_sac/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/mau-sac/add")) {
            this.add(request, response);
        } else if (uri.equals("/mau-sac/update")) {
            this.update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        String maMauSac = request.getParameter("maMauSac");
        String tenMauSac = request.getParameter("tenMauSac");
        String trangThai = request.getParameter("trangThai");
        Integer id = Integer.parseInt(idStr);
        // Lấy ngày hiện tại
        Date ngayTao = new Date();
        Date ngaySua = new Date();

        // Tạo đối tượng MauSac mới
        MauSac mauSac = new MauSac(id, maMauSac, tenMauSac, trangThai, ngaySua, ngayTao);
        mauSacRepository.update(mauSac);
        response.sendRedirect("/mausacservlet");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Lấy thông tin từ request
        String maMauSac = request.getParameter("maMauSac");
        String tenMauSac = request.getParameter("tenMauSac");
        String trangThai = request.getParameter("trangThai");

        // Lấy ngày hiện tại
        Date ngayTao = new Date();
        Date ngaySua = new Date();

        // Tạo đối tượng MauSac mới
        MauSac mauSac = new MauSac(null, maMauSac, tenMauSac, trangThai, ngaySua, ngayTao);
        mauSacRepository.add(mauSac);
        response.sendRedirect("/mausacservlet");


    }
}
