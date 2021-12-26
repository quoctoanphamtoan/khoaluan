package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.NgayNghi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NgayNghiDto {
    private Integer id;
    private Long ngayNghi;
    private boolean phep;
    public NgayNghiDto(NgayNghi ngayNghi){
        this.id = ngayNghi.getId();
        this.ngayNghi = ngayNghi.getNgayNghi().getTime();
        this.phep = ngayNghi.isCoPhep();
    }
}
