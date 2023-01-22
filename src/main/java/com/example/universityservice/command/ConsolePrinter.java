package com.example.universityservice.command;

import org.springframework.stereotype.Component;
import picocli.CommandLine;

@Component
public class ConsolePrinter implements Printer {
    private static final String PREFIX = "Answer: ";

    @Override
    public void print(String string, String ansiPattern) {
        String result = CommandLine.Help.Ansi.AUTO.string(String.format(ansiPattern, string));
        System.out.println(PREFIX + result);
    }
}
