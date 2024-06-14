package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.HoaDonChiTiet;
import com.example.asm_datdtph40745.utils.HibernateUtil;
import com.example.asm_datdtph40745.entities.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HoaDonRepository {
    public ArrayList<HoaDon> getList() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<HoaDon> list = (ArrayList<HoaDon>) session.createQuery("from HoaDon order by ngayTao asc ").list();
        for(HoaDon hd : list){
            hd.setTongTien(tongTienByIDHoaDon(hd.getId()));
        }
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
            e.printStackTrace();
            return 0.0;
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
        for(HoaDon hd : list){
            hd.setTongTien(tongTienByIDHoaDon(hd.getId()));
        }
        session.close();
        return list;
    }

    public void add(HoaDon hoaDon) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hoaDon);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public void update(HoaDon hoaDon, Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            HoaDon hoaDonToUpdate = session.get(HoaDon.class, id);
            if (hoaDonToUpdate != null) {
                hoaDonToUpdate.setSoDienThoai(hoaDon.getSoDienThoai());
                hoaDonToUpdate.setTrangThai(hoaDon.getTrangThai());
                hoaDonToUpdate.setDiaChi(hoaDon.getDiaChi());
                hoaDonToUpdate.setNgaySua(hoaDon.getNgaySua());
                hoaDonToUpdate.setKhachHang(hoaDon.getKhachHang());
                session.saveOrUpdate(hoaDonToUpdate);
                transaction.commit();
            } else {
                throw new RuntimeException("Không tìm thấy danh mục để cập nhật với id: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }

    }

    public int delete(Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            HoaDon hoaDonToDelete = session.get(HoaDon.class, id);
            if (hoaDonToDelete != null) {
                session.delete(hoaDonToDelete);
                transaction.commit();
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return -1;
        } finally {
            session.close();
        }
    }

    public HoaDon detail(Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        HoaDon hoaDon = null;
        try {
            hoaDon = session.get(HoaDon.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return hoaDon;
    }
}
