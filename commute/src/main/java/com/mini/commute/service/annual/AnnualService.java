package com.mini.commute.service.annual;

import com.mini.commute.common.exception.CustomException;
import com.mini.commute.common.exception.ErrorCode;
import com.mini.commute.dto.annual.AnnualRemainResponse;
import com.mini.commute.entity.annual.Annual;
import com.mini.commute.entity.employee.Employee;
import com.mini.commute.repository.annual.AnnualRepository;
import com.mini.commute.repository.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class AnnualService {
    private final AnnualRepository annualRepository;
    private final EmployeeRepository employeeRepository;

    public AnnualService(AnnualRepository annualRepository, EmployeeRepository employeeRepository) {
        this.annualRepository = annualRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public AnnualRemainResponse useAnnual(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.EMPLOYEE_NOT_FOUND));
        Annual annual = annualRepository.findByEmployeeId(id);
        if(annual == null) {
            annual = new Annual(employee);
            employee.setAnnual(annual);
        }

        int remainAnnual = annual.getRemainAnnual();
        if(remainAnnual <= 0) {
            throw new CustomException(ErrorCode.REMAIN_ANNUAL_NOT_EXISTED);
        }

        annual.useAnnual();
        annualRepository.save(annual);
        return new AnnualRemainResponse(annual.getRemainAnnual());
    }
}
