package com.solienlac.khoaluan.web.service;

import com.solienlac.khoaluan.web.common.dto.GetLop;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public interface LopService {
    GetLop getLop(Pageable pageable,Integer idGiangVien);
}
