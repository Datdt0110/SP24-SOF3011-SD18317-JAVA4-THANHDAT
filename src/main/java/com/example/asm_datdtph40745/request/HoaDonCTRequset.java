package com.example.asm_datdtph40745.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonCTRequset {
       private  int hoaDonChiTiet;
       private String tenSPChiTiet;
       private int soLuong;
       private Double tongTien;
       private String trangThaiHoaDon;
       private Double giaBan;

}
