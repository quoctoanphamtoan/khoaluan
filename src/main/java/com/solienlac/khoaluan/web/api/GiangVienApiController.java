package com.solienlac.khoaluan.web.api;

import com.solienlac.khoaluan.web.common.dto.*;
import com.solienlac.khoaluan.web.common.dto.param.*;
import com.solienlac.khoaluan.web.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solienlacdientu/v1/giangvien")
public class GiangVienApiController {
    private final GiangVienService gIangVienService;
    private final LopService lopService;
    private final SmsSenderService service;
    private final SinhVienService sinhVienService;
    private final DonXinNghiHocService donXinNghiHocService;
    private final ThongBaoService thongBaoService;
    private final CanhBaoService canhBaoService;
    private final LopHocPhanService lopHocPhanService;
    private final DiemService diemService;
    private final NgayNghiService ngayNghiService;

    /*
     *Lấy danh sách tất cả lớp học của giảng viên theo mã giảng viên(idGiangVien)
     * Giá trị trả về bao gồm:
     *      tenLop;
            tenChuyenNganh;
            siSo;
     */
    @GetMapping("/{idGiangVien}/danhsachlophoc")
    public ResponseEntity<GetLop> getLops(@PathVariable("idGiangVien") Integer idGiangVien, @PageableDefault(size = 10, page = 2, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(lopService.getLop(pageable, idGiangVien));
    }

    @GetMapping("/{idGiangVien}/danhsachlophocphan")
    public ResponseEntity<GetLopHocPhan> getLopHocPhans(@PathVariable("idGiangVien") Integer idGiangVien, @PageableDefault(size = 10, page = 2, direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(lopHocPhanService.lopHocPhans(idGiangVien, pageable));
    }


    /*
      Gởi tin nhắn cảnh báo cho sinh viên và phụ huynh
    */
    @PostMapping("/canhbao")
    @ResponseStatus(HttpStatus.CREATED)
    public Integer smsCanhBaoSinhVien(@RequestBody PostSmsCanhBao thongTinCanhBao) throws IllegalAccessException {
        return service.senderSms(thongTinCanhBao);
    }

    @PutMapping("/donnghihoc/{idGiangVien}/{idDonNghiHoc}")
    @ResponseStatus(HttpStatus.OK)
    public Integer duyetDonNghiHoc(@PathVariable("idGiangVien") Integer idGiangVien,
                                   @PathVariable("idDonNghiHoc") Integer idDonNghiHoc) {
        return donXinNghiHocService.duyetDonXinNghiHoc(idGiangVien, idDonNghiHoc);
    }

    @GetMapping("/{idGiangVien}/{idLopHocPhan}/list-donxinnghihoc")
    public GetDonXinNghiHoc getDonXinNghiHoc(@PathVariable("idGiangVien") Integer idGiangVien, @PathVariable("idLopHocPhan") Integer idLopHocPhan,
                                             @PageableDefault(size = 10, page = 1, direction = Sort.Direction.ASC) Pageable pageable) {
        return donXinNghiHocService.getDonXinNghiHoc(pageable, idGiangVien, idLopHocPhan);


    }

    @GetMapping("/{idLopHoc}/sinhvien")
    public GetSinhVienLopHoc getSinhVienLopHoc(@PathVariable("idLopHoc") Integer idLopHoc,
                                               @PageableDefault(size = 10, page = 1, direction = Sort.Direction.ASC) Pageable pageable) {
        return sinhVienService.getSinhVienLopHoc(pageable, idLopHoc);
    }

    @GetMapping("/{idLopHocPhan}/lophocphan/sinhvien")
    public GetSinhVienLopHocPhan getSinhVienLopHocPhan(@PathVariable("idLopHocPhan") Integer idLopHocPhan,
                                                       @PageableDefault(size = 10, page = 1, direction = Sort.Direction.ASC) Pageable pageable) {
        return sinhVienService.getSinhVienLopHocPhan(pageable, idLopHocPhan);
    }


    @GetMapping("/{idGiangVien}/{idLopHoc}/thongbaolop")
    public GetThongBaoLopOfGiangVien getThongBaoLopOfGiangVien(@PathVariable("idGiangVien") Integer idLopHoc, @PathVariable("idLopHoc") Integer idGiangVien,
                                                               @PageableDefault(size = 10, page = 1, direction = Sort.Direction.ASC) Pageable pageable) {
        return thongBaoService.getThongBaoLopOfGiangVien(pageable, idGiangVien, idLopHoc);
    }

    @GetMapping("/{idGiangVien}/{idLopHoc}/thongbaolophocphan")
    public GetThongBaoLopOfGiangVien getThongBaoLopHocPhanOfGiangVien(@PathVariable("idGiangVien") Integer idLopHoc, @PathVariable("idLopHoc") Integer idGiangVien,
                                                                      @PageableDefault(size = 10, page = 1, direction = Sort.Direction.ASC) Pageable pageable) {
        return thongBaoService.getThongBaoLopHocPhanOfGiangVien(pageable, idGiangVien, idLopHoc);
    }


    @PostMapping("/{idGiangVien}/{idLop}/thongbaolop")
    public Integer taoThongBaoLopHoc(@PathVariable("idGiangVien") Integer idGiangVien, @PathVariable("idLop") Integer idLop
            , @RequestBody PostThongBaoLop postThongBaoLop) {
        return thongBaoService.themThongBaoLop(idGiangVien, idLop, postThongBaoLop);
    }

    @PostMapping("/{idGiangVien}/{idLopHocPhan}/thongbaolophocphan")
    public Integer taoThongBaoLopHocPhan(@PathVariable("idGiangVien") Integer idGiangVien, @PathVariable("idLopHocPhan") Integer idLopHocPhan
            , @RequestBody PostThongBaoLop postThongBaoLop) {
        return thongBaoService.themThongBaoLopHocPhan(idGiangVien, idLopHocPhan, postThongBaoLop);
    }


    @PutMapping("/{idThongBao}/thongbaolop")
    public Integer chinhSuaThongBaoLopHoc(@PathVariable("idThongBao") Integer idThongBao
            , @RequestBody PostThongBaoLop postThongBaoLop) {
        return thongBaoService.chinhSuaThongBaoLop(idThongBao, postThongBaoLop);
    }


    @PutMapping("/{idThongBao}/thongbaolop/hienthi")
    public Integer chinhSuaHienThiThongBaoLopHoc(@PathVariable("idThongBao") Integer idThongBao) {
        return thongBaoService.chinhSuaHienThiThongBaoLop(idThongBao);
    }

    @GetMapping("/{idGiangVien}/{idSinhVien}/canhbao")
    public List<CanhBaoDto> getCanhBaoSinhVienOfLopHOc(@PathVariable("idGiangVien") Integer idGiangVien,
                                                       @PathVariable("idSinhVien") Integer idSinhVien) {
        return canhBaoService.listCanhBao(idSinhVien, idGiangVien);
    }

    @PutMapping("/{idBangDiem}/monhoc/bangdiem")
    public Integer chinhSuaBangDiemSinhVienMonHoc(@PathVariable("idBangDiem") Integer idBangDiem,
                                                  @RequestBody PutBangDiemSinhVien putBangDiemSinhVien) {
        return diemService.chinhSuaBangDiemSinhVienMonHoc(idBangDiem, putBangDiemSinhVien);
    }

    @PostMapping("/{idSinhVien}/{idLopHocPhan}/diemdanh")
    public Integer diemDanh(@PathVariable("idSinhVien") Integer idSinhVien,
                            @PathVariable("idLopHocPhan") Integer idLopHocPhan,
                            @RequestBody PostDiemDanh postDiemDanh) {
        return donXinNghiHocService.diemDanh(idSinhVien, idLopHocPhan, postDiemDanh);
    }

    @GetMapping("/{idSinhVienLhp}/ngaynghis")
    public List<NgayNghiDto> getNgayNghis(@PathVariable Integer idSinhVienLhp) {
        return ngayNghiService.listNgayNghiDtoOfSinhVienLhp(idSinhVienLhp);
    }

    @DeleteMapping("/ngaynghis/{id}")
    public ResponseEntity xoaNgayNghi(@PathVariable Integer id) {
        return ngayNghiService.xoaNgayNghi(id);
    }


}
