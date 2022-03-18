/*
package ru.liga.medvedev.services.impl.algorithms;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.SpringConfiguration;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class ActualAlgorithmImplTest {

    List<Rate> listRate = new ArrayList<>();
    ActualAlgorithmImpl actualAlgorithm = new ActualAlgorithmImpl();

*/
/*    @Before
    public void addRateList() {
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -period week -alg moon");
        List<List<String>> list = fileSystemCsvRepository.getRateDataRepository(commands);
        listRate = SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
                SpringConfiguration.RATE_DATA_MAPPER.mapRate(list), commands);
    }*//*


    @Test
    public void generateRate() {
        Command command = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -Date 13.03.2022 -alg moon");
        List<List<String>> list = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command.getCurrency());
        listRate = SpringConfiguration.RATE_DATA_MAPPER.mapRate(list, );
        LocalDate localDate = command.getLocalDate();
        List<Rate> newRateStatistic = actualAlgorithm.getRateStatistic(localDate, listRate);
        System.out.println("123");
    }
}*/
