package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.LopHocPhan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LopHocPhanDto {
    private Integer id;
    private String tenLopHocPhan;
    private Integer idMonHoc;
    private String tenMonHoc;
    private Integer siSo;
    public LopHocPhanDto(LopHocPhan lopHocPhan){
        this.id = lopHocPhan.getId();
        this.tenLopHocPhan = lopHocPhan.getTenLopHP();
        this.idMonHoc = lopHocPhan.getMonHoc().getId();
        this.tenMonHoc = lopHocPhan.getMonHoc().getTenMonHoc();
        this.siSo = lopHocPhan.getSiSo();
    }
}
