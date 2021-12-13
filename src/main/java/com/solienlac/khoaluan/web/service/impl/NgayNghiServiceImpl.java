package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.NgayNghiDto;
import com.solienlac.khoaluan.web.domain.NgayNghi;
import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.domain.SinhVien_LopHocPhan;
import com.solienlac.khoaluan.web.repository.NgayNghiRepository;
import com.solienlac.khoaluan.web.repository.SinhVienLopHocPhanRepository;
import com.solienlac.khoaluan.web.service.NgayNghiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NgayNghiServiceImpl implements NgayNghiService {
    private final NgayNghiRepository ngayNghiRepository;
    private final SinhVienLopHocPhanRepository sinhVienLopHocPhanRepository;

    @Override
    public List<NgayNghiDto> listNgayNghiDtoOfSinhVienLhp(Integer idSinhVienLhp) {
        return sinhVienLopHocPhanRepository.findById(idSinhVienLhp)
                .orElseThrow(() -> new IllegalArgumentException("id not found!"))
                .getNgayNghis().stream().map(NgayNghiDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseEntity<Void> xoaNgayNghi(Integer idNgayNghi) {
        NgayNghi ngayNghi = ngayNghiRepository.findById(idNgayNghi).orElseThrow(() -> new IllegalArgumentException("id not found"));
        SinhVien_LopHocPhan svlhpCheck= ngayNghi.getSinhVien_lopHocPhan();
        Integer soNgayNghiPhep = svlhpCheck.getNgayNghis()
                .stream().filter(ngayNghiCheck ->ngayNghiCheck.isCoPhep()).collect(Collectors.toList()).size();

        Integer soNgayNghiKhongPhep = svlhpCheck.getNgayNghis()
                .stream().filter(ngayNghiCheck ->!ngayNghiCheck.isCoPhep()).collect(Collectors.toList()).size();
        if (((soNgayNghiPhep/2)+soNgayNghiKhongPhep)<3){
            svlhpCheck.choHocLai();
        }else {
            svlhpCheck.dinhChiHoc();
        }
        ngayNghiRepository.deleteById(idNgayNghi);
        return ResponseEntity.ok().build();
    }
}
