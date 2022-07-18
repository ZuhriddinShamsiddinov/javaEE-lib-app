package com.example.libapp.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Language {
    UZ("Uzbek"),
    RU("Russian"),
    EN("English");
    private final String value;

    public static Language findByName(String language) {
        return Arrays.stream(Language.values())
                .filter(language1 -> language1.name().equals(language))
                .findFirst()
                .orElse(Language.RU);
    }
}
