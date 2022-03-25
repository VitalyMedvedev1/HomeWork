package ru.liga.medvedev.telegram.bot.command;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Slf4j
public class StartCommand extends ServiceCommand {

    public StartCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        log.info("Обработка команды: {}", this.getCommandIdentifier());
        sendAnswer(absSender, chat.getId(), "Давайте начнём! Если Вам нужна помощь, нажмите \n/help \n/start\n/stop");
    }
}