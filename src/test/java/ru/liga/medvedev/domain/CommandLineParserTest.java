package ru.liga.medvedev.domain;

import org.junit.jupiter.api.Test;

class CommandLineParserTest {

    private Command command = new Command();
    private final CommandLineParser commandLineParser = new CommandLineParser();

    @Test
    void errorLength() {
        command = commandLineParser.parse("123 123 123");
    }

    @Test
    public void parseTest(){
        command = commandLineParser.parse("rate TRY -date 22.02.2030 -alg moon");
        System.out.println(command.toString());
    }
}