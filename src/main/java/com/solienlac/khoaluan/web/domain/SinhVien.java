package com.solienlac.khoaluan.web.domain;


import com.solienlac.khoaluan.web.common.dto.param.PutSinhVienParam;
import com.solienlac.khoaluan.web.domain.common.AbstractEntity;
import com.solienlac.khoaluan.web.domain.common.TrangThaiSinhVien;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "sinhvien")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SinhVien  extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "maSinhVien")
    private String maSinhVien;

    @Column(name = "hoTen")
    private String hoTen;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "soDienThoai")
    private String soDienThoai;

    @Column(name = "gioiTinh")
    private boolean gioiTinh;

    @Column(name = "email")
    private String email;

    private String imgUrl;
    private String chuyenNganh;

    @Column(name = "trangThai")
    @Enumerated(EnumType.STRING)
    private TrangThaiSinhVien trangThai = TrangThaiSinhVien.DANG_HOC;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idTaiKhoan")
    private TaiKhoan taiKhoan;

    @OneToMany(mappedBy = "idSinhVien")
    private List<CanhBao> canhBaoList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPhuHuynh")
    private PhuHuynh phuHuynh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLop")
    private Lop lop;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBangDiemTongKet")
    private BangDiemTongKet bangDiemTongKet;


    @OneToMany(mappedBy = "sinhVien")
    private List<SinhVien_LopHocPhan> sinhVien_lopHocPhans = new ArrayList<>();


    public LopHocPhan getLopHocPhan(Integer idLopHocPhan) {
        return getSinhVien_lopHocPhans()
                .stream().filter(sinhVien_lopHocPhan ->
                 sinhVien_lopHocPhan.getSinhVien()
                        .id==this.id&&sinhVien_lopHocPhan.getLopHocPhan().getId()==idLopHocPhan)
                .findAny().orElseThrow(() -> new IllegalArgumentException("id not found! ")).getLopHocPhan();
    }

    @OneToMany(mappedBy = "sinhVien")
    private List<DonXinNghiHoc> donXinNghiHocs= new ArrayList<>();

    public SinhVien(String maSinhvVien, String hoTen, String diaChi, String soDienThoai, boolean gioiTinh, String email,TaiKhoan taiKhoan,BangDiemTongKet bangDiemTongKet) {
        this.maSinhVien = maSinhvVien;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.taiKhoan = taiKhoan;
        this.bangDiemTongKet = bangDiemTongKet;
    }

    public void chinhSua(PutSinhVienParam putSinhVienParam){
        this.email = putSinhVienParam.getEmail();
        this.soDienThoai = putSinhVienParam.getSoDT();
        this.diaChi = putSinhVienParam.getDiaChi();
    }
}
