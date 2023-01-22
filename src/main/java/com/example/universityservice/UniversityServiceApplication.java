package com.example.universityservice;

import com.example.universityservice.command.UniversityServiceCommand;
import lombok.RequiredArgsConstructor;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import picocli.CommandLine;

@RequiredArgsConstructor
@SpringBootApplication
public class UniversityServiceApplication implements CommandLineRunner {
    private final UniversityServiceCommand universityServiceCommand;
    private final CommandLine.IFactory factory;

    public static void main(String[] args) {
        AnsiConsole.systemInstall();
        System.exit(SpringApplication
                .exit(SpringApplication
                        .run(UniversityServiceApplication.class, args)));
        AnsiConsole.systemUninstall();
    }

    @Override
    public void run(String... args) {
        new CommandLine(universityServiceCommand, factory).execute(args);
    }
}
