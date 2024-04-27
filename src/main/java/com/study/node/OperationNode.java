package com.study.node;

import com.study.token.OperatorToken;

public record OperationNode(
        OperatorToken operatorToken,
        Node leftChild,
        Node rightChild) implements Node {

    public OperationNode copyWithSpecifiedRightChild(Node specifiedRightChild) {
        return new OperationNode(operatorToken, leftChild, specifiedRightChild);
    }

    @Override
    public NodeType getType() {
        return NodeType.OPERATION;
    }
}
