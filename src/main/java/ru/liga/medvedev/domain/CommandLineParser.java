package ru.liga.medvedev.domain;

import ru.liga.medvedev.domain.Enum.RateAlgorithms;
import ru.liga.medvedev.domain.Enum.RateCurrencies;
import ru.liga.medvedev.domain.Enum.RateOutTypes;
import ru.liga.medvedev.domain.Enum.RatePeriods;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    public Commands parse(String commandInputStr) {
        String[] commandLineParts = commandInputStr.trim().split(COMMAND_SPLITTER);
        Commands commands = new Commands();

        if (validationLength(commands, commandLineParts)){
            if (validationCurrency(commands, commandLineParts)){
                if (validationOutType(commands, commandLineParts)){
                    if (validationAlgorithmName(commands, commandLineParts)){
                        if (validationPeriod(commands, commandLineParts)){
                        }
                    }
                }
            }
        }
        return commands;
    }


    private boolean validationLength(Commands commands, String[] commandLineParts) {
        if (!((commandLineParts.length == COMMAND_LENGTH || commandLineParts.length == COMMAND_LENGTH_MAX) && commandLineParts[COMMAND_RATE_INDEX].equalsIgnoreCase(commands.getInCommand()))) {
            commands.setErrorMessage("Неверный формат строки!\n" +
                    "Доступный формат!\n" +
                    "'rate TRY -date 22.02.2030 -alg moon'\n" +
                    "Попробуйте ввести снова");
            return false;
        }
        return true;
    }

    private boolean validationCurrency(Commands commands, String[] commandLineParts) {
        String currency = commandLineParts[COMMAND_CUR_INDEX].toUpperCase();
        if (!isInEnum(currency, RateCurrencies.class)) {
            commands.setErrorMessage("Неверный формат валюты!\n" +
                    "Доступные валюты:\n " +
                    "USD, TRY, EUR, BGN, AMD\n" +
                    "Попробуйте ввести снова");
            return false;
        }
        else {
            commands.setCurrency(currency);
        }
        return true;
    }

    private boolean validationOutType(Commands commands, String[] commandLineParts) {
        if (commandLineParts.length == COMMAND_LENGTH_MAX){
            String outType = commandLineParts[COMMAND_OUT_TYPE_INDEX].toUpperCase();
            if (!isInEnum(outType.toUpperCase(), RateOutTypes.class)){
                commands.setErrorMessage("Неверный формат типа вывода!\n" +
                        "Доступные названия:\n" +
                        "LIST, GRAPH или без флага -output\n" +
                        "Попробуйте ввести снова");
                return false;
            }
            commands.setOutputType(outType);
        }
        else {
            commands.setOutputType(RateOutTypes.DEFAULT.name());
        }
        return true;
    }

    private boolean validationAlgorithmName(Commands commands, String[] commandLineParts) {
        String algorithmName = commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase();
        if (!isInEnum(algorithmName.toUpperCase(), RateAlgorithms.class)) {
            commands.setErrorMessage("Неверный формат алгоритма!\n" +
                    "Доступные названия алгоритмов:\n" +
                    "MOON, AVERAGE, LINER, ACTUAL\n" +
                    "Попробуйте ввести снова");
            return false;
        }
        else {
            commands.setAlgorithmName(algorithmName);
        }
        return true;
    }

    private boolean validationPeriod(Commands commands, String[] commandLineParts) {
        String period = commandLineParts[COMMAND_PERIOD_INDEX_FLAG].toUpperCase().replaceAll("\\W", "");
        if (period.equals(RatePeriods.DATE.toString())) {
            try {
                commands.setLocalDate(LocalDate.parse(commandLineParts[COMMAND_PERIOD_DATE_INDEX], Reference.INPUT_DATE_FORMATTER));
                commands.setPeriod(period);
            } catch (DateTimeParseException e) {
                commands.setErrorMessage("Неверный формат даты!");
                return false;
            }
        } else {
            if (!isInEnum(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase(), RatePeriods.class)) {
                commands.setErrorMessage("Неверный формат периода!\n" +
                        "Формат периода:\n" +
                        "week/tomorrow/month\n" +
                        "Попробуйте ввести снова");
                return false;
            } else {
                commands.setPeriod(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase());
            }
        }
        return true;
    }

    private static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
