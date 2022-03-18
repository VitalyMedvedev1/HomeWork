package ru.liga.medvedev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.medvedev.domain.enums.RateAlgorithms;
import ru.liga.medvedev.domain.enums.RateCurrencies;
import ru.liga.medvedev.domain.enums.RateOutTypes;
import ru.liga.medvedev.domain.enums.RatePeriods;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    private static final int COMMAND_LENGTH = 6;
    private static final int COMMAND_LENGTH_MAX = 8;
    private static final int COMMAND_OUT_TYPE_INDEX = 7;

    private final String inCommand = "RATE";
    private String currency;
    private List<String> listCurrency = new ArrayList<>();
    private String period;
    private String errorMessage;
    private String algorithmName;
    private String outputType;
    private LocalDate localDate;

    public static class Builder implements CommandBuilder {
        private final Command inputCommand;

        public Builder() {
            this.inputCommand = new Command();
        }

        @Override
        public Builder validationLength(int commandLength, String rateCommand) throws RuntimeException {
            if (!((commandLength == COMMAND_LENGTH || commandLength == COMMAND_LENGTH_MAX) && rateCommand.equalsIgnoreCase(inputCommand.getInCommand()))) {
                throw new RuntimeException("Неверный формат строки!\n" +
                        "Доступный формат!\n" +
                        "'rate TRY -date 22.02.2030 -alg moon -output list'\n" +
                        "Попробуйте ввести снова");
            }
            return this;
        }

        @Override
        public Builder validationCurrency(String currency) {
            if (equalsInEnum(currency, RateCurrencies.class)) {
                throw new RuntimeException("Неверный формат валюты!\n" +
                        "Доступные валюты:\n " +
                        "USD, TRY, EUR, BGN, AMD\n" +
                        "Попробуйте ввести снова");
            } else {
                inputCommand.currency = currency;
            }
            return this;
        }

        @Override
        public Builder validationCurrencies(String currency) {
            Arrays.stream(currency.split(","))
                    .forEach(c -> {
                        if (equalsInEnum(c, RateCurrencies.class)) {
                            throw new RuntimeException("Неверный формат валюты!\n" +
                                    "Доступные валюты:\n " +
                                    "USD, TRY, EUR, BGN, AMD\n" +
                                    "Попробуйте ввести снова");
                        }
                        else {
                            inputCommand.getListCurrency().add(c);
                        }
                    });
            return this;
        }

        @Override
        public Builder validationOutType(String[] commandLineParts) {
            if (commandLineParts.length == COMMAND_LENGTH_MAX) {
                String outType = commandLineParts[COMMAND_OUT_TYPE_INDEX].toUpperCase();
                if (equalsInEnum(outType.toUpperCase(), RateOutTypes.class)) {
                    throw new RuntimeException("Неверный формат типа вывода!\n" +
                            "Доступные названия:\n" +
                            "LIST, GRAPH или без флага -output\n" +
                            "Попробуйте ввести снова");
                } else {
                    inputCommand.outputType = outType;
                }
            } else {
                inputCommand.outputType = RateOutTypes.DEFAULT.name();
            }
            return this;
        }

        @Override
        public Builder validationAlgorithmName(String algorithmName) {
            if (equalsInEnum(algorithmName.toUpperCase(), RateAlgorithms.class)) {
                throw new RuntimeException("Неверный формат алгоритма!\n" +
                        "Доступные названия алгоритмов:\n" +
                        "MOON, AVERAGE, LINER, ACTUAL\n" +
                        "Попробуйте ввести снова");
            } else {
                inputCommand.algorithmName = algorithmName;
            }
            return this;
        }

        @Override
        public Builder validationPeriod(String period, String date) {
            if (period.equals(RatePeriods.DATE.toString())) {
                try {
                    inputCommand.localDate = LocalDate.parse(date, Reference.INPUT_DATE_FORMATTER);
                    inputCommand.period = period;
                } catch (DateTimeParseException e) {
                    throw new RuntimeException("Неверный формат введенной даты!\n" +
                            "Доступный dd.MM.yyyy");
                }
            } else {
                period = date;
                if (equalsInEnum(period.toUpperCase(), RatePeriods.class)) {
                    throw new RuntimeException("Неверный формат периода!\n" +
                            "Формат периода:\n" +
                            "week/tomorrow/month\n" +
                            "Попробуйте ввести снова");
                } else {
                    inputCommand.period = period;
                }
            }
            return this;
        }

        public Command build() {
            return inputCommand;
        }
    }
}