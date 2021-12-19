package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class DiemSinhVienDto {
    private Integer id;
    private String tenMonHoc;
    private double tk1;
    private double tk2;
    private double tk3;
    private double gk;
    private double ck;
    private double tbc;
    private boolean trangThai;

    public DiemSinhVienDto(BangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc){
        log.info("info id sinh vien->{}",bangDiem_sinhVien_monHoc.getBangDiemTongKet().getSinhVien().getId());
        log.info("info id bang diem sinh vien mon hoc ->{}",bangDiem_sinhVien_monHoc.getId());
        this.id = bangDiem_sinhVien_monHoc.getId();
        this.tenMonHoc = bangDiem_sinhVien_monHoc.getMonHoc().getTenMonHoc();
        this.tk1= bangDiem_sinhVien_monHoc.getDiemTK1();
        this.tk2 = bangDiem_sinhVien_monHoc.getDiemTK2();
        this.tk3 = bangDiem_sinhVien_monHoc.getDiemTK3();
        this.gk = bangDiem_sinhVien_monHoc.getDiemGK();
        this.ck = bangDiem_sinhVien_monHoc.getDiemCK();
        this.tbc = bangDiem_sinhVien_monHoc.getDiemTBC();
        this.trangThai = bangDiem_sinhVien_monHoc
                .getBangDiemTongKet().getSinhVien()
                .getSinhVien_lopHocPhans().stream()
                .filter(sinhVien_lopHocPhan ->
                        sinhVien_lopHocPhan.getSinhVien().getId()==bangDiem_sinhVien_monHoc.getBangDiemTongKet().getSinhVien().getId())
                .findAny().orElseThrow(() -> new IllegalArgumentException("id not found")).isTrangThai();
    }

}
