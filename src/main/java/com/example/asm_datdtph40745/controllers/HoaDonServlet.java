package com.example.asm_datdtph40745.controllers;

import com.example.asm_datdtph40745.entities.HoaDon;
import com.example.asm_datdtph40745.repositories.HoaDonChiTietRepository;
import com.example.asm_datdtph40745.repositories.HoaDon1Repository;
import com.example.asm_datdtph40745.request.HoaDonCTRequset;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "HoaDonServlet", value = {
        "/hoa-don/hien-thi",
        "/hoa-don/add",
        "/hoa-don/detailHDCT",
        "/hoa-don/hdct"
//        "/hoa-don/update",
//        "/hoa-don/detail",
//        "/hoa-don/delete",


})
public class HoaDonServlet extends HttpServlet {
    HoaDon1Repository hoaDonRepository = new HoaDon1Repository();
    ArrayList<HoaDon> list = new ArrayList<>();

    HoaDonChiTietRepository hoaDonChiTietRepository = new HoaDonChiTietRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.equals("/hoa-don/hien-thi")) {
            this.hienThi(request, response);
        } else if (uri.equals("/hoa-don/detailHDCT")) {
            this.detailHDCT(request, response);
        } else if (uri.equals("/hoa-don/hdct")) {
             this.getDetailHDCT(request , response);
        }
    }

    private void getDetailHDCT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =    request.getParameter("idHD");
        List<HoaDonCTRequset> listHdct = hoaDonChiTietRepository.getHdctByIdHoaDon(Integer.parseInt(id));
        request.setAttribute("listHDCT1" , listHdct);
        request.getRequestDispatcher("/ban-hang/hien-thi").forward(request, response);

    }

    private void detailHDCT(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("id")!=null){
            request.setAttribute("listHDCT", hoaDonRepository.listHDCTByIDHoaDon(Integer.parseInt(request.getParameter("id"))));
            hienThi(request,response);
        }


    }

    private void hienThi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<HoaDon> list = hoaDonRepository.getAll();
        request.setAttribute("listHoaDon", list);
        request.getRequestDispatcher("/views/hoa_don/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
