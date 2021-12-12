package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class GetDiemSinhVien {
    private List<DiemSinhVienDto> diemSinhVienDtos;
    private float diemTongKet;
    public GetDiemSinhVien(BangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc){
        this.diemTongKet = bangDiem_sinhVien_monHoc.getBangDiemTongKet().getTrungBinhTichLuy();
    }


}
