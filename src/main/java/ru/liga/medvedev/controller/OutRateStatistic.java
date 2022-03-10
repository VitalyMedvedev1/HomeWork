package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface OutRateStatistic {
    void getOutData(String period, List<Rate> listRates);
}
