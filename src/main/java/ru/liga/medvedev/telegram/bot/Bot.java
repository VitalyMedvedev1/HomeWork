package ru.liga.medvedev.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.telegram.bot.command.HelpCommand;
import ru.liga.medvedev.telegram.bot.command.StartCommand;
import ru.liga.medvedev.telegram.bot.nonCommand.NonCommand;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class Bot extends TelegramLongPollingCommandBot {
    private Logger logger = LoggerFactory.getLogger(Bot.class);
    private String BOT_TOKEN;
    private String BOT_NAME;
    private final NonCommand nonCommand;

    public Bot(String BOT_TOKEN, String BOT_NAME) {
        super();
        logger.debug("Super constructor was worked");
        this.BOT_TOKEN = BOT_TOKEN;
        this.BOT_NAME = BOT_NAME;
        this.nonCommand = new NonCommand();

        register(new StartCommand("start", "Старт"));

        register(new HelpCommand("help", "Помощь"));
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        nonCommand.nonCommandExecute(update);
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            long chatId = update.getMessage().getChatId();
//            String text = update.getMessage().getText();
//            SendMessage message = new SendMessage();
//            message.setChatId(String.valueOf(chatId));
//            message.setText("OUT_TEST_4");
//            try {
//                execute(message);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}