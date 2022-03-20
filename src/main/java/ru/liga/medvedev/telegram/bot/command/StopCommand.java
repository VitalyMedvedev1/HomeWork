package ru.liga.medvedev.telegram.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
public class StopCommand extends ServiceCommand {

    public static final String STOP_MESSAGE = "Я выключаюсь 3...2...1... \uD83D\uDE1F.";

    public StopCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        log.info("Передана команда на завершение");
        sendAnswer(absSender, chat.getId(), STOP_MESSAGE);
        System.exit(0);
    }
}



