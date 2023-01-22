package com.example.universityservice.command;

import com.example.universityservice.service.DepartmentService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picocli.CommandLine;

/*
Created getters for fields for tests
 */

@Getter
@Service
@RequiredArgsConstructor
@CommandLine.Command(name = "departments",
        aliases = "dep",
        mixinStandardHelpOptions = true,
        description = "Provides information about departments.")
public class DepartmentCommand implements Runnable {
    @Getter(AccessLevel.NONE)
    private static final String COMMON_ANSI_PATTERN = "@|bold,blue %s|@";
    @Getter(AccessLevel.NONE)
    private final DepartmentService departmentService;
    @Getter(AccessLevel.NONE)
    private final Printer printer;

    @CommandLine.Option(names = {"--head"},
            description = "Get name of head for specific department.",
            paramLabel = "DEPARTMENT_NAME")
    private String head;

    @CommandLine.Option(names = {"-st","--stats"},
            description = "Show department statistics "
            + "by employees academic degree.", paramLabel = "DEPARTMENT_NAME")
    private String statistics;

    @CommandLine.Option(names = {"-sal", "--salary"},
            description = "Show average salary in department.",
            paramLabel = "DEPARTMENT_NAME")
    private String averageSalary;

    @CommandLine.Option(names = {"-c", "--count"},
            description = "Show department employee count.",
            paramLabel = "DEPARTMENT_NAME")
    private String count;

    @Override
    public void run() {
        if (head != null) {
            printHead();
        }
        if (statistics != null) {
            printStatistics();
        }
        if (averageSalary != null) {
            printAverageSalary();
        }
        if (count != null) {
            printEmployeeCount();
        }
    }

    private void printHead() {
        printer.print(departmentService.getHeadName(head), COMMON_ANSI_PATTERN);
    }

    private void printStatistics() {
        printer.print(departmentService
                        .getDepartmentSummaryStatistics(statistics).toString(),
                COMMON_ANSI_PATTERN);
    }

    private void printAverageSalary() {
        printer.print(departmentService
                .getAverageSalary(averageSalary).toString(), COMMON_ANSI_PATTERN);
    }

    private void printEmployeeCount() {
        printer.print(departmentService.getEmployeeCount(count).toString(), COMMON_ANSI_PATTERN);
    }
}
