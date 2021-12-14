package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.GetLopHocPhan;
import com.solienlac.khoaluan.web.common.dto.LopHocPhanOfSinhVienDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LopHocPhanService {
    GetLopHocPhan lopHocPhans(Integer idGiangVien, Pageable pageable);
    List<LopHocPhanOfSinhVienDto> getLopHocPhanOfSinhVien(Integer idSinhVien);
}
