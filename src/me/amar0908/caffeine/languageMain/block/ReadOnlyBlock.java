package me.amar0908.caffeine.languageMain.block;

import me.amar0908.caffeine.languageMain.Variable;
import me.amar0908.caffeine.languageMain.tokenizer.Token;

import java.util.Optional;

/**
 * Represents a block that cannot have subblocks nor variables.
 */
public abstract class ReadOnlyBlock extends Block {

    public ReadOnlyBlock(Block superBlock, Token... propertyTokens) {
        super(superBlock, propertyTokens);
    }

    @Override
    public <T extends Block> void add(T subBlock) {
        throw new UnsupportedOperationException("Read-only block cannot have subblocks.");
    }

    @Override
    public Optional<Variable> getVariable(String name) {
        throw new UnsupportedOperationException("Read-only block cannot have variables");
    }

    public boolean hasVariable(String name) {
//        throw new UnsupportedOperationException("Read-only block cannot have variables");
        return false;
    }

    public void addVariable(Variable variable) {
        throw new UnsupportedOperationException("Read-only block cannot have variables");
    }
}
