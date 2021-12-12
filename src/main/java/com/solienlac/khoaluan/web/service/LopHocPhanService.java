package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.GetLopHocPhan;
import org.springframework.data.domain.Pageable;

public interface LopHocPhanService {
    GetLopHocPhan lopHocPhans(Integer idGiangVien, Pageable pageable);
}
