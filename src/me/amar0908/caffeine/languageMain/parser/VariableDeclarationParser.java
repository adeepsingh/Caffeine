package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.VariableDeclaration;
import me.amar0908.caffeine.languageMain.tokenizer.Token;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import java.util.ArrayList;

import static me.amar0908.caffeine.languageMain.tokenizer.Regex.IDENTIFIER;
import static me.amar0908.caffeine.languageMain.tokenizer.Regex.PROPERTY;

public class VariableDeclarationParser extends Parser<VariableDeclaration> {

    public VariableDeclarationParser() {
        super(VariableDeclaration.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches("((" + PROPERTY + " )*)?" + IDENTIFIER + " " + IDENTIFIER + "( = .*)?");
    }

    @Override
    public VariableDeclaration parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // [@...] string name = "Noah"
        // [@...] Person person = new("Noah")

        ArrayList<Token> properties = new ArrayList<>();

        while (tokenizer.hasNextToken()) {
            Token token = tokenizer.nextToken();

            if (token.getType() == Token.TokenType.PROPERTY) {
                properties.add(token);
            }

            else {
                tokenizer.pushBack();
                break;
            }
        }

        Token type = tokenizer.nextToken();

        Token name = tokenizer.nextToken();

        Token possibleEquals = tokenizer.nextToken();

        if (possibleEquals.getToken() == null || !possibleEquals.getToken().equals("=")) {
            tokenizer.pushBack();
        }

        return new VariableDeclaration(superBlock, type, name, tokenizer, properties.toArray(new Token[properties.size()]));
    }
}