package ru.liga.medvedev.telegram.bot.nonCommand;

public interface AnswerMessage {
    void answer(byte[] message, Long chatId, boolean typeAnswer);
}
