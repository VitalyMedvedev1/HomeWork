package ru.liga.medvedev.services.impl;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.CommandService;

import java.util.Scanner;

@Component("ConsoleService")
public class InConsoleServiceImpl implements CommandService {

    private static final String MENU = "Ведите входные данные для прогноза курса валют, в формате: 'курс валюта период'. Для выхода введите 'exit'";
    private static final String EXIT = "exit";
//    public static final CommandLineParser COMMAND_LINE_PARSER = new CommandLineParser();


    @Override
    public Commands getCommands() {
        System.out.println(MENU);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String inputConsoleCommand = scanner.nextLine();
                if (!inputConsoleCommand.equals(EXIT)) {
                    Commands commands = Reference.COMMAND_LINE_PARSER.parse(inputConsoleCommand);
                    if (commands.getErrorMessage() != null) {
                        return commands;
                    } else {
                        System.out.println(commands.getErrorMessage());
                    }
                } else {
                    System.out.println("Exit");
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public Commands getCommands(String text) {
        throw new UnsupportedOperationException();
    }
}
