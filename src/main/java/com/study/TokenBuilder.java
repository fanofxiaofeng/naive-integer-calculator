package com.study;

import com.study.token.NumberToken;
import com.study.token.OperatorToken;
import com.study.token.ParenthesisToken;
import com.study.token.Token;

public class TokenBuilder {
    public Token build(Parenthesis parenthesis) {
        return new ParenthesisToken(parenthesis);
    }

    public Token build(Operator operator) {
        return new OperatorToken(operator);
    }

    public Token build(Number number) {
        return new NumberToken(number);
    }
}
