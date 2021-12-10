package com.solienlac.khoaluan.web.common.dto.param;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostSmsCanhBao {
    private String tieuDe;
    private String noiDung;
    private Integer idSinhVien;
    private Integer idGiangVien;
}
