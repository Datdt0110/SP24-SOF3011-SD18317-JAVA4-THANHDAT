package com.example.asm_datdtph40745.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ctsp")
public class ChiTietSanPham {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_sp", nullable = true)
    private SanPham sanPham;
    @ManyToOne
    @JoinColumn(name = "id_mau_sac", nullable = true)
    private MauSac mauSac;
    @ManyToOne
    @JoinColumn(name = "id_size", nullable = true)
    private Size size;

    @Basic
    @Column(name = "gia_ban", nullable = true, precision = 2)
    private Double giaBan;
    @Basic
    @Column(name = "so_luong_ton", nullable = true)
    private Integer soLuongTon;
    @Basic
    @Column(name = "trang_thai", nullable = true, length = 50)
    private String trangThai;
    @Basic
    @Column(name = "ngay_tao", nullable = true)
    private Date ngayTao;
    @Basic
    @Column(name = "ngay_sua", nullable = true)
    private Date ngaySua;
}
