package ru.liga.medvedev.domain;

import ru.liga.medvedev.domain.enums.RateAlgorithms;
import ru.liga.medvedev.domain.enums.RateCurrencies;
import ru.liga.medvedev.domain.enums.RateOutTypes;
import ru.liga.medvedev.domain.enums.RatePeriods;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class CommandLineParser {
    private static final String COMMAND_SPLITTER = " ";
    private static final int COMMAND_LENGTH = 6;
    private static final int COMMAND_LENGTH_MAX = 8;
    private static final int COMMAND_RATE_INDEX = 0;
    private static final int COMMAND_CUR_INDEX = 1;
    private static final int COMMAND_PERIOD_INDEX_FLAG = 2;
    private static final int COMMAND_PERIOD_DATE_INDEX = 3;
    private static final int COMMAND_ALGORITHM_INDEX = 5;
    private static final int COMMAND_OUT_TYPE_INDEX = 7;

    public Command parse(String commandInputStr) {
        String[] commandLineParts = commandInputStr.trim().split(COMMAND_SPLITTER);
//        Command command = new Command();

        /*if (validationLength(commands, commandLineParts)) {
            if (validationCurrency(commands, commandLineParts)) {
                if (validationOutType(commands, commandLineParts)) {
                    if (validationAlgorithmName(commands, commandLineParts)) {
                        validationPeriod(commands, commandLineParts);
                    }
                }
            }
        }*/
//        Optional outType = Optional.ofNullable(commandLineParts[COMMAND_OUT_TYPE_INDEX]);
        try {
            Command command = new Command.Builder()
                    .validationLength(commandLineParts.length, commandLineParts[COMMAND_RATE_INDEX].toUpperCase())
                    .validationCurrency(commandLineParts[COMMAND_CUR_INDEX].toUpperCase())
                    .validationAlgorithmName(commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase())
                    .validationPeriod(commandLineParts[COMMAND_PERIOD_INDEX_FLAG].toUpperCase().replaceAll("\\W", ""), commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase())
                    .validationOutType(commandLineParts)
                    .build();
            return command;
        }
        catch (RuntimeException e){
            Command command = new Command();
            command.setErrorMessage(e.getMessage());
            return command;
        }
    }

    private boolean validationLength(Command command, String[] commandLineParts) {
        if (!((commandLineParts.length == COMMAND_LENGTH || commandLineParts.length == COMMAND_LENGTH_MAX) && commandLineParts[COMMAND_RATE_INDEX].equalsIgnoreCase(command.getInCommand()))) {
            command.setErrorMessage("Неверный формат строки!\n" +
                    "Доступный формат!\n" +
                    "'rate TRY -date 22.02.2030 -alg moon'\n" +
                    "Попробуйте ввести снова");
            return false;
        }
        return true;
    }

    private boolean validationCurrency(Command command, String[] commandLineParts) {
        String currency = commandLineParts[COMMAND_CUR_INDEX].toUpperCase();
        if (equalsInEnum(currency, RateCurrencies.class)) {
            command.setErrorMessage("Неверный формат валюты!\n" +
                    "Доступные валюты:\n " +
                    "USD, TRY, EUR, BGN, AMD\n" +
                    "Попробуйте ввести снова");
            return false;
        } else {
            command.setCurrency(currency);
        }
        return true;
    }

    private boolean validationOutType(Command command, String[] commandLineParts) {
        if (commandLineParts.length == COMMAND_LENGTH_MAX) {
            String outType = commandLineParts[COMMAND_OUT_TYPE_INDEX].toUpperCase();
            if (equalsInEnum(outType.toUpperCase(), RateOutTypes.class)) {
                command.setErrorMessage("Неверный формат типа вывода!\n" +
                        "Доступные названия:\n" +
                        "LIST, GRAPH или без флага -output\n" +
                        "Попробуйте ввести снова");
                return false;
            }
            command.setOutputType(outType);
        } else {
            command.setOutputType(RateOutTypes.DEFAULT.name());
        }
        return true;
    }

    private boolean validationAlgorithmName(Command command, String[] commandLineParts) {
        String algorithmName = commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase();
        if (equalsInEnum(algorithmName.toUpperCase(), RateAlgorithms.class)) {
            command.setErrorMessage("Неверный формат алгоритма!\n" +
                    "Доступные названия алгоритмов:\n" +
                    "MOON, AVERAGE, LINER, ACTUAL\n" +
                    "Попробуйте ввести снова");
            return false;
        } else {
            command.setAlgorithmName(algorithmName);
        }
        return true;
    }

    private void validationPeriod(Command command, String[] commandLineParts) {
        String period = commandLineParts[COMMAND_PERIOD_INDEX_FLAG].toUpperCase().replaceAll("\\W", "");
        if (period.equals(RatePeriods.DATE.toString())) {
            try {
                command.setLocalDate(LocalDate.parse(commandLineParts[COMMAND_PERIOD_DATE_INDEX], Reference.INPUT_DATE_FORMATTER));
                command.setPeriod(period);
            } catch (DateTimeParseException e) {
                command.setErrorMessage("Неверный формат даты!");
            }
        } else {
            if (equalsInEnum(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase(), RatePeriods.class)) {
                command.setErrorMessage("Неверный формат периода!\n" +
                        "Формат периода:\n" +
                        "week/tomorrow/month\n" +
                        "Попробуйте ввести снова");
            } else {
                command.setPeriod(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase());
            }
        }
    }

    private static <E extends Enum<E>> boolean equalsInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return false;
            }
        }
        return true;
    }
}
