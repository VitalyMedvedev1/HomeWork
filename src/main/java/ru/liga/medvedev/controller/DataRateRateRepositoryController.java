package ru.liga.medvedev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component("DataRepositoryController")
public class DataRateRateRepositoryController implements DataRateRepository {
    private final DataRateRepository dataRateRepository;

    @Autowired
    public DataRateRateRepositoryController(@Qualifier("LocalRepository") DataRateRepository dataRateRepository) {
        this.dataRateRepository = dataRateRepository;
    }

    @Override
    public List<List<String>> getRateDataRepository(String currency) {
        log.info("Начало загрузки данных из репозитория: {}", dataRateRepository.getClass());
        return dataRateRepository.getRateDataRepository(currency);
    }
}