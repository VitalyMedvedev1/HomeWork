package ru.liga.medvedev.services.impl;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.CommandLineParser;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.services.CommandService;

import java.util.Scanner;

@Component("ConsoleService")
public class InConsoleServiceImpl implements CommandService {

    private static final String MENU = "Ведите входные данные для прогноза курса валют, в формате: 'курс валюта период'. Для выхода введите 'exit'";
    private static final String EXIT = "exit";
    private static final CommandLineParser commandLineParser = new CommandLineParser();
    private static Commands commands = new Commands();


    @Override
    public Commands getCommands() {
        System.out.println(MENU);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String inputConsoleCommand = scanner.nextLine();
                if (!inputConsoleCommand.equals(EXIT)) {
                    commands = commandLineParser.parse(inputConsoleCommand);
                    if (commands.isValidationFlg()) {
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
}
