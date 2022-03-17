package ru.liga.medvedev.services.impl.algorithms;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.RateStatisticFunctions;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.linearregression.LinearRegression;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component("LinearRegressionAlgorithm")
public class LinearRegressionServiceImpl implements RateAlgorithmService {

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command) {
        listRate = RateStatisticFunctions.sortedRateList(listRate);
        List<double[]> listArrayRate = generateRateArraysLastMonth(listRate);
        LinearRegression linearRegression = new LinearRegression(listArrayRate.get(0), listArrayRate.get(1));
        return genRateFromLinearRegressionFunction(linearRegression.slope(), linearRegression.intercept(), listRate.size(), command);
    }

    private List<double[]> generateRateArraysLastMonth(List<Rate> listRate) {
        double[] predictVariable = new double[listRate.size()];
        double[] responseVariable = new double[listRate.size()];
        for (int i = 0; i < listRate.size(); i++) {
            predictVariable[listRate.size() - i - 1] = listRate.get(i).getValue();
            responseVariable[listRate.size() - i - 1] = listRate.size() - i;
        }
        List<double[]> listArrayRate = Stream.of(predictVariable, responseVariable)
                .collect(Collectors.toList());
        return listArrayRate;
    }

    private List<Rate> genRateFromLinearRegressionFunction(double slope, double intercept, int rateStatisticSize, Command command) {
        List<Rate> listRate = new ArrayList<>();
        LocalDate localDate = RateStatisticFunctions.getFromWhatDateRate(command);
        for (int i = 0; i < Reference.COLLECTION_SIZE; i++) {
            listRate.add(new Rate(localDate.plusDays(i), Precision.round((rateStatisticSize + i - intercept) / slope, Reference.PRECISION)));
        }
        return listRate;
    }
}
