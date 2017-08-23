package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.MethodInvocation;
import me.amar0908.caffeine.languageMain.expression.Expression;
import me.amar0908.caffeine.languageMain.tokenizer.PreProcessedTokenizer;
import me.amar0908.caffeine.languageMain.tokenizer.Token;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import java.util.ArrayList;

import static me.amar0908.caffeine.languageMain.tokenizer.Regex.IDENTIFIER;

public class MethodInvocationParser extends Parser<MethodInvocation> {

    public MethodInvocationParser() {
        super(MethodInvocation.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        // TODO: Using .* is probably not the best approach.
        return line.matches(IDENTIFIER + "[.]" + IDENTIFIER + "\\((.*(,.*)*)*\\)( " + IDENTIFIER + ")?");
    }

    @Override
    public MethodInvocation parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // System.print("Hello there")

        Token invokableToken = tokenizer.nextToken();

        if (!tokenizer.nextToken().getToken().equals(".")) {
            throw new InvalidCodeException("Not a method invocation.");
        }

        Token methodToken = tokenizer.nextToken();

        if (!tokenizer.nextToken().getToken().equals("(")) {
            throw new InvalidCodeException("Method invocation does not contain opening parenthesis.");
        }

        ArrayList<Expression> params = new ArrayList<>();

        ArrayList<Token> tokens = new ArrayList<>();
        int numClosingParensIgnore = 0; // TODO: This probably needs to be implemented everywhere or something...

        while (tokenizer.hasNextToken()) {
            Token token = tokenizer.nextToken();

            if (token.getToken().equals("(")) {
                numClosingParensIgnore++;
            }

            else if (token.getToken().equals(",")) {
                continue;
            }

            else if (token.getToken().equals(")")) {
                if (numClosingParensIgnore-- > 0) {
                    tokens.add(token);
                    continue;
                }

                else {
                    if (!tokens.isEmpty()) {
                        params.add(Expression.parse(new PreProcessedTokenizer(tokens), superBlock, null));
                        tokens.clear();
                    }

                    break;
                }
            }

            tokens.add(token);
        }

        Token optionalVariable = tokenizer.nextToken();

        if (optionalVariable.getType() != Token.TokenType.EMPTY) {
            if (optionalVariable.getType() != Token.TokenType.IDENTIFIER) {
                throw new InvalidCodeException("Capture must be a variable.");
            }
        }

        return new MethodInvocation(superBlock, invokableToken, methodToken, params, optionalVariable);
    }
}
