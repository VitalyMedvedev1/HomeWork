package ru.liga.medvedev.services;

import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface RateAlgorithmService {
    List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command);
}
