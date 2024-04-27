package com.study.token;

import com.study.Operator;

public record OperatorToken(Operator operator) implements Token {

    @Override
    public TokenType getType() {
        return TokenType.OPERATOR;
    }

    @Override
    public String toString() {
        return operator.toString();
    }
}
