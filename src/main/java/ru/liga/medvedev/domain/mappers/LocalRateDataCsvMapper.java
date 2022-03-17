package ru.liga.medvedev.domain.mappers;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.Reference;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component("LocalCsvMapper")
public class LocalRateDataCsvMapper implements RateDataMapper {

    private static final NumberFormat numberFormat = NumberFormat.getInstance();

    @Override
    public List<Rate> mapRate(List<List<String>> listData) {
        List<Rate> listRates = new ArrayList<>();
        List<String> headers = listData.get(Reference.HEADER_INDEX);
        try {
            for (int i = 1; i < listData.size(); i++) {
                if (headers.size() == listData.get(i).size()) {
                    LocalDate localDate = null;
                    double course = 0d;
                    double nominal = 1d;
                    List<String> listStrRate = listData.get(i);
                    for (int j = 0; j < headers.size(); j++) {
                        if (headers.get(j).replaceAll("\\W", "").equals("data")) {
                            localDate = LocalDate.parse(listStrRate.get(j), Reference.INPUT_DATE_FORMATTER);
                        } else if (headers.get(j).equals("curs")) {
                            course = Double.parseDouble(String.valueOf(numberFormat.parse(listStrRate.get(j).replaceAll("\"", ""))));
                        } else if (headers.get(j).equals("nominal")) {
                            nominal = Double.parseDouble(String.valueOf(numberFormat.parse(listStrRate.get(j))));
                        }
                    }
                    listRates.add(new Rate(localDate, course / nominal));
                }
            }
        } catch (ParseException e) {
            System.out.print("\n      Неверный формат курса валюты в файле\n");
            throw new RuntimeException();
        }
        return listRates;
    }
}
