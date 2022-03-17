package ru.liga.medvedev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.medvedev.controller.AlgorithmsRateController;
import ru.liga.medvedev.controller.CommandController;
import ru.liga.medvedev.controller.DataRateRateRepositoryController;
import ru.liga.medvedev.controller.OutRateStatisticController;
import ru.liga.medvedev.domain.mappers.LocalRateDataCsvMapper;
import ru.liga.medvedev.domain.mappers.RateDataMapper;
import ru.liga.medvedev.telegram.bot.Bot;

@Configuration
@ComponentScan("ru.liga.medvedev")
@PropertySource("classpath:resource.properties")
public class SpringConfiguration {
    public static final AnnotationConfigApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    public static final CommandController COMMAND_CONTROLLER = APPLICATION_CONTEXT.getBean("CommandController", CommandController.class);
    public static final DataRateRateRepositoryController DATA_REPOSITORY_CONTROLLER = APPLICATION_CONTEXT.getBean("DataRepositoryController", DataRateRateRepositoryController.class);
    public static final OutRateStatisticController OUT_RATE_STATISTIC_CONTROLLER = APPLICATION_CONTEXT.getBean("OutStatisticController", OutRateStatisticController.class);
    public static final RateDataMapper RATE_DATA_MAPPER = APPLICATION_CONTEXT.getBean("LocalCsvMapper", LocalRateDataCsvMapper.class);
    public static final AlgorithmsRateController ALGORITHMS_RATE_CONTROLLER = APPLICATION_CONTEXT.getBean("AlgorithmsController", AlgorithmsRateController.class);
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            BotSession bot = telegramBotsApi.registerBot(new Bot("5186434723:AAHHel-2nbQFIXwfx75oa7vmA_dP_m-5xKs", "medvedevtestTG"));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}