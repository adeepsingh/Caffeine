package me.amar0908.lipi.languageMain.parser;

import static me.amar0908.lipi.languageMain.tokenizer.Regex.IDENTIFIER;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.Class;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

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