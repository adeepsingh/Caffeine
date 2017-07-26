package me.amar0908.caffeine.languageMain;

import java.util.Arrays;

import me.amar0908.caffeine.languageMain.ConditionalOperator;

public enum ConditionalOperator {

    AND("&");

    private String token;

    private ConditionalOperator(String token) {
        this.token = token;
    }

    public static ConditionalOperator valueOfToken(String token) {
        return Arrays.stream(values())
                .filter(comparison -> comparison.toString().equals(token))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return token;
    }
}