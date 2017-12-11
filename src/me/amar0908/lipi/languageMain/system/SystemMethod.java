package me.amar0908.lipi.languageMain.system;

import java.io.IOException;
import java.util.List;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.Parameter;
import me.amar0908.lipi.languageMain.Type;
import me.amar0908.lipi.languageMain.Value;
import me.amar0908.lipi.languageMain.block.Block;
import me.amar0908.lipi.languageMain.block.Method;
import me.amar0908.lipi.languageMain.tokenizer.Token;

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
