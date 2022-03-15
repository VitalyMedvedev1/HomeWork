package ru.liga.medvedev.telegram.bot.nonCommand;

import java.io.File;

public interface AnswerMessage {
    void answer(String message, Long ChatId);
    void answer(File file, Long ChatId);
}
