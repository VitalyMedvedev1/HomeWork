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

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Command {
    private static final int COMMAND_LENGTH = 6;
    private static final int COMMAND_LENGTH_MAX = 8;
    private static final int COMMAND_OUT_TYPE_INDEX = 7;

    private String currency;
    private List<String> listCurrency = new ArrayList<>();
    private String period;
    private String errorMessage;
    private String algorithmName;
    private String outputType;
    private LocalDate localDate;

    public static class Builder implements CommandBuilder {
        private final Command inputCommand;
        private static final String COMMAND_PATH_SPLITTER = ",";

        public Builder() {
            this.inputCommand = new Command();
        }

        @Override
        public Builder validationLength(int commandLength, String rateCommand) throws IllegalArgumentException {
            if (!((commandLength == COMMAND_LENGTH || commandLength == COMMAND_LENGTH_MAX) && rateCommand.equalsIgnoreCase(StaticParams.IN_COMMAND))) {
                throw new IllegalArgumentException(StaticParams.ERROR_LENGTH_MESSAGE);
            }
            return this;
        }

        @Override
        public Builder validationCurrencies(String currency) {
            Arrays.stream(currency.split(COMMAND_PATH_SPLITTER))
                    .forEach(c -> {
                        if (equalsInEnum(c, RateCurrencies.class)) {
                            throw new IllegalArgumentException(StaticParams.ERROR_CURRENCY_MESSAGE);
                        } else {
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
                    throw new IllegalArgumentException(StaticParams.ERROR_OUT_TYPE_MESSAGE);
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
                throw new IllegalArgumentException(StaticParams.ERROR_ALGORITHM_MESSAGE);
            } else {
                inputCommand.algorithmName = algorithmName;
            }
            return this;
        }

        @Override
        public Builder validationPeriod(String period, String date) {
            if (period.equals(RatePeriods.DATE.toString())) {
                try {
                    inputCommand.localDate = LocalDate.parse(date, StaticParams.INPUT_DATE_FORMATTER);
                    inputCommand.period = period;
                } catch (DateTimeParseException e) {
                    throw new IllegalArgumentException(StaticParams.ERROR_DATE_FORMAT_MESSAGE);
                }
            } else {
                period = date;
                if (equalsInEnum(period.toUpperCase(), RatePeriods.class)) {
                    throw new IllegalArgumentException(StaticParams.ERROR_PERIOD_MESSAGE);
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