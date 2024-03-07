package com.mini.commute.repository.work;

import com.mini.commute.dto.work.WorkResponseByEmployee;

import java.time.LocalDate;
import java.util.List;

public interface WorkRepositoryUsingQD {
    List<WorkResponseByEmployee> findAllWorkingMinutes(Long id, LocalDate start, LocalDate end);
}
