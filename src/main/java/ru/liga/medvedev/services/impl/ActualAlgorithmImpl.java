package ru.liga.medvedev.services.impl;

import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.services.RateAlgorithmService;

import java.time.LocalDate;
import java.util.List;

@Component("ActualAlgorithm")
public class ActualAlgorithmImpl implements RateAlgorithmService {

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Commands commands) {
        LocalDate localDate = null;
        if ((localDate = commands.getLocalDate()) == null) {
            localDate = LocalDate.now().plusMonths(1);
        }

        return null;
    }
}
