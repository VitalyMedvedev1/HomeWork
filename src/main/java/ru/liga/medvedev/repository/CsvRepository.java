package ru.liga.medvedev.repository;

import java.util.List;

public interface CsvRepository {
    List<List<String>> ReadLocalCsv(String currency);
}
