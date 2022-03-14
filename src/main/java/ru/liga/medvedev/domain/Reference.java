package ru.liga.medvedev.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Reference {
    public static final int HEADER_INDEX = 0;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("E dd.MM.yyyy");
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final CommandLineParser COMMAND_LINE_PARSER = new CommandLineParser();
    public static final int PRECISION = 2;
    public static final int COLLECTION_SIZE = LocalDate.now().lengthOfMonth() + 1;
}