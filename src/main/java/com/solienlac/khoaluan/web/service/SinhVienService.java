package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.GetSinhVienLopHoc;
import com.solienlac.khoaluan.web.common.dto.GetSinhVienLopHocPhan;
import com.solienlac.khoaluan.web.common.dto.ThongTinSinhVienDto;
import com.solienlac.khoaluan.web.common.dto.param.PutSinhVienParam;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface SinhVienService {
    ThongTinSinhVienDto xemThongTin(Integer id);
    Integer chinhSuaSinhVien(Integer id,PutSinhVienParam putSinhVienParam);
    GetSinhVienLopHoc getSinhVienLopHoc(Pageable pageable,Integer idLopHoc);
    GetSinhVienLopHocPhan getSinhVienLopHocPhan(Pageable pageable,Integer idLopHocPhan);


}
