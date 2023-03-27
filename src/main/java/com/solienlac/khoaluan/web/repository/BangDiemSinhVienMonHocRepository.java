package com.solienlac.khoaluan.web.repository;

import com.solienlac.khoaluan.web.domain.BangDiemTongKet;
import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BangDiemSinhVienMonHocRepository extends JpaRepository<BangDiem_SinhVien_MonHoc, Integer> {
    BangDiem_SinhVien_MonHoc findByBangDiemTongKet(BangDiemTongKet bangDiemTongKet);
}
