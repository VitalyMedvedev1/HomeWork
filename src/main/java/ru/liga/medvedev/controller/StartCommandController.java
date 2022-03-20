package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Command;

public interface StartCommandController {
    Command getCommand();

    Command getCommand(String text);
}
