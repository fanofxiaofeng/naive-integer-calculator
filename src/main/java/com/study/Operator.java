package com.study;

public enum Operator {
    PLUS('+', OperatorPriority.LEVEL_ONE),
    MINUS('-', OperatorPriority.LEVEL_ONE),
    MULTIPLY('*', OperatorPriority.LEVEL_TWO),
    DIVIDE('/', OperatorPriority.LEVEL_TWO);

    private final int symbol;
    private final OperatorPriority operatorPriority;

    Operator(int symbol, OperatorPriority operatorPriority) {
        this.symbol = symbol;
        this.operatorPriority = operatorPriority;
    }

    public int getSymbol() {
        return symbol;
    }

    public OperatorPriority getOperatorPriority() {
        return operatorPriority;
    }

    public static boolean qualified(int codepoint) {
        for (Operator operator : values()) {
            if (operator.symbol == codepoint) {
                return true;
            }
        }
        return false;
    }

    public static Operator build(int codepoint) {
        for (Operator operator : values()) {
            if (operator.symbol == codepoint) {
                return operator;
            }
        }

        String message = String.format("Unexpected codepoint: %s", StringUtil.fromCodepoint(codepoint));
        throw new IllegalArgumentException(message);
    }

    @Override
    public String toString() {
        return StringUtil.fromCodepoint(symbol);
    }
}
