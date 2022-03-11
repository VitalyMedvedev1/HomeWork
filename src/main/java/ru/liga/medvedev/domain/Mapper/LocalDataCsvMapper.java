package ru.liga.medvedev.domain.Mapper;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.Reference;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component("LocalCsvMapper")
public class LocalDataCsvMapper implements RateDataMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final NumberFormat numberFormat = NumberFormat.getInstance();

    @Override
    public List<Rate> mapRate(List<List<String>> listData) {
        List<Rate> listRates = new ArrayList<>();
        List<String> headers = listData.get(Reference.HEADER_INDEX);
        try {
            for (int i = 1; i < listData.size(); i++) {
                if (headers.size() == listData.get(i).size()) {
                    LocalDate localDate = null;
                    Double course = null;
                    List<String> listStrRate = listData.get(i);
                    for (int j = 0; j < headers.size(); j++) {
                        if (headers.get(j).replaceAll("\\W", "").equals("data")) {
                            localDate = LocalDate.parse(listStrRate.get(j), formatter);
                        } else if (headers.get(j).equals("curs")) {
                            course = Double.parseDouble(String.valueOf(numberFormat.parse(listStrRate.get(j))));
                        }
                    }
                    listRates.add(new Rate(localDate, course));
                }
            }
        } catch (ParseException e) {
            System.out.print("\n      Неверный формат курса валюты в файле\n");
            throw new RuntimeException();
        }
        return listRates;
    }
}
