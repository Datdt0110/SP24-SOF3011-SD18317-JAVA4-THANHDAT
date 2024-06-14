package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.ChiTietSanPham;
import com.example.asm_datdtph40745.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ChiTietSanPhamRepository {
    public ArrayList<ChiTietSanPham> getAll() {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            return (ArrayList<ChiTietSanPham>) session.createQuery("from ChiTietSanPham order by ngayTao asc").list();
        }
    }

    public boolean add(ChiTietSanPham chiTietSanPham) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(chiTietSanPham);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Integer id) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            ChiTietSanPham chiTietSanPham = session.get(ChiTietSanPham.class, id);
            if (chiTietSanPham != null) {
                session.delete(chiTietSanPham);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

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

    public boolean update(ChiTietSanPham ctsp) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(ctsp);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void update2(ChiTietSanPham sanPhamCT, Integer id) {
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



    public ChiTietSanPham getByID(Integer id) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            return session.get(ChiTietSanPham.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
