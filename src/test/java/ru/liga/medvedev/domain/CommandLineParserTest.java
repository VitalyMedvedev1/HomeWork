package ru.liga.medvedev.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineParserTest {

    private Commands commands = new Commands();
    private final CommandLineParser commandLineParser = new CommandLineParser();

    @Test
    public void parseTest(){
        commands = commandLineParser.parse("rate TRY -date 22.02.2030 -alg moon");
        System.out.println(commands.toString());
    }
}