package ru.liga.medvedev.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class StaticParams {
    public static final int HEADER_INDEX = 0;
    public static final int SECOND_INDEX = 1;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("E dd.MM.yyyy");
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static final CommandLineParser COMMAND_LINE_PARSER = new CommandLineParser();
    public static final int PRECISION = 2;
    public static final int DAY = 1;
    public static final int WEEK = 7;
    public static final int MONTH = 30;
    public static final int COLLECTION_SIZE = LocalDate.now().lengthOfMonth() + 1;

    public static final String ERROR_LENGTH_MESSAGE = "Неверный формат строки!\n" +
            "Доступный формат!\n" +
            "команда - rate\n" +
            "валюты - TRY,USD,EUR,AMD,BGN\n" +
            "период - date/period\n" +
            "алгоритмы - moon/actual/average/liner\n" +
            "вывод - list/graph/...\n" +
            "Пример:\n rate USD,EUR,BGN -date 17.07.2022 alg liner -output graph\n" +
            "Попробуйте ввести снова";
    public static final String ERROR_CURRENCY_MESSAGE = "Неверный формат валюты!\n" +
            "Доступные валюты:\n " +
            "USD/TRY/EUR/BGN/AMD или перечисление через запятую\n" +
            "Попробуйте ввести снова";
    public static final String ERROR_OUT_TYPE_MESSAGE = "Неверный формат типа вывода!\n" +
            "Доступные названия:\n" +
            "LIST, GRAPH или без флага -output\n" +
            "Попробуйте ввести снова";
    public static final String ERROR_ALGORITHM_MESSAGE = "Неверный формат алгоритма!\n" +
            "Доступные названия алгоритмов:\n" +
            "MOON, AVERAGE, LINER, ACTUAL\n" +
            "Попробуйте ввести снова";
    public static String ERROR_DATE_FORMAT_MESSAGE = "Неверный формат введенной даты!\n" +
            "Доступный dd.MM.yyyy";
    public static String ERROR_PERIOD_MESSAGE = "Неверный формат периода!\n" +
            "Формат периода:\n" +
            "week/tomorrow/month\n" +
            "Попробуйте ввести снова";
}