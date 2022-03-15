package ru.liga.medvedev;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.medvedev.telegram.bot.Bot;

public class Application {
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotSession bot = telegramBotsApi.registerBot(new Bot("5186434723:AAHHel-2nbQFIXwfx75oa7vmA_dP_m-5xKs", "medvedevtestTG"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
}