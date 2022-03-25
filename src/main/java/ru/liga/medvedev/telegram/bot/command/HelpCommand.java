package ru.liga.medvedev.telegram.bot.command;

import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import ru.liga.medvedev.domain.StaticParams;

public class HelpCommand extends ServiceCommand {

    public HelpCommand(String identifier, String description) {
        super(identifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        sendAnswer(absSender, chat.getId(), "Ведите входные данные для прогноза курса валют, в формате: 'курс валюта период алгоритм'.\n\n" +
                "команда - *rate*\n" +
                "валюты - " + StaticParams.CURRENCY + "\n" +
                "период - *date/period*\n" +
                "алгоритмы - " + StaticParams.ALGORITHMS + "\n" +
                "вывод - " + StaticParams.OUT_TYPE + "\n" +
                "Пример:\n " + StaticParams.EXAMPLE_COMMAND + "\n");
    }
}