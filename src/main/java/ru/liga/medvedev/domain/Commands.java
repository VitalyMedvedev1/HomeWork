package ru.liga.medvedev.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commands {
    private final String inCommand = "rate";
    private String currency;
    private String period;
    private boolean validationFlg;
    private String errorMessage;
}
