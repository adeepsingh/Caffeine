package me.amar0908.caffeine.languageMain;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Nameable;
import me.amar0908.caffeine.languageMain.Type;
import me.amar0908.caffeine.languageMain.tokenizer.Token;

public class Parameter implements Nameable {

    private String name;
    private Token tokenType;
    private Type matchedType;

    public Parameter(Type matchedType, String name) {
        this.matchedType = matchedType;
        this.name = name;
    }

    public Parameter(Token tokenType, String name) {
        this.tokenType = tokenType;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getUnmatchedType() {
        return tokenType.getToken();
    }

    public Type getMatchedType() throws InvalidCodeException {
        if (matchedType != null) {
            return matchedType;
        }

        else {
            return matchedType = Type.match(tokenType.getToken());
        }
    }

    public String toString() {
        return getClass() + " name=" + name + " tokenType=" + tokenType;
    }
}