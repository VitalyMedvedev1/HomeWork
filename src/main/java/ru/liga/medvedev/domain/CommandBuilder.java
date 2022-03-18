package ru.liga.medvedev.domain;

public interface CommandBuilder {
    Command.Builder validationLength(int commandLength, String rateCommand);

    Command.Builder validationCurrencies(String currency);

    Command.Builder validationOutType(String[] commandLineParts);

    Command.Builder validationAlgorithmName(String algorithmName);

    Command.Builder validationPeriod(String period, String date);

    default <E extends Enum<E>> boolean equalsInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return false;
            }
        }
        return true;
    }
}