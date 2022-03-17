package ru.liga.medvedev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.services.CommandService;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.inputcommad.InConsoleServiceImpl;
import ru.liga.medvedev.services.impl.inputcommad.InTelegramBotServiceImpl;

import java.util.HashMap;

@Component("CommandController")
public class CommandController implements ru.liga.medvedev.controller.Command {

    private HashMap<String, CommandService> commandServiceHashMap = new HashMap<>();
    @Autowired
    public CommandController(@Qualifier("InTelegramBotService") InTelegramBotServiceImpl inTelegramBotService,
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
        return commandServiceHashMap.get("BOT").getCommands(text);
    }
}