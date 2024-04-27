package com.study.token;

public sealed interface Token
        permits NumberToken,
        ParenthesisToken,
        OperatorToken {
    TokenType getType();
}

