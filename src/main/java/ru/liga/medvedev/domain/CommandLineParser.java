package ru.liga.medvedev.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Data
public class CommandLineParser {
    private static final String COMMAND_SPLITTER = " ";

    public Commands parse(String commandInputStr) {
        String[] commandLineParts = commandInputStr.trim().split(COMMAND_SPLITTER);
        return isValid(commandLineParts);
    }

    private Commands isValid(String[] commandLineParts) {
        Commands commands = new Commands();
        if (commandLineParts.length != 6 || !commandLineParts[0].equalsIgnoreCase(commands.getInCommand())) {
            commands.setErrorMessage("Неверный формат строки, формат 'rate + USD/TRY/EUR + week/tomorrow', попробуйте ввести снова");
        }
        else {
            String currency = commandLineParts[1];
            if (!isInEnum(currency, RateCurrency.class)) {
                commands.setErrorMessage("Формат доступный валюты USD/TRY/EUR, попробуйте ввести снова");
            }
            else {
                String period = commandLineParts[2].toUpperCase().replaceAll("\\W", "");
                if (period.equals(RatePeriod.DATE.toString())) {
                    try {
                        commands.setLocalDate(LocalDate.parse(commandLineParts[3], Reference.INPUT_DATE_FORMATTER));
                        commands.setCurrency(currency);
                        commands.setValidationFlg(true);
                        commands.setPeriod(period);
                    } catch (DateTimeParseException e) {
                        commands.setErrorMessage("Неверны формат даты");
                    }
                }
                else {
                    if (!isInEnum(commandLineParts[3].toUpperCase(), RatePeriod.class)) {
                        commands.setErrorMessage("Формат периода week/tomorrow, попробуйте ввести снова");
                    }
                    else {
                        commands.setPeriod(commandLineParts[3].toUpperCase());
                        commands.setCurrency(currency);
                        commands.setValidationFlg(true);
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
