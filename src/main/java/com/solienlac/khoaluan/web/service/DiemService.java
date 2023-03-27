package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.GetDiemSinhVien;
import com.solienlac.khoaluan.web.common.dto.param.PutBangDiemSinhVien;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface DiemService {
    GetDiemSinhVien getDiemSinhViens(Integer idSinhVien);

    Integer chinhSuaBangDiemSinhVienMonHoc(Integer idBangDiem, PutBangDiemSinhVien putBangDiemSinhVien);
}
