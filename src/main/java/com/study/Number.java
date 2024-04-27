package com.study;

import java.math.BigInteger;

public record Number(BigInteger bigInteger) {

    public static boolean qualified(int codepoint) {
        return (codepoint >= '0') && (codepoint <= '9');
    }

    @Override
    public String toString() {
        return bigInteger.toString();
    }
}
