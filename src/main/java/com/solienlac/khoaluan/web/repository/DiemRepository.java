package com.solienlac.khoaluan.web.repository;

import com.solienlac.khoaluan.web.domain.BangDiemTongKet;
import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiemRepository extends JpaRepository<BangDiem_SinhVien_MonHoc,Integer> {
    List<BangDiem_SinhVien_MonHoc> findAllByBangDiemTongKet(BangDiemTongKet bangDiemTongKet);
}
