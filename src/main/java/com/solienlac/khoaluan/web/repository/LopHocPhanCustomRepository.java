package com.solienlac.khoaluan.web.repository;

import com.solienlac.khoaluan.web.domain.LopHocPhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LopHocPhanCustomRepository {
    Page<LopHocPhan> getLopHocPhans(Integer idGiangVien, Pageable pageable);
}
