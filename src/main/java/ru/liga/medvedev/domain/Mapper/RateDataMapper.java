package ru.liga.medvedev.domain.Mapper;

import ru.liga.medvedev.domain.Rate;

import java.util.List;

public interface RateDataMapper {
    List<Rate> mapRate(List<List<String>> listData);
}
