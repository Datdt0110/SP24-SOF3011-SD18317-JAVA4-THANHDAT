package com.example.asm_datdtph40745.repositories;

import com.example.asm_datdtph40745.entities.DanhMuc;
import com.example.asm_datdtph40745.entities.MauSac;
import com.example.asm_datdtph40745.utils.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class MauSacRepository {
    public ArrayList<MauSac> getAll() {
        Session session = HibernateUtil.getFACTORY().openSession();
        ArrayList<MauSac> list = (ArrayList<MauSac>) session.createQuery("from MauSac").list();
        session.close();
        return list;
    }

    public boolean add(MauSac mauSac){
        Session session = HibernateUtil.getFACTORY().openSession();
        Transaction transaction  = session.beginTransaction();
        try {
            session.saveOrUpdate(mauSac);
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
          MauSac mauSac = session.get(MauSac.class,id);
          if(mauSac!=null){
              session.delete(mauSac);
              transaction.commit();
              return true;
          }
      }
      return false;
    }

    public boolean update( MauSac ms) {
        try (Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(ms);
            transaction.commit();
            return true;
        }
    }


    public MauSac getByID(Integer id){
        try(Session session = HibernateUtil.getFACTORY().openSession()) {
            Transaction transaction = session.beginTransaction();
            MauSac mauSac = session.get(MauSac.class,id);
            if(mauSac!=null){
                return mauSac;
            }else{
                return null;
            }
        }
    }

    public static void main(String[] args) {
        ArrayList<MauSac> list = new MauSacRepository().getAll();
        for (MauSac mauSac : list) {
            System.out.println(mauSac.toString());
        }
    }


}
