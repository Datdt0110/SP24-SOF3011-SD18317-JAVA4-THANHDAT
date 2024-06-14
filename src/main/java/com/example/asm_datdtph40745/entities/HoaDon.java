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
@Table(name = "hoa_don ")
public class HoaDon {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
        private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_khach_hang", nullable = true)
        private KhachHang khachHang;
    @Column(name= "trang_thai ")
        private String trangThai;
    @Column(name = "ngay_tao")
    private Date ngayTao;
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Transient
    private Double tongTien;
    @Column(name= "dia_chi ")
        private String diaChi;


    @Column(name= "so_dien_thoai ")
        private String soDienThoai;

}
