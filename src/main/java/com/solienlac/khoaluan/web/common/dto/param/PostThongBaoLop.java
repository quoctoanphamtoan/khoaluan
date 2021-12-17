package com.solienlac.khoaluan.web.common.dto.param;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostThongBaoLop {
    private String tieuDe;
    private String noiDung;
}
