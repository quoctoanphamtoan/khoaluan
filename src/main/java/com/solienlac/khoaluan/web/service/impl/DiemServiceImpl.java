package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.DiemSinhVienDto;
import com.solienlac.khoaluan.web.common.dto.GetDiemSinhVien;
import com.solienlac.khoaluan.web.common.dto.param.PutBangDiemSinhVien;
import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.repository.BangDiemSinhVienMonHocRepository;
import com.solienlac.khoaluan.web.repository.DiemCustomRepository;
import com.solienlac.khoaluan.web.repository.SinhVienRepository;
import com.solienlac.khoaluan.web.service.DiemService;
import com.solienlac.khoaluan.web.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DiemServiceImpl implements DiemService {
    private final SinhVienService sinhVienService;
    private final SinhVienRepository sinhVienRepository;
    private final DiemCustomRepository diemCustomRepository;
    private final BangDiemSinhVienMonHocRepository bangDiemSinhVienMonHocRepository;
    @Override
    public GetDiemSinhVien getDiemSinhViens(Integer idSinhVien) {

        return new GetDiemSinhVien(diemCustomRepository.listDiemSinhVien(idSinhVien)
                .stream().map(DiemSinhVienDto::new).collect(Collectors.toList())
                ,sinhVienRepository.findById(idSinhVien).orElseThrow(() -> new IllegalArgumentException("id not found")).getBangDiemTongKet().getTrungBinhTichLuy());


    }

    @Override
    @Transactional
    public Integer chinhSuaBangDiemSinhVienMonHoc(Integer idBangDiem, PutBangDiemSinhVien putBangDiemSinhVien) {
        BangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc = bangDiemSinhVienMonHocRepository.findById(idBangDiem)
                .orElseThrow(() -> new IllegalArgumentException("id not foud!"));
         bangDiem_sinhVien_monHoc.suaDiem(putBangDiemSinhVien);

        SinhVien sinhVien = sinhVienRepository.findById(bangDiem_sinhVien_monHoc.getBangDiemTongKet().getSinhVien().getId())
                .orElseThrow(() -> new IllegalArgumentException("id not found!"));
        sinhVien.getBangDiemTongKet().setBangDiemTongKet(sinhVien.getBangDiemTongKet().getBangDiem_sinhVien_monHocs());

        return bangDiem_sinhVien_monHoc.getId();
    }
}
