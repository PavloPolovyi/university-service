package com.example.universityservice.command;

import com.example.universityservice.service.impl.LectorServiceImpl;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import picocli.CommandLine;

/*
Created getter for field for tests
 */

@Getter
@Service
@RequiredArgsConstructor
@CommandLine.Command(name = "lectors",
        aliases = "lec",
        mixinStandardHelpOptions = true,
        description = "Provides information about lectors.")
public class LectorCommand implements Runnable {
    @Getter(AccessLevel.NONE)
    private static final String HIGHLIGHT_ANSI_PATTERN = "@|bold,blue %s|@";
    @Getter(AccessLevel.NONE)
    private static final String PATTERN_TO_PRINT = "%s";
    @Getter(AccessLevel.NONE)
    private final LectorServiceImpl lectorService;
    @Getter(AccessLevel.NONE)
    private final Printer printer;

    @CommandLine.Option(names = {"-q", "--query"},
            required = true, description = "Global search by query.")
    private String query;

    @Override
    public void run() {
        String result = lectorService.findByNameLike(query);
        printer.print(formatResultOutput(result), PATTERN_TO_PRINT);
    }

    private String formatResultOutput(String result) {
        return CommandLine.Help.Ansi.AUTO
                .string(result.replaceAll(query, String.format(HIGHLIGHT_ANSI_PATTERN, query)));
    }
}
