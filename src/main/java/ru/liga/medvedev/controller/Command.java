package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.CommandLineParser;
import ru.liga.medvedev.domain.Commands;

public interface Command {
    Commands getCommand();
    Commands getCommand(String text);
}
