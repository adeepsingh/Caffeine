package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.languageMain.InvalidCodeException;
import me.amar0908.caffeine.languageMain.Nameable;
import me.amar0908.caffeine.languageMain.Type;
import me.amar0908.caffeine.languageMain.tokenizer.Token;

import java.util.Optional;

/**
 * Represents a root block that can contain methods. Current implementations are {@link Class} and {@link Property}
 */
public abstract class RootBlock extends Block implements Nameable {

    public RootBlock(Block superBlock, Token... rootTokens) {
        super(superBlock, rootTokens);
    }

    public Optional<Method> getMethod(String name, Type... paramTypes) {
        return getSubBlocks(Method.class).stream()
                .filter(method -> method.getName().equals(name))
                .filter(method -> {
                    try {
                        if (method.getParameters().length != paramTypes.length) {
                            return false;
                        }

                        for (int i = 0; i < method.getParameters().length && i < paramTypes.length; i++) {
                            if (!paramTypes[i].equalsType(method.getParameters()[i].getMatchedType())) {
                                return false;
                            }
                        }

                        return true;
                    }

                    catch (InvalidCodeException e) {
                        e.printStackTrace();
                        return false;
                    }
                })
                .findFirst();
    }

    public boolean hasMethod(String name, Type... paramTypes) {
        return getMethod(name, paramTypes).isPresent();
    }
}