package com.solienlac.khoaluan.web.common.dto.param;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class XinNghiHocParam {
    private Integer id;
    private Date ngayNghi;
    private String lyDo;
}
