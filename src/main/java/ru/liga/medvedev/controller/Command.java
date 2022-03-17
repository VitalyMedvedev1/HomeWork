package ru.liga.medvedev.controller;

public interface Command {
    ru.liga.medvedev.domain.Command getCommand();
    ru.liga.medvedev.domain.Command getCommand(String text);
}
