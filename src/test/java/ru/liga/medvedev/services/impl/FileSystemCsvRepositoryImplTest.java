package ru.liga.medvedev.services.impl;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.repository.FileSystemCsvRateRepositoryImpl;

import java.util.List;

public class FileSystemCsvRepositoryImplTest {
    FileSystemCsvRateRepositoryImpl fileSystemCsvRepository = new FileSystemCsvRateRepositoryImpl();
    @Test
    public void test1(){
        List<List<String>> list = fileSystemCsvRepository.getRateDataRepository("USD");
        System.out.println("123");
    }
}