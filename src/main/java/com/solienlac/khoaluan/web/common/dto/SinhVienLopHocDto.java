package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.domain.common.TrangThaiSinhVien;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SinhVienLopHocDto {
    private Integer id;
    private String hoTen;
    private String maSV;
    private boolean isGioiTinh;
    private String email;
    private String soDT;
    private TrangThaiSinhVien trangThaiSinhVien;
    private String soDTPhuHuynh;
    private boolean isCanhBao;
    private double diemTBTL;




    public SinhVienLopHocDto(SinhVien sinhVien) {
        this.id = sinhVien.getId();
        this.hoTen = sinhVien.getHoTen();
        this.isGioiTinh = sinhVien.isGioiTinh();
        this.email = sinhVien.getEmail();
        this.trangThaiSinhVien = sinhVien.getTrangThai();
        this.soDT = sinhVien.getSoDienThoai();
        this.isCanhBao= sinhVien.getCanhBaoList().size()>0?true:false;
        this.maSV = sinhVien.getMaSinhVien();
        this.diemTBTL = sinhVien.getBangDiemTongKet()!=null?sinhVien.getBangDiemTongKet().getTrungBinhTichLuy():0;
//        this.soDTPhuHuynh = sinhVien.getPhuHuynh().getHoTen();

    }
}
