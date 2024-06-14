package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.ChiTietSanPham;
import com.example.asm_datdtph40745.entities.MauSac;
import com.example.asm_datdtph40745.entities.SanPham;
import com.example.asm_datdtph40745.entities.Size;
import com.example.asm_datdtph40745.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CTSP_Repository {
    public ArrayList<ChiTietSanPham> getList() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<ChiTietSanPham> list = (ArrayList<ChiTietSanPham>) session.createQuery("from ChiTietSanPham ").list();
        session.close();
        return list;
    }
    public ArrayList<ChiTietSanPham> getListTrangThai(String trangThai) {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<ChiTietSanPham> list = (ArrayList<ChiTietSanPham>) session.createQuery("from ChiTietSanPham" +
                " where trangThai =:trangThai_1").setParameter("trangThai_1", trangThai).list();
        session.close();
        return list;
    }
    public ArrayList<SanPham> getTenSanPham() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<SanPham> list = (ArrayList<SanPham>) session.createQuery(
                "select tenSanPham from SanPham ").list();
        session.close();
        return list;
    }

    public ArrayList<MauSac> getTenMauSac() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<MauSac> list = (ArrayList<MauSac>) session.createQuery(
                "select tenMau from MauSac ").list();
        session.close();
        return list;
    }

    public ArrayList<Size> getTenSize() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<Size> list = (ArrayList<Size>) session.createQuery(
                "select tenSize from Size ").list();
        session.close();
        return list;
    }

    public void add(ChiTietSanPham sanPhamCT) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(sanPhamCT);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        session.close();
    }

    public void update(ChiTietSanPham sanPhamCT, Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            ChiTietSanPham sanPhamCTToUpdate = session.get(ChiTietSanPham.class, id);
            if (sanPhamCTToUpdate != null) {
                sanPhamCTToUpdate.setSanPham(sanPhamCT.getSanPham());
                sanPhamCTToUpdate.setMauSac(sanPhamCT.getMauSac());
                sanPhamCTToUpdate.setSize(sanPhamCT.getSize());
                sanPhamCTToUpdate.setGiaBan(sanPhamCT.getGiaBan());
                sanPhamCTToUpdate.setSoLuongTon(sanPhamCT.getSoLuongTon());
                sanPhamCTToUpdate.setTrangThai(sanPhamCT.getTrangThai());
                sanPhamCTToUpdate.setNgaySua(sanPhamCT.getNgaySua());
                session.saveOrUpdate(sanPhamCTToUpdate);
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

    // Update lại số lượng sau khi xóa
    public void updateSoLuong(ChiTietSanPham spct) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            // Lấy sản phẩm chi tiết từ cơ sở dữ liệu
            ChiTietSanPham spctToUpdate = session.get(ChiTietSanPham.class, spct.getId());
            if (spctToUpdate != null) {
                int soLuongHienTai = spctToUpdate.getSoLuongTon();
                int soLuongMoi = soLuongHienTai + spct.getSoLuongTon(); // Tính toán số lượng mới sau khi xóa
                spctToUpdate.setSoLuongTon(soLuongMoi);
                session.saveOrUpdate(spctToUpdate);
                transaction.commit();
            } else {
                throw new RuntimeException("Không tìm thấy sản phẩm chi tiết để cập nhật");
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
            ChiTietSanPham sanPhamCTToDelete = session.get(ChiTietSanPham.class, id);
            if (sanPhamCTToDelete != null) {
                session.delete(sanPhamCTToDelete);
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

    public ChiTietSanPham detail(Integer id) {
        Session session = HibernateUtil.getFACTORY().openSession();
        ChiTietSanPham sanPhamCT = null;
        try {
            sanPhamCT = session.get(ChiTietSanPham.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return sanPhamCT;
    }
}
