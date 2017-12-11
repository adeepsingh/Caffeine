package me.amar0908.lipi.languageMain.block;

import java.util.Optional;

import me.amar0908.lipi.languageMain.Variable;
import me.amar0908.lipi.languageMain.tokenizer.Token;

/**
 * Represents a block that cannot have sub blocks nor variables.
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
