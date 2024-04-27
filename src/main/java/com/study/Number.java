package com.study;

import java.math.BigInteger;

public record Number(BigInteger bigInteger) {

    public static final int NEGATIVE_SYMBOL = '-';

    public static boolean qualifiedStartCodepoint(int codepoint) {
        // It indicates that a negative integer appears
        if (codepoint == NEGATIVE_SYMBOL) {
            return true;
        }
        return qualifiedDigit(codepoint);
    }

    public static boolean qualifiedDigit(int codepoint) {
        return (codepoint >= '0') && (codepoint <= '9');
    }

    @Override
    public String toString() {
        return bigInteger.toString();
    }
}
