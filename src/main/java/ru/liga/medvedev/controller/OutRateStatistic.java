package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface OutRateStatistic {
    void outRateStatistic(String period, List<Rate> listRates);
}
