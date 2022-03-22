package ru.liga.medvedev.domain.mappers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.StaticParams;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Component("LocalCsvMapper")
public class LocalRateDataCsvMapper implements RateDataMapper {

    @Override
    public List<Rate> mapRate(List<List<String>> listData, String currency) {
        log.debug("Начало мапинга валютной статистики");
        List<Rate> listRates = new ArrayList<>();
        List<String> headers = listData.get(StaticParams.HEADER_INDEX);
        for (int i = 1; i < listData.size(); i++) {
            if (headers.size() == listData.get(i).size()) {
                LocalDate localDate = null;
                double course = 0d;
                double nominal = 1d;
                List<String> listStrRate = listData.get(i);
                for (int j = 0; j < headers.size(); j++) {
                    if (headers.get(j).replaceAll("\\W", "").equals("data")) {
                        localDate = LocalDate.parse(listStrRate.get(j), StaticParams.INPUT_DATE_FORMATTER);
                    } else if (headers.get(j).equals("curs")) {
                        course = Double.parseDouble(listStrRate.get(j).replaceAll("\"", ""));
                    } else if (headers.get(j).equals("nominal")) {
                        nominal = Double.parseDouble(listStrRate.get(j));
                    }
                }
                listRates.add(new Rate(currency, localDate, course / nominal));
            }
        }
        log.debug("Конец мапинга валютной статистики");
        return listRates;
    }
}
