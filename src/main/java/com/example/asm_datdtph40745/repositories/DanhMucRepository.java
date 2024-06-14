package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.DanhMuc;
import com.example.asm_datdtph40745.entities.SanPham;
import com.example.asm_datdtph40745.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class DanhMucRepository {
    public ArrayList<DanhMuc> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<DanhMuc> list = (ArrayList<DanhMuc>) session.createQuery("from DanhMuc").list();
        session.close();
        return list;

    }

    public boolean add(DanhMuc danhMuc) {
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.saveOrUpdate(danhMuc);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }
    public boolean delete(Integer id){
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            DanhMuc danhMuc = session.get(DanhMuc.class,id);
            if(danhMuc!=null){
                session.delete(danhMuc);
                transaction.commit();
                return true;
            }
        }
        return false;
    }
    public boolean update( DanhMuc dm) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(dm);
            transaction.commit();
            return true;
        }
    }

    public DanhMuc getByID(Integer id){
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            DanhMuc danhMuc = session.get(DanhMuc.class,id);
            if(danhMuc!=null){
                return danhMuc;
            }else{
                return null;
            }
        }
    }







}
