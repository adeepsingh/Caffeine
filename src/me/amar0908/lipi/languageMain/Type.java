package me.amar0908.lipi.languageMain;

import me.amar0908.lipi.languageMain.system.MethodMeta;
import me.amar0908.lipi.languageMain.system.SystemClass;
import me.amar0908.lipi.languageMain.system.VariableMeta;

/**
 * Represents a value that can be returned from a method. Subclasses are {@link me.amar0908.lipi.languageMain.block.Class} and {@link me.amar0908.lipi.languageMain.Variable}.
 */
public interface Type {

    public static Type match(String str) throws InvalidCodeException {
        Type type = null;

        if (str.equals("System")) {
            type = SystemClass.getInstance();
        }

        else if (str.equals("MethodMeta")) {
            type = MethodMeta.TYPE;
        }

        else if (str.equals("VariableMeta")) {
            type = VariableMeta.TYPE;
        }

        try {
            type = PrimitiveType.valueOf(str.toUpperCase());
        } catch (Exception ignored) {

        }

        if (type == null) {
            type = Runtime.RUNTIME.getPogoClass(str);
        }

        if (type == null) {
            throw new InvalidCodeException("Expected type, got " + str);
        }

        return type;
    }

    public boolean equalsType(Type other);
}