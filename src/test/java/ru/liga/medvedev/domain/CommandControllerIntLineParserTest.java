package ru.liga.medvedev.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class CommandControllerIntLineParserTest {

    private Command command = new Command();
    private final CommandLineParser commandLineParser = new CommandLineParser();

    @Test
    void errorLengthTest() {
        command = commandLineParser.createCommand("123 123 123");
        Assertions.assertNotNull(command.getErrorMessage());
    }

    @Test
    public void parseCreateCommandTest(){
        command = commandLineParser.createCommand("rate TRY -date 22.02.2030 -alg moon");
        Assertions.assertEquals("RATE", command.getInCommand());
        Assertions.assertEquals("TRY", command.getCurrency());
        Assertions.assertEquals("DATE", command.getPeriod());
        Assertions.assertEquals(command.getLocalDate(), LocalDate.parse("22.02.2030", Reference.INPUT_DATE_FORMATTER));
        Assertions.assertEquals("MOON", command.getAlgorithmName());
        Assertions.assertEquals("DEFAULT", command.getOutputType());
        command = commandLineParser.createCommand("rate TRY -date 22.02.2030 -alg moon -out list");
        Assertions.assertEquals("LIST", command.getOutputType());
    }
}