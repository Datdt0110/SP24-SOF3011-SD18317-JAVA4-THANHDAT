package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.ChiTietSanPham;
import com.example.asm_datdtph40745.entities.HoaDon;
import com.example.asm_datdtph40745.entities.HoaDonChiTiet;
import com.example.asm_datdtph40745.request.HoaDonCTRequset;
import com.example.asm_datdtph40745.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HoaDonChiTietRepository {

    public ArrayList<HoaDonChiTiet> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) session.createQuery("from HoaDonChiTiet order by ngayTao asc").list();
        session.close();
        return list;
    }
    public HoaDonChiTiet detail(Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        HoaDonChiTiet hdct = (HoaDonChiTiet) session.createQuery("from  HoaDonChiTiet where id =:id_1").setParameter("id_1", id).getSingleResult();
        session.close();
        return hdct;

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


    public ArrayList<HoaDonChiTiet> getListIdHoaDon(Integer idHDCT) {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<HoaDonChiTiet> list = (ArrayList<HoaDonChiTiet>) session.createQuery("from HoaDonChiTiet where hoaDon.id = :idHDCT")
                .setParameter("idHDCT", idHDCT)
                .list();
        session.close();
        return list;
    }

    public boolean add(HoaDonChiTiet hoaDonChiTiet) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(hoaDonChiTiet);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HoaDonCTRequset> getHdctByIdHoaDon (int idHoaDon){
         try(Session session = HibernateUtil.getFACTORY().openSession()) {
             String hql = "SELECT NEW com.example.asm_datdtph40745.request.HoaDonCTRequset(ct.id, sp.tenSanPham ,ct.soLuongMua, ct.tongTien, h.trangThai,ct.giaBan) " +
                     "FROM HoaDonChiTiet ct " +
                     "JOIN ct.hoaDon h " +
                     "JOIN ct.chiTietSanPham cts " +
                     "JOIN cts.sanPham sp " +
                     "WHERE h.id = :idHoaDon";
             Query<HoaDonCTRequset> query = session.createQuery(hql, HoaDonCTRequset.class);
             query.setParameter("idHoaDon" , idHoaDon);
             return  query.getResultList();
         }
    }

}
