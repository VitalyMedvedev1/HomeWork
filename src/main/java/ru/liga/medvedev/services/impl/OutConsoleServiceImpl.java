package ru.liga.medvedev.services.impl;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.RatePeriod;

import java.util.List;

@Component("OutConsoleService")
public class OutConsoleServiceImpl implements OutRateStatistic {
    private static final String TOMORROW = String.valueOf(RatePeriod.TOMORROW);

    @Override
    public void outRateStatistic(String period, List<Rate> listRates) {
        if (TOMORROW.equals(period.toUpperCase())) {
            System.out.println(listRates.get(0).toString());
        } else {
            listRates.stream()
                    .forEach(System.out::println);
        }
    }
}
