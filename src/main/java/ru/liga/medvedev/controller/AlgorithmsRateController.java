package ru.liga.medvedev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.enums.RateAlgorithms;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.ActualAlgorithmImpl;
import ru.liga.medvedev.services.impl.algorithms.ArithmeticAverageAlgorithmImpl;
import ru.liga.medvedev.services.impl.algorithms.LinearRegressionServiceImpl;
import ru.liga.medvedev.services.impl.algorithms.MoonAlgorithmImpl;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller("AlgorithmsController")
public class AlgorithmsRateController implements AlgorithmsRate {

    private final HashMap<String, RateAlgorithmService> algorithmServiceHashMap = new HashMap<>();

    public AlgorithmsRateController(@Qualifier("ActualAlgorithm") ActualAlgorithmImpl actualAlgorithm,
                                    @Qualifier("AvgArithmeticAlgorithm") ArithmeticAverageAlgorithmImpl arithmeticAverageAlgorithm,
                                    @Qualifier("LinearRegressionAlgorithm") LinearRegressionServiceImpl linearRegressionService,
                                    @Qualifier("MoonAlgorithm") MoonAlgorithmImpl moonAlgorithm) {
        this.algorithmServiceHashMap.put(RateAlgorithms.AVERAGE.name(), arithmeticAverageAlgorithm);
        this.algorithmServiceHashMap.put(RateAlgorithms.ACTUAL.name(), actualAlgorithm);
        this.algorithmServiceHashMap.put(RateAlgorithms.MOON.name(), moonAlgorithm);
        this.algorithmServiceHashMap.put(RateAlgorithms.LINER.name(), linearRegressionService);
    }

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command) {
        log.info("Начачало работы расчета статистики, алгоритм: " + command.getAlgorithmName());
        return algorithmServiceHashMap.get(command.getAlgorithmName()).generateStatisticRateCurrency(listRate, command);
    }
}