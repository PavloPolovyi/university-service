package com.example.universityservice.repository;

import com.example.universityservice.dto.DepartmentSummaryStatistics;
import com.example.universityservice.model.Department;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT h.name FROM Department d JOIN d.head h WHERE d.name = :departmentName")
    Optional<String> getNameOfHeadOfDepartment(String departmentName);

    @Query("SELECT AVG(l.salary) FROM Department d JOIN d.lectors l WHERE d.name = :departmentName")
    Optional<BigDecimal> getAverageSalaryOfDepartment(String departmentName);

    @Query("SELECT new com.example.universityservice.dto.DepartmentSummaryStatistics("
            + "SUM(CASE WHEN l.degree.degreeName = com.example"
            + ".universityservice.model.AcademicDegree.ASSISTANT THEN 1 ELSE 0 END) "
            + "as assistantsCount, "
            + "SUM(CASE WHEN l.degree.degreeName = com.example"
            + ".universityservice.model.AcademicDegree.ASSOCIATIVE_PROFESSOR THEN 1 ELSE 0 END) "
            + "as associateProfessorsCount, "
            + "SUM(CASE WHEN l.degree.degreeName = com.example"
            + ".universityservice.model.AcademicDegree.PROFESSOR THEN 1 ELSE 0 END) "
            + "as professorsCount)"
            + " FROM Department d JOIN d.lectors l "
            + "WHERE d.name = :departmentName")
    DepartmentSummaryStatistics getDepartmentSummaryStatistics(String departmentName);

    @Query("SELECT COUNT(l) FROM Department d JOIN d.lectors l WHERE d.name = :departmentName")
    Optional<Long> getEmployeeCount(String departmentName);
}
