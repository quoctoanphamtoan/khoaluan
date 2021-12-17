package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.DonXinNghiHocDto;
import com.solienlac.khoaluan.web.common.dto.GetDonXinNghiHoc;
import com.solienlac.khoaluan.web.common.dto.param.PostDiemDanh;
import com.solienlac.khoaluan.web.common.dto.param.PostDonXinNghiHoc;
import com.solienlac.khoaluan.web.common.dto.param.XinNghiHocParam;
import com.solienlac.khoaluan.web.common.page.PaginationMeta;
import com.solienlac.khoaluan.web.domain.*;
import com.solienlac.khoaluan.web.repository.*;
import com.solienlac.khoaluan.web.service.DonXinNghiHocService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
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
public class DonXinNghiHocServiceImpl implements DonXinNghiHocService {
    private final DonXinNghiHocRepository donXinNghiHocRepository;
    private final LopHocPhanRepository lopHocPhanRepository;
    private final SinhVienRepository sinhVienRepository;
    private final DonXinNghiHocCustomRepository donXinNghiHocCustomRepository;
    private final NgayNghiRepository ngayNghiRepository;
    private final SinhVienLopHocPhanRepository sinhVienLopHocPhanRepository;
    private final DiemTongKetRepository diemTongKetRepository;
    private final BangDiemSinhVienMonHocRepository bangDiemSinhVienMonHocRepository;

    @Override
    public Integer xinNghiHoc(  Integer idSinhVien, Integer idLopHocPhan,PostDonXinNghiHoc postDonXinNghiHoc) {
        SinhVien sinhVien  = sinhVienRepository.findById(idSinhVien).orElseThrow(() -> new IllegalArgumentException("id not found!"));
        LopHocPhan lopHocPhan  = lopHocPhanRepository.getOne(idLopHocPhan);
        DonXinNghiHoc donXinNghiHoc =new DonXinNghiHoc(postDonXinNghiHoc.getNoiDung(), postDonXinNghiHoc.getNgayNghi(),sinhVien,lopHocPhan );
        return  donXinNghiHocRepository.save(donXinNghiHoc).getId();
    }

    @Override
    @Transactional
    public Integer duyetDonXinNghiHoc(  Integer idGiangVien,   Integer idDonXinNghiHoc) {
        DonXinNghiHoc donXinNghiHoc = donXinNghiHocRepository.findById(idDonXinNghiHoc)
                .orElseThrow(() -> new IllegalArgumentException("id not found"));
        donXinNghiHoc.duyetDonNghiHoc();
        SinhVien_LopHocPhan sinhVien_lopHocPhan =
                sinhVienLopHocPhanRepository.findSinhVien_LopHocPhanBySinhVienAndLopHocPhan(donXinNghiHoc.getSinhVien(),donXinNghiHoc.getLopHocPhan());

       ngayNghiRepository.save(new NgayNghi(donXinNghiHoc.getNgayNghi(),sinhVien_lopHocPhan));
        Integer nghiPhep = sinhVien_lopHocPhan.getNgayNghis().stream().filter(ngayNghi -> ngayNghi.isCoPhep()).collect(Collectors.toList()).size();
        Integer nghiKhongPhep = sinhVien_lopHocPhan.getNgayNghis().stream().filter(ngayNghi -> !ngayNghi.isCoPhep()).collect(Collectors.toList()).size();

        if (((nghiPhep/2)+nghiKhongPhep)>=3){
            sinhVien_lopHocPhan.dinhChiHoc();
        }
        return donXinNghiHoc.getId();
    }

    @Override
    public GetDonXinNghiHoc getDonXinNghiHoc(Pageable pageable,  Integer idGiangVien, Integer idLopHocPhan) {
        List<DonXinNghiHocDto> list = new ArrayList<>();
        Page<DonXinNghiHoc> page = donXinNghiHocCustomRepository.listDonXinNghiHoc(pageable,idGiangVien,idLopHocPhan);
        page.getContent().forEach(donXinNghiHoc -> {
            list.add(new DonXinNghiHocDto(donXinNghiHoc));
        });
        PaginationMeta paginationMeta = PaginationMeta.createPagination(page);
        return new GetDonXinNghiHoc(list,paginationMeta);
    }

    @Override
    @Transactional
    public Integer diemDanh( Integer idSinhVien, Integer idLopHocPhan,PostDiemDanh postDiemDanh) {
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
            BangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc = bangDiemSinhVienMonHocRepository.findByBangDiemTongKet(svlhpCheck.getSinhVien().getBangDiemTongKet());
            bangDiem_sinhVien_monHoc.huyDiem();
            svlhpCheck.getSinhVien().getBangDiemTongKet().setBangDiemTongKet(svlhpCheck.getSinhVien().getBangDiemTongKet().getBangDiem_sinhVien_monHocs());

        }

        return ngayNghi.getId();
    }

    @Override
    public Integer xinNghiHoc(XinNghiHocParam xinNghiHocParam) {
        SinhVien_LopHocPhan sinhVien_lopHocPhan = sinhVienLopHocPhanRepository.findById(xinNghiHocParam.getId())
                .orElseThrow(() -> new IllegalArgumentException("id not found!"));

        SinhVien sinhVien = sinhVienRepository.findById(sinhVien_lopHocPhan.getSinhVien().getId())
                .orElseThrow(() -> new IllegalArgumentException("id not found"));
        LopHocPhan lopHocPhan = lopHocPhanRepository.findById(sinhVien_lopHocPhan.getLopHocPhan().getId())
                .orElseThrow(() -> new IllegalArgumentException("id not found !"));
        return donXinNghiHocRepository.save(new DonXinNghiHoc(xinNghiHocParam,sinhVien,lopHocPhan)).getId();
    }

    @Override
    public List<DonXinNghiHocDto> getDonXinNghiHocOfSinhVien(Integer idSinhVien) {
        return donXinNghiHocCustomRepository.listDonXinNghiHocOfSinhVien(idSinhVien)
                .stream().map(DonXinNghiHocDto::new).collect(Collectors.toList());
    }


}
