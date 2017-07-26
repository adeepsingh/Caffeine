package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.VariableReassignment;
import me.amar0908.caffeine.languageMain.expression.Expression;
import me.amar0908.caffeine.languageMain.tokenizer.Token;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import static me.amar0908.caffeine.languageMain.tokenizer.Regex.IDENTIFIER;

public class VariableReassignmentParser extends Parser<VariableReassignment> {

    public VariableReassignmentParser() {
        super(VariableReassignment.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches(IDENTIFIER + "( )?=( )?.*");
    }

    @Override
    public VariableReassignment parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // name = "Noah"
        // person = new("Noah")

        Token name = tokenizer.nextToken();

        Token possibleEquals = tokenizer.nextToken();

        if (possibleEquals.getToken() == null || !possibleEquals.getToken().equals("=")) {
            tokenizer.pushBack();
        }

        return new VariableReassignment(superBlock, name, Expression.parse(tokenizer, superBlock, null));
    }
}
