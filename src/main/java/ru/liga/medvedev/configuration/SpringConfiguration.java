package ru.liga.medvedev.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.liga.medvedev.controller.*;
import ru.liga.medvedev.domain.mappers.LocalRateDataCsvMapper;
import ru.liga.medvedev.domain.mappers.RateDataMapper;

@Configuration
@ComponentScan("ru.liga.medvedev")
@PropertySource("classpath:resource.properties")
public class SpringConfiguration {
    public static final AnnotationConfigApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(SpringConfiguration.class);
    public static final CommandController COMMAND_CONTROLLER = APPLICATION_CONTEXT.getBean("CommandController", CommandController.class);
    public static final DataRateRateRepositoryController DATA_REPOSITORY_CONTROLLER = APPLICATION_CONTEXT.getBean("DataRepositoryController", DataRateRateRepositoryController.class);
    public static final OutRateStatisticController OUT_RATE_STATISTIC_CONTROLLER = APPLICATION_CONTEXT.getBean("OutStatisticController", OutRateStatisticController.class);
    public static final RateDataMapper RATE_DATA_MAPPER = APPLICATION_CONTEXT.getBean("LocalCsvMapper", LocalRateDataCsvMapper.class);
    public static final AlgorithmsRateController ALGORITHMS_RATE_CONTROLLER = APPLICATION_CONTEXT.getBean("AlgorithmsController", AlgorithmsRateController.class);
}