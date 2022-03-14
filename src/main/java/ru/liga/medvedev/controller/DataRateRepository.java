package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Commands;

import java.util.List;

public interface DataRateRepository {
    List<List<String>> getRateDataRepository(Commands commands);
}
