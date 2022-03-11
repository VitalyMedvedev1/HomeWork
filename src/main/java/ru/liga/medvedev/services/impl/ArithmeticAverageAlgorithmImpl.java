package ru.liga.medvedev.services.impl;

import lombok.Data;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.controller.DataRepository;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Mapper.RateDataMapper;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component("ArithmeticAvg")
public class ArithmeticAverageAlgorithmImpl implements RateAlgorithmService {
    private final DataRepository dataRepository;
    private final RateDataMapper rateDataMapper;
    private final OutRateStatistic outRateStatistic;
    private LocalDate DATE_NOW_PLUS_WEEK = LocalDate.now().plusDays(7);
    private int PRECISION = 2;
    private final int COLLECTION_SIZE = 7;

    @Autowired
    public ArithmeticAverageAlgorithmImpl(@Qualifier("DataRepositoryController") DataRepository dataRepository,
                                          @Qualifier("LocalCsvMapper") RateDataMapper rateDataMapper,
                                          @Qualifier("OutStatisticController") OutRateStatistic outRateStatistic) {
        this.dataRepository = dataRepository;
        this.rateDataMapper = rateDataMapper;
        this.outRateStatistic = outRateStatistic;
    }

    @Override
    public void generateStatisticRateCurrency(Commands commands) {
        List<List<String>> dataStatisticList = dataRepository.getRateDataRepository(commands.getCurrency());
        List<Rate> listRate = rateDataMapper.mapRate(dataStatisticList).stream()
                .sorted(Comparator.comparing(Rate::getDate).reversed())
                .limit(COLLECTION_SIZE)
                .collect(Collectors.toCollection(LinkedList::new));
        lastWeekStatistic(listRate);
        outRateStatistic.outRateStatistic(commands.getPeriod(), listRate);
    }

    private void lastWeekStatistic(List<Rate> listRate) {
        while (!listRate.get(0).getDate().equals(DATE_NOW_PLUS_WEEK)) {
            Double avgCurs = listRate.stream()
                    .mapToDouble(rate -> rate.getValue())
                    .average().orElse(0);
            listRate.add(0, new Rate(listRate.get(0).getDate().plusDays(1), Precision.round(avgCurs, PRECISION)));
            listRate.remove(listRate.size() - 1);
        }
    }
}