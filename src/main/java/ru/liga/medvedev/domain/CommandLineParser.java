package ru.liga.medvedev.domain;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandLineParser {
    private static final String COMMAND_SPLITTER = " ";
    private static final int COMMAND_RATE_INDEX = 0;
    private static final int COMMAND_CUR_INDEX = 1;
    private static final int COMMAND_PERIOD_INDEX_FLAG = 2;
    private static final int COMMAND_PERIOD_DATE_INDEX = 3;
    private static final int COMMAND_ALGORITHM_INDEX = 5;

    public Command createCommand(String commandInputStr) {
        log.debug("Парсинг и создание команды из строки");
        String[] commandLineParts = commandInputStr.trim().split(COMMAND_SPLITTER);
        try {
            return new Command.Builder()
                    .validationLength(commandLineParts.length, commandLineParts[COMMAND_RATE_INDEX].toUpperCase())
//                    .validationCurrency(commandLineParts[COMMAND_CUR_INDEX].toUpperCase())
                    .validationCurrencies(commandLineParts[COMMAND_CUR_INDEX].toUpperCase())
                    .validationAlgorithmName(commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase())
                    .validationPeriod(commandLineParts[COMMAND_PERIOD_INDEX_FLAG].toUpperCase().replaceAll("\\W", ""), commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase())
                    .validationOutType(commandLineParts)
                    .build();
        } catch (RuntimeException e) {
            Command command = new Command();
            command.setErrorMessage(e.getMessage());
            return command;
        }
    }
}
