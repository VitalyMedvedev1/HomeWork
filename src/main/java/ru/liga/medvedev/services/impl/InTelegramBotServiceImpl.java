package ru.liga.medvedev.services.impl;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.CommandService;

@Component("BotService")
public class InTelegramBotServiceImpl implements CommandService {

    @Override
    public Commands getCommands() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Commands getCommands(String text) {
        return Reference.COMMAND_LINE_PARSER.parse(text);
    }
}