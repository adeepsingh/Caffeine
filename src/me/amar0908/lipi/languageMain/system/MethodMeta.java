package me.amar0908.lipi.languageMain.system;

import me.amar0908.lipi.languageMain.InvalidCodeException;
import me.amar0908.lipi.languageMain.PrimitiveType;
import me.amar0908.lipi.languageMain.Type;
import me.amar0908.lipi.languageMain.Value;
import me.amar0908.lipi.languageMain.block.Class;
import me.amar0908.lipi.languageMain.block.Method;

public class MethodMeta extends Class {

    public static MethodMeta TYPE = new MethodMeta(null);

    private Method method;

    public MethodMeta(Method method) {
        super("MethodMeta");

        this.method = method;

        add(new GetName());
        add(new GetReturnType());
    }

    @Override
    public boolean equalsType(Type other) {
        return other instanceof MethodMeta;
    }

    private class GetName extends SystemMethod {

        private GetName() {
            super(MethodMeta.this, "getName", PrimitiveType.STRING);
        }

        @Override
        public Value invoke() throws InvalidCodeException {
            return new Value(PrimitiveType.STRING, method.getName());
        }
    }

    private class GetReturnType extends SystemMethod {

        private GetReturnType() {
            super(MethodMeta.this, "getReturnType", PrimitiveType.STRING);
        }

        @Override
        public Value invoke() throws InvalidCodeException {
            return new Value(PrimitiveType.STRING, method.getType().toString());
        }
    }
}