package ru.liga.medvedev.services.impl.algorithms;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.math3.util.Precision;
import org.shredzone.commons.suncalc.MoonPhase;
import org.springframework.stereotype.Component;
import ru.liga.medvedev.domain.Command;
import ru.liga.medvedev.domain.Rate;
import ru.liga.medvedev.domain.RateStatisticFunctions;
import ru.liga.medvedev.domain.Reference;
import ru.liga.medvedev.services.RateAlgorithmService;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j
@Component("MoonAlgorithm")
public class MoonAlgorithmImpl implements RateAlgorithmService {

    @Override
    public List<Rate> generateStatisticRateCurrency(List<Rate> listRate, Command command) {
        int MOON_PHASE_DAYS = 90;
        log.debug("Формирование статистики по мистическому алгоритму");
        return generateRateMoonStatistic(listRate.stream()
                        .limit(MOON_PHASE_DAYS)
                        .collect(Collectors.toCollection(TreeSet::new)),
                getMoonPhasePredict(listRate.get(0).getDate()), command);
    }

    private List<LocalDate> getMoonPhasePredict(LocalDate lastDateRate) {
        log.debug("Формирование списка лунных дат полнолуний");
        List<LocalDate> moonPhaseDate = new LinkedList<>();
        LocalDate dateMoonPhasePredictStart = lastDateRate.minusMonths(4);
        MoonPhase.Parameters parameters = MoonPhase.compute().phase(MoonPhase.Phase.FULL_MOON);
        MoonPhase moonPhase = parameters.on(dateMoonPhasePredictStart).execute();
        LocalDate nextFullMoon = moonPhase.getTime().toLocalDate();
        while (nextFullMoon.isBefore(lastDateRate)) {
            moonPhaseDate.add(nextFullMoon);
            dateMoonPhasePredictStart = nextFullMoon.plusDays(Reference.DAY);
            moonPhase = parameters.on(dateMoonPhasePredictStart).execute();
            nextFullMoon = moonPhase.getTime().toLocalDate();
        }
        log.debug("Ссписк лунных дат полнолуний" + moonPhaseDate);
        return moonPhaseDate;
    }

    private List<Rate> generateRateMoonStatistic(Set<Rate> setRate, List<LocalDate> listMoonPhaseDate, Command command) {
        log.debug("Формирование списка статистики по датам полнолуний");
        LinkedList<Rate> listRate = new LinkedList<>();
        LocalDate localDate = RateStatisticFunctions.getFromWhatDateRate(command);
        listRate.add(new Rate(
                localDate.plusDays(Reference.DAY),
                Precision.round((setRate.stream()
                        .filter(rate -> listMoonPhaseDate.contains(rate.getDate()))
                        .limit(3)
                        .mapToDouble(Rate::getValue).sum()) / 3, 2)));
        for (int i = 2; i < Reference.COLLECTION_SIZE; i++) {
            listRate.add(new Rate(
                    localDate.plusDays(i),
                    Precision.round(
                            listRate.getLast().getValue() + listRate.getLast().getValue() * ThreadLocalRandom.current().nextDouble(-0.1, 0.1),
                            Reference.PRECISION)));
        }
        log.debug("Спискок статистики по датам полнолуний" + listRate);
        return listRate;
    }
}
