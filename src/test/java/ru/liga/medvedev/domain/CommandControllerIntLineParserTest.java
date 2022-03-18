package ru.liga.medvedev.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CommandControllerIntLineParserTest {

    private Command command = new Command();
    private final CommandLineParser commandLineParser = new CommandLineParser();

    @Test
    void errorLengthTest() {
        command = commandLineParser.createCommand("123 123 123");
        assertNotNull(command.getErrorMessage());
    }

    @Test
    public void parseCreateCommandTest(){
        command = commandLineParser.createCommand("rate TRY -date 22.02.2030 -alg moon");
        assertEquals("RATE", command.getInCommand());
//        assertEquals("TRY", command.getCurrency());
        assertEquals(command.getListCurrency().get(0), "TRY");
        assertEquals("DATE", command.getPeriod());
        assertEquals(command.getLocalDate(), LocalDate.parse("22.02.2030", Reference.INPUT_DATE_FORMATTER));
        assertEquals("MOON", command.getAlgorithmName());
        assertEquals("DEFAULT", command.getOutputType());
        command = commandLineParser.createCommand("rate TRY -date 22.02.2030 -alg moon -out list");
        assertEquals("LIST", command.getOutputType());
    }

    @Test
    public void parseCreateCommandTestWithMultiValue(){
        command = commandLineParser.createCommand("rate TRY,USD,EUR -date 22.02.2030 -alg moon");
        System.out.println(command);

    }
}