package ru.liga.medvedev.telegram.bot;

public interface AnswerMessage {
    void answer(byte[] message, Long chatId, boolean typeAnswer);
}
