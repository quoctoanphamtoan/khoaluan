package com.solienlac.khoaluan.web.domain;

import com.solienlac.khoaluan.web.common.dto.param.PostDiemDanh;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ngaynghihoc")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NgayNghi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ngayNghi")
    private Date ngayNghi;

    @Column(name = "coPhep")
    private boolean coPhep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idSinhVien_LopHocPhan")
    private SinhVien_LopHocPhan sinhVien_lopHocPhan;
    public NgayNghi(PostDiemDanh postDiemDanh,SinhVien_LopHocPhan sinhVien_lopHocPhan){
        this.coPhep = postDiemDanh.isPhep();
        this.ngayNghi = postDiemDanh.getDate();
        this.sinhVien_lopHocPhan = sinhVien_lopHocPhan;
    }

    public NgayNghi(Date date,SinhVien_LopHocPhan sinhVien_lopHocPhan){
        this.coPhep = true;
        this.ngayNghi = date;
        this.sinhVien_lopHocPhan = sinhVien_lopHocPhan;
    }



}
