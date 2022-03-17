package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface AlgorithmsRate {
    List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command);
}
