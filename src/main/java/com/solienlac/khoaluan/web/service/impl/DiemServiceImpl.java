package com.solienlac.khoaluan.web.service.impl;

import com.solienlac.khoaluan.web.common.dto.DiemSinhVienDto;
import com.solienlac.khoaluan.web.common.dto.GetDiemSinhVien;
import com.solienlac.khoaluan.web.common.dto.param.PutBangDiemSinhVien;
import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.domain.SinhVien_LopHocPhan;
import com.solienlac.khoaluan.web.repository.BangDiemSinhVienMonHocRepository;
import com.solienlac.khoaluan.web.repository.DiemCustomRepository;
import com.solienlac.khoaluan.web.repository.DiemRepository;
import com.solienlac.khoaluan.web.repository.SinhVienRepository;
import com.solienlac.khoaluan.web.service.DiemService;
import com.solienlac.khoaluan.web.service.SinhVienService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.query.criteria.internal.IllegalDereferenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DiemServiceImpl implements DiemService {
    private final SinhVienService sinhVienService;
    private final SinhVienRepository sinhVienRepository;
    private final DiemCustomRepository diemCustomRepository;
    private final BangDiemSinhVienMonHocRepository bangDiemSinhVienMonHocRepository;
    private final DiemRepository diemRepository;
    @Override
    public GetDiemSinhVien getDiemSinhViens(Integer idSinhVien) {
            List<BangDiem_SinhVien_MonHoc> list =
                    diemRepository
                            .findAllByBangDiemTongKet(sinhVienRepository.findById(idSinhVien).orElseThrow(() ->
                                    new IllegalArgumentException("idn not found!")).getBangDiemTongKet());

        return new GetDiemSinhVien(list
                .stream()
                .map(DiemSinhVienDto::new).collect(Collectors.toList())
                ,sinhVienRepository.findById(idSinhVien).orElseThrow(() -> new IllegalArgumentException("id not found")).getBangDiemTongKet().getTrungBinhTichLuy());


    }

    @Override
    @Transactional
    public Integer chinhSuaBangDiemSinhVienMonHoc(Integer idBangDiem, PutBangDiemSinhVien putBangDiemSinhVien) {
        BangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc = bangDiemSinhVienMonHocRepository.findById(idBangDiem)
                .orElseThrow(() -> new IllegalArgumentException("id not foud!"));
         bangDiem_sinhVien_monHoc.suaDiem(putBangDiemSinhVien);
        if (putBangDiemSinhVien.getGk()>0&&putBangDiemSinhVien.getGk()>0){
            SinhVien_LopHocPhan sinhVien_lopHocPhanRs= bangDiem_sinhVien_monHoc.getBangDiemTongKet().getSinhVien().getSinhVien_lopHocPhans()
                    .stream().filter(sinhVien_lopHocPhan -> sinhVien_lopHocPhan.getLopHocPhan().getMonHoc().getId()==bangDiem_sinhVien_monHoc.getMonHoc().getId())
                    .findAny().orElseThrow(() -> new IllegalArgumentException("id not found"));
            sinhVien_lopHocPhanRs.choHocLai();
        }
         if(putBangDiemSinhVien.getGk()==0){
             Integer idMonHoc = bangDiem_sinhVien_monHoc.getMonHoc().getId();
             SinhVien_LopHocPhan sinhVien_lopHocPhanRs= bangDiem_sinhVien_monHoc.getBangDiemTongKet().getSinhVien().getSinhVien_lopHocPhans()
                     .stream().filter(sinhVien_lopHocPhan -> sinhVien_lopHocPhan.getLopHocPhan().getMonHoc().getId()==idMonHoc)
                     .findAny().orElseThrow(() -> new IllegalArgumentException("id not found"));
             sinhVien_lopHocPhanRs.dinhChiHoc();
         }
        SinhVien sinhVien = sinhVienRepository.findById(bangDiem_sinhVien_monHoc.getBangDiemTongKet().getSinhVien().getId())
                .orElseThrow(() -> new IllegalArgumentException("id not found!"));
        sinhVien.getBangDiemTongKet()


                //set bang diem tong ket , voi dieu kien la so ngay nghi co phep /2 + so ngay nghi khong phep phai nho hon 3 va diem giua ki lon hon ko
                //va neu nhu putdata co diem cuoi ki moi chay chuc nang nay
                .setBangDiemTongKet(sinhVien.getBangDiemTongKet().getBangDiem_sinhVien_monHocs().stream().filter(
                        bangDiemSinhVien ->bangDiemSinhVien.getDiemGK()>0
                                &&((bangDiemSinhVien.getBangDiemTongKet().getSinhVien().getSinhVien_lopHocPhans()
                                .stream().filter(sinhVien_lopHocPhan -> sinhVien_lopHocPhan.getSinhVien().getId()==sinhVien.getId())
                                .findAny().orElseThrow(() -> new IllegalArgumentException("id not found")).getNgayNghis()
                                .stream().filter(ngayNghi -> ngayNghi.isCoPhep()).collect(Collectors.toList()).size()/2)+
                                (bangDiemSinhVien.getBangDiemTongKet().getSinhVien().getSinhVien_lopHocPhans()
                                        .stream().filter(sinhVien_lopHocPhan -> sinhVien_lopHocPhan.getSinhVien().getId()==sinhVien.getId())
                                        .findAny().orElseThrow(() -> new IllegalArgumentException("id not found")).getNgayNghis()
                                        .stream().filter(ngayNghi -> !ngayNghi.isCoPhep()).collect(Collectors.toList()).size())
                        )<3&&putBangDiemSinhVien.getCk()>=0
                ).collect(Collectors.toList()));


        return bangDiem_sinhVien_monHoc.getId();
    }
}
