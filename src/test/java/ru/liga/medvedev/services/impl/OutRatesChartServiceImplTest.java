package ru.liga.medvedev.services.impl;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.SpringConfiguration;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.ActualAlgorithmImpl;
import ru.liga.medvedev.services.impl.algorithms.LinearRegressionServiceImpl;
import ru.liga.medvedev.services.impl.algorithms.MoonAlgorithmImpl;
import ru.liga.medvedev.services.impl.outtypes.OutRatesChartServiceImpl;

import java.util.ArrayList;
import java.util.List;

class OutRatesChartServiceImplTest {
    List<Rate> listRate = new ArrayList<>();
    RateAlgorithmService linearRegressionService = new LinearRegressionServiceImpl();
    RateAlgorithmService moonAlgorithm = new MoonAlgorithmImpl();
    RateAlgorithmService actualAlgorithm = new ActualAlgorithmImpl();
    OutRatesChartServiceImpl outRatesChartService = new OutRatesChartServiceImpl();

/*    @Test
    public void generateRate() {
        Command command = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -Date 17.03.2022 -alg moon");
        List<List<String>> list = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command.getCurrency());
        listRate = SpringConfiguration.RATE_DATA_MAPPER.mapRate(list);
        listRate = actualAlgorithm.generateStatisticRateCurrency(listRate, command);
        outRatesChartService.outRateStatistic(command, listRate, "USD");


        System.out.println(listRate);
    }*/
}