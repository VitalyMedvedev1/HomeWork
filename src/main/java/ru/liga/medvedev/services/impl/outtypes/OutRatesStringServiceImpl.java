package ru.liga.medvedev.services.impl.outtypes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service("OutRatesStringService")
public class OutRatesStringServiceImpl implements OutRateStatistic {
    @Override
    public byte[] outRateStatistic(Command command, List<Rate> listRates) {
        log.debug("Формирование стринги ответа статистики\n"+
                listRates + "\nна период - " + command.getPeriod());
        return listRates.toString().getBytes(StandardCharsets.UTF_8);
    }
}
