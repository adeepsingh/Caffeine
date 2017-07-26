package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.Comparison;
import me.amar0908.caffeine.languageMain.Condition;
import me.amar0908.caffeine.languageMain.ConditionalOperator;
import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.ConditionalBlock;
import me.amar0908.caffeine.languageMain.block.DoWhile;
import me.amar0908.caffeine.languageMain.block.While;
import me.amar0908.caffeine.languageMain.expression.Expression;
import me.amar0908.caffeine.languageMain.tokenizer.PreProcessedTokenizer;
import me.amar0908.caffeine.languageMain.tokenizer.Token;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import java.util.ArrayList;

import static me.amar0908.caffeine.languageMain.tokenizer.Regex.COMPARISON;
import static me.amar0908.caffeine.languageMain.tokenizer.Regex.IDENTIFIER_OR_LITERAL;

public class WhileParser extends Parser<ConditionalBlock> {

    public WhileParser() {
        super(ConditionalBlock.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.matches("(while|dowhile) \\(" + IDENTIFIER_OR_LITERAL + "( )?" + COMPARISON + "( )?" + IDENTIFIER_OR_LITERAL + "( )? ((&( )?" + IDENTIFIER_OR_LITERAL + "( )?" + COMPARISON + "( )?" + IDENTIFIER_OR_LITERAL + ")*)?\\)");
    }

    @Override
    public ConditionalBlock parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // while|dowhile (name == "Noah")

        Token type = tokenizer.nextToken();

        if (!tokenizer.nextToken().getToken().equals("(")) {
            throw new InvalidCodeException("While statement does not begin with opening parenthesis.");
        }

        ArrayList<Condition> conditions = new ArrayList<>();

        ArrayList<Token> a = new ArrayList<>(), b = new ArrayList<>();
        Comparison comparison = null;
        boolean isA = true;

        while (tokenizer.hasNextToken()) {
            Token token = tokenizer.nextToken();

            ConditionalOperator operator = ConditionalOperator.valueOfToken(token.getToken());

            if (operator != null || token.getToken().equals(")")) {
                /*
                This might cause an issue with superBlock.
                */
                conditions.add(new Condition(Expression.parse(new PreProcessedTokenizer(a), superBlock, null), Expression.parse(new PreProcessedTokenizer(b), superBlock, null), comparison));

                a.clear();
                b.clear();
                comparison = null;
                isA = true;

                if (token.getToken().equals(")")) {
                    break;
                }

                else {
                    continue;
                }
            }

            if (Comparison.valueOfToken(token.getToken()) == null) {
                if (isA) {
                    a.add(token);
                }

                else {
                    b.add(token);
                }
            }

            else {
                comparison = Comparison.valueOfToken(token.getToken());
                isA = false;
            }
        }

        if (type.getToken().equals("while")) {
            return new While(superBlock, conditions.toArray(new Condition[conditions.size()]));
        }

        else {
            return new DoWhile(superBlock, conditions.toArray(new Condition[conditions.size()]));
        }
    }
}
