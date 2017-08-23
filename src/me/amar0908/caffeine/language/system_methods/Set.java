package me.amar0908.caffeine.language.system_methods;

import me.amar0908.caffeine.language.Block;
import me.amar0908.caffeine.language.Utils;
import me.amar0908.caffeine.language.Variable;

public class Set extends SystemMethod {

    public Set() {
        super("set");
    }

    /*
    Usage: set(<value>, [index]) <var>
     */
    @Override
    public void invoke(Block b, String[] params, Variable receiver) throws Utils.InvalidCodeException {
        if (receiver == null)
            throw new Utils.InvalidCodeException("Attempted to set variable but no variable specified.");

        if (receiver.isArray()) receiver.setValue(Utils.implode(params[0], b), Integer.parseInt(params[1]));
        else receiver.setValue(Utils.implode(params[0], b));
    }
}