package ru.liga.medvedev.controller;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.repository.FileSystemCsvRepositoryImpl;

import java.util.List;

@Data
@Component("DataRepositoryController")
public class DataRepositoryController implements DataRepository {
    private final DataRepository dataRepository;

    public DataRepositoryController() {
        this.dataRepository = new FileSystemCsvRepositoryImpl();
    }

    @Override
    public List<List<String>> getRateDataRepository(String currency) {
        return dataRepository.getRateDataRepository(currency);
    }
}