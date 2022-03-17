package ru.liga.medvedev.telegram.bot.nonCommand;

import java.io.File;

public interface AnswerMessage {
    void answer(byte[] message, Long ChatId, boolean);
}
