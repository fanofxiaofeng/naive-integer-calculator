package com.study.node;

public record ParenthesisNode(Node node) implements Node {

    @Override
    public NodeType getType() {
        return NodeType.PARENTHESIS;
    }
}
