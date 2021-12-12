package com.solienlac.khoaluan.web.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HocKiDto {
    private String nam;
    private String ki;
    private List<DiemSinhVienDto> diemSinhVienDtos;
    private float tongKetKi;

}
