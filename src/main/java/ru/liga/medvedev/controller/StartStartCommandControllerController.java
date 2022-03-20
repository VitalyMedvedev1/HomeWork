package ru.liga.medvedev.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.services.CommandService;
import ru.liga.medvedev.services.impl.inputcommad.InConsoleServiceImpl;
import ru.liga.medvedev.services.impl.inputcommad.InTelegramBotServiceImpl;

import java.util.HashMap;

@Slf4j
@Component("CommandController")
public class StartStartCommandControllerController implements StartCommandController {

    private final HashMap<String, CommandService> commandServiceHashMap = new HashMap<>();

    @Autowired
    public StartStartCommandControllerController(@Qualifier("InTelegramBotService") InTelegramBotServiceImpl inTelegramBotService,
                                                 @Qualifier("InConsoleService") InConsoleServiceImpl inConsoleService) {
        commandServiceHashMap.put("BOT", inTelegramBotService);
        commandServiceHashMap.put("CONSOLE", inConsoleService);
    }

    @Override
    public Command getCommand() {
        return commandServiceHashMap.get("CONSOLE").getCommands();
    }

    @Override
    public Command getCommand(String text) {
        log.info("Начало обработки команды для работы: " + text);
        return commandServiceHashMap.get("BOT").getCommands(text);
    }
}