package ru.liga.medvedev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.controller.CommandController;
import ru.liga.medvedev.telegram.bot.Bot;

public class Application {
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotSession bot = telegramBotsApi.registerBot(new Bot("5186434723:AAHHel-2nbQFIXwfx75oa7vmA_dP_m-5xKs", "medvedevtestTG"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        /*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        CommandController controller = context.getBean("CommandController", CommandController.class);

        Commands commands = controller.getCommand();

        RateAlgorithmService rateAlgorithmService = context.getBean("ArithmeticAvg", ArithmeticAverageAlgorithmImpl.class);
        rateAlgorithmService.generateStatisticRateCurrency(commands);*/
        // InTelegramBotServiceImpl botApplication = context.getBean("BotApplication", InTelegramBotServiceImpl.class);
    }
}