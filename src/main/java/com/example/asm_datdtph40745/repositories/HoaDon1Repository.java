package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.HoaDon;
import com.example.asm_datdtph40745.entities.HoaDonChiTiet;
import com.example.asm_datdtph40745.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HoaDon1Repository {
    public ArrayList<HoaDon> getAll() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            ArrayList<HoaDon> list = (ArrayList<HoaDon>) session.createQuery("from HoaDon order by ngayTao asc").list();
            for (HoaDon hoaDon : list) {
                double tongTien = tongTienByIDHoaDon(hoaDon.getId());
                hoaDon.setTongTien(tongTien);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Trả về danh sách rỗng nếu có lỗi
        }
    }

    public ArrayList<HoaDon> getListTheoSDT(String sdtKH) {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<HoaDon> list = (ArrayList<HoaDon>) session.createQuery("from HoaDon " +
                        "where soDienThoai like :sdt_1 or diaChi like :diaChi_1 or trangThai like:trangThai_1")
                .setParameter("sdt_1", "%" + sdtKH + "%")
                .setParameter("diaChi_1", "%" + sdtKH + "%")
                .setParameter("trangThai_1", "%" + sdtKH + "%")
                .list();
        session.close();
        return list;
    }

    public ArrayList<HoaDon> getListTT(String trangThai) {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<HoaDon> list = (ArrayList<HoaDon>) session.createQuery("from HoaDon" +
                " where trangThai =:trangThai_1").setParameter("trangThai_1", trangThai).list();
        session.close();
        return list;
    }

    public HoaDon getHoaDonById(Integer id){
        try(Session ses = HibernateUtil.getFACTORY().openSession()){
            HoaDon hd = ses.get(HoaDon.class, id);
            hd.setTongTien(tongTienByIDHoaDon(id));
            return hd;
        }
    }
    public boolean add(HoaDon hoaDon) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hoaDon);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }


    public List<HoaDonChiTiet> listHDCTByIDHoaDon(Integer idHoaDon) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query query = session.createQuery("FROM HoaDonChiTiet WHERE hoaDon.id = :idHoaDon");
            query.setParameter("idHoaDon", idHoaDon);
            return query.getResultList();
        }
    }

    public Double tongTienByIDHoaDon(Integer idHoaDon) {
        double tongTien = 0.0; // Khai báo và khởi tạo biến tongTien
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Query<HoaDonChiTiet> query = session.createQuery(
                    "FROM HoaDonChiTiet h WHERE h.hoaDon.id = :idHoaDon",
                    HoaDonChiTiet.class
            );
            query.setParameter("idHoaDon", idHoaDon);

            List<HoaDonChiTiet> hoaDonChiTiets = query.getResultList();

            for (HoaDonChiTiet hoaDonChiTiet : hoaDonChiTiets) {
                tongTien += hoaDonChiTiet.getTongTien();
            }

            return tongTien;
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu cần
            e.printStackTrace();
            return 0.0; // hoặc throw lại ngoại lệ tùy theo trường hợp cụ thể
        }
    }




}


