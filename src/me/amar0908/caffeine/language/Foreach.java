package me.amar0908.caffeine.language;

import me.amar0908.caffeine.language.Block;
import me.amar0908.caffeine.language.Utils;
import me.amar0908.caffeine.language.Variable;

public class Foreach extends Block {

    private final String varName, arrayName;

    // foreach num numbers
    public Foreach(Block superBlock, String varName, String arrayName) {
        super(superBlock);

        this.varName = varName;
        this.arrayName = arrayName;
    }

    @Override
    public void run() throws Utils.InvalidCodeException {
        Variable arrayVar = getSuperBlock().getVariable(arrayName);

        if (!arrayVar.isArray()) throw new Utils.InvalidCodeException("Attempted to use foreach on non-array");

        addVariable(arrayVar.getType(), varName, false);

        for (Object val : arrayVar.getValues()) {
            getVariable(varName).setValue(val);
            super.run();
        }
    }

    @Override
    public String toString() {
        return "Foreach varName=" + varName + " arrayName=" + arrayName;
    }
}
