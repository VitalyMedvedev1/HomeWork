package ru.liga.medvedev.services.impl.algorithms;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.liga.medvedev.CreateRandomDataListEmulLoad;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class AlgorithmRatesTest {

    private List<Rate> listRate = new ArrayList<>();
    private List<Rate> newListRate = new ArrayList<>();
    private Command command = new Command();
    private final ActualAlgorithmImpl actualAlgorithm = new ActualAlgorithmImpl();
    private final ArithmeticAverageAlgorithmImpl averageAlgorithm = new ArithmeticAverageAlgorithmImpl();
    private final MoonAlgorithmImpl moonAlgorithm = new MoonAlgorithmImpl();
    private final LinearRegressionServiceImpl linearRegressionService = new LinearRegressionServiceImpl();

    @BeforeEach
    public void generateTestStatistic() {
        command.setCurrency("USD");
        listRate = CreateRandomDataListEmulLoad.emulationRateStatistic();
    }

    @AfterEach
    public void clearRates() {
        listRate.clear();
        newListRate.clear();
    }

    @Test
    public void generateRatesWithActualAlgTest() {
        newListRate = averageAlgorithm.generateStatisticRateCurrency(listRate, command);
        Assertions.assertEquals(newListRate.get(0).getDate(), LocalDate.now());
    }

    @Test
    void generateRatesWithAverageAlgTest() {
        newListRate = actualAlgorithm.generateStatisticRateCurrency(listRate, command);
        Assertions.assertEquals(newListRate.get(0).getDate(), LocalDate.now());
    }

    @Test
    void generateRatesWithMoonAlgTest() {
        newListRate = moonAlgorithm.generateStatisticRateCurrency(listRate, command);
        Assertions.assertEquals(newListRate.get(0).getDate(), LocalDate.now());
    }

    @Test
    void generateRatesWithLinearAlgTest() {
        newListRate = linearRegressionService.generateStatisticRateCurrency(listRate, command);
        Assertions.assertEquals(newListRate.get(0).getDate(), LocalDate.now());
    }
}
