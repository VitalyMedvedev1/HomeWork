package ru.liga.medvedev.services.impl.algorithms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.RateStatisticFunctions;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.RateAlgorithmService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service("ActualAlgorithm")
public class ActualAlgorithmImpl implements RateAlgorithmService {

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command) {
        log.debug("Формирование статистики по актуальному алгоритму");
        LocalDate localDate = RateStatisticFunctions.getFromWhatDateRate(command);

        return getRateStatistic(localDate, listRate);
    }

    public List<Rate> getRateStatistic(LocalDate localDate, List<Rate> listRate) {
        List<Rate> newRateStatistic = new LinkedList<>();
        double avgCourse = 0d;
        for (int i = 0; i < Reference.COLLECTION_SIZE; i++) {
            LocalDate finalLocalDate = localDate;
            avgCourse = new TreeSet<>(listRate).stream()
                    .filter(rate -> rate.getDate().equals(finalLocalDate.minusYears(2)) || rate.getDate().equals(finalLocalDate.minusYears(3)))
                    .mapToDouble(Rate::getValue)
                    .average().orElse(avgCourse);
            newRateStatistic.add(new Rate(localDate, Precision.round(avgCourse, Reference.PRECISION)));
            localDate = localDate.plusDays(1);
        }
        log.debug("Конец формирование статистики по актуальному алгоритму" + newRateStatistic);
        return new ArrayList<>(newRateStatistic).stream()
                .sorted(Comparator.comparing(Rate::getDate))
                .collect(Collectors.toList());
    }
}
