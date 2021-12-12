package com.solienlac.khoaluan.web.repository;

import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.domain.SinhVien_LopHocPhan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SinhVienCustomRepository {
    Page<SinhVien> listSinhVienLopHoc(Pageable pageable,Integer idLopHoc);
    Page<SinhVien_LopHocPhan> listSinhVienLopHocPhan(Pageable pageable, Integer idLopHocPhan);
}
