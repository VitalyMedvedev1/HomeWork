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
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component("ArithmeticAvg")
public class ArithmeticAverageAlgorithmImpl implements RateAlgorithmService {
    private final DataRepository dataRepository;
    private final RateDataMapper rateDataMapper;
    private final OutRateStatistic outRateStatistic;
    private List<Rate> listRate;
    private LocalDate DATE_NOW_PLUS_WEEK = LocalDate.now().plusDays(7);
    private int PRECISION = 2;

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
        listRate = rateDataMapper.mapRate(dataStatisticList);

        listRate = listRate.stream()
                .sorted(Comparator.comparing(Rate::getDate).reversed())
                .limit(7)
                .collect(Collectors.toList());
        getLastWeekStatistic(listRate);

        outRateStatistic.getOutData(commands.getPeriod(), listRate);
    }

    private void getLastWeekStatistic(List<Rate> listRate) {
        while (!listRate.get(0).getDate().equals(DATE_NOW_PLUS_WEEK)) {
            Double avgCurs = listRate.stream()
                    .mapToDouble(rate -> rate.getValue())
                    .average().orElse(0);
            listRate.add(0, new Rate(listRate.get(0).getDate().plusDays(1), Precision.round(avgCurs, PRECISION)));
            listRate.remove(listRate.size() - 1);
        }
    }
}