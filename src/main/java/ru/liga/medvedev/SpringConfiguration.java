package ru.liga.medvedev;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.liga.medvedev.controller.AlgorithmsRateController;
import ru.liga.medvedev.controller.StartStartCommandControllerController;
import ru.liga.medvedev.controller.DataRateRateRepositoryController;
import ru.liga.medvedev.controller.OutRateStatisticController;
import ru.liga.medvedev.domain.mappers.LocalRateDataCsvMapper;
import ru.liga.medvedev.domain.mappers.RateDataMapper;
import ru.liga.medvedev.telegram.bot.Bot;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("ru.liga.medvedev")
@PropertySource("classpath:resource.properties")
@Slf4j
public class SpringConfiguration {
    public static final AnnotationConfigApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    public static final StartStartCommandControllerController COMMAND_CONTROLLER = APPLICATION_CONTEXT.getBean("CommandController", StartStartCommandControllerController.class);
    public static final DataRateRateRepositoryController DATA_REPOSITORY_CONTROLLER = APPLICATION_CONTEXT.getBean("DataRepositoryController", DataRateRateRepositoryController.class);
    public static final OutRateStatisticController OUT_RATE_STATISTIC_CONTROLLER = APPLICATION_CONTEXT.getBean("OutStatisticController", OutRateStatisticController.class);
    public static final RateDataMapper RATE_DATA_MAPPER = APPLICATION_CONTEXT.getBean("LocalCsvMapper", LocalRateDataCsvMapper.class);
    public static final AlgorithmsRateController ALGORITHMS_RATE_CONTROLLER = APPLICATION_CONTEXT.getBean("AlgorithmsController", AlgorithmsRateController.class);

    public static void main(String[] args) {
        try {
            log.info("----------------Начало работы----------------------------");
            log.info("----------------Старт Spring Dependency------------------");
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            log.info("----------------Старт Telegram Bot App-------------------");
            Properties properties = new Properties();
            properties.load(new ClassPathResource("resource.properties").getInputStream());

            telegramBotsApi.registerBot(new Bot(properties.getProperty("BOT_TOKEN"), properties.getProperty("BOT_NAME")));
            log.info("----------------Telegram Bot зарегистрирован--------------");
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }
}