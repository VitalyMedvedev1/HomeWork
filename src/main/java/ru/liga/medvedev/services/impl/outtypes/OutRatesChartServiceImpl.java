package ru.liga.medvedev.services.impl.outtypes;

import lombok.extern.slf4j.Slf4j;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Service;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service("OutRatesChartService")
public class OutRatesChartServiceImpl implements OutRateStatistic {
    @Override
    public byte[] outRateStatistic(Command command, List<List<Rate>> listRates) {
        log.debug("Формирование графика - картинки!!! для валюты");
        TimeSeriesCollection dataSet = new TimeSeriesCollection();
/*        for (String cur : command.getListCurrency()
        ) {
            dataSet.addSeries(generateTimeSeries(listRates, cur));
            dataSet.generateDataSet(listRates, command.getCurrency());
        }*/
        for (List<Rate> listRate : listRates) {
            dataSet.addSeries(generateTimeSeries(listRate, listRate.get(0).getCurrency()));
        }
        return generateChart(dataSet);
    }

    public TimeSeries generateTimeSeries(List<Rate> listRates, String currency) {
        log.debug("Формирование графика для валюты - " + currency +
                "\nПо статистики" + listRates);
//        TimeSeriesCollection dataSet = new TimeSeriesCollection();
        TimeSeries timeSeries = new TimeSeries(currency);
        listRates
                .forEach(rate ->
                        timeSeries.add(new Day(rate.getDate().getDayOfMonth(), rate.getDate().getMonthValue(), rate.getDate().getYear()), rate.getValue()));
//        dataSet.addSeries(timeSeries);
        log.debug("Конец формирования dataset");
        return timeSeries;
    }

    private byte[] generateChart(TimeSeriesCollection dataSet) {
        log.debug("Начало формирования граффика");
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Rates prediction", "date", "rate", dataSet, true, false, false);

        XYPlot plot = chart.getXYPlot();
        XYItemRenderer renderer = plot.getRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesFillPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesFillPaint(1, Color.RED);
        renderer.setSeriesPaint(2, Color.YELLOW);
        renderer.setSeriesFillPaint(2, Color.YELLOW);

        byte[] byteGraph;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ChartUtils.writeChartAsJPEG(out, chart, 450, 400);
            byteGraph = out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения граффика статистики!");
        }
        log.debug("Конец формирования граффика");
        return byteGraph;
    }
}
