package com.solienlac.khoaluan.web.repository;

import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;

import java.util.List;

public interface DiemCustomRepository {
    List<BangDiem_SinhVien_MonHoc> listDiemSinhVien(Integer idSinhVien);
}
