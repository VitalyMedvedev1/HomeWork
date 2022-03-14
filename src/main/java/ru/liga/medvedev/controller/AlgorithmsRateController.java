package ru.liga.medvedev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;

import java.util.List;

@Component("AlgorithmsController")
@PropertySource("classpath:resource.properties")
public class AlgorithmsRateController implements AlgorithmsRate {

    private RateAlgorithmService rateAlgorithmService;

    @Autowired
    public AlgorithmsRateController(@Qualifier("ActualAlgorithm") RateAlgorithmService rateAlgorithmService) {
        this.rateAlgorithmService = rateAlgorithmService;
    }

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Commands commands) {
        return rateAlgorithmService.generateStatisticRateCurrency(listRate, commands);
    }
}