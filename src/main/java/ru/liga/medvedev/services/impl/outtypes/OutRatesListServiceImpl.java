package ru.liga.medvedev.services.impl.outtypes;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.controller.OutRateStatistic;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.enums.RateAlgorithms;
import ru.liga.medvedev.domain.enums.RatePeriods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component("OutDataRateListService")
public class OutRatesListServiceImpl implements OutRateStatistic {

    @Override
    public byte[] outRateStatistic(Command command, List<List<Rate>> listRates) {
        log.debug("Формирование списка ответа статистики\n" +
                listRates + "\nна период - " + command.getPeriod());
        String periodStr = command.getPeriod().toUpperCase();
        int period = periodStr.equals(String.valueOf(RatePeriods.TOMORROW)) || periodStr.equals(String.valueOf(RatePeriods.DATE)) ? 1 : periodStr.equals(String.valueOf(RatePeriods.WEEK)) ? 7 : 30;
        byte[] outMessageByte = null;
        try (ByteArrayOutputStream outStream = new ByteArrayOutputStream()) {
            for (List<Rate> listRate : listRates) {
                outStream.write(("Статистика по валюте - " + listRate.get(0).getCurrency() + "\n").getBytes(StandardCharsets.UTF_8));
                outStream.write(listRate.stream()
                        .skip(command.getAlgorithmName().equals(RateAlgorithms.MOON.name()) || periodStr.equals(RatePeriods.DATE.name()) ? 0 : 1)
                        .limit(period)
                        .map(Rate::toString)
                        .collect(Collectors.joining("\n")).getBytes(StandardCharsets.UTF_8));
                outStream.write("\n\n\n".getBytes(StandardCharsets.UTF_8));
            }
            outMessageByte = outStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outMessageByte;
    }
}