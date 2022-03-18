package ru.liga.medvedev.services.impl.inputcommad;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.CommandService;

import java.util.Scanner;

@Slf4j
@Component("InConsoleService")
public class InConsoleServiceImpl implements CommandService {

    private static final String MENU = "Ведите входные данные для прогноза курса валют, в формате: 'курс валюта период'. Для выхода введите 'exit'";
    private static final String EXIT = "exit";

    @Override
    public Command getCommands() {
        log.debug("Чтение команды с консоли");
        System.out.println(MENU);
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                String inputConsoleCommand = scanner.nextLine();
                log.debug("Команды с консоли" + inputConsoleCommand);
                if (!inputConsoleCommand.equals(EXIT)) {
                    Command command = Reference.COMMAND_LINE_PARSER.createCommand(inputConsoleCommand);
                    if (command.getErrorMessage() != null) {
                        log.debug("Команды с консоли обработана" + command);
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