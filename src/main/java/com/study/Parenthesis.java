package com.study;

public enum Parenthesis {
    LEFT('('),
    RIGHT(')');

    private final int symbol;

    Parenthesis(int symbol) {
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }

    public static boolean qualified(int codepoint) {
        return LEFT.symbol == codepoint || RIGHT.symbol == codepoint;
    }

    public static Parenthesis build(int codepoint) {
        for (Parenthesis parenthesis : values()) {
            if (parenthesis.symbol == codepoint) {
                return parenthesis;
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
