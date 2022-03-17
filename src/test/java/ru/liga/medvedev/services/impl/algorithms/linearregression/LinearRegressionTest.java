package ru.liga.medvedev.services.impl.algorithms.linearregression;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.LinearRegressionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class LinearRegressionTest {

    List<Rate> listRate = new ArrayList<>();
    RateAlgorithmService linearRegressionService = new LinearRegressionServiceImpl();

    @Test
    public void generateRate() {
        Command command = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -Date 13.03.2022 -alg moon");
        List<List<String>> list = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command);
        listRate = SpringConfiguration.RATE_DATA_MAPPER.mapRate(list);

        linearRegressionService.generateStatisticRateCurrency(listRate, command);

    }

    public Double getDayPlusWeek(LocalDate localDate) {
        String day = String.valueOf(localDate.getDayOfMonth());
        Double d = Double.parseDouble(localDate.getMonthValue() + String.valueOf(localDate.getDayOfMonth()));
        return Double.parseDouble(localDate.getMonthValue() + String.valueOf(localDate.getDayOfMonth()));
    }
}