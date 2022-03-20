package ru.liga.medvedev.services.impl.algorithms;

import org.junit.jupiter.api.BeforeAll;
import org.mockito.Mockito;

public class AlgorithmsTest {
    private final ActualAlgorithmImpl actualAlgorithm = Mockito.mock(ActualAlgorithmImpl.class);
    private final ArithmeticAverageAlgorithmImpl averageAlgorithm = Mockito.mock(ArithmeticAverageAlgorithmImpl.class);
    private final LinearRegressionServiceImpl linearAlgorithm = Mockito.mock(LinearRegressionServiceImpl.class);
    private final MoonAlgorithmImpl moonAlgorithm = Mockito.mock(MoonAlgorithmImpl.class);

    @BeforeAll
    public void createListRateStatistics() {

    }
}
