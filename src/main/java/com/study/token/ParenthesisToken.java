package com.study.token;

import com.study.Parenthesis;

public class ParenthesisToken implements Token {

    private final Parenthesis parenthesis;

    public ParenthesisToken(Parenthesis parenthesis) {
        this.parenthesis = parenthesis;
    }

    @Override
    public TokenType getType() {
        return TokenType.PARENTHESIS;
    }

    @Override
    public String toString() {
        return parenthesis.toString();
    }
}
