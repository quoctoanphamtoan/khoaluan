package com.solienlac.khoaluan.web.domain;

import com.solienlac.khoaluan.web.common.dto.param.PutBangDiemSinhVien;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.DecimalFormat;

@Table(name = "diem_sinhvien_monhoc")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BangDiem_SinhVien_MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private double diemCK;
    private double diemGK;

    private double diemTK1;
    private double diemTK2;
    private double diemTK3;
    private double diemTBC;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMonHoc")
    private MonHoc monHoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idBangDiemTongKet")
    private BangDiemTongKet bangDiemTongKet;
    public void huyDiem(){
        this.diemTBC=0;
        this.diemCK=0;

    }
    public void suaDiem(PutBangDiemSinhVien putBangDiemSinhVien){
        this.diemTK1 = putBangDiemSinhVien.getTk1();
        this.diemTK2 = putBangDiemSinhVien.getTk2();
        this.diemTK3 = putBangDiemSinhVien.getTk3();
        this.diemGK = putBangDiemSinhVien.getGk();
        if (putBangDiemSinhVien.getGk()<=0||putBangDiemSinhVien.getCk()<=0){
            this.diemCK=Double.valueOf("0.0");
            this.diemTBC = Double.valueOf("0.0");

            return;
        }else {
            this.diemCK = putBangDiemSinhVien.getCk();
            double dtbc =(((this.diemTK1+this.diemTK2+this.diemTK3)/3)*0.2)+(this.diemGK*0.3)+this.diemCK*0.5;
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            this.diemTBC = Double.valueOf(decimalFormat.format(dtbc));
        }


    }
}
