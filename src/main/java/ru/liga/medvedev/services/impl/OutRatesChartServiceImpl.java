package ru.liga.medvedev.services.impl;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OutRatesChartServiceImpl implements OutRateStatistic {
    @Override
    public String outRateStatistic(Commands commands, List<Rate> listRates) {
        TimeSeriesCollection dataSet = generateDataSet(listRates, commands.getCurrency());
        saveChartOnLocal(dataSet);
        return "chart";
    }

    public TimeSeriesCollection generateDataSet(List<Rate> listRates, String currency) {
        TimeSeriesCollection dataSet = new TimeSeriesCollection();
        TimeSeries timeSeries = new TimeSeries(currency);
        listRates
                .forEach(rate ->
                        timeSeries.add(new Day(rate.getDate().getDayOfMonth(), rate.getDate().getMonthValue(), rate.getDate().getYear()), rate.getValue()));
        dataSet.addSeries(timeSeries);
        return dataSet;
    }

    private void saveChartOnLocal(TimeSeriesCollection dataSet){
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Rates prediction", "date", "rate", dataSet, true, false, false);
        try {
            String str = System.getProperty("user.dir");
            ChartUtils.saveChartAsJPEG(new File("chart.png"), chart, 450, 400);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения граффика статистики!");
        }
    }
}
