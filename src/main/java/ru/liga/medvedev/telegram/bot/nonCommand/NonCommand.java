package ru.liga.medvedev.telegram.bot.nonCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.telegram.bot.Bot;

public class NonCommand {
    private final Logger logger = LoggerFactory.getLogger(NonCommand.class);

    public void nonCommandExecute(Update update, Bot bot) {
        logger.debug("Строка команды\n" + update.getMessage().getText());
        Long chatId = update.getMessage().getChatId();
        Command command = SpringConfiguration.COMMAND_CONTROLLER.getCommand(update.getMessage().getText());

        if (command.getErrorMessage() != null) {
            //return command.getErrorMessage().getBytes(StandardCharsets.UTF_8);
            bot.answer(command.getErrorMessage(), chatId, true);
        }

       /* List<Rate> listRate = SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
                SpringConfiguration.RATE_DATA_MAPPER.mapRate(SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command)), command);
        String message = SpringConfiguration.OUT_RATE_STATISTIC_CONTROLLER.outRateStatistic(command, listRate);*/
        //return message.getBytes(StandardCharsets.UTF_8);


        bot.answer(SpringConfiguration.OUT_RATE_STATISTIC_CONTROLLER.outRateStatistic(command, SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
                SpringConfiguration.RATE_DATA_MAPPER.mapRate(SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command)), command)), chatId, false);
    }
}