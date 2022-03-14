package ru.liga.medvedev.services.impl;

import lombok.Data;
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

@Data
@Component("ArithmeticAvg")
public class ArithmeticAverageAlgorithmImpl implements RateAlgorithmService {
    private LocalDate DATE_NOW_PLUS_WEEK = LocalDate.now().plusMonths(1);

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Commands commands) {
        return lastWeekStatistic(listRate.stream()
                .sorted(Comparator.comparing(Rate::getDate).reversed())
                .limit(Reference.COLLECTION_SIZE)
                .collect(Collectors.toCollection(LinkedList::new)));
    }

    private List<Rate> lastWeekStatistic(List<Rate> listRate) {
        while (!listRate.get(0).getDate().equals(DATE_NOW_PLUS_WEEK)) {
            Double avgCurs = listRate.stream()
                    .mapToDouble(rate -> rate.getValue())
                    .average().orElse(0);
            listRate.add(0, new Rate(listRate.get(0).getDate().plusDays(1), Precision.round(avgCurs, Reference.PRECISION)));
            listRate.remove(listRate.size() - 1);
        }
        return listRate.stream()
                .sorted(Comparator.comparing(Rate::getDate))
                .collect(Collectors.toList());
    }
}