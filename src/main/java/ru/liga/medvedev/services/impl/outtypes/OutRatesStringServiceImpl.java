package ru.liga.medvedev.services.impl.outtypes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.StaticParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Service("OutRatesStringService")
public class OutRatesStringServiceImpl implements OutRateStatistic {
    @Override
    public byte[] outRateStatistic(Command command, List<List<Rate>> listRates) {
        log.debug("Формирование стринги ответа статистики\n" +
                listRates + "\nна период - " + command.getPeriod());
        byte[] outMessageByte = null;
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            for (List<Rate> listRate : listRates) {
                outStream.write(("Статистика по валюте - " + listRate.get(StaticParams.HEADER_INDEX).getCurrency() + "\n").getBytes(StandardCharsets.UTF_8));
                outStream.write(listRate.toString().getBytes(StandardCharsets.UTF_8));
                outStream.write("\n\n\n".getBytes(StandardCharsets.UTF_8));
            }
            outMessageByte = outStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outMessageByte;
    }

}
