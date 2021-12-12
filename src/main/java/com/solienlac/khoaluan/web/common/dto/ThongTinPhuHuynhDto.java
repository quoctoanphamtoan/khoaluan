package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.domain.GiangVien;
import com.solienlac.khoaluan.web.domain.PhuHuynh;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThongTinPhuHuynhDto {
    private Integer id;
    private String maSinhVien;
    private String chuyenNganh;
    private String hoTen;
    private boolean gioiTinh;
    private String email;
    private String soDT;
    private String diaChi;
    private String imgUrl;
    private Integer idSv;
    public ThongTinPhuHuynhDto(PhuHuynh phuHuynh){
        this.id = phuHuynh.getId();
        this.hoTen =phuHuynh.getHoTen();
        this.gioiTinh = phuHuynh.isGioiTinh();

        this.email = phuHuynh.getEmail();
        this.soDT = phuHuynh.getSoDienThoai();
        this.diaChi= phuHuynh.getDiaChi();
        this.idSv=phuHuynh.getSinhVienList().get(0).getId();
//        this.imgUrl = giangVien.getImgUrl();

    }
}
