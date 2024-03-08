package com.mini.commute.service.annual;

import com.mini.commute.common.exception.CustomException;
import com.mini.commute.common.exception.ErrorCode;
import com.mini.commute.dto.annual.AnnualRemainResponse;
import com.mini.commute.entity.annual.Annual;
import com.mini.commute.entity.annual.AnnualUsage;
import com.mini.commute.entity.employee.Employee;
import com.mini.commute.repository.annual.AnnualRepository;
import com.mini.commute.repository.annual.AnnualUsageRepository;
import com.mini.commute.repository.employee.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
public class AnnualService {
    private final AnnualRepository annualRepository;
    private final EmployeeRepository employeeRepository;
    private final AnnualUsageRepository usageRepository;

    public AnnualService(AnnualRepository annualRepository, EmployeeRepository employeeRepository, AnnualUsageRepository usageRepository) {
        this.annualRepository = annualRepository;
        this.employeeRepository = employeeRepository;
        this.usageRepository = usageRepository;
    }

    @Transactional
    public AnnualRemainResponse useAnnual(Long id, LocalDate date) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.EMPLOYEE_NOT_FOUND));

        /*      해당 날짜에 연차를 신청할 수 있는지 확인
                1. 연차 신청 기간 내에 신청했는지
                2. 이미 신청했는지
        */
        AnnualUsage usage = usageRepository.findByEmployeeIdAndDate(id, date);
        if(!LocalDate.now().isBefore(date.minusDays(employee.getTeam().getAnnualRegisterPeriod()))) {
            throw new CustomException(ErrorCode.ANNUAL_REGISTRATION_DATE_HAS_PASSED);
        }
        if(usage != null) {
            throw new CustomException(ErrorCode.ANNUAL_ALREADY_USED);
        }
        usage = new AnnualUsage(date);
        usage.setEmployee(employee);

        Annual annual = annualRepository.findByEmployeeId(id);
        if(annual == null) {
            annual = new Annual(employee);
            annualRepository.save(annual);
            employee.setAnnual(annual);
        }

        int remainAnnual = annual.getRemainAnnual();
        if(remainAnnual <= 0) {
            throw new CustomException(ErrorCode.REMAIN_ANNUAL_NOT_EXISTED);
        }
        annual.useAnnual();

        usageRepository.save(usage);
        annualRepository.save(annual);

        return new AnnualRemainResponse(annual.getRemainAnnual());
    }
}
