package ru.liga.medvedev.domain;

import ru.liga.medvedev.domain.Enum.RateAlgorithms;
import ru.liga.medvedev.domain.Enum.RateCurrencies;
import ru.liga.medvedev.domain.Enum.RatePeriods;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandLineParser {
    private static final String COMMAND_SPLITTER = " ";
    private static final int COMMAND_LENGTH = 6;
    private static final int COMMAND_RATE_INDEX = 0;
    private static final int COMMAND_CUR_INDEX = 1;
    private static final int COMMAND_PERIOD_INDEX_FLAG = 2;
    private static final int COMMAND_PERIOD_DATE_INDEX = 3;
    private static final int COMMAND_ALGORITHM_INDEX = 5;

    public Commands parse(String commandInputStr) {
        String[] commandLineParts = commandInputStr.trim().split(COMMAND_SPLITTER);
        return isValid(commandLineParts);
    }

    private Commands isValid(String[] commandLineParts) {
        Commands commands = new Commands();
        if (commandLineParts.length != COMMAND_LENGTH || !commandLineParts[COMMAND_RATE_INDEX].equalsIgnoreCase(commands.getInCommand())) {
            commands.setErrorMessage("Неверный формат строки!\n" +
                    "Доступный формат!\n" +
                    "'rate TRY -date 22.02.2030 -alg moon'\n" +
                    "Попробуйте ввести снова");
        } else {
            String currency = commandLineParts[COMMAND_CUR_INDEX];
            if (!isInEnum(currency, RateCurrencies.class)) {
                commands.setErrorMessage("Неверный формат валюты!\n" +
                        "Доступные валюты:\n " +
                        "USD, TRY, EUR, BGN, AMD\n" +
                        "Попробуйте ввести снова");
            } else {
                String algorithmName = commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase();
                if (!isInEnum(algorithmName.toUpperCase(), RateAlgorithms.class)) {
                    commands.setErrorMessage("Неверный формат алгоритма!\n" +
                            "Доступные названия алгоритмов:\n" +
                            "MOON, AVERAGE, LINER, ACTUAL\n" +
                            "Попробуйте ввести снова");
                } else {
                    String period = commandLineParts[COMMAND_PERIOD_INDEX_FLAG].toUpperCase().replaceAll("\\W", "");
                    if (period.equals(RatePeriods.DATE.toString())) {
                        try {
                            commands.setLocalDate(LocalDate.parse(commandLineParts[COMMAND_PERIOD_DATE_INDEX], Reference.INPUT_DATE_FORMATTER));
                            commands.setCurrency(currency);
                            commands.setValidationFlg(true);
                            commands.setPeriod(period);
                            commands.setAlgorithmName(algorithmName);
                        } catch (DateTimeParseException e) {
                            commands.setErrorMessage("Неверный формат даты!");
                        }
                    } else {
                        if (!isInEnum(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase(), RatePeriods.class)) {
                            commands.setErrorMessage("Неверный формат периода!\n" +
                                    "Формат периода:\n" +
                                    "week/tomorrow/month\n" +
                                    "Попробуйте ввести снова");
                        } else {
                            commands.setPeriod(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase());
                            commands.setCurrency(currency);
                            commands.setAlgorithmName(algorithmName);
                            commands.setValidationFlg(true);
                        }
                    }
                }
            }
        }
        return commands;
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
