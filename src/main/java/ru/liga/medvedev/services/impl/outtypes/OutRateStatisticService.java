package ru.liga.medvedev.services.impl.outtypes;

import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.StaticParams;
import ru.liga.medvedev.domain.enums.RatePeriods;

public class OutRateStatisticService {
    protected int OutRatesStatisticLength(Command command){
        String periodStr = command.getPeriod().toUpperCase();
        return periodStr.equals(String.valueOf(RatePeriods.TOMORROW)) || periodStr.equals(String.valueOf(RatePeriods.DATE)) ? StaticParams.DAY
                : periodStr.equals(String.valueOf(RatePeriods.WEEK)) ? StaticParams.WEEK : StaticParams.MONTH;
    }
}
