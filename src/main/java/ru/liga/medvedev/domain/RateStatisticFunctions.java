package ru.liga.medvedev.domain;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RateStatisticFunctions {

    public static List<Rate> sortedRateList(List<Rate> listRate){
        return listRate.stream()
                .sorted(Comparator.comparing(Rate::getDate).reversed())
                .limit(Reference.COLLECTION_SIZE)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static LocalDate getFromWhatDateRate(Command command){
        LocalDate localDate;
        if ((localDate = command.getLocalDate()) == null) {
            localDate = LocalDate.now();
        }
        return localDate;
    }
}