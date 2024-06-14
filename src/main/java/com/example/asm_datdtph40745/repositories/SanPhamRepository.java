package com.example.asm_datdtph40745.repositories;


import com.example.asm_datdtph40745.entities.SanPham;
import com.example.asm_datdtph40745.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SanPhamRepository {

    public ArrayList<SanPham> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<SanPham> list = (ArrayList<SanPham>) session.createQuery("from SanPham order by ngayTao asc").list();
        session.close();
        return list;
    }


    public boolean add(SanPham sanPham) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(sanPham);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean delete(Integer id) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            SanPham sanPham = session.get(SanPham.class, id);
            if (sanPham != null) {
                session.delete(sanPham);
                transaction.commit();
                return true;
            }
        }
        return false;
    }

    public boolean update( SanPham sp) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(sp);
            transaction.commit();
            return true;
        }
    }

    public SanPham getByID(Integer id) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            SanPham sanPham = session.get(SanPham.class, id);
            if (sanPham != null) {
                return sanPham;
            } else {
                return null;
            }
        }
    }


}
