package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.*;
import com.solienlac.khoaluan.web.common.dto.param.PutSinhVienParam;
import com.solienlac.khoaluan.web.common.page.PaginationMeta;
import com.solienlac.khoaluan.web.domain.Lop;
import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.domain.SinhVien_LopHocPhan;
import com.solienlac.khoaluan.web.repository.SinhVienCustomRepository;
import com.solienlac.khoaluan.web.repository.SinhVienRepository;
import com.solienlac.khoaluan.web.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Log4j2
@Validated
public class SinhVienServiceImpl implements SinhVienService {
    private final SinhVienRepository sinhVienRepository;
    private final SinhVienCustomRepository sinhVienCustomRepository;
    @Override
    public ThongTinSinhVienDto xemThongTin(Integer id) {
        SinhVien sinhVien = sinhVienRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));

        return new ThongTinSinhVienDto(sinhVien);
    }

    @Override
    @Transactional
    public Integer chinhSuaSinhVien(Integer id,PutSinhVienParam putSinhVienParam) {
        SinhVien sinhVien = sinhVienRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("id not found"));
        sinhVien.chinhSua(putSinhVienParam);
        return id;
    }

    @Override
    public GetSinhVienLopHoc getSinhVienLopHoc(Pageable pageable,Integer idLopHoc) {
        Page<SinhVien> page = sinhVienCustomRepository.listSinhVienLopHoc(pageable,idLopHoc);
        List<SinhVienLopHocDto> list =page.getContent().stream().map(sinhVien -> new SinhVienLopHocDto(sinhVien)).collect(Collectors.toList());
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        return new GetSinhVienLopHoc(list,paginationMeta);
    }

    @Override
    public GetSinhVienLopHocPhan getSinhVienLopHocPhan(Pageable pageable,Integer idLopHocPhan) {
        Page<SinhVien_LopHocPhan> page = sinhVienCustomRepository.listSinhVienLopHocPhan(pageable,idLopHocPhan);
        log.info(page.getContent().size());
        List<SinhVienLopHocPhanDto> list = page.getContent()
                .stream().map(SinhVienLopHocPhanDto::new)
                .collect(Collectors.toList());
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        return new GetSinhVienLopHocPhan(list,paginationMeta);
    }
}
