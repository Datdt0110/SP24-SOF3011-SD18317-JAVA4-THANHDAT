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
@Table(name = "size")
public class Size {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "ma_size")
    private String maSize;
    @Column(name = "ten_size")
    private String tenSize;
    @Column(name = "trang_thai")
    private String trangThai;
    @Column(name = "ngay_sua")
    private Date ngaySua;
    @Column(name = "ngay_tao")
    private Date ngayTao;
}
