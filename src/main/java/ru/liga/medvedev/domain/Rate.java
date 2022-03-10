package ru.liga.medvedev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class Rate {
    private LocalDate date;
    private Double value;

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(Reference.DATE_PATTERN);
        return dateFormatter.format(date).toUpperCase() + " " + value;
    }
}