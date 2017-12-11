package me.amar0908.lipi.languageMain.parser;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

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