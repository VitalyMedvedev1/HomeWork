package ru.liga.medvedev.domain.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.medvedev.CreateRandomDataListEmulLoad;
import ru.liga.medvedev.controller.DataRateRateRepositoryController;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.repository.FileSystemCsvRateRepositoryImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LocalRateDataCsvMapperTest {

    private final FileSystemCsvRateRepositoryImpl fileSystemCsvRateRepository = Mockito.mock(FileSystemCsvRateRepositoryImpl.class);
    private final DataRateRateRepositoryController dataRateRateRepositoryController = new DataRateRateRepositoryController(fileSystemCsvRateRepository);
    private final LocalRateDataCsvMapper localRateDataCsvMapper = new LocalRateDataCsvMapper();
    List<List<String>> listRates;

    @Test
    void mappingFromRandomStatisticData() {
        listRates = CreateRandomDataListEmulLoad.emulationListRandomDataFromLocalFile();
        assertNotNull(fileSystemCsvRateRepository);
        Mockito.when(dataRateRateRepositoryController.getRateDataRepository("USD")).thenReturn(listRates);
        List<Rate> listRate = localRateDataCsvMapper.mapRate(listRates, "USD");
        assertEquals(listRate.size(), listRates.size() - 1);
        assertNotNull(listRate.get(0).getDate());
        assertNotNull(listRate.get(0).getValue());
        System.out.println(listRates);
    }

    @SuppressWarnings("All")
    @Test
    public void compareRatesFromMapMockitoToEqualsRates() {
        listRates = CreateRandomDataListEmulLoad.emulationListDataFromLocalFile();
        Mockito.when(dataRateRateRepositoryController.getRateDataRepository("USD")).thenReturn(listRates);
        List<Rate> listRate = localRateDataCsvMapper.mapRate(listRates, "USD");
        List<Rate> listEqualsRate = new ArrayList(Arrays.asList(new Rate("USD", LocalDate.of(2020, 3, 2), 14.66d),
                new Rate("USD", LocalDate.of(2021, 10, 10), 333.5d),
                new Rate("USD", LocalDate.of(2022, 1, 21), 166.66d)));
        assertEquals(listRate, listEqualsRate);
        System.out.println(listRates);
    }
}