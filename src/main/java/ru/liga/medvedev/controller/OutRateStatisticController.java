package ru.liga.medvedev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.impl.outtypes.OutRatesChartServiceImpl;
import ru.liga.medvedev.services.impl.outtypes.OutRatesListServiceImpl;
import ru.liga.medvedev.services.impl.outtypes.OutRatesStringServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("OutStatisticController")
public class OutRateStatisticController implements OutRateStatistic {

    private final Map<String, OutRateStatistic> outRateStatisticMap = new HashMap<>();

    @Autowired
    public OutRateStatisticController(@Qualifier("OutDataRateListService") OutRatesListServiceImpl outRatesListService,
                                      @Qualifier("OutRatesChartService") OutRatesChartServiceImpl outRatesChartService,
                                      @Qualifier("OutRatesStringService") OutRatesStringServiceImpl outRatesStringService) {
        this.outRateStatisticMap.put("LIST", outRatesListService);
        this.outRateStatisticMap.put("DEFAULT", outRatesStringService);
        this.outRateStatisticMap.put("GRAPH", outRatesChartService);
    }

    @Override
    public byte[] outRateStatistic(Command command, List<List<Rate>> listRates) {
        log.info("Начало обработки данных статистики для ответа: " + command.getOutputType());
        return outRateStatisticMap.get(command.getOutputType()).outRateStatistic(command, listRates);
    }
}