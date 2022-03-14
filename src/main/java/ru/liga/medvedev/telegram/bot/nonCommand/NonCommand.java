package ru.liga.medvedev.telegram.bot.nonCommand;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.domain.Commands;

import java.util.List;

public class NonCommand {
    private final Logger logger = LoggerFactory.getLogger(NonCommand.class);

    public String nonCommandExecute(String text) {
        logger.debug("Строка команды\n" + text);
        Commands commands = SpringConfiguration.COMMAND_CONTROLLER.getCommand(text);
        if (!commands.isValidationFlg()) {
            return commands.getErrorMessage();
        }
        List<List<String>> dataStatisticList = SpringConfiguration.DATA_REPOSITORY_CONTROLLER.getRateDataRepository(commands);
        return SpringConfiguration.OUT_RATE_STATISTIC_CONTROLLER.outRateStatistic(commands, SpringConfiguration.ALGORITHMS_RATE_CONTROLLER.generateStatisticRateCurrency(
                SpringConfiguration.RATE_DATA_MAPPER.mapRate(dataStatisticList), commands));
    }
}