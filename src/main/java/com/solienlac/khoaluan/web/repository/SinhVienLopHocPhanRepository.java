package com.solienlac.khoaluan.web.repository;

import com.solienlac.khoaluan.web.domain.LopHocPhan;
import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.domain.SinhVien_LopHocPhan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinhVienLopHocPhanRepository extends JpaRepository<SinhVien_LopHocPhan,Integer> {
    SinhVien_LopHocPhan findSinhVien_LopHocPhanBySinhVienAndLopHocPhan(SinhVien sinhVien, LopHocPhan lopHocPhan);
}
