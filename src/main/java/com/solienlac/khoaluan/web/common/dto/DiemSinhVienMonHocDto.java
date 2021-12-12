package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiemSinhVienMonHocDto {
    private Integer id;
    private double tk1;
    private double tk2;
    private double tk3;
    private double gk;
    private double ck;
    private double tbc;

    public DiemSinhVienMonHocDto(BangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc) {
        this.id = bangDiem_sinhVien_monHoc.getId();
        this.tk1 = bangDiem_sinhVien_monHoc.getDiemTK1();
        this.tk2 = bangDiem_sinhVien_monHoc.getDiemTK2();
        this.tk3 = bangDiem_sinhVien_monHoc.getDiemTK3();
        this.gk = bangDiem_sinhVien_monHoc.getDiemGK();
        this.ck = bangDiem_sinhVien_monHoc.getDiemCK();
        this.tbc = bangDiem_sinhVien_monHoc.getDiemTBC();
    }
}
