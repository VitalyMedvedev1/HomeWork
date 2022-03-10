package ru.liga.medvedev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.liga.medvedev.configuration.SpringConfiguration;
import ru.liga.medvedev.controller.CommandController;
import ru.liga.medvedev.domain.Commands;
import ru.liga.medvedev.services.RateAlgorithmService;
import ru.liga.medvedev.services.impl.ArithmeticAverageAlgorithmImpl;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        CommandController controller = context.getBean("CommandController", CommandController.class);

        Commands commands = controller.getCommand();

        RateAlgorithmService rateAlgorithmService = context.getBean("ArithmeticAvg", ArithmeticAverageAlgorithmImpl.class);
        rateAlgorithmService.generateStatisticRateCurrency(commands);
    }
}