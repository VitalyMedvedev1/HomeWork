package ru.liga.medvedev.services.impl;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.repository.FileSystemCsvRepositoryImpl;

import java.util.List;

public class FileSystemCsvRepositoryImplTest {
    FileSystemCsvRepositoryImpl fileSystemCsvRepository = new FileSystemCsvRepositoryImpl();
    @Test
    public void test1(){
        List<List<String>> list = fileSystemCsvRepository.getRateDataRepository("USD");
        System.out.println("123");
    }
}