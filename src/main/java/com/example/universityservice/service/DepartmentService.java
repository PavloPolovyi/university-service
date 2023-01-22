package com.example.universityservice.service;

import com.example.universityservice.dto.DepartmentSummaryStatistics;
import java.math.BigDecimal;

public interface DepartmentService {
    String getHeadName(String departmentName);

    DepartmentSummaryStatistics getDepartmentSummaryStatistics(String departmentName);

    BigDecimal getAverageSalary(String departmentName);

    Long getEmployeeCount(String departmentName);
}
