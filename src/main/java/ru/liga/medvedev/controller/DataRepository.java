package ru.liga.medvedev.controller;

import java.util.List;

public interface DataRepository {
    List<List<String>> getRateDataRepository(String currency);
}
