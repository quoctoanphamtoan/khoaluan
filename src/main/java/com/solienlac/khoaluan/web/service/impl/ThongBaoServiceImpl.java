package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.GetThongBaoLopOfGiangVien;
import com.solienlac.khoaluan.web.common.dto.GetThongBaoSinhVien;
import com.solienlac.khoaluan.web.common.dto.ThongBaoLopOfGiangVien;
import com.solienlac.khoaluan.web.common.dto.ThongBaoSinhVienDto;
import com.solienlac.khoaluan.web.common.dto.param.PostThongBaoLop;
import com.solienlac.khoaluan.web.common.page.PaginationMeta;
import com.solienlac.khoaluan.web.domain.*;
import com.solienlac.khoaluan.web.repository.*;
import com.solienlac.khoaluan.web.service.ThongBaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ThongBaoServiceImpl implements ThongBaoService {
    private final ThongBaoCustomRepository thongBaoCustomRepository;
    private final SinhVienRepository sinhVienRepository;
    private final LopRepository lopRepository;
    private final GiangVienRepository giangVienRepository;
    private final ThongBaoRepository thongBaoRepository;
    private final ThongBaoLopRepository thongBaoLopRepository;
    private final LopHocPhanRepository lopHocPhanRepository;
    private final ThongBaoLopHocPhanRepository thongBaoLopHocPhanRepository;
    @Override
    public GetThongBaoSinhVien getThongBaoSinhVien(Pageable pageable,Integer idSinhVien) {
        SinhVien sinhVien = sinhVienRepository.findById(idSinhVien).orElseThrow(() -> new IllegalArgumentException("id not foud"));
        List<ThongBaoSinhVienDto> listCanhBao= sinhVien.getCanhBaoList()
                .stream().map(canhBao -> new ThongBaoSinhVienDto(null,canhBao)).collect(Collectors.toList());


        Page<ThongBao> page = thongBaoCustomRepository.listThongBao(pageable, idSinhVien);
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        List<ThongBaoSinhVienDto> listThongBao = page.getContent().stream().map(thongBao -> new ThongBaoSinhVienDto(thongBao,null)).collect(Collectors.toList());
        if (listCanhBao.size()>0&&pageable.getPageNumber()==0&&pageable.getPageSize()<=10){
            listThongBao.stream().forEach(listCanhBao::add);
            return new GetThongBaoSinhVien(listCanhBao,paginationMeta);
        }


        return new GetThongBaoSinhVien(listThongBao,paginationMeta);

//        return null;
    }

    @Override
    public GetThongBaoLopOfGiangVien getThongBaoLopOfGiangVien(Pageable pageable,Integer idLop,Integer idGiangVien) {
        Page<ThongBao> page = thongBaoCustomRepository.thongBaoLopSinhVien(pageable,idGiangVien,idLop);
        List<ThongBaoLopOfGiangVien> list = page
                .getContent().stream()
                .map(thongBao -> new ThongBaoLopOfGiangVien(thongBao)).collect(Collectors.toList());
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        return new GetThongBaoLopOfGiangVien(list,paginationMeta);
    }

    @Override
    public GetThongBaoLopOfGiangVien getThongBaoLopHocPhanOfGiangVien(Pageable pageable,Integer idLopHocPhan,Integer idGiangVien) {
        Page<ThongBao> page = thongBaoCustomRepository.thongBaoLopHocPhanSinhVien(pageable,idGiangVien,idLopHocPhan);
        List<ThongBaoLopOfGiangVien> list = page
                .getContent().stream()
                .map(thongBao -> new ThongBaoLopOfGiangVien(thongBao)).collect(Collectors.toList());
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        return new GetThongBaoLopOfGiangVien(list,paginationMeta);
    }

    @Override
    public Integer themThongBaoLop(Integer idGiangVien,Integer idLop,PostThongBaoLop postThongBaoLop) {
        Lop lop = lopRepository.findById(idLop).orElseThrow(() -> new IllegalArgumentException("id not found"));
        GiangVien giangVien = giangVienRepository.findById(idGiangVien).orElseThrow(() -> new IllegalArgumentException("id not found"));
        ThongBao thongBao =new ThongBao(postThongBaoLop,giangVien);
        ThongBao thongBaoResult = thongBaoRepository.save(thongBao);
        thongBaoLopRepository.save(new ThongBao_Lop(thongBaoResult,lop));
        return thongBaoResult.getId();
    }

    @Override
    public Integer themThongBaoLopHocPhan(Integer idGiangVien,Integer idLopHocPhan,PostThongBaoLop postThongBaoLop) {
        LopHocPhan lopHocPhan = lopHocPhanRepository.findById(idLopHocPhan).orElseThrow(() -> new IllegalArgumentException("id not found"));
        GiangVien giangVien = giangVienRepository.findById(idGiangVien).orElseThrow(() -> new IllegalArgumentException("id not found"));
        ThongBao thongBao =new ThongBao(postThongBaoLop,giangVien);
        ThongBao thongBaoResult = thongBaoRepository.save(thongBao);
        thongBaoLopHocPhanRepository.save(new ThongBao_LopHocPhan(thongBaoResult,lopHocPhan));
        return thongBaoResult.getId();
    }

    @Override
    @Transactional
    public Integer chinhSuaThongBaoLop(Integer idThongBao,PostThongBaoLop postThongBaoLop) {
        ThongBao thongBao = thongBaoRepository.findById(idThongBao).orElseThrow(() -> new IllegalArgumentException("id not found"));
        thongBao.chinhSuaThongBaoLop(postThongBaoLop);
        return thongBao.getId();
    }


    @Override
    @Transactional
    public Integer chinhSuaHienThiThongBaoLop(Integer idThongBao) {
        ThongBao thongBao = thongBaoRepository.findById(idThongBao).orElseThrow(() -> new IllegalArgumentException("id not found"));
        thongBao.chinhSuaHienThiThongBaoLop();
        return thongBao.getId();
    }
}
