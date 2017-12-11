package me.amar0908.lipi.language.system_methods;

import java.util.Arrays;

import me.amar0908.lipi.language.Block;
import me.amar0908.lipi.language.Utils;
import me.amar0908.lipi.language.Variable;
import me.amar0908.lipi.language.Variable.SystemVariableType;
import me.amar0908.lipi.language.Variable.VariableType;

public class Declare extends SystemMethod {

    public Declare() {
        super("declare");
    }

    /*
    Usage: declare(<variabletype>(:), <name>, [value(s)])
     */
    @Override
    public void invoke(Block b, String[] params, Variable receiver) throws Utils.InvalidCodeException {
        boolean isArray = params[0].endsWith(":");

        if (isArray) params[0] = params[0].substring(0, params[0].length() - 1);

        VariableType t = VariableType.VariableTypeMatcher.match(params[0]);

        if (t == SystemVariableType.VOID) throw new Utils.InvalidCodeException("Attempted to declare void variable.");

        String name = params[1];

        Object[] values = new Object[0];

        if (params.length >= 3) {
            values = Utils.implode(Arrays.copyOfRange(params, 2, params.length), b);
        }

        b.addVariable(t, name, isArray, values);
    }
}