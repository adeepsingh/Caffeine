package me.amar0908.lipi.languageMain.parser;

import static me.amar0908.lipi.languageMain.tokenizer.Regex.IDENTIFIER;
import static me.amar0908.lipi.languageMain.tokenizer.Regex.PROPERTY;

import java.util.ArrayList;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.Parameter;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.Constructor;
import me.amar0908.lipi.languageMain.tokenizer.Token;
import me.amar0908.lipi.languageMain.tokenizer.Tokenizer;

public class ConstructorParser extends Parser<Constructor> {

    public ConstructorParser() {
        super(Constructor.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches("((" + PROPERTY + " )*)?" + "constructor( )?=( )?\\((" + IDENTIFIER + " " + IDENTIFIER + ")?((,( )?" + IDENTIFIER + " " + IDENTIFIER + ")?)*\\)");
    }

    @Override
    public Constructor parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // [@...] constructor = (string name)

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

        tokenizer.nextToken(); // Skip the constructor token.
        tokenizer.nextToken(); // Skip the = token.

        if (!tokenizer.nextToken().getToken().equals("(")) {
            throw new InvalidCodeException("Method declaration missing parentheses.");
        }

        Token beginningParams = tokenizer.nextToken();

        ArrayList<Parameter> params = new ArrayList<>();

        if (!beginningParams.getToken().equals(")")) {
            Object[] paramData = { beginningParams, null };

            while (tokenizer.hasNextToken()) {
                Token token = tokenizer.nextToken();

                if (token.getToken().equals(",")) {
                    continue;
                }

                else if (token.getToken().equals(")")) {
                    break;
                }

                if (paramData[0] == null) { // In this case, we expect this token to be the type.
                    paramData[0] = token;
                }

                else { // In this case, we expect this token to be the name.
                    paramData[1] = token.getToken();

                    params.add(new Parameter((Token) paramData[0], (String) paramData[1]));

                    paramData[0] = null;
                    paramData[1] = null;
                }
            }
        }

        return new Constructor(superBlock, params.toArray(new Parameter[params.size()]), properties.toArray(new Token[properties.size()]));
    }
}
