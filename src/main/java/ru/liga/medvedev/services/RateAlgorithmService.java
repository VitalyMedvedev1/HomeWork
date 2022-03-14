package ru.liga.medvedev.services;

import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface RateAlgorithmService {
    List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Commands commands);
}
