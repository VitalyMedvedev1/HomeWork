package ru.liga.medvedev.domain.mappers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.liga.medvedev.CreateRandomDataListEmulLoad;
import ru.liga.medvedev.controller.DataRateRateRepositoryController;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.repository.FileSystemCsvRateRepositoryImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LocalRateDataCsvMapperTest {

    private final FileSystemCsvRateRepositoryImpl fileSystemCsvRateRepository = Mockito.mock(FileSystemCsvRateRepositoryImpl.class);
    private final DataRateRateRepositoryController dataRateRateRepositoryController = new DataRateRateRepositoryController(fileSystemCsvRateRepository);
    private final LocalRateDataCsvMapper localRateDataCsvMapper = new LocalRateDataCsvMapper();
    List<List<String>> listRates = CreateRandomDataListEmulLoad.emulationListRandomDataFromLocalFile();

    @Test
    void MappingFromRandomStatisticData() {
        assertNotNull(fileSystemCsvRateRepository);
        Mockito.when(dataRateRateRepositoryController.getRateDataRepository("USD")).thenReturn(listRates);
        List<Rate> listRate = localRateDataCsvMapper.mapRate(listRates, "USD");
        assertEquals(listRate.size(), listRates.size() - 1);
        assertNotNull(listRate.get(0).getDate());
        assertNotNull(listRate.get(0).getValue());
        System.out.println(listRates);
    }
}