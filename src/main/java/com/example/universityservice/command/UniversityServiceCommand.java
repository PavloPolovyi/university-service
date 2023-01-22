package com.example.universityservice.command;

import org.springframework.stereotype.Service;
import picocli.CommandLine;

@Service
@CommandLine.Command(headerHeading = "@|bold,blue   _   _       "
        + "_                    _ _         ____                  _          \n"
        + " | | | |_ __ (_)_   _____ _ __ ___(_) |_ _   _/ ___|  ___ _ ____   _(_) ___ ___ \n"
        + " | | | | '_ \\| \\ \\ / / _ \\ '__/ __| | __| | | \\___ \\ / _ \\ '__"
        + "\\ \\ / / |/ __/ _ \\\n"
        + " | |_| | | | | |\\ V /  __/ |  \\__ \\ | |_| |_| |___) |  __/ |   \\ V"
        + " /| | (_|  __/\n"
        + "  \\___/|_| |_|_| \\_/ \\___|_|  |___/_|\\__|\\__, |____/ \\___|_|    \\_/ "
        + "|_|\\___\\___|\n"
        + "                                         |___/              |@\n\n\n",
        scope = CommandLine.ScopeType.INHERIT,
        description = """
                This is the console interface for university, which consists
                of departments and lectors. You can get info about
                departments and find lectors""",
        mixinStandardHelpOptions = true,
        version = "University Service 1.0",
        subcommands = {LectorCommand.class, DepartmentCommand.class},
        footer = """
                Copyright
                (c) Copyright by the Pavlo Polovyi""")
public class UniversityServiceCommand implements Runnable {
    @Override
    public void run() {
    }
}
