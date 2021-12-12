package com.solienlac.khoaluan.web.common.dto;

import com.solienlac.khoaluan.web.common.page.PaginationMeta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetLopHocPhan {
    private List<LopHocPhanDto> lopHocPhanDtos;
    private PaginationMeta paginationMeta;

}
