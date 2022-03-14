package ru.liga.medvedev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Rate {
    private LocalDate date;
    private Double value;

    @Override
    public String toString() {
        return Reference.DATE_FORMATTER.format(date).toUpperCase() + " " + value;
    }
}