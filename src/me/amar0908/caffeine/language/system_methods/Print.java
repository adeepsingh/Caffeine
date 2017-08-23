package me.amar0908.caffeine.language.system_methods;

import me.amar0908.caffeine.ide.Console.MessageType;
import me.amar0908.caffeine.language.Block;
import me.amar0908.caffeine.language.IDEInstance;
import me.amar0908.caffeine.language.Utils;
import me.amar0908.caffeine.language.Variable;

public class Print extends SystemMethod {

    public Print() {
        super("print");
    }

    /*
    Usage: print(<message>)
     */
    @Override
    public void invoke(Block b, String[] params, Variable receiver) throws Utils.InvalidCodeException {
        IDEInstance.CURRENT_INSTANCE.getWritable().write(Utils.implode(params[0], b), MessageType.OUTPUT);
    }
}