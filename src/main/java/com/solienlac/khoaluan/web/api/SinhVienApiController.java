package com.solienlac.khoaluan.web.api;

import com.solienlac.khoaluan.web.common.dto.*;
import com.solienlac.khoaluan.web.common.dto.param.PostDonXinNghiHoc;
import com.solienlac.khoaluan.web.common.dto.param.PutSinhVienParam;
import com.solienlac.khoaluan.web.common.dto.param.XinNghiHocParam;
import com.solienlac.khoaluan.web.domain.LopHocPhan;
import com.solienlac.khoaluan.web.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solienlacdientu/v1/sinhvien")
public class SinhVienApiController {
    private final DonXinNghiHocService donXinNghiHocService;
    private final SinhVienService sinhVienService;
    private final ThongBaoService thongBaoService;
    private final DiemService diemService;
    private final LopHocPhanService lopHocPhanService;

    @PostMapping("/donxinnghihoc/{idSinhVien}/{idLopHocPhan}")
    public Integer postDonXinNghiHoc(@PathVariable("idSinhVien") Integer idSinhVien
            , @PathVariable("idLopHocPhan") Integer idLopHocPhan, @RequestBody PostDonXinNghiHoc postDonXinNghiHoc){
        return donXinNghiHocService.xinNghiHoc(idSinhVien,idLopHocPhan,postDonXinNghiHoc);
    }

    @GetMapping("/thongtin/{id}")
    public ThongTinSinhVienDto xemThongTin(@PathVariable("id") Integer id){
        return sinhVienService.xemThongTin(id);
    }

    @PutMapping("/thongtin/{id}")
    public Integer chinhSuaThongTin(@PathVariable("id") Integer id, @RequestBody PutSinhVienParam putSinhVienParam){
        return sinhVienService.chinhSuaSinhVien(id,putSinhVienParam);
    }


    @GetMapping("/{idSinhVien}/thongbaos")
    public GetThongBaoSinhVien getDonXinNghiHoc(@PathVariable("idSinhVien") Integer idSinhVien,
                                                @PageableDefault(size = 10, page = 1, direction = Sort.Direction.ASC) Pageable pageable){
        return thongBaoService.getThongBaoSinhVien(pageable,idSinhVien);


    }

    @GetMapping("/{idSinhVien}/diem")
    public GetDiemSinhVien getDonXinNghiHoc(@PathVariable("idSinhVien") Integer idSinhVien){
        return diemService.getDiemSinhViens(idSinhVien);
    }
    //
    @GetMapping("/{idSinhVien}/lophocphan")
    public List<LopHocPhanOfSinhVienDto> getLopHocPhan(@PathVariable("idSinhVien") Integer idSinhVien){
        return lopHocPhanService.getLopHocPhanOfSinhVien(idSinhVien);
    }
    @PostMapping("/donxinnghihoc")
    public Integer xinNghiHoc(@RequestBody XinNghiHocParam xinNghiHocParam){
        return donXinNghiHocService.xinNghiHoc(xinNghiHocParam);
    }
    @GetMapping("/{idSinhVien}/donxinnghihoc")
    public List<DonXinNghiHocDto> donNghiHocs(@PathVariable("idSinhVien") Integer idSinhVien){
        return donXinNghiHocService.getDonXinNghiHocOfSinhVien(idSinhVien);
    }


}
