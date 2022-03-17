package ru.liga.medvedev.services.impl.inputcommad;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.CommandService;

@Component("BotService")
public class InTelegramBotServiceImpl implements CommandService {

    @Override
    public Command getCommands() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Command getCommands(String text) {
        return Reference.COMMAND_LINE_PARSER.createCommand(text);
    }
}