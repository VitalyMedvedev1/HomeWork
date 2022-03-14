package ru.liga.medvedev.controller;

import java.util.List;

public interface DataRateRepository {
    List<List<String>> getRateDataRepository(String currency);
}
