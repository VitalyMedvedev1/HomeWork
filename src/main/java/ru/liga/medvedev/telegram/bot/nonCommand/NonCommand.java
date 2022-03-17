package ru.liga.medvedev.telegram.bot.nonCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.medvedev.SpringConfiguration;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.enums.RateOutTypes;

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
            outMapMessage.put(command.getErrorMessage().getBytes(StandardCharsets.UTF_8), true);
        } else {
            outMapMessage.put(
                    SpringConfiguration.OUT_RATE_STATISTIC_CONTROLLER.outRateStatistic(
                            command, SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
                                    SpringConfiguration.RATE_DATA_MAPPER.mapRate(
                                            SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(command)), command)), !command.getOutputType().equals(RateOutTypes.GRAPH.name()));
        }
        return outMapMessage;
    }
}