package com.example.asm_datdtph40745.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hdct")
public class HoaDonChiTiet {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = " id_hoa_don", nullable = true)
    private HoaDon hoaDon;

    @ManyToOne
    @JoinColumn(name = "id_ctsp", nullable = true)
    private ChiTietSanPham  chiTietSanPham;

    @Column(name = "so_luong_mua ")
    private Integer soLuongMua;
    @Column(name = "gia_ban  ")
    private Double giaBan;
    @Column(name = "tong_tien  ")
    private Double tongTien;
    @Column(name = "trang_thai  ")
    private String trangThai;
    @Column(name = "ngay_tao ")
    private Date ngayTao;
    @Column(name = "ngay_sua ")
    private Date ngaySua;


}
