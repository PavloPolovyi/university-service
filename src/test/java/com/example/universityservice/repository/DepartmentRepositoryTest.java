package com.example.universityservice.repository;

import com.example.universityservice.dto.DepartmentSummaryStatistics;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

// Data for tests is taken from db/changelog/version/mock-data.xml

@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DepartmentRepositoryTest {
    private static final String TEST_DEPARTMENT_NAME = "Biological Sciences Department";
    private static final String NOT_EXISTED_DEPARTMENT_NAME = "Chemistry Department";

    @Container
    private static final PostgreSQLContainer<?> dataBase =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:15-alpine"))
                    .withUsername("postgres")
                    .withPassword("postgres")
                    .withDatabaseName("university_db");
    @Autowired
    private DepartmentRepository repository;

    @DynamicPropertySource
    public static void setDataSourceProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.datasource.url", dataBase::getJdbcUrl);
        dynamicPropertyRegistry.add("spring.datasource.username", dataBase::getUsername);
        dynamicPropertyRegistry.add("spring.datasource.password", dataBase::getPassword);
        dynamicPropertyRegistry.add("spring.datasource.driver-class-name",
                dataBase::getDriverClassName);
    }

    @Test
    void test() {
        Assertions.assertTrue(dataBase.isRunning());
    }

    @Test
    public void shouldReturnCorrectHeadOfDepartment_Ok() {
        String expected = "John Dou";
        Optional<String> optional = repository.getNameOfHeadOfDepartment(TEST_DEPARTMENT_NAME);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(expected, optional.get());
    }

    @Test
    public void shouldReturnNoHeadOfDepartment_Ok() {
        Optional<String> optional = repository
                .getNameOfHeadOfDepartment(NOT_EXISTED_DEPARTMENT_NAME);
        Assertions.assertTrue(optional.isEmpty());
    }

    @Test
    public void shouldReturnCorrectAverageSalary_Ok() {
        BigDecimal expected = BigDecimal.valueOf(6640.0);
        Optional<BigDecimal> optional = repository
                .getAverageSalaryOfDepartment(TEST_DEPARTMENT_NAME);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(expected, optional.get());
    }

    @Test
    public void shouldReturnCorrectStatistics_Ok() {
        DepartmentSummaryStatistics expected =
                new DepartmentSummaryStatistics(3L, 1L, 1L);
        DepartmentSummaryStatistics actual = repository
                .getDepartmentSummaryStatistics(TEST_DEPARTMENT_NAME);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnCorrectEmployeeCount_Ok() {
        Long expected = 5L;
        Optional<Long> optional = repository.getEmployeeCount(TEST_DEPARTMENT_NAME);
        Assertions.assertTrue(optional.isPresent());
        Assertions.assertEquals(expected, optional.get());
    }
}
