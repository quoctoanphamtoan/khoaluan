package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import com.solienlac.khoaluan.web.domain.NgayNghi;
import com.solienlac.khoaluan.web.domain.SinhVien;
import com.solienlac.khoaluan.web.domain.SinhVien_LopHocPhan;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Log4j2
public class SinhVienLopHocPhanDto {
    private Integer idSvLhp;
    private Integer id;
    private String maSV;
    private String email;
    private String hoTen;
    private DiemSinhVienMonHocDto diemSinhVienMonHocDto;
    private Integer soNgayNghiPhep;
    private Integer soNgayNghiKhongPhep;
    private boolean trangThai;

    public SinhVienLopHocPhanDto(SinhVien_LopHocPhan sinhVien_lopHocPhan){
        this.idSvLhp = sinhVien_lopHocPhan.getId();
        this.id=sinhVien_lopHocPhan.getSinhVien().getId();
        this.hoTen = sinhVien_lopHocPhan.getSinhVien().getHoTen();
        this.email = sinhVien_lopHocPhan.getSinhVien().getEmail();
        this.maSV = sinhVien_lopHocPhan.getSinhVien().getMaSinhvVien();
        this.diemSinhVienMonHocDto = new DiemSinhVienMonHocDto(sinhVien_lopHocPhan.getSinhVien().getBangDiemTongKet().getBangDiem_sinhVien_monHocs()
                .stream().filter(
                        bangDiem_sinhVien_monHoc -> bangDiem_sinhVien_monHoc.getBangDiemTongKet().getSinhVien().getId()==sinhVien_lopHocPhan.getSinhVien().getId()
                ).findAny().orElseThrow(() -> new IllegalArgumentException("id not found")));
        this.trangThai = sinhVien_lopHocPhan.isTrangThai();
        List<NgayNghi> ngayNghiList= sinhVien_lopHocPhan.getLopHocPhan().getSinhVien_lopHocPhans().stream().filter(svlhp ->
                svlhp.getSinhVien().getId()==sinhVien_lopHocPhan.getSinhVien().getId())
                .findAny().orElseThrow(() -> new IllegalArgumentException("id not found! "))
                .getNgayNghis();

        log.info("list ngay nghi size->{}",ngayNghiList.size());
        this.soNgayNghiPhep = ngayNghiList.stream().filter(ngayNghi -> ngayNghi.isCoPhep()).collect(Collectors.toList()).size();
        this.soNgayNghiKhongPhep =ngayNghiList.stream().filter(ngayNghi -> !ngayNghi.isCoPhep()).collect(Collectors.toList()).size();
    }
}
