package ru.liga.medvedev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Rate implements Comparable<Rate> {
    private LocalDate date;
    private Double value;

    @Override
    public String toString() {
        return Reference.DATE_FORMATTER.format(date).toUpperCase() + " " + value;
    }

    @Override
    public int compareTo(Rate anotherRate) {
        if (this.date.equals(anotherRate.getDate())) {
            return 0;
        } else if (this.date.isAfter(anotherRate.getDate())) {
            return -1;
        } else {
            return 1;
        }
    }
}