package ru.liga.medvedev.domain;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RateStatisticFunctionsTest {

    @Test
    void sortedRateList() {
        List<Rate> listRate = new ArrayList(
                Arrays.asList(new Rate(LocalDate.now(), 1d),
                        new Rate(LocalDate.of(1990, 4,4), 1d),
                        new Rate(LocalDate.of(2030, 1 ,1), 1d)));
        listRate = RateStatisticFunctions.sortedRateList(listRate);

        assertTrue(listRate.get(0).getDate().isAfter(listRate.get(1).getDate()) && listRate.get(1).getDate().isAfter(listRate.get(2).getDate()));
        assertTrue(listRate instanceof LinkedList);
    }

    @Test
    void getFromWhatDateRate() {
        Command command = new Command();
        assertEquals(RateStatisticFunctions.getFromWhatDateRate(command), LocalDate.now());
        command.setLocalDate(LocalDate.of(2222, 2, 2));
        assertNotEquals(RateStatisticFunctions.getFromWhatDateRate(command), LocalDate.now());
    }
}