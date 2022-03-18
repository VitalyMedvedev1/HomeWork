package ru.liga.medvedev.domain;

import lombok.extern.slf4j.Slf4j;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;

import java.util.List;

@Slf4j
public class GenerateTimeSeries {
    public static TimeSeries generateTimeSeries(List<Rate> listRates, String currency) {
        log.debug("Формирование графика для валюты - " + currency +
                "\nПо статистики" + listRates);
        TimeSeries timeSeries = new TimeSeries(currency);
        listRates
                .forEach(rate ->
                        timeSeries.add(new Day(rate.getDate().getDayOfMonth(), rate.getDate().getMonthValue(), rate.getDate().getYear()), rate.getValue()));
        log.debug("Конец формирования dataset");
        return timeSeries;
    }
}
