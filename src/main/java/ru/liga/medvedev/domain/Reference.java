package ru.liga.medvedev.domain;

import java.time.format.DateTimeFormatter;

public final class Reference {
    public static final int HEADER_INDEX = 0;
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("E dd.MM.yyyy");
}