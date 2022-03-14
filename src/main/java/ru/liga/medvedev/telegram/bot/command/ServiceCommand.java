package ru.liga.medvedev.telegram.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class ServiceCommand extends BotCommand {
    private Logger logger = LoggerFactory.getLogger(ServiceCommand.class);

    ServiceCommand(String identifier, String description) {
        super(identifier, description);
    }

    void sendAnswer(AbsSender absSender, Long chatId, String commandName, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            //logger.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName));
            e.printStackTrace();
        }
    }
}
