package com.example.asm_datdtph40745.utils;

import com.example.asm_datdtph40745.entities.*;

import com.example.asm_datdtph40745.entities.ChiTietSanPham;
import com.example.asm_datdtph40745.entities.HoaDonChiTiet;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=ASM_JAVA4_Datdtph40745;Encrypt=True;TrustServerCertificate=True");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "123456");
        properties.put(Environment.SHOW_SQL, "true");
        conf.setProperties(properties);
        conf.addAnnotatedClass(DanhMuc.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(SanPham.class);
       conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(Size.class);
        conf.addAnnotatedClass(ChiTietSanPham.class);
        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);


        ///

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);
    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
        getFACTORY();
    }
}
