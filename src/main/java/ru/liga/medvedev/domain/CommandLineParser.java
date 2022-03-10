package ru.liga.medvedev.domain;

import lombok.Data;

@Data
public class CommandLineParser {
    private Commands commands = new Commands();
    private static final String CONSOLE_SPLITTER = " ";

    public Commands parse(String commandInputStr) {
        String[] commandLineParts = commandInputStr.trim().split(CONSOLE_SPLITTER);
        isValid(commandLineParts);
        return commands;
    }

    private boolean isValid(String[] commandLineParts) {
        if (commandLineParts.length != 3 || !commandLineParts[0].equalsIgnoreCase(commands.getInCommand())) {
            commands.setErrorMessage("Неверный формат строки, формат 'rate + USD/TRY/EUR + week/tomorrow', попробуйте ввести снова");
            return false;
        }
        String currency = commandLineParts[1];
        if (!isInEnum(currency, RateCurrency.class)) {
            commands.setErrorMessage("Формат доступный валюты USD/TRY/EUR, попробуйте ввести снова");
            return false;
        }
        String period = commandLineParts[2];
        if (!isInEnum(period, RatePeriod.class)) {
            commands.setErrorMessage("Формат периода week/tomorrow, попробуйте ввести снова");
            return false;
        }
        commands.setCurrency(currency);
        commands.setPeriod(period);
        commands.setValidationFlg(true);
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
