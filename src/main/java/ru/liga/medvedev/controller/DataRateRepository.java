package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Command;

import java.util.List;

public interface DataRateRepository {
    List<List<String>> getRateDataRepository(Command command);
}
