package com.solienlac.khoaluan.web.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.solienlac.khoaluan.web.domain.DonXinNghiHoc;
import com.solienlac.khoaluan.web.domain.QDonXinNghiHoc;
import com.solienlac.khoaluan.web.repository.DonXinNghiHocCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class DonXinNghiHocCustomRepositoryImpl implements DonXinNghiHocCustomRepository {
    private QDonXinNghiHoc donXinNghiHoc = QDonXinNghiHoc.donXinNghiHoc;
    private final EntityManager em;
    @Override
    public Page<DonXinNghiHoc> listDonXinNghiHoc(Pageable pageable, Integer idGiangVien,Integer idLopHocPhan) {
        JPAQuery query = new JPAQueryFactory(em)
                .selectFrom(donXinNghiHoc).where(donXinNghiHoc.lopHocPhan.giangVien.id.eq(idGiangVien),
                        donXinNghiHoc.lopHocPhan.id.eq(idLopHocPhan))
                .orderBy(donXinNghiHoc.trangThai.asc())
                .offset(pageable.getPageNumber()*pageable.getPageSize()).limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }

    @Override
    public List<DonXinNghiHoc> listDonXinNghiHocOfSinhVien(Integer idSinhVien) {
        return new JPAQueryFactory(em)
                .selectFrom(donXinNghiHoc).where(donXinNghiHoc.sinhVien.id.eq(idSinhVien)).fetch();
    }
}
