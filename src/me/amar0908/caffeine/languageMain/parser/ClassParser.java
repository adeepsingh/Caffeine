package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.Class;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import static me.amar0908.caffeine.languageMain.tokenizer.Regex.IDENTIFIER;

public class ClassParser extends Parser<Class> {

    public ClassParser() {
        super(Class.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches("class " + IDENTIFIER);
    }

    @Override
    public Class parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        tokenizer.nextToken(); // Skip the class token.

        return new Class(tokenizer.nextToken().getToken());
    }
}