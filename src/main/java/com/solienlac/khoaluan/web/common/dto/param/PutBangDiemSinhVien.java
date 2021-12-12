package com.solienlac.khoaluan.web.common.dto.param;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PutBangDiemSinhVien {
    private float tk1;
    private float tk2;
    private float tk3;
    private float gk;
    private float ck;
}
