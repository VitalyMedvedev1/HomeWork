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
import ru.liga.medvedev.domain.StaticParams;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service("OutRatesChartService")
public class OutRatesChartServiceImpl extends OutRateStatisticService implements OutRateStatistic {
    private final static Map<Integer, Color> mapColor = new HashMap<Integer, Color>() {{
        put(0, Color.BLUE);
        put(1, Color.RED);
        put(2, Color.YELLOW);
        put(3, Color.GRAY);
        put(4, Color.GREEN);
    }};

    @Override
    public byte[] outRateStatistic(Command command, List<List<Rate>> listRates) {
        log.debug("Формирование графика - картинки!!! для валюты");
        TimeSeriesCollection dataSet = new TimeSeriesCollection();
        int i = OutRatesStatisticLength(command);
        for (List<Rate> listRate : listRates) {
            dataSet.addSeries(generateTimeSeries(
                    listRate.stream()
                            .limit(OutRatesStatisticLength(command))
                            .collect(Collectors.toList()),
                    listRate.get(0).getCurrency()));
        }
        return generateChart(dataSet, listRates.size());
    }

    public TimeSeries generateTimeSeries(List<Rate> listRates, String currency) {
        log.debug("Формирование графика для валюты - " + currency +
                "\nПо статистики" + listRates);
        TimeSeries timeSeries = new TimeSeries(currency);
        listRates
                .forEach(rate ->
                        timeSeries.add(new Day(rate.getDate().getDayOfMonth(), rate.getDate().getMonthValue(), rate.getDate().getYear()), rate.getValue()));
        log.debug("Конец формирования dataset");
        return timeSeries;
    }

    private byte[] generateChart(TimeSeriesCollection dataSet, int countCurrency) {
        log.debug("Начало формирования граффика");
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Rates prediction", "date", "rate", dataSet, true, false, false);

        XYPlot plot = chart.getXYPlot();
        XYItemRenderer renderer = plot.getRenderer();
        for (int i = 0; i < countCurrency; i++) {
            renderer.setSeriesPaint(i, mapColor.get(i));
            renderer.setSeriesFillPaint(i, mapColor.get(i));
        }

        byte[] byteGraph;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            ChartUtils.writeChartAsJPEG(out, chart, 450, 400);
            byteGraph = out.toByteArray();
        } catch (IOException e) {
            log.error("\"Ошибка сохранения граффика статистики!" + e.getMessage());
            throw new RuntimeException("Ошибка сохранения граффика статистики!");
        }
        log.debug("Конец формирования граффика");
        return byteGraph;
    }
}
