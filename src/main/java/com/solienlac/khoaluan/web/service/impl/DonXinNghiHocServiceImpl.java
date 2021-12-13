package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.DonXinNghiHocDto;
import com.solienlac.khoaluan.web.common.dto.GetDonXinNghiHoc;
import com.solienlac.khoaluan.web.common.dto.param.PostDiemDanh;
import com.solienlac.khoaluan.web.common.dto.param.PostDonXinNghiHoc;
import com.solienlac.khoaluan.web.common.page.PaginationMeta;
import com.solienlac.khoaluan.web.domain.*;
import com.solienlac.khoaluan.web.repository.*;
import com.solienlac.khoaluan.web.service.DonXinNghiHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonXinNghiHocServiceImpl implements DonXinNghiHocService {
    private final DonXinNghiHocRepository donXinNghiHocRepository;
    private final LopHocPhanRepository lopHocPhanRepository;
    private final SinhVienRepository sinhVienRepository;
    private final DonXinNghiHocCustomRepository donXinNghiHocCustomRepository;
    private final NgayNghiRepository ngayNghiRepository;
    private final SinhVienLopHocPhanRepository sinhVienLopHocPhanRepository;

    @Override
    public Integer xinNghiHoc(Integer idSinhVien, Integer idLopHocPhan, PostDonXinNghiHoc postDonXinNghiHoc) {
        SinhVien sinhVien  = sinhVienRepository.getOne(idSinhVien);
        LopHocPhan lopHocPhan  = lopHocPhanRepository.getOne(idLopHocPhan);
        DonXinNghiHoc donXinNghiHoc =new DonXinNghiHoc(postDonXinNghiHoc.getNoiDung(), postDonXinNghiHoc.getNgayNghi(),sinhVien,lopHocPhan );
        return  donXinNghiHocRepository.save(donXinNghiHoc).getId();
    }

    @Override
    public Integer duyetDonXinNghiHoc(Integer idGiangVien, Integer idDonXinNghiHoc) {
        DonXinNghiHoc donXinNghiHoc = donXinNghiHocRepository.getOne(idDonXinNghiHoc);
        donXinNghiHoc.duyetDonNghiHoc();
        return donXinNghiHoc.getId();
    }

    @Override
    public GetDonXinNghiHoc getDonXinNghiHoc(Pageable pageable, Integer idGiangVien) {
        List<DonXinNghiHocDto> list = new ArrayList<>();
        Page<DonXinNghiHoc> page = donXinNghiHocCustomRepository.listDonXinNghiHoc(pageable,idGiangVien);
        page.getContent().forEach(donXinNghiHoc -> {
            list.add(new DonXinNghiHocDto(donXinNghiHoc));
        });
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        return new GetDonXinNghiHoc(list,paginationMeta);
    }

    @Override
    @Transactional
    public Integer diemDanh(Integer idSinhVien, Integer idLopHocPhan, PostDiemDanh postDiemDanh) {
        SinhVien sinhVien = sinhVienRepository.findById(idSinhVien)
                .orElseThrow(() -> new IllegalArgumentException("id not found"));
        LopHocPhan lopHocPhan = lopHocPhanRepository.findById(idLopHocPhan)
                .orElseThrow(() -> new IllegalArgumentException("id not found"));
        SinhVien_LopHocPhan sinhVien_lopHocPhan = sinhVienLopHocPhanRepository.findSinhVien_LopHocPhanBySinhVienAndLopHocPhan(sinhVien,lopHocPhan);
        NgayNghi ngayNghi = ngayNghiRepository.save(new NgayNghi(postDiemDanh,sinhVien_lopHocPhan));
        SinhVien_LopHocPhan svlhpCheck = sinhVienLopHocPhanRepository.findSinhVien_LopHocPhanBySinhVienAndLopHocPhan(sinhVien,lopHocPhan);

        Integer soNgayNghiPhep = svlhpCheck.getNgayNghis()
                .stream().filter(ngayNghiCheck ->ngayNghiCheck.isCoPhep()).collect(Collectors.toList()).size();

        Integer soNgayNghiKhongPhep = svlhpCheck.getNgayNghis()
                .stream().filter(ngayNghiCheck ->!ngayNghiCheck.isCoPhep()).collect(Collectors.toList()).size();
        if (((soNgayNghiPhep/2)+soNgayNghiKhongPhep)>=3){
            svlhpCheck.dinhChiHoc();
        }

        return ngayNghi.getId();
    }


}
