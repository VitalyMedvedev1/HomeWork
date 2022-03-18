package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface OutRateStatistic {
    byte[] outRateStatistic(Command command, List<List<Rate>> listRates);
}