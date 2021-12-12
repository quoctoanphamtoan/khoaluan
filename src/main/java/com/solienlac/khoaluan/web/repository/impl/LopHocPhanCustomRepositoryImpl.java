package com.solienlac.khoaluan.web.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.solienlac.khoaluan.web.domain.LopHocPhan;
import com.solienlac.khoaluan.web.domain.QLopHocPhan;
import com.solienlac.khoaluan.web.repository.LopHocPhanCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class LopHocPhanCustomRepositoryImpl implements LopHocPhanCustomRepository {
    private final QLopHocPhan lopHocPhan = QLopHocPhan.lopHocPhan;
    private final EntityManager em;
    @Override
    public Page<LopHocPhan> getLopHocPhans(Integer idGiangVien, Pageable pageable) {
        JPAQuery<LopHocPhan> query = new JPAQueryFactory(em)
                .selectFrom(lopHocPhan)
                .where(lopHocPhan.giangVien.id.eq(idGiangVien))
                .offset(pageable.getPageNumber()*pageable.getPageSize()).limit(pageable.getPageSize());
        return PageableExecutionUtils.getPage(query.fetch(), pageable, query::fetchCount);
    }
}
