package ru.liga.medvedev.services.impl.algorithms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.RateStatisticFunctions;
import ru.liga.medvedev.domain.StaticParams;
import ru.liga.medvedev.services.RateAlgorithmService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("AvgArithmeticAlgorithm")
public class ArithmeticAverageAlgorithmImpl implements RateAlgorithmService {
    private final LocalDate DATE_NOW_PLUS_WEEK = LocalDate.now().plusMonths(1);

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command) {
        log.debug("Формирование статистики по среднему алгоритму");
        return lastWeekStatistic(RateStatisticFunctions.sortedRateList(listRate));
    }

    private List<Rate> lastWeekStatistic(List<Rate> listRate) {
        String currency = listRate.get(0).getCurrency();
        while (!listRate.get(0).getDate().equals(DATE_NOW_PLUS_WEEK)) {
            double avgCurs = listRate.stream()
                    .mapToDouble(Rate::getValue)
                    .average().orElse(0);
            listRate.add(StaticParams.HEADER_INDEX, new Rate(currency, listRate.get(0).getDate().plusDays(StaticParams.DAY), Precision.round(avgCurs, StaticParams.PRECISION)));
            listRate.remove(listRate.size() - 1);
        }
        log.debug("Конец формирование статистики по среднему алгоритму: {}", listRate);
        return listRate.stream()
                .sorted(Comparator.comparing(Rate::getDate))
                .collect(Collectors.toList());
    }
}