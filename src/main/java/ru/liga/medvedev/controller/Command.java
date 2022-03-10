package ru.liga.medvedev.controller;

import ru.liga.medvedev.domain.Commands;

public interface Command {
    Commands getCommand();
}
