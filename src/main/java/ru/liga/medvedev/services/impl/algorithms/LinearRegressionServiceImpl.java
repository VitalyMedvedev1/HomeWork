package ru.liga.medvedev.services.impl.algorithms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.RateStatisticFunctions;
import ru.liga.medvedev.domain.StaticParams;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.linearregression.LinearRegression;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component("LinearRegressionAlgorithm")
public class LinearRegressionServiceImpl implements RateAlgorithmService {

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command) {
        log.debug("Формирование статистики по линейному алгоритму");
        String currency = listRate.get(0).getCurrency();
        listRate = RateStatisticFunctions.sortedRateList(listRate);
        List<double[]> listArrayRate = generateRateArraysLastMonth(listRate);
        LinearRegression linearRegression = new LinearRegression(listArrayRate.get(0), listArrayRate.get(1));
        return genRateFromLinearRegressionFunction(linearRegression.slope(), linearRegression.intercept(), listRate.size(), command, currency);
    }

    private List<double[]> generateRateArraysLastMonth(List<Rate> listRate) {
        log.debug("Формирование массивов по линейному алгоритму");
        double[] predictVariable = new double[listRate.size()];
        double[] responseVariable = new double[listRate.size()];
        for (int i = 0; i < listRate.size(); i++) {
            predictVariable[listRate.size() - i - StaticParams.SECOND_INDEX] = listRate.get(i).getValue();
            responseVariable[listRate.size() - i - StaticParams.SECOND_INDEX] = listRate.size() - i;
        }
        log.debug("Получение линейной функции");
        List<double[]> listArrayRate = Stream.of(predictVariable, responseVariable)
                .collect(Collectors.toList());
        log.debug("Конец формирования массивов по линейному алгоритму");
        return listArrayRate;
    }

    private List<Rate> genRateFromLinearRegressionFunction(double slope, double intercept, int rateStatisticSize, Command command, String currency) {
        log.debug("Получение стаистики по линейному алгоритму");
        List<Rate> listRate = new ArrayList<>();
        LocalDate localDate = RateStatisticFunctions.getFromWhatDateRate(command);
        for (int i = 0; i < StaticParams.COLLECTION_SIZE; i++) {
            listRate.add(new Rate(currency, localDate.plusDays(i), Precision.round((rateStatisticSize + i - intercept) / slope, StaticParams.PRECISION)));
        }
        log.debug("Стаистика по линейному алгоритму: {}", listRate);
        return listRate;
    }
}
