package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

public abstract class Parser<T extends Block> {

    private Class<T> type;

    public Parser(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return type;
    }

    public abstract boolean shouldParseLine(String line);

    public abstract T parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException;
}