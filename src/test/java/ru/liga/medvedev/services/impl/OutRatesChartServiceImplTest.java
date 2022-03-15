package ru.liga.medvedev.services.impl;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.ActualAlgorithmImpl;
import ru.liga.medvedev.services.impl.algorithms.LinearRegressionServiceImpl;
import ru.liga.medvedev.services.impl.algorithms.MoonAlgorithmImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OutRatesChartServiceImplTest {
    List<Rate> listRate = new ArrayList<>();
    RateAlgorithmService linearRegressionService = new LinearRegressionServiceImpl();
    RateAlgorithmService moonAlgorithm = new MoonAlgorithmImpl();
    RateAlgorithmService actualAlgorithm = new ActualAlgorithmImpl();
    OutRatesChartServiceImpl outRatesChartService = new OutRatesChartServiceImpl();

    @Test
    public void generateRate() {
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -Date 17.03.2022 -alg moon");
        List<List<String>> list = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(commands);
        listRate = SpringConfiguration.RATE_DATA_MAPPER.mapRate(list);
        listRate = actualAlgorithm.generateStatisticRateCurrency(listRate, commands);
        outRatesChartService.outRateStatistic(commands, listRate);


        System.out.println(listRate);
    }
}