package ru.liga.medvedev.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.CommandLineParser;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.services.CommandService;
import ru.liga.medvedev.services.impl.InTelegramBotServiceImpl;

@Component("CommandController")
public class CommandController implements Command {
    private final CommandService commandService;

    /*    @Autowired
        public CommandController(@Qualifier("ConsoleService") CommandService commandService) {
            this.commandService = new InConsoleServiceImpl();
        }*/
    @Autowired
    public CommandController(@Qualifier("BotService") CommandService commandService) {
        this.commandService = new InTelegramBotServiceImpl();
    }

    @Override
    public Commands getCommand() {
        return commandService.getCommands();
    }

    @Override
    public Commands getCommand(String text) {
        return commandService.getCommands(text);
    }
}