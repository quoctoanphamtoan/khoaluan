package com.solienlac.khoaluan.web.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.solienlac.khoaluan.web.domain.BangDiem_SinhVien_MonHoc;
import com.solienlac.khoaluan.web.domain.QBangDiem_SinhVien_MonHoc;
import com.solienlac.khoaluan.web.repository.DiemCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class DiemCustomRepositoryImpl implements DiemCustomRepository {
    private final QBangDiem_SinhVien_MonHoc bangDiem_sinhVien_monHoc = QBangDiem_SinhVien_MonHoc.bangDiem_SinhVien_MonHoc;
    private final EntityManager em;

    @Override
    public List<BangDiem_SinhVien_MonHoc> listDiemSinhVien(Integer idSinhVien) {
        return new JPAQueryFactory(em)
                .selectFrom(bangDiem_sinhVien_monHoc)
                .where(bangDiem_sinhVien_monHoc.bangDiemTongKet.sinhVien.id.eq(idSinhVien))
                .fetch();
    }
}
