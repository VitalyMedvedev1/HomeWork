package ru.liga.medvedev.services.impl;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.repository.FileSystemCsvRateRepositoryImpl;

import java.util.List;

public class FileSystemCsvRepositoryImplTest {
    FileSystemCsvRateRepositoryImpl fileSystemCsvRepository = new FileSystemCsvRateRepositoryImpl();


    @Test
    public void test1(){
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -period week -alg moon");
        List<List<String>> list = fileSystemCsvRepository.getRateDataRepository(commands);
        System.out.println("123");
    }
}