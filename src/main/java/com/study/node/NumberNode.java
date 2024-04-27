package com.study.node;

import com.study.token.NumberToken;

public record NumberNode(NumberToken numberToken) implements Node {

    @Override
    public NodeType getType() {
        return NodeType.NUMBER;
    }
}
