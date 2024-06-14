package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.HoaDon;
import com.example.asm_datdtph40745.entities.HoaDonChiTiet;
import com.example.asm_datdtph40745.utils.HibernateUtil;


import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;


public class HDCT_Repository {
    public ArrayList<HoaDonChiTiet> getList() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) session.createQuery("from HoaDonChiTiet ").list();
        session.close();
        return list;
    }

    public ArrayList<HoaDonChiTiet> getListIdHoaDon(Integer idHDCT) {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) session.createQuery("from HoaDonChiTiet where hoaDon.id = :idHDCT")
                .setParameter("idHDCT", idHDCT)
                .list();
        session.close();
        return list;
    }

    public void add(HoaDonChiTiet hoaDonCT) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(hoaDonCT);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public void update(HoaDonChiTiet hoaDonCT, Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            HoaDonChiTiet hoaDonCTToUpdate = session.get(HoaDonChiTiet.class, id);
            if (hoaDonCTToUpdate != null) {
                hoaDonCTToUpdate.setHoaDon(hoaDonCT.getHoaDon());
                hoaDonCTToUpdate.setChiTietSanPham(hoaDonCT.getChiTietSanPham());
                hoaDonCTToUpdate.setSoLuongMua(hoaDonCT.getSoLuongMua());
                hoaDonCTToUpdate.setGiaBan(hoaDonCT.getGiaBan());
                hoaDonCTToUpdate.setTongTien(hoaDonCT.getTongTien());
                hoaDonCTToUpdate.setTrangThai(hoaDonCT.getTrangThai());
                hoaDonCTToUpdate.setNgaySua(hoaDonCT.getNgaySua());
                session.saveOrUpdate(hoaDonCT);
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

    //Update so luong sau khi chon mua
    public void updateSoLuong(HoaDonChiTiet hoaDonCT) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Lấy hóa đơn chi tiết từ cơ sở dữ liệu
            HoaDonChiTiet hoaDonChiTietToUpdate = session.get(HoaDonChiTiet.class, hoaDonCT.getId());
            if (hoaDonChiTietToUpdate != null) {
                // Cập nhật số lượng và tổng tiền của hóa đơn chi tiết
                hoaDonChiTietToUpdate.setSoLuongMua(hoaDonCT.getSoLuongMua());
                hoaDonChiTietToUpdate.setTongTien(hoaDonCT.getTongTien());

                // Lưu hoặc cập nhật hóa đơn chi tiết
                session.saveOrUpdate(hoaDonChiTietToUpdate);
                transaction.commit();
            } else {
                throw new RuntimeException("Không tìm thấy hóa đơn chi tiết để cập nhật");
            }
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    public void delete(HoaDonChiTiet hoaDonCT) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.delete(hoaDonCT);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public HoaDonChiTiet detail(Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        HoaDonChiTiet hdct = (HoaDonChiTiet) session.createQuery("from  HoaDonChiTiet where id =:id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return hdct;

    }

//    public int delete(Integer id) {
//        Session session = DBconnect.getFACTORY().openSession();
//        Transaction transaction = session.beginTransaction();
//        try {
//            HDCT hoaDonCTToDelete = session.get(HDCT.class, id);
//            if (hoaDonCTToDelete != null) {
//                session.delete(hoaDonCTToDelete);
//                transaction.commit();
//                return 1;
//            } else {
//                return 0;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            transaction.rollback();
//            return -1;
//        } finally {
//            session.close();
//        }
//    }
//
//    public HDCT detail(Integer id) {
//        Session session = DBconnect.getFACTORY().openSession();
//        HDCT hoaDonCTdetail = null;
//        try {
//            hoaDonCTdetail = session.get(HDCT.class, id);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return hoaDonCTdetail;
//    }
}
