package ru.liga.medvedev.services;

import ru.liga.medvedev.domain.Command;

public interface CommandService {
    Command getCommands();
    Command getCommands(String text);
}
