package com.example.asm_datdtph40745.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "danh_muc")
public class DanhMuc {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id

    @Column(name = "id")
    private Integer id;
    @Column(name = "ma_danh_muc")
    private String maDanhMuc;
    @Column(name = "ten_danh_muc")
    private String tenDanhMuc;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;

    @Override
    public String toString() {
        return "DanhMuc{" +
                "id=" + id +
                ", maDanhMuc='" + maDanhMuc + '\'' +
                ", tenDanhMuc='" + tenDanhMuc + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", ngayTao=" + ngayTao +
                ", ngaySua=" + ngaySua +
                '}';
    }

}
