package me.amar0908.caffeine.languageMain.parser;

import me.amar0908.caffeine.languageMain.Comparison;
import me.amar0908.caffeine.languageMain.Condition;
import me.amar0908.caffeine.languageMain.ConditionalOperator;
import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.Else;
import me.amar0908.caffeine.languageMain.block.ElseIf;
import me.amar0908.caffeine.languageMain.block.If;
import me.amar0908.caffeine.languageMain.expression.Expression;
import me.amar0908.caffeine.languageMain.tokenizer.PreProcessedTokenizer;
import me.amar0908.caffeine.languageMain.tokenizer.Token;
import me.amar0908.caffeine.languageMain.tokenizer.Tokenizer;

import java.util.ArrayList;

import static me.amar0908.caffeine.languageMain.tokenizer.Regex.COMPARISON;
import static me.amar0908.caffeine.languageMain.tokenizer.Regex.IDENTIFIER_OR_LITERAL;

public class IfParser extends Parser<Block> {

    private If lastIf;

    public IfParser() {
        super(Block.class);
    }

    @Override
    public boolean shouldParseLine(String line) {
        return line.equals("else") || line.matches("(if|elseif) \\(" + IDENTIFIER_OR_LITERAL + "( )?" + COMPARISON + "( )?" + IDENTIFIER_OR_LITERAL + "( )?(&( )?" + IDENTIFIER_OR_LITERAL + "( )?" + COMPARISON + "( )?" + IDENTIFIER_OR_LITERAL + ")*\\)");
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) throws InvalidCodeException {
        // if|elseif|else ((name == "Noah"))

        String type = tokenizer.nextToken().getToken();

        if (type.equals("elseif") && lastIf == null) {
            throw new InvalidCodeException("Attempted to write elseif statement without if statement.");
        }

        if (type.equals("else")) {
            if (lastIf == null) {
                throw new InvalidCodeException("Attempted to write else statement without if statement.");
            }

            else {
                Else elze = new Else(superBlock);
                lastIf.setElse(elze);
                lastIf = null;
                return elze;
            }
        }

        if (!tokenizer.nextToken().getToken().equals("(")) {
            throw new InvalidCodeException("If statement does not begin with opening parenthesis.");
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

        if (type.equals("if")) {
            return lastIf = new If(superBlock, conditions.toArray(new Condition[conditions.size()]));
        }

        else {
            return lastIf.addElseIf(new ElseIf(superBlock, conditions.toArray(new Condition[conditions.size()])));
        }
    }
}
