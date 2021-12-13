package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.NgayNghiDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NgayNghiService {
    List<NgayNghiDto> listNgayNghiDtoOfSinhVienLhp(Integer idSinhVienLhp);
    ResponseEntity<Void> xoaNgayNghi(Integer idNgayNghi);
}
