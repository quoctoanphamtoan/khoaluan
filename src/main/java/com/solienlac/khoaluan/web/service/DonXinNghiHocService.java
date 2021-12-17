package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.DonXinNghiHocDto;
import com.solienlac.khoaluan.web.common.dto.GetDonXinNghiHoc;
import com.solienlac.khoaluan.web.common.dto.param.PostDiemDanh;
import com.solienlac.khoaluan.web.common.dto.param.PostDonXinNghiHoc;
import com.solienlac.khoaluan.web.common.dto.param.XinNghiHocParam;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


public interface DonXinNghiHocService {
    Integer xinNghiHoc(Integer idSinhVien,Integer idLopHocPhan,PostDonXinNghiHoc postDonXinNghiHoc);
    Integer duyetDonXinNghiHoc(Integer idGiangVien,Integer idDonXinNghiHoc);
    GetDonXinNghiHoc getDonXinNghiHoc(Pageable pageable,Integer idGiangVien,Integer idLopHocPhan);
    Integer diemDanh(Integer idSinhVien,Integer idLopHocPhan,PostDiemDanh postDiemDanh);
    Integer xinNghiHoc(XinNghiHocParam xinNghiHocParam);
    List<DonXinNghiHocDto> getDonXinNghiHocOfSinhVien(Integer idSinhVien);
}
