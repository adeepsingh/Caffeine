package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.Property;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import static me.amar0908.caffeine.languageMain.tokenizer.Regex.IDENTIFIER;

public class PropertyParser extends Parser<Property> {

    public PropertyParser() {
        super(Property.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches("^(property " + IDENTIFIER + ")");
    }

    @Override
    public Property parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        tokenizer.nextToken(); // Skip the class token.
        return new Property(tokenizer.nextToken().getToken());
    }
}