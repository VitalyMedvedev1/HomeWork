package ru.liga.medvedev;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class CreateDataList {
    public static Random random = new Random();
    public static String FILE_HEADER = "nominal;data;curs;cdx";

    public static List<List<String>> emulationListFromLocalFile() {
        List<List<String>> listRates = new ArrayList(Arrays.asList(new ArrayList(Arrays.asList(FILE_HEADER.split(";")))));
        for (int i = 1; i < random.nextInt(2000 - 1000) + 1000; i++) {
            long minDay = LocalDate.of(2018, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2022, 2, 28).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            String[] strings = {
                    String.valueOf((random.nextInt(100 - 1) + 1)),
                    DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDate.ofEpochDay(randomDay)),
                    String.valueOf(Math.abs(((random.nextDouble() * 100) - 50))),
                    "валюта"};
            listRates.add(Arrays.asList(strings));
        }
        System.out.println(listRates);
        return listRates;
    }
}
