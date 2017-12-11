package me.amar0908.lipi.languageMain.system;

import me.amar0908.lipi.languageMain.*;
import me.amar0908.lipi.languageMain.block.Class;

public class VariableMeta extends Class {

    public static VariableMeta TYPE = new VariableMeta(null);

    private Variable variable;

    public VariableMeta(Variable variable) {
        super("VariableMeta");

        this.variable = variable;

        add(new GetName());
        add(new GetType());
    }

    @Override
    public boolean equalsType(Type other) {
        return other instanceof VariableMeta;
    }

    private class GetName extends SystemMethod {

        private GetName() {
            super(VariableMeta.this, "getName", PrimitiveType.STRING);
        }

        @Override
        public Value invoke() throws InvalidCodeException {
            return new Value(PrimitiveType.STRING, variable.getName());
        }
    }

    private class GetType extends SystemMethod {

        private GetType() {
            super(VariableMeta.this, "getType", PrimitiveType.STRING);
        }

        @Override
        public Value invoke() throws InvalidCodeException {
            return new Value(PrimitiveType.STRING, variable.getType().toString());
        }
    }
}