package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.GetThongBaoLopOfGiangVien;
import com.solienlac.khoaluan.web.common.dto.GetThongBaoSinhVien;
import com.solienlac.khoaluan.web.common.dto.param.PostThongBaoLop;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface ThongBaoService {
    GetThongBaoSinhVien getThongBaoSinhVien(Pageable pageable,Integer idSinhVien);
    GetThongBaoLopOfGiangVien getThongBaoLopOfGiangVien(Pageable pageable,Integer idLop,Integer idGiangVien);



    GetThongBaoLopOfGiangVien getThongBaoLopHocPhanOfGiangVien(Pageable pageable,Integer idLopHocPhan,Integer idGiangVien);



    Integer themThongBaoLop(Integer idGiangVien,Integer idLop,PostThongBaoLop postThongBaoLop);
    Integer themThongBaoLopHocPhan(Integer idGiangVien,Integer idLopHocPhan,PostThongBaoLop postThongBaoLop);



    Integer chinhSuaThongBaoLop(Integer idThongBao, PostThongBaoLop postThongBaoLop);



    Integer chinhSuaHienThiThongBaoLop(Integer idThongBao);
}
