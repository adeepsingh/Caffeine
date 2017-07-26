package me.amar0908.caffeine.languageMain;

import java.util.Arrays;

import me.amar0908.caffeine.languageMain.Comparison;

public enum Comparison {

    EQUALS("=="), NOTEQUALS("!="), GREATERTHAN(">"), LESSTHAN("<"), GREATERTHANEQUALTO(">="), LESSTHANEQUALTO("<=");

    private String token;

    private Comparison(String token) {
        this.token = token;
    }

    public static Comparison valueOfToken(String token) {
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
