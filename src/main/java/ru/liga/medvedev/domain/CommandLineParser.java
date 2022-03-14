package ru.liga.medvedev.domain;

import lombok.Data;
import ru.liga.medvedev.domain.Enum.RateAlgorithms;
import ru.liga.medvedev.domain.Enum.RateCurrencies;
import ru.liga.medvedev.domain.Enum.RatePeriods;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Data
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
            commands.setErrorMessage("Неверный формат строки, формат 'rate + USD/TRY/EUR + week/tomorrow', попробуйте ввести снова");
        } else {
            String currency = commandLineParts[COMMAND_CUR_INDEX];
            if (!isInEnum(currency, RateCurrencies.class)) {
                commands.setErrorMessage("Формат доступный валюты USD/TRY/EUR, попробуйте ввести снова");
            } else {
                if (!isInEnum(commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase(), RateAlgorithms.class)) {
                    commands.setErrorMessage("Формат доступный для названия алгоритмов: MOON, AVERAGE, LINER, ACTUAL");
                } else {
                    String period = commandLineParts[COMMAND_PERIOD_INDEX_FLAG].toUpperCase().replaceAll("\\W", "");
                    if (period.equals(RatePeriods.DATE.toString())) {
                        try {
                            commands.setLocalDate(LocalDate.parse(commandLineParts[COMMAND_PERIOD_DATE_INDEX], Reference.INPUT_DATE_FORMATTER));
                            commands.setCurrency(currency);
                            commands.setValidationFlg(true);
                            commands.setPeriod(period);
                            commands.setAlgorithmName(commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase());
                        } catch (DateTimeParseException e) {
                            commands.setErrorMessage("Неверны формат даты");
                        }
                    } else {
                        if (!isInEnum(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase(), RatePeriods.class)) {
                            commands.setErrorMessage("Формат периода week/tomorrow, попробуйте ввести снова");
                        } else {
                            commands.setPeriod(commandLineParts[COMMAND_PERIOD_DATE_INDEX].toUpperCase());
                            commands.setCurrency(currency);
                            commands.setAlgorithmName(commandLineParts[COMMAND_ALGORITHM_INDEX].toUpperCase());
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
