package com.solienlac.khoaluan.web.domain;

import com.solienlac.khoaluan.web.common.dto.param.XinNghiHocParam;
import com.solienlac.khoaluan.web.domain.common.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Table(name = "donxinnghihoc")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DonXinNghiHoc extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String noiDung;
    private Date ngayNghi;
    private Boolean trangThai = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSinhVien")
    private SinhVien sinhVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLopHocPhan")
    private LopHocPhan lopHocPhan;

    public void duyetDonNghiHoc(){
        this.trangThai = true;
    }

    public DonXinNghiHoc(String noiDung, Date ngayNghi, SinhVien sinhVien, LopHocPhan lopHocPhan) {

        this.noiDung = noiDung;
        this.ngayNghi = ngayNghi;
        this.sinhVien = sinhVien;
        this.lopHocPhan = lopHocPhan;
    }
    public DonXinNghiHoc(XinNghiHocParam xinNghiHocParam,SinhVien sinhVien,LopHocPhan lopHocPhan){
        this.noiDung = xinNghiHocParam.getLyDo();
        this.ngayNghi = xinNghiHocParam.getNgayNghi();
        this.sinhVien = sinhVien;
        this.lopHocPhan = lopHocPhan;
    }
}
