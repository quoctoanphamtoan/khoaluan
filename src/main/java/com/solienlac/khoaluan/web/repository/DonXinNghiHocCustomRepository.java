package com.solienlac.khoaluan.web.repository;

import com.solienlac.khoaluan.web.domain.DonXinNghiHoc;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonXinNghiHocCustomRepository {
    Page<DonXinNghiHoc> listDonXinNghiHoc(Pageable pageable, Integer idGiangVien,Integer idLopHocPhan);
    List<DonXinNghiHoc> listDonXinNghiHocOfSinhVien(Integer idSinhVien);
}
