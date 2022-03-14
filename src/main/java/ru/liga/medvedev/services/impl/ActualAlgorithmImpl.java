package ru.liga.medvedev.services.impl;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.RateAlgorithmService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Component("ActualAlgorithm")
public class ActualAlgorithmImpl implements RateAlgorithmService {

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Commands commands) {
        LocalDate localDate;
        if ((localDate = commands.getLocalDate()) == null) {
            localDate = LocalDate.now();
        }

        return getRateStatistic(localDate, listRate);
    }

    public List<Rate> getRateStatistic(LocalDate localDate, List<Rate> listRate) {
        List<Rate> newRateStatistic = new LinkedList<>();
        Double avgCourse = 0d;
        for (int i = 0; i < Reference.COLLECTION_SIZE; i++) {
            LocalDate finalLocalDate = localDate;
            avgCourse = listRate.stream()
                    .filter(rate -> rate.getDate().equals(finalLocalDate.minusYears(2)) || rate.getDate().equals(finalLocalDate.minusYears(3)))
                    .mapToDouble(r -> r.getValue())
                    .average().orElse(avgCourse);
            newRateStatistic.add(new Rate(localDate, Precision.round(avgCourse, Reference.PRECISION)));
            localDate = localDate.plusDays(1);
        }
        return newRateStatistic.stream()
                .sorted(Comparator.comparing(Rate::getDate))
                .collect(Collectors.toList());
    }
}
