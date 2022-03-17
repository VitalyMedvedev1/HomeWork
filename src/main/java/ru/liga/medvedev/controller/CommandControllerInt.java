package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Command;

public interface CommandControllerInt {
    Command getCommand();

    Command getCommand(String text);
}
