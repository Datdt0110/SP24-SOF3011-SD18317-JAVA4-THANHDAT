package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.KhachHang;
import com.example.asm_datdtph40745.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class KhachHang1Repository {

    public ArrayList<KhachHang> getAll(){
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<KhachHang> list = (ArrayList<KhachHang>) session.createQuery("from KhachHang").list();
        session.close();
        return  list;
    }

    public boolean add(KhachHang khachHang){
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction  = session.beginTransaction();
        try {
            session.saveOrUpdate(khachHang);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }
        return false;
    }

    public boolean delete(Integer id){
        try(  Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction  = session.beginTransaction();
            KhachHang khachHang = session.get(KhachHang.class,id);
            if(khachHang!=null){
                session.delete(khachHang);
                transaction.commit();
                return true;
            }
        }
        return false;
    }

    public boolean update( KhachHang kh) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(kh);
            transaction.commit();
            return true;
        }
    }

    public KhachHang getFind(String sdt){
        Session session = HibernateUtil.getFACTORY().openSession();
        KhachHang khachHang = (KhachHang) session.createQuery("from KhachHang where sdt like: id_1").setParameter("id_1",sdt).getSingleResult();
        session.close();
        return khachHang;
    }


    public KhachHang getByID(Integer id){
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            KhachHang khachHang = session.get(KhachHang.class,id);
            if(khachHang!=null){
                return khachHang;
            }else{
                return null;
            }
        }
    }
}
