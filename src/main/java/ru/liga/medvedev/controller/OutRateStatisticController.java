package ru.liga.medvedev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

@Component("OutStatisticController")
public class OutRateStatisticController implements OutRateStatistic {

    private final OutRateStatistic outRateStatistic;

    @Autowired
    public OutRateStatisticController(@Qualifier("OutConsoleService") OutRateStatistic outRateStatistic) {
        this.outRateStatistic = outRateStatistic;
    }

    @Override
    public void outRateStatistic(String period, List<Rate> listRates) {
        outRateStatistic.outRateStatistic(period, listRates);
    }
}
