package me.amar0908.caffeine.languageMain.system;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Parameter;
import me.amar0908.caffeine.languageMain.Type;
import me.amar0908.caffeine.languageMain.Value;
import me.amar0908.caffeine.languageMain.block.Block;
import me.amar0908.caffeine.languageMain.block.Method;
import me.amar0908.caffeine.languageMain.tokenizer.Token;

import java.io.IOException;
import java.util.List;

public abstract class SystemMethod extends Method {

    public SystemMethod(Block superBlock, String name, Type type, Parameter... parameters) {
        super(superBlock, name, new Token(Token.TokenType.IDENTIFIER, type.toString()), parameters);
    }

    @Override
    public Value invoke(List<Value> values) throws IOException, InvalidCodeException {
        super.invoke(values);
        return invoke();
    }

    protected abstract Value invoke() throws InvalidCodeException;
}
