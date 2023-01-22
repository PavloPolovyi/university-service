package com.example.universityservice.service.impl;

import com.example.universityservice.dto.DepartmentSummaryStatistics;
import com.example.universityservice.repository.DepartmentRepository;
import com.example.universityservice.service.DepartmentService;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private static final Long MIN_EMPLOYEE_COUNT = 0L;
    private final DepartmentRepository departmentRepository;

    @Override
    public String getHeadName(String departmentName) {
        return departmentRepository.getNameOfHeadOfDepartment(departmentName)
                .orElse(StringUtils.EMPTY);
    }

    @Override
    public DepartmentSummaryStatistics getDepartmentSummaryStatistics(String departmentName) {
        return departmentRepository.getDepartmentSummaryStatistics(departmentName);
    }

    @Override
    public BigDecimal getAverageSalary(String departmentName) {
        return departmentRepository.getAverageSalaryOfDepartment(departmentName)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public Long getEmployeeCount(String departmentName) {
        return departmentRepository.getEmployeeCount(departmentName)
                .orElse(MIN_EMPLOYEE_COUNT);
    }
}
