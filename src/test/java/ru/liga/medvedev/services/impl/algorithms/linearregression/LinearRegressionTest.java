package ru.liga.medvedev.services.impl.algorithms.linearregression;

import org.junit.jupiter.api.Test;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.algorithms.LinearRegressionServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

class LinearRegressionTest {

    List<Rate> listRate = new ArrayList<>();
    RateAlgorithmService linearRegressionService = new LinearRegressionServiceImpl();

    @Test
    public void generateRate() {
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand("rate USD -Date 13.03.2022 -alg moon");
        List<List<String>> list = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(commands);
        listRate = SpringConfiguration.RATE_DATA_MAPPER.mapRate(list);

        linearRegressionService.generateStatisticRateCurrency(listRate, commands);


/*        Map<Double, Double> newListRate = listRate.stream()
//                .filter(rate -> rate.getDate().isAfter(LocalDate.of(listRate.get(0).getDate().getYear(),listRate.get(0).getDate().getMonthValue(),1)))
                .filter(rate -> rate.getDate().isAfter(listRate.get(0).getDate().minusMonths(1)))
                .distinct()
                .collect(Collectors.toMap(Rate -> (double) Rate.getDate().getDayOfMonth(), Rate::getValue));
        //                .collect(Collectors.toMap(rate -> 123d, rate -> rate.getValue()));
        double[] x = new double[newListRate.size()];
        double[] y = new double[newListRate.size()];
        for (Map.Entry<Double, Double> pair : newListRate.entrySet()
             ) {
            x=Arrays.pair.getKey()
        }
        LinearRegression linearRegression = new LinearRegression(x, y);*/

/*        Map<Double, Double> newListRate = listRate.stream().filter(rate -> rate.getDate().isAfter(listRate.get(0).getDate().minusMonths(1)))
                .sorted(Comparator.comparing(Rate::getDate).reversed())
                .collect(Collectors.toCollection(LinkedList::new)).stream()
                    .collect(Collectors.toMap(Rate -> (double) Rate.getDate().getDayOfMonth(), Rate::getValue, LinkedHashMap::new));*/
/*        List<Rate> newList = listRate.stream().filter(rate -> rate.getDate().isAfter(listRate.get(0).getDate().minusMonths(1)))
                .sorted(Comparator.comparing(Rate::getDate).reversed())
                .collect(Collectors.toCollection(LinkedList::new));

        double[] x = new double[newList.size()];
        double[] y = new double[newList.size()];

        for (int i = 0; i < newList.size(); i++) {
            y[i] = newList.get(i).getValue();
            x[i] = newList.size() - i - 1;
        }

        LinearRegression linearRegression = new LinearRegression(x, y);


        System.out.println("123");
        System.out.println(linearRegression.toString());*/
    }

    public Double getDayPlusWeek(LocalDate localDate) {
        String day = String.valueOf(localDate.getDayOfMonth());
        Double d = Double.parseDouble(localDate.getMonthValue() + String.valueOf(localDate.getDayOfMonth()));
        return Double.parseDouble(localDate.getMonthValue() + String.valueOf(localDate.getDayOfMonth()));
    }
}