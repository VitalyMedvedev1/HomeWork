package ru.liga.medvedev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.services.CommandService;
import ru.liga.medvedev.services.impl.inputcommad.InTelegramBotServiceImpl;

@Component("CommandController")
public class CommandController implements ru.liga.medvedev.controller.Command {
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
    public Command getCommand() {
        return commandService.getCommands();
    }

    @Override
    public Command getCommand(String text) {
        return commandService.getCommands(text);
    }
}