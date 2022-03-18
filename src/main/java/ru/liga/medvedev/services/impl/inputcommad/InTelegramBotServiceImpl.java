package ru.liga.medvedev.services.impl.inputcommad;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.CommandService;

@Slf4j
@Component("InTelegramBotService")
public class InTelegramBotServiceImpl implements CommandService {

    @Override
    public Command getCommands() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Command getCommands(String text) {
        log.debug("Начало обработки команды от телеграм бота" + text);
        return Reference.COMMAND_LINE_PARSER.createCommand(text);
    }
}