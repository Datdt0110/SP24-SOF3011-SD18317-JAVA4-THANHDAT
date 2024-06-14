package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.MauSac;
import com.example.asm_datdtph40745.entities.Size;
import com.example.asm_datdtph40745.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class SizeRepository {
    public ArrayList<Size> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<Size> list = (ArrayList<Size>) session.createQuery("from Size").list();
        session.close();
        return list;
    }
    public boolean add(Size size){
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction  = session.beginTransaction();
        try {
            session.saveOrUpdate(size);
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
            Size size = session.get(Size.class,id);
            if(size!=null){
                session.delete(size);
                transaction.commit();
                return true;
            }
        }
        return false;
    }

    public boolean update( Size size) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(size);
            transaction.commit();
            return true;
        }
    }


    public Size getByID(Integer id){
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            Size size = session.get(Size.class,id);
            if(size!=null){
                return size;
            }else{
                return null;
            }
        }
    }
}
