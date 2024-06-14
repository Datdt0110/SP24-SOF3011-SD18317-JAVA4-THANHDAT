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
@Table(name = "mau_sac")
public class MauSac {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma_mau")
    private String maMauSac;
    @Column(name = "ten_mau")
    private String tenMauSac;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Override
    public String toString() {
        return "MauSac{" +
                "id=" + id +
                ", maMauSac='" + maMauSac + '\'' +
                ", tenMauSac='" + tenMauSac + '\'' +
                ", trangThai='" + trangThai + '\'' +
                ", ngaySua=" + ngaySua +
                ", ngayTao=" + ngayTao +
                '}';
    }



}
