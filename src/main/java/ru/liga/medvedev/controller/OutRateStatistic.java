package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface OutRateStatistic {
    String outRateStatistic(Commands commands, List<Rate> listRates);
}