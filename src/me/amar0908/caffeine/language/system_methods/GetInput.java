package me.amar0908.caffeine.language.system_methods;

import me.amar0908.caffeine.ide.Console;
import me.amar0908.caffeine.language.Block;
import me.amar0908.caffeine.language.IDEInstance;
import me.amar0908.caffeine.language.Utils;
import me.amar0908.caffeine.language.Utils.Writable;
import me.amar0908.caffeine.language.Variable;

public class GetInput extends SystemMethod {

    public GetInput() {
        super("getinput");
    }

    /*
    Usage: getinput() <var>
     */
    @Override
    public void invoke(Block b, String[] params, Variable receiver) throws Utils.InvalidCodeException {
        if (receiver == null) return;

        Writable writable = IDEInstance.CURRENT_INSTANCE.getWritable();

        if (writable instanceof Console) receiver.setValue(((Console) writable).prompt());
        else receiver.setValue(Utils.prompt());
    }
}