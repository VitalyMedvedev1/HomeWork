package ru.liga.medvedev.controller;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Enum.RateAlgorithms;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.ActualAlgorithmImpl;
import ru.liga.medvedev.services.impl.algorithms.ArithmeticAverageAlgorithmImpl;
import ru.liga.medvedev.services.impl.algorithms.LinearRegressionServiceImpl;
import ru.liga.medvedev.services.impl.algorithms.MoonAlgorithmImpl;

import java.util.HashMap;
import java.util.List;

@Component("AlgorithmsController")
public class AlgorithmsRateController implements AlgorithmsRate {

    private HashMap<String, RateAlgorithmService> algorithmServiceHashMap = new HashMap<>();

    public AlgorithmsRateController() {
        this.algorithmServiceHashMap.put(RateAlgorithms.AVERAGE.name(), new ArithmeticAverageAlgorithmImpl());
        this.algorithmServiceHashMap.put(RateAlgorithms.ACTUAL.name(), new ActualAlgorithmImpl());
        this.algorithmServiceHashMap.put(RateAlgorithms.MOON.name(), new MoonAlgorithmImpl());
        this.algorithmServiceHashMap.put(RateAlgorithms.LINER.name(), new LinearRegressionServiceImpl());
    }

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Commands commands) {
        return algorithmServiceHashMap.get(commands.getAlgorithmName()).generateStatisticRateCurrency(listRate, commands);
    }
}