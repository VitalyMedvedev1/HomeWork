package ru.liga.medvedev.services.impl.outtypes;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.enums.RateAlgorithms;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.enums.RatePeriods;

import java.util.List;
import java.util.stream.Collectors;

@Component("OutDataRateService")
public class OutRatesListServiceImpl implements OutRateStatistic {

    @Override
    public String outRateStatistic(Commands commands, List<Rate> listRates) {
        String periodStr = commands.getPeriod().toUpperCase();
        int period = periodStr.equals(String.valueOf(RatePeriods.TOMORROW)) || periodStr.equals(String.valueOf(RatePeriods.DATE))  ? 1 :
                         periodStr.equals(String.valueOf(RatePeriods.WEEK)) ? 7 : 30;
        return listRates.stream()
                .skip(commands.getAlgorithmName().equals(RateAlgorithms.MOON.name()) || periodStr.equals(String.valueOf(RatePeriods.DATE)) ? 0 : 1)
                .limit(period)
                .map(Rate::toString)
                .collect(Collectors.joining("\n"));
    }
}