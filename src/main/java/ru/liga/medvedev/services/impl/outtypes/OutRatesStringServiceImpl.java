package ru.liga.medvedev.services.impl.outtypes;

import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

public class OutRatesStringServiceImpl implements OutRateStatistic {
    @Override
    public String outRateStatistic(Command command, List<Rate> listRates) {
        return listRates.toString();
    }
}
