package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.SinhVien_LopHocPhan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LopHocPhanOfSinhVienDto {
    private Integer idSinhVienLopHocPhan;
    private String tenLopHocPhan;
    private Integer soNgayNghiPhep;
    private Integer soNgayNghiKhongPhep;
    public LopHocPhanOfSinhVienDto(SinhVien_LopHocPhan sinhVienLopHocPhan){
        this.idSinhVienLopHocPhan = sinhVienLopHocPhan.getId();
        this.soNgayNghiKhongPhep = sinhVienLopHocPhan.getNgayNghis().stream().filter(ngayNghi -> !ngayNghi.isCoPhep()).collect(Collectors.toList()).size();
        this.soNgayNghiPhep = sinhVienLopHocPhan.getNgayNghis().stream().filter(ngayNghi -> ngayNghi.isCoPhep()).collect(Collectors.toList()).size();
        this.tenLopHocPhan = sinhVienLopHocPhan.getLopHocPhan().getTenLopHP();
    }
}
