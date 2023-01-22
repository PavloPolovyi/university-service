package com.example.universityservice;

import com.example.universityservice.command.DepartmentCommand;
import com.example.universityservice.command.LectorCommand;
import com.example.universityservice.command.UniversityServiceCommand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import picocli.CommandLine;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class UniversityServiceApplicationTests {
    @Autowired
    private CommandLine.IFactory factory;

    @Autowired
    private UniversityServiceCommand universityServiceCommand;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLectorsSubCommand_ok() {
        CommandLine.ParseResult parseResult = new CommandLine(universityServiceCommand, factory)
                .parseArgs("lec", "-q", "av");
        Assertions.assertTrue(parseResult.hasSubcommand());
        CommandLine.ParseResult subcommand = parseResult.subcommand();
        LectorCommand lectorCommand = (LectorCommand) subcommand.commandSpec().userObject();
        Assertions.assertEquals("av", lectorCommand.getQuery());
    }

    @Test
    public void testLectorsSubCommandEmptyQuery_notOk() {
        CommandLine.MissingParameterException exception =
                Assertions.assertThrows(CommandLine.MissingParameterException.class,
                    () -> new CommandLine(universityServiceCommand, factory)
                .parseArgs("lec", "-q"));
        Assertions.assertEquals("Missing required parameter for option '--query' (<query>)",
                exception.getMessage());
    }

    @Test
    public void testDepartmentsSubCommandHead_ok() {
        String testDepartmentName = "Biological Sciences Department";
        CommandLine.ParseResult parseResult = new CommandLine(universityServiceCommand, factory)
                .parseArgs("dep", "--head", testDepartmentName);
        Assertions.assertTrue(parseResult.hasSubcommand());
        CommandLine.ParseResult subcommand = parseResult.subcommand();
        DepartmentCommand departmentCommand =
                (DepartmentCommand) subcommand.commandSpec().userObject();
        Assertions.assertEquals(testDepartmentName, departmentCommand.getHead());
        Assertions.assertNull(departmentCommand.getCount());
        Assertions.assertNull(departmentCommand.getAverageSalary());
        Assertions.assertNull(departmentCommand.getStatistics());
    }

    @Test
    public void testDepartmentsSubCommandHead_notOk() {
        CommandLine.MissingParameterException exception =
                Assertions.assertThrows(CommandLine.MissingParameterException.class,
                    () -> new CommandLine(universityServiceCommand, factory)
                    .parseArgs("dep", "--head"));
        Assertions.assertEquals("Missing required parameter for option '--head' (DEPARTMENT_NAME)",
                exception.getMessage());
    }

    @Test
    public void testDepartmentsSubCommandCount_ok() {
        String testDepartmentName = "Biological Sciences Department";
        CommandLine.ParseResult parseResult = new CommandLine(universityServiceCommand, factory)
                .parseArgs("dep", "--count", testDepartmentName);
        Assertions.assertTrue(parseResult.hasSubcommand());
        CommandLine.ParseResult subcommand = parseResult.subcommand();
        DepartmentCommand departmentCommand =
                (DepartmentCommand) subcommand.commandSpec().userObject();
        Assertions.assertEquals(testDepartmentName, departmentCommand.getCount());
        Assertions.assertNull(departmentCommand.getHead());
        Assertions.assertNull(departmentCommand.getAverageSalary());
        Assertions.assertNull(departmentCommand.getStatistics());
    }

    @Test
    public void testDepartmentsSubCommandSalary_ok() {
        String testDepartmentName = "Biological Sciences Department";
        CommandLine.ParseResult parseResult = new CommandLine(universityServiceCommand, factory)
                .parseArgs("dep", "-sal", testDepartmentName);
        Assertions.assertTrue(parseResult.hasSubcommand());
        CommandLine.ParseResult subcommand = parseResult.subcommand();
        DepartmentCommand departmentCommand =
                (DepartmentCommand) subcommand.commandSpec().userObject();
        Assertions.assertEquals(testDepartmentName, departmentCommand.getAverageSalary());
        Assertions.assertNull(departmentCommand.getHead());
        Assertions.assertNull(departmentCommand.getCount());
        Assertions.assertNull(departmentCommand.getStatistics());
    }

    @Test
    public void testDepartmentsSubCommandSalaryStats_ok() {
        String testDepartmentName = "Biological Sciences Department";
        CommandLine.ParseResult parseResult = new CommandLine(universityServiceCommand, factory)
                .parseArgs("dep", "-st", testDepartmentName);
        Assertions.assertTrue(parseResult.hasSubcommand());
        CommandLine.ParseResult subcommand = parseResult.subcommand();
        DepartmentCommand departmentCommand =
                (DepartmentCommand) subcommand.commandSpec().userObject();
        Assertions.assertEquals(testDepartmentName, departmentCommand.getStatistics());
        Assertions.assertNull(departmentCommand.getHead());
        Assertions.assertNull(departmentCommand.getCount());
        Assertions.assertNull(departmentCommand.getAverageSalary());
    }
}
