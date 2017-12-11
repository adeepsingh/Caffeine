package me.amar0908.lipi.languageMain.parser;

import static me.amar0908.lipi.languageMain.tokenizer.Regex.IDENTIFIER;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.Property;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

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