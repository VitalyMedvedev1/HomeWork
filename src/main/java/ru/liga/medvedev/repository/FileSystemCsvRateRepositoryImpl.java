package ru.liga.medvedev.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.liga.medvedev.controller.DataRateRepository;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Reference;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("LocalRepository")
public class FileSystemCsvRateRepositoryImpl implements DataRateRepository, CsvRepository {

    private static final String DELIMITER = ";";
    private static final String EXTENSION = ".csv";

    @Override
    public List<List<String>> getRateDataRepository(Command command) {
        return ReadLocalCsv(command.getCurrency());
    }

    @Override
    public List<List<String>> ReadLocalCsv(String currency) {
        log.debug("Начало чтения данных из локального файла по валюте - " + currency);
        URL url = getClass().getClassLoader().getResource(currency + "_NEW" + EXTENSION);//!!!!!!!!!!!!!!!!!!!!!!!!!
        if (url == null) {
            throw new RuntimeException("Файл не найден");
        }
        List<List<String>> result = new ArrayList<>();
        List<String> fileContent = readFile(url);
        if (fileContent.isEmpty() || fileContent.size() == 1) {
            throw new RuntimeException("В локальном файле нет статистики по введенной валюте");
        }
        result.add(Arrays.asList(fileContent.get(Reference.HEADER_INDEX).split(DELIMITER)));
        for (int i = Reference.HEADER_INDEX + 1; i < fileContent.size(); i++) {
            result.add(Arrays.asList(fileContent.get(i).split(DELIMITER)));
        }
        log.debug("Конец чтения данных из локального файла по валюте - " + currency);
        return result;
    }

    private List<String> readFile(URL url) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(url.getFile()))) {
            log.debug("Чтение данных из файла");
            return bufferedReader.lines()
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}