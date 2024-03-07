package com.mini.commute.repository.work;

import com.mini.commute.dto.work.WorkResponseByEmployee;
import com.mini.commute.entity.work.QWork;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class WorkRepositoryUsingQDImpl implements WorkRepositoryUsingQD {
    private final JPAQueryFactory jpaQueryFactory;

    public WorkRepositoryUsingQDImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<WorkResponseByEmployee> findAllWorkingMinutes(Long id, LocalDate start, LocalDate end) {
        QWork qWork = QWork.work;
        return jpaQueryFactory
                .selectFrom(qWork)
                .where(qWork.employee.id.eq(id), qWork.isArrived.eq(false), qWork.date.between(start, end))
                .orderBy(qWork.date.asc())
                .fetch()
                .stream().map(WorkResponseByEmployee::new).collect(Collectors.toList());
    }
}
