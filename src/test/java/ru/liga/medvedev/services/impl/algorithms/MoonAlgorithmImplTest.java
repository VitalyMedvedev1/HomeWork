package ru.liga.medvedev.services.impl.algorithms;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoonAlgorithmImplTest {
    List<Rate> listRate = new ArrayList<>();
    MoonAlgorithmImpl moonAlgorithm = new MoonAlgorithmImpl();

    @Test
    public void generateRate() {
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -Date 19.03.2022 -alg moon");
        List<List<String>> list = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(commands);
        listRate = SpringConfiguration.RATE_DATA_MAPPER.mapRate(list);
        LocalDate localDate = commands.getLocalDate();
        List<Rate> newRateStatistic = moonAlgorithm.generateStatisticRateCurrency(listRate, commands);
        System.out.println("123");
    }
}