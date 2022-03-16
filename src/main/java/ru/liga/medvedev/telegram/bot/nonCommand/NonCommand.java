package ru.liga.medvedev.telegram.bot.nonCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;

import java.io.File;
import java.util.List;

public class NonCommand implements AnswerMessage {
    private final Logger logger = LoggerFactory.getLogger(NonCommand.class);

    public void nonCommandExecute(Update update) {
        logger.debug("Строка команды\n" + update.getMessage().getText());
        Long chatId = update.getMessage().getChatId();
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand(update.getMessage().getText());

        if (commands.getErrorMessage() != null) {
            answer(commands.getErrorMessage(), chatId);
        }

        List<Rate> listRate = SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
                SpringConfiguration.RATE_DATA_MAPPER.mapRate(SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(commands)), commands);
//        if (commands.get)
    }

    @Override
    public void answer(String message, Long chatId) {

    }

    @Override
    public void answer(File file, Long chatId) {

    }
}