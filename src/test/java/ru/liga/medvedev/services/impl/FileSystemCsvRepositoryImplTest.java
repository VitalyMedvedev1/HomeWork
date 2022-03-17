package ru.liga.medvedev.services.impl;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.repository.FileSystemCsvRateRepositoryImpl;

import java.util.List;

public class FileSystemCsvRepositoryImplTest {
    FileSystemCsvRateRepositoryImpl fileSystemCsvRepository = new FileSystemCsvRateRepositoryImpl();


    @Test
    public void test1(){
        Command command = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -period week -alg moon");
        List<List<String>> list = fileSystemCsvRepository.getRateDataRepository(command);
        System.out.println("123");
    }
}