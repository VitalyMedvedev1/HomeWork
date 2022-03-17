package ru.liga.medvedev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.impl.outtypes.OutRatesChartServiceImpl;
import ru.liga.medvedev.services.impl.outtypes.OutRatesListServiceImpl;
import ru.liga.medvedev.services.impl.outtypes.OutRatesStringServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("OutStatisticController")
public class OutRateStatisticController implements OutRateStatistic {

    private Map<String, OutRateStatistic> outRateStatisticMap = new HashMap<>();

    @Autowired
    public OutRateStatisticController() {
        this.outRateStatisticMap.put("LIST", new OutRatesListServiceImpl());
        this.outRateStatisticMap.put("DEFAULT", new OutRatesStringServiceImpl());
        this.outRateStatisticMap.put("GRAPH", new OutRatesChartServiceImpl());
    }

    @Override
    public byte[] outRateStatistic(Command command, List<Rate> listRates) {
        return outRateStatisticMap.get(command.getOutputType()).outRateStatistic(command, listRates);
    }
}