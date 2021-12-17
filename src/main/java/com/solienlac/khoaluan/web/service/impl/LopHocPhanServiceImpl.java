package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.GetLopHocPhan;
import com.solienlac.khoaluan.web.common.dto.LopHocPhanDto;
import com.solienlac.khoaluan.web.common.dto.LopHocPhanOfSinhVienDto;
import com.solienlac.khoaluan.web.common.page.PaginationMeta;
import com.solienlac.khoaluan.web.domain.LopHocPhan;
import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.repository.LopHocPhanCustomRepository;
import com.solienlac.khoaluan.web.repository.SinhVienLopHocPhanRepository;
import com.solienlac.khoaluan.web.repository.SinhVienRepository;
import com.solienlac.khoaluan.web.service.LopHocPhanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class LopHocPhanServiceImpl implements LopHocPhanService {
    private final LopHocPhanCustomRepository lopHocPhanCustomRepository;
    private final SinhVienLopHocPhanRepository sinhVienLopHocPhanRepository;
    private final SinhVienRepository sinhVienRepository;
    @Override
    public GetLopHocPhan lopHocPhans(Integer idGiangVien, Pageable pageable) {
        Page<LopHocPhan> page = lopHocPhanCustomRepository.getLopHocPhans(idGiangVien,pageable);
        List<LopHocPhanDto> list = page.getContent()
                .stream().map(LopHocPhanDto::new).collect(Collectors.toList());
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        return new GetLopHocPhan(list,paginationMeta);
    }

    @Override
    public List<LopHocPhanOfSinhVienDto> getLopHocPhanOfSinhVien( Integer idSinhVien) {
        SinhVien sinhVien = sinhVienRepository.findById(idSinhVien)
                .orElseThrow(() -> new IllegalArgumentException("id not found"));
        return sinhVienLopHocPhanRepository.findSinhVien_LopHocPhanBySinhVien(sinhVien)
                .stream().map(LopHocPhanOfSinhVienDto::new).collect(Collectors.toList());
    }
}
