package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiemSinhVienDto {
    private Integer id;
    private String tenMonHoc;
    private float tk1;
    private float tk2;
    private float tk3;
    private float gk;
    private float ck;
    private float tbc;
//    private boolean trangThai;

    public DiemSinhVienDto(BangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc){
        this.id = bangDiem_sinhVien_monHoc.getId();
        this.tenMonHoc = bangDiem_sinhVien_monHoc.getMonHoc().getTenMonHoc();
        this.tk1= bangDiem_sinhVien_monHoc.getDiemTK1();
        this.tk2 = bangDiem_sinhVien_monHoc.getDiemTK2();
        this.tk3 = bangDiem_sinhVien_monHoc.getDiemTK3();
        this.gk = bangDiem_sinhVien_monHoc.getDiemGK();
        this.ck = bangDiem_sinhVien_monHoc.getDiemCK();
        this.tbc = bangDiem_sinhVien_monHoc.getDiemTBC();
//        this.trangThai = bangDiem_sinhVien_monHoc.get
    }

}
