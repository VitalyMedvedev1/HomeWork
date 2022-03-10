package ru.liga.medvedev.services;

import ru.liga.medvedev.domain.Commands;

public interface RateAlgorithmService {
    void generateStatisticRateCurrency(Commands commands);
}
