package com.study.token;

import com.study.Number;

public record NumberToken(Number number) implements Token {

    @Override
    public TokenType getType() {
        return TokenType.NUMBER;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
