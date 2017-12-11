package me.amar0908.lipi.language.system_methods;

import me.amar0908.lipi.ide.Console;
import me.amar0908.lipi.language.Block;
import me.amar0908.lipi.language.IDEInstance;
import me.amar0908.lipi.language.Utils;
import me.amar0908.lipi.language.Variable;
import me.amar0908.lipi.language.Utils.Writable;

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