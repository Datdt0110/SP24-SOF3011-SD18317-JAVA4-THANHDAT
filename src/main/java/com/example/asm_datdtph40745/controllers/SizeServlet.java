package com.example.asm_datdtph40745.controllers;

import com.example.asm_datdtph40745.entities.MauSac;
import com.example.asm_datdtph40745.entities.Size;
import com.example.asm_datdtph40745.repositories.SizeRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SizeServlet",  value = {
        "/SizeServlet",
        "/size/add",
        "/size/detail",
        "/size/delete",
        "/size/update"
})
public class SizeServlet extends HttpServlet {
    SizeRepository sizeRepository = new SizeRepository();
    ArrayList<Size> listSize = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri  = request.getRequestURI();
        if(uri.equals("/SizeServlet")){
            this.hienThi(request,response);
        } else if (uri.equals("/size/delete")) {
            this.delete(request, response);
        } else if (uri.equals("/size/detail")) {
            this.detail(request, response);
        }


    }

    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("s", sizeRepository.getByID(Integer.parseInt(request.getParameter("id"))));
        listSize = sizeRepository.getAll();
        request.setAttribute("listSize", listSize);
        request.getRequestDispatcher("/views/size/detail.jsp").forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        sizeRepository.delete(Integer.parseInt(request.getParameter("id")));
        response.sendRedirect("/SizeServlet");
    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Size> list = sizeRepository.getAll();
        request.setAttribute("listSize",list);
        request.getRequestDispatcher("/views/size/index.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/size/add")) {
            this.add(request, response);
        } else if (uri.equals("/size/update")) {
            this.update(request, response);
        }
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idStr = request.getParameter("id");
        String maSize = request.getParameter("maSize");
        String tenSize = request.getParameter("tenSize");
        String trangThai = request.getParameter("trangThai");
        Integer id = Integer.parseInt(idStr);
        // Lấy ngày hiện tại
        Date ngayTao = new Date();
        Date ngaySua = new Date();

        // Tạo đối tượng MauSac mới
        Size size = new Size(id, maSize, tenSize, trangThai, ngaySua, ngayTao);
        sizeRepository.update(size);
        response.sendRedirect("/SizeServlet");
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String maSize = request.getParameter("maSize");
        String tenSize = request.getParameter("tenSize");
        String trangThai = request.getParameter("trangThai");

        // Lấy ngày hiện tại
        Date ngayTao = new Date();
        Date ngaySua = new Date();

        // Tạo đối tượng Size mới
        Size size = new Size(null, maSize, tenSize, trangThai, ngaySua, ngayTao);

        // Gọi DAO để thêm mới Size vào cơ sở dữ liệu

        sizeRepository.add(size);

        // Chuyển hướng về trang danh sách Size sau khi thêm mới thành công
        response.sendRedirect("/SizeServlet");
    }
}
