package ru.liga.medvedev.telegram.bot.nonCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.enums.RateOutTypes;
import ru.liga.medvedev.telegram.bot.Bot;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class NonCommand {
    private final Logger logger = LoggerFactory.getLogger(NonCommand.class);

    public Map<byte[], Boolean> nonCommandExecute(String messageText) {
        logger.debug("Строка команды\n" + messageText);
        Command command = SpringConfiguration.COMMAND_CONTROLLER.getCommand(messageText);
        Map<byte[], Boolean> outMapMessage = new HashMap<>();
        if (command.getErrorMessage() != null) {
//            bot.answer(command.getErrorMessage().getBytes(StandardCharsets.UTF_8), chatId, true);
            outMapMessage.put(command.getErrorMessage().getBytes(StandardCharsets.UTF_8), true);
            return outMapMessage;
        }
        else {
//            bot.answer(SpringConfiguration.OUT_RATE_STATISTIC_CONTROLLER.outRateStatistic(command, SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
//                    SpringConfiguration.RATE_DATA_MAPPER.mapRate(SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command)), command)), chatId, !command.getOutputType().equals(RateOutTypes.GRAPH.name()));
            outMapMessage.put(
                    SpringConfiguration.OUT_RATE_STATISTIC_CONTROLLER.outRateStatistic(
                            command, SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
                                    SpringConfiguration.RATE_DATA_MAPPER.mapRate(
                                            SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command)), command)), !command.getOutputType().equals(RateOutTypes.GRAPH.name()));
            return outMapMessage;
        }
    }
}