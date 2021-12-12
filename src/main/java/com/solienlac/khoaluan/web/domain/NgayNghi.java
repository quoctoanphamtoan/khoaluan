package com.solienlac.khoaluan.web.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ngaynghihoc")
@Getter
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
}
