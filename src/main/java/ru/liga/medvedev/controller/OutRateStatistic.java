package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface OutRateStatistic {
    String outRateStatistic(Command command, List<Rate> listRates);
}