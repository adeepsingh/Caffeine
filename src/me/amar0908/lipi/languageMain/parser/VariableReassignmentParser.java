package me.amar0908.lipi.languageMain.parser;

import static me.amar0908.lipi.languageMain.tokenizer.Regex.IDENTIFIER;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.VariableReassignment;
import me.amar0908.lipi.languageMain.expression.Expression;
import me.amar0908.lipi.languageMain.tokenizer.Token;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

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
        // name = "Amar"
        // person = new("Amar")

        Token name = tokenizer.nextToken();

        Token possibleEquals = tokenizer.nextToken();

        if (possibleEquals.getToken() == null || !possibleEquals.getToken().equals("=")) {
            tokenizer.pushBack();
        }

        return new VariableReassignment(superBlock, name, Expression.parse(tokenizer, superBlock, null));
    }
}
