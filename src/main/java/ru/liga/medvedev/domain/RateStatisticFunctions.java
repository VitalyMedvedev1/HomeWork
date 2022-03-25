package ru.liga.medvedev.domain;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class RateStatisticFunctions {

    public static List<Rate> sortedRateList(List<Rate> listRate) {
        log.debug("Сортировка статистики: {}", listRate);
        return listRate.stream()
                .sorted(Comparator.comparing(Rate::getDate).reversed())
                .limit(StaticParams.COLLECTION_SIZE)
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public static LocalDate getFromWhatDateRate(Command command) {
        LocalDate localDate;
        if ((localDate = command.getLocalDate()) == null) {
            localDate = LocalDate.now();
        }
        return localDate;
    }
}