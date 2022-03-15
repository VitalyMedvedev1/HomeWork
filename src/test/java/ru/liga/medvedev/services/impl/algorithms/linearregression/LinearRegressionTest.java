package ru.liga.medvedev.services.impl.algorithms.linearregression;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.LinearRegressionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class LinearRegressionTest {

    List<Rate> listRate = new ArrayList<>();
    RateAlgorithmService linearRegressionService = new LinearRegressionServiceImpl();

    @Test
    public void generateRate() {
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -Date 13.03.2022 -alg moon");
        List<List<String>> list = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(commands);
        listRate = SpringConfiguration.RATE_DATA_MAPPER.mapRate(list);

        linearRegressionService.generateStatisticRateCurrency(listRate, commands);

    }

    public Double getDayPlusWeek(LocalDate localDate) {
        String day = String.valueOf(localDate.getDayOfMonth());
        Double d = Double.parseDouble(localDate.getMonthValue() + String.valueOf(localDate.getDayOfMonth()));
        return Double.parseDouble(localDate.getMonthValue() + String.valueOf(localDate.getDayOfMonth()));
    }
}