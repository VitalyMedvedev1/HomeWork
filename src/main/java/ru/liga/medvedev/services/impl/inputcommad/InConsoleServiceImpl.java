package ru.liga.medvedev.services.impl.inputcommad;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.CommandService;

import java.util.Scanner;

@Component("ConsoleService")
public class InConsoleServiceImpl implements CommandService {

    private static final String MENU = "Ведите входные данные для прогноза курса валют, в формате: 'курс валюта период'. Для выхода введите 'exit'";
    private static final String EXIT = "exit";
//    public static final CommandLineParser COMMAND_LINE_PARSER = new CommandLineParser();


    @Override
    public Command getCommands() {
        System.out.println(MENU);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String inputConsoleCommand = scanner.nextLine();
                if (!inputConsoleCommand.equals(EXIT)) {
                    Command command = Reference.COMMAND_LINE_PARSER.parse(inputConsoleCommand);
                    if (command.getErrorMessage() != null) {
                        return command;
                    } else {
                        System.out.println(command.getErrorMessage());
                    }
                } else {
                    System.out.println("Exit");
                    System.exit(0);
                }
            }
        }
    }

    @Override
    public Command getCommands(String text) {
        throw new UnsupportedOperationException();
    }
}
