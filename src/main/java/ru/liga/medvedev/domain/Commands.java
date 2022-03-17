package ru.liga.medvedev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commands {
    private final String inCommand = "rate";
    private String currency;
    private String period;
    //private boolean validationFlg;
    private String errorMessage;
    private LocalDate localDate;
    private String algorithmName;
    private String outputType;

    public static class CommandsBuilder{

    }
}